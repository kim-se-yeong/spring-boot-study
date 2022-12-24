package com.example.servlet.web.frontcontroller.v1;

import com.example.servlet.web.frontcontroller.v1.controller.PhoneFormControllerV1;
import com.example.servlet.web.frontcontroller.v1.controller.PhoneListControllerV1;
import com.example.servlet.web.frontcontroller.v1.controller.PhoneSaveFormControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//프론트 컨트롤러 서블릿 만들기
@WebServlet(name = "frontControllerServiceV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/phone/new-form", new PhoneFormControllerV1());
        controllerMap.put("/front-controller/v1/phone/save", new PhoneSaveFormControllerV1());
        controllerMap.put("/front-controller/phones", new PhoneListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        System.out.println("FrontControllerServletV1.service");

        ControllerV1 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_FOUND);
            return;
        }

        controller.process(request, response);
    }
}