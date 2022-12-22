package com.example.study.servlet.web.frontcontroller.v2;

import com.example.study.servlet.web.frontcontroller.MyView;

import com.example.study.servlet.web.frontcontroller.v2.controller.PhoneFormControllerV2;
import com.example.study.servlet.web.frontcontroller.v2.controller.PhoneListControllerV2;
import com.example.study.servlet.web.frontcontroller.v2.controller.PhoneSaveFormControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//프론트 컨트롤러 서블릿 만들기
@WebServlet(name = "frontControllerServiceV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/phone/new-form", new PhoneFormControllerV2());
        controllerMap.put("/front-controller/v2/phone/save", new PhoneSaveFormControllerV2());
        controllerMap.put("/front-controller/phones", new PhoneListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV2 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_FOUND);
            return;
        }

        MyView myView = controller.process(request, response);
        myView.render(request, response);
    }
}