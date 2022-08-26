package pers.guzx.user.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

/**
 * @author 25446
 * 预校验，参考DefaultPreAuthenticationChecks
 */
@Slf4j
@Component
public class PreAuthenticationChecks implements UserDetailsChecker {
    @Override
    public void check(UserDetails userDetails) {
        log.info("Checking user details for previously");
    }
}
