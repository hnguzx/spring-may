package pers.guzx.user.authorize;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author 25446
 * 授权决策
 */
@Slf4j
@Component
public class AccessDecisionManagerImpl extends AbstractAccessDecisionManager {

    public AccessDecisionManagerImpl(List<AccessDecisionVoter<?>> decisionVoters) {
        super(decisionVoters);
    }

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        int deny = 0;
        for (AccessDecisionVoter voter : getDecisionVoters()) {
            int result = voter.vote(authentication, object, configAttributes);
            switch (result) {
                case AccessDecisionVoter.ACCESS_GRANTED:
                    return;
                case AccessDecisionVoter.ACCESS_DENIED:
                    deny++;
                    break;
                default:
                    break;
            }
        }
        if (deny > 0) {
            throw new AccessDeniedException(
                    this.messages.getMessage("AbstractAccessDecisionManager.accessDenied", "Access is denied"));
        }
        // To get this far, every AccessDecisionVoter abstained
        checkAllowIfAllAbstainDecisions();
    }
}
