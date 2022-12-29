package com.example.basic.request;

import com.example.basic.PhoneData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        log.info("name={}, price={}", name, price);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("name") String phoneName,
            @RequestParam("price") int phonePrice) {

        log.info("name={}, price={}", phoneName, phonePrice);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String name,
            @RequestParam int price) {

        log.info("name={}, price={}", name, price);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String name, int price) {
        log.info("name={}, price={}", name, price);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String name,
            @RequestParam(required = false) Integer price) {
        log.info("name={}, price={}", name, price);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "iphone-mini") String name,
            @RequestParam(defaultValue = "0") int price) {
        log.info("name={}, price={}", name, price);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("name={}, price={}", paramMap.get("name"), paramMap.get("price"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@RequestParam String name, @RequestParam int price) {
        PhoneData phoneData = new PhoneData();
        phoneData.setName(name);
        phoneData.setPrice(price);

        log.info("name={}, price={}", phoneData.getName(), phoneData.getPrice());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(@ModelAttribute PhoneData phoneData) {
        log.info("name={}, price={}", phoneData.getName(), phoneData.getPrice());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v3")
    public String modelAttributeV3(PhoneData phoneData) {
        log.info("name={}, price={}", phoneData.getName(), phoneData.getPrice());
        return "ok";
    }
}
