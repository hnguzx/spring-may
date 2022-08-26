package pers.guzx.user.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Component;

/**
 * @author 25446
 * 后校验，参考DefaultPostAuthenticationChecks
 */
@Slf4j
@Component
public class PostAuthenticationChecks implements UserDetailsChecker {
    @Override
    public void check(UserDetails userDetails) {
        log.info("Checking user details for post");
    }
}
