package pers.guzx.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author 25446
 */
@TableName("sys_user")
@Getter
@Setter
public class User implements UserDetails, Serializable {

    @TableId(value = "id")
    private Integer userId;
    private String username;
    private String password;
    @TableField(value = "account_expired")
    private Boolean accountNonExpired;
    @TableField(value = "account_locked")
    private Boolean accountNonLocked;
    @TableField(value = "credentials_expired")
    private Boolean credentialsNonExpired;
    private Boolean enabled;

    @TableField(exist = false)
    private List<Role> roles;
    @TableField(exist = false)
    private List<Authority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(getUserId(), user.getUserId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(isAccountNonExpired(), user.isAccountNonExpired()) && Objects.equals(isAccountNonLocked(), user.isAccountNonLocked()) && Objects.equals(isCredentialsNonExpired(), user.isCredentialsNonExpired()) && Objects.equals(isEnabled(), user.isEnabled()) && Objects.equals(getRoles(), user.getRoles()) && Objects.equals(getAuthorities(), user.getAuthorities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUsername(), getPassword(), isAccountNonExpired(), isAccountNonLocked(), isCredentialsNonExpired(), isEnabled(), getRoles(), getAuthorities());
    }
}
