package pers.guzx.user.authorize;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pers.guzx.user.authentication.AuthenticationToken;
import pers.guzx.user.entity.Authority;
import pers.guzx.user.entity.Role;
import pers.guzx.user.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static pers.guzx.user.common.Constant.COMMON_REQUEST_PREFIX;

/**
 * @author 25446
 * 自定义投票器，参考RoleVoter
 */
@Slf4j
@Component
public class AccessDecisionVoterImpl implements AccessDecisionVoter<FilterInvocation> {
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation filterInvocation, Collection collection) {
        if (authentication == null) {
            return ACCESS_DENIED;
        }
        String requestURI = filterInvocation.getRequest().getRequestURI();
        String prefix = Arrays.asList(requestURI.split("/")).get(1);
        List<Role> roles;
        // common请求全部通过
        if(prefix.equalsIgnoreCase(COMMON_REQUEST_PREFIX)){
            return ACCESS_GRANTED;
        }

        if (authentication instanceof AuthenticationToken) {
            roles = ((AuthenticationToken) authentication).getRoles();
            if (!CollectionUtils.isEmpty(roles) && prefix.equalsIgnoreCase(roles.get(0).getRoleName())) {
                return ACCESS_GRANTED;
            }
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (CollectionUtils.isEmpty(authorities)) {
            return ACCESS_DENIED;
        }
        List<? extends GrantedAuthority> collect = authorities.parallelStream().filter(authority -> ((Authority)authority).getUrl().equals(requestURI)).collect(Collectors.toList());
        if (collect.size() > 0) {
            return ACCESS_GRANTED;
        }

        return ACCESS_DENIED;
    }

    @Override
    public boolean supports(Class clazz) {
        return true;
    }
}
