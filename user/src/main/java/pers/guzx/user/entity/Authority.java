package pers.guzx.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author 25446
 */
@TableName("authority")
@Data
@Builder
public class Authority implements GrantedAuthority {
    @TableId
    private Integer authorityId;
    private String authority;
    private String code;
    private String url;
    private Boolean enabled;

    @Override
    public String getAuthority() {
        return null;
    }
}
