package pers.guzx.user.authorize;

import pers.guzx.common.entity.Result;
import pers.guzx.common.enums.Code;
import pers.guzx.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 25446
 * 授权失败
 */
@Slf4j
@Component
public class AuthorizeFailure implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.info("Access Denied",accessDeniedException);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=utf-8");
        Result<Code> result = null;
        result = Result.failed(Code.USER_NOT_AUTH);
        response.getWriter().write(JsonUtils.toJsonString(result));
    }
}
