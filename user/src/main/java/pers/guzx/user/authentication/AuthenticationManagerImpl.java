package pers.guzx.user.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author 25446
 */
public class AuthenticationManagerImpl implements AuthenticationManager {

    @Autowired
    private AuthenticationProviderImpl authenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication authenticate = authenticationProvider.authenticate(authentication);
        return authenticate;
    }
}
