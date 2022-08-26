package pers.guzx.user.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import pers.guzx.common.entity.dto.Result;
import pers.guzx.common.enums.Code;
import pers.guzx.common.util.JsonUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 25446
 * 认证异常处理器(未登录)
 */
@Slf4j
@Component
public class AuthenticationException implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
        log.error("authentication exception：{}", authException);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JsonUtils.toJsonString(Result.succeed(Code.USER_NOT_AUTH)));
    }
}
