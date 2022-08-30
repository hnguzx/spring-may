package pers.guzx.user.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
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
        log.info("authentication failed!", exception);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=utf-8");
        Result<Code> result = null;
        if (exception instanceof SessionAuthenticationException) {
            result = Result.failed(Code.USER_ACCOUNT_USE_BY_OTHERS);
        } else if (exception instanceof BadCredentialsException) {
            result = Result.failed(Code.USER_CREDENTIALS_ERROR);
        } else if (exception instanceof UsernameNotFoundException) {
            result = Result.failed(Code.USER_ACCOUNT_NOT_EXIST);
        } else if (exception instanceof AccountStatusException) {
            result = Result.failed(Code.CUSTOMER_ACCOUNT_STATUS_ERROR);
        } else {
            result = Result.failed(Code.CUSTOMER_AUTHORITY_FAIL);
        }
        response.getWriter().write(JsonUtils.toJsonString(result));
    }
}
