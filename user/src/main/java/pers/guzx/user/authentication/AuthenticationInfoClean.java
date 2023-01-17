package pers.guzx.user.authentication;

import pers.guzx.common.entity.Result;
import pers.guzx.common.enums.Code;
import pers.guzx.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author 25446
 * 清除认证信息
 */
@Slf4j
@Component
public class AuthenticationInfoClean implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("logout success!");

        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JsonUtils.toJsonString(Result.succeed(Code.CUSTOMER_AUTHORITY_CLEAN)));
    }
}
