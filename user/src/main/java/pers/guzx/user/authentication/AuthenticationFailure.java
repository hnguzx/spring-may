package pers.guzx.user.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
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
 * 认证失败处理器
 */
@Slf4j
@Component
public class AuthenticationFailure implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("authentication failed! {}", exception);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JsonUtils.toJsonString(Result.succeed(Code.CUSTOMER_AUTHORITY_FAIL)));
    }
}
