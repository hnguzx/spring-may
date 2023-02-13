package pers.guzx.user.authentication;

import pers.guzx.user.common.LoginType;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import pers.guzx.user.entity.Role;

import java.util.Collection;
import java.util.List;

/**
 * @author 25446
 * 自定义认证流程，token令牌。
 * 参考：UsernamePasswordAuthenticationToken
 */
public class AuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;

    private Object credentials;

    private LoginType loginType;

    private List<Role> roles;

    /**
     * 未认证
     *
     * @param principal
     * @param credentials
     * @param loginType
     */
    public AuthenticationToken(Object principal, Object credentials, LoginType loginType) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.loginType = loginType;
        super.setAuthenticated(false);
    }

    /**
     * 认证成功
     *
     * @param principal
     * @param authorities
     */
    public AuthenticationToken(Object principal, List<Role> roles, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
        this.roles = roles;
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public LoginType getLoginType() {
        return this.loginType;
    }

    public List<Role> getRoles() {
        return this.roles;
    }
}
