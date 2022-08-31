package pers.guzx.user.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;
import pers.guzx.user.common.Constant;
import pers.guzx.user.common.LoginType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author 25446
 * 自定义认证过滤器。构建一个未认证的AuthenticationToken
 * 参考：UsernamePasswordAuthenticationFilter
 */
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private boolean postOnly = true;
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher(Constant.LOGIN_URL,
            "POST");

    private SessionAuthenticationStrategy sessionAuthenticationStrategy;

    public AuthenticationFilter() {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
    }

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
    }

    /**
     * 用户认证
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String username;
        String mobile;
        String email;
        String password;
        String code;
        String loginType;
        String contentType = request.getContentType();
        // json格式登录
        if (contentType != null && contentType.equals(MediaType.APPLICATION_JSON_VALUE)) {
            ObjectMapper mapper = new ObjectMapper();
            try (InputStream is = request.getInputStream()) {
                Map<String, String> authenticationBean = mapper.readValue(is, Map.class);
                username = authenticationBean.get(Constant.LOGIN_USER_NAME);
                mobile = authenticationBean.get(Constant.LOGIN_MOBILE);
                email = authenticationBean.get(Constant.LOGIN_EMAIL);
                password = authenticationBean.get(Constant.LOGIN_PASSWORD);
                code = authenticationBean.get(Constant.CODE);
                loginType = authenticationBean.get(Constant.LOGIN_TYPE);
            } catch (IOException e) {
                e.printStackTrace();
                throw new AuthenticationServiceException("Get Authentication parameters fail");
            }
        } else {
            // 表单登录
            username = obtainUsername(request);
            password = obtainPassword(request);
            mobile = obtainMobile(request);
            email = obtainEmail(request);
            code = obtainCode(request);
            loginType = obtainLoginType(request);
        }

        // 未认证token
        AuthenticationToken authToken;
        String login_username, login_password;
        if (StringUtils.hasLength(username) && StringUtils.hasLength(password)) {
            login_username = username;
            login_password = password;
        } else if (StringUtils.hasLength(mobile) && StringUtils.hasLength(code)) {
            login_username = mobile;
            login_password = code;
        } else if (StringUtils.hasLength(email) && StringUtils.hasLength(code)) {
            login_username = email;
            login_password = code;
        } else {
            throw new AuthenticationServiceException("Get Authentication parameters fail");
        }
        authToken = new AuthenticationToken(login_username, login_password, LoginType.valueOf(loginType));

        Object detail = this.authenticationDetailsSource.buildDetails(request);
        authToken.setDetails(detail);
        // 已认证token
        Authentication authenticate = this.getAuthenticationManager().authenticate(authToken);

        return authenticate;
    }

    @Nullable
    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(Constant.LOGIN_PASSWORD);
    }

    @Nullable
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(Constant.LOGIN_USER_NAME);
    }

    @Nullable
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(Constant.LOGIN_MOBILE);
    }

    @Nullable
    protected String obtainEmail(HttpServletRequest request) {
        return request.getParameter(Constant.LOGIN_EMAIL);
    }

    @Nullable
    protected String obtainCode(HttpServletRequest request) {
        return request.getParameter(Constant.CODE);
    }

    @Nullable
    protected String obtainLoginType(HttpServletRequest request) {
        return request.getParameter(Constant.LOGIN_TYPE);
    }
}
