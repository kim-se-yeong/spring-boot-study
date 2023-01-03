package com.example.basic.request;

import com.example.basic.PhoneData;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        PhoneData phoneData = objectMapper.readValue(messageBody, PhoneData.class);
        log.info("name={}, price={}", phoneData.getName(), phoneData.getPrice());

        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        PhoneData phoneData = objectMapper.readValue(messageBody, PhoneData.class);
        log.info("name={}, price={}", phoneData.getName(), phoneData.getPrice());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody PhoneData phoneData) throws IOException {
        log.info("name={}, price={}", phoneData.getName(), phoneData.getPrice());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<PhoneData> httpEntity) throws IOException {
        PhoneData phoneData = httpEntity.getBody();
        log.info("name={}, price={}", phoneData.getName(), phoneData.getPrice());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public PhoneData requestBodyJsonV5(@RequestBody PhoneData phoneData) throws IOException {
        log.info("name={}, price={}", phoneData.getName(), phoneData.getPrice());
        return phoneData;
    }
}