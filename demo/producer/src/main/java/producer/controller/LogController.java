package producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/5/15 17:47
 * @describe
 */
@Slf4j
@RestController
public class LogController {

    @GetMapping("/log")
    public void log(){
        log.trace("Connect Execute start");
        log.debug("debug log");
        log.info("info log");
        log.warn("warn log");
        log.error("error log");
    }

}
