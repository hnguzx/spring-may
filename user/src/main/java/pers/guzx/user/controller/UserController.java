package pers.guzx.user.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.guzx.common.entity.Result;
import pers.guzx.user.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author 25446
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getLoginUserByPrincipal")
    public Result<String> getLoginUserByPrincipal(Principal principal) {
        return Result.succeed(principal.getName());
    }

    @GetMapping(value = "/getLoginUserByAuthentication")
    public Result<String> currentUserName(Authentication authentication) {
        return Result.succeed(authentication.getName());
    }

    @GetMapping(value = "/username")
    public Result<String> currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return Result.succeed(principal.getName());
    }

    @GetMapping("/getLoginUser")
    public Result<Object> getLoginUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User){
            return Result.succeed(((User) principal).getUsername());
        }
        return Result.succeed(principal);
    }
}
