package pers.guzx.user.authentication;

import pers.guzx.common.enums.Code;
import pers.guzx.common.util.JsonUtils;
import pers.guzx.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 25446
 * 认证超时处理
 */
@Slf4j
@Component
public class AuthenticationExpired implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        log.info("session expired");
        HttpServletResponse response = event.getResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JsonUtils.toJsonString(Result.succeed(Code.CUSTOMER_AUTHORITY_TIMEOUT)));
    }
}
