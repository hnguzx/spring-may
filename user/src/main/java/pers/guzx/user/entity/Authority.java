package pers.guzx.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author 25446
 */
@TableName("sys_authority")
@Data
@Builder
public class Authority implements GrantedAuthority {

    private Integer authenticationId;
    private String authority;
    private String enabled;

    @Override
    public String getAuthority() {
        return null;
    }
}
