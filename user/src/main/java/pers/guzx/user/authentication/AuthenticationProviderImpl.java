package pers.guzx.user.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pers.guzx.user.entity.User;
import pers.guzx.user.service.impl.UserServiceImpl;

/**
 * @author 25446
 * 自定义认证校验器。
 * 参考：AbstractUserDetailsAuthenticationProvider
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PreAuthenticationChecks preAuthenticationChecks;
    @Autowired
    private PostAuthenticationChecks postAuthenticationChecks;
    @Autowired
    private AdditionalAuthenticationChecks additionalAuthenticationChecks;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails userDetails = userService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("username not found");
        }

        preAuthenticationChecks.check(userDetails);
        additionalAuthenticationChecks.check(userDetails, authentication);
        postAuthenticationChecks.check(userDetails);

        AuthenticationToken token = new AuthenticationToken(userDetails, ((User) userDetails).getRoles(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
