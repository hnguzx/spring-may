package pers.guzx.user.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author 25446
 * 特殊校验，参考AccountStatusUserDetailsChecker
 */
@Slf4j
@Component
public class AdditionalAuthenticationChecks implements UserDetailsChecker {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void check(UserDetails userDetails) {
        log.info("Checking user details for additional");

    }

    public void check(UserDetails userDetails, Authentication authentication) {
        if (authentication.getCredentials() == null) {
            throw new BadCredentialsException("Bad credentials");
        }
        String presentedPassword = authentication.getCredentials().toString();
        if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }
    }
}
