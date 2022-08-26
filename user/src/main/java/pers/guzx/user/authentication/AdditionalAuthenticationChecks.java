package pers.guzx.user.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

/**
 * @author 25446
 * 特殊校验，参考AccountStatusUserDetailsChecker
 */
@Slf4j
@Component
public class AdditionalAuthenticationChecks implements UserDetailsChecker {
    @Override
    public void check(UserDetails userDetails) {
        log.info("Checking user details for additional");
    }
}
