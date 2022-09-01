package pers.guzx.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.session.*;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.security.web.session.SimpleRedirectSessionInformationExpiredStrategy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pers.guzx.user.authentication.*;
import pers.guzx.user.authorize.AccessDecisionManagerImpl;
import pers.guzx.user.authorize.AccessDecisionVoterImpl;
import pers.guzx.user.authorize.AuthorizeFailure;
import pers.guzx.user.service.impl.UserServiceImpl;

import javax.sql.DataSource;


import java.util.ArrayList;
import java.util.List;

import static pers.guzx.user.common.Constant.*;

/**
 * @author 25446
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {

    @Autowired
    private AuthenticationProviderImpl authenticationProvider;
    @Autowired
    private AuthenticationSuccess successHandler;
    @Autowired
    private AuthenticationFailure failureHandler;
    @Autowired
    private AuthenticationExpired authenticationExpired;
    @Autowired
    private AuthenticationInfoClean authenticationInfoClean;
    @Autowired
    private AuthenticationException authenticationException;
    @Autowired
    private AuthorizeFailure authorizeFailure;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AdditionalAuthenticationChecks additionalAuthenticationChecks;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AuthenticationManagerImpl authenticationManager;
    @Autowired
    private AccessDecisionVoterImpl accessDecisionVoter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeRequests() // 对请求进行授权
                .accessDecisionManager(accessDecisionManager())
                .anyRequest() // 其它所有请求需要认证
                .authenticated();

        // 认证相关配置
        http.authenticationProvider(authenticationProvider)
                .addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        // 清除认证信息
        http.logout()
                .logoutUrl(LOGOUT_URL)
                .logoutSuccessHandler(authenticationInfoClean)
                .deleteCookies("JSESSIONID");

        // remember me
        http.rememberMe()
                .rememberMeServices(rememberMeServices());

        // session
        http.sessionManagement()
                .sessionAuthenticationStrategy(sessionAuthenticationStrategy());

        //认证异常处理
        http.exceptionHandling()
                .accessDeniedHandler(authorizeFailure)
                .authenticationEntryPoint(authenticationException);

        return http.build();
    }

    /**
     * 认证处理
     *
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationFilter authenticationFilter() throws Exception {
        AuthenticationFilter authFilter = new AuthenticationFilter();
        authFilter.setAuthenticationSuccessHandler(successHandler);
        authFilter.setAuthenticationFailureHandler(failureHandler);
        authFilter.setFilterProcessesUrl(LOGIN_URL);
        authFilter.setAuthenticationManager(authenticationManager);
        authFilter.setRememberMeServices(rememberMeServices());
        authFilter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy());
        return authFilter;
    }

    /**
     * 授权处理
     */
    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> accessDecisionVoters = new ArrayList<>();
        accessDecisionVoters.add(accessDecisionVoter);
        AccessDecisionManagerImpl accessDecisionManager = new AccessDecisionManagerImpl(accessDecisionVoters);
        accessDecisionManager.setAllowIfAllAbstainDecisions(true);
        return new AccessDecisionManagerImpl(accessDecisionVoters);
    }

    // remember me begin

    @Bean
    public PersistentTokenBasedRememberMeServices rememberMeServices() {
        PersistentTokenBasedRememberMeServices rememberMeServices = new PersistentTokenBasedRememberMeServices(REMEMBER_ME_KEY, userService, persistentTokenRepository());
        rememberMeServices.setParameter(REMEMBER_ME_PARAMETER);
        rememberMeServices.setCookieName(REMEMBER_ME_PARAMETER);
        rememberMeServices.setTokenValiditySeconds(MAX_REMEMBER_TIME);
        rememberMeServices.setUserDetailsChecker(additionalAuthenticationChecks);
        rememberMeServices.setAlwaysRemember(false);
        return rememberMeServices;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl persistentTokenRepository = new JdbcTokenRepositoryImpl();
        persistentTokenRepository.setDataSource(dataSource);
        return persistentTokenRepository;
    }
    // remember me end

    // session concurrent begin

    @Bean
    public ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy() {
        ConcurrentSessionControlAuthenticationStrategy sessionControlAuthenticationStrategy =
                new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
        sessionControlAuthenticationStrategy.setMaximumSessions(MAX_SESSIONS);
        sessionControlAuthenticationStrategy.setExceptionIfMaximumExceeded(false);
        return sessionControlAuthenticationStrategy;
    }

    @Bean
    public SessionFixationProtectionStrategy sessionFixationProtectionStrategy() {
        SessionFixationProtectionStrategy sessionFixationProtectionStrategy = new SessionFixationProtectionStrategy();
        return sessionFixationProtectionStrategy;
    }

    @Bean
    public RegisterSessionAuthenticationStrategy registerSessionAuthenticationStrategy() {
        RegisterSessionAuthenticationStrategy registerSessionAuthenticationStrategy = new RegisterSessionAuthenticationStrategy(sessionRegistry());
        return registerSessionAuthenticationStrategy;
    }

    @Bean
    public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        List<SessionAuthenticationStrategy> strategies = new ArrayList<>();
        strategies.add(concurrentSessionControlAuthenticationStrategy());
        strategies.add(sessionFixationProtectionStrategy());
        strategies.add(registerSessionAuthenticationStrategy());
        CompositeSessionAuthenticationStrategy sessionAuthenticationStrategy = new CompositeSessionAuthenticationStrategy(strategies);
        return sessionAuthenticationStrategy;
    }

    @Bean
    public ConcurrentSessionFilter concurrentSessionFilter() {
        return new ConcurrentSessionFilter(sessionRegistry(), simpleRedirectSessionInformationExpiredStrategy());
    }

    /**
     * 会话过期后跳转路径
     *
     * @return
     */
    @Bean
    public SimpleRedirectSessionInformationExpiredStrategy simpleRedirectSessionInformationExpiredStrategy() {
        return new SimpleRedirectSessionInformationExpiredStrategy(INVALID_SESSION_URL);
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
    // session concurrent end

}
