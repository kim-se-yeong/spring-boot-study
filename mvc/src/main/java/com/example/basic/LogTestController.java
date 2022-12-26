package com.example.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @GetMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        log.trace("trace log 1 = " + name); //문자 더하기 연산 발생 -> 연산이 이루어졌는데, 만약 해당 로그 레벨을 남기지 않을 시 연산에 대한 리소스 낭비가 있다.

        log.trace("trace log = {}", name); //매우 상세한 로그를 남기기 위함
        log.debug("debug log = {}", name); //테스트 환경에서 문제 확인 및 해결하기 위한 정보를 나타내기 위함
        log.info("info log = {}", name); //정보성 로그를 나타내기 위함
        log.warn("warn log = {}", name); //애플리케이션 실행에는 문제가 없지만 향후 문제가 될 수 있을 때, 즉 경고성
        log.error("error log = {}", name); //애플리케이션이 제대로 동작하지 않는 문제가 발생하였으며 즉시 문제를 조치해야 할 때

        return "ok";
    }
}
