package com.example.servlet.web.frontcontroller.v4;

import com.example.servlet.web.frontcontroller.MyView;
import com.example.servlet.web.frontcontroller.v4.controller.PhoneFormControllerV4;
import com.example.servlet.web.frontcontroller.v4.controller.PhoneListControllerV4;
import com.example.servlet.web.frontcontroller.v4.controller.PhoneSaveFormControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServiceV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    //각 컨트롤러는 한번만 생성된다.
    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/phone/new-form", new PhoneFormControllerV4());
        controllerMap.put("/front-controller/v4/phone/save", new PhoneSaveFormControllerV4());
        controllerMap.put("/front-controller/phones", new PhoneListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>(); //모델 직접 생성

        String viewName = controller.process(paramMap, model);

        //viewResolver 를 통해 물리적 이름 생성하기
        MyView myView = viewResolver(viewName);
        myView.render(model, request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {

        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}