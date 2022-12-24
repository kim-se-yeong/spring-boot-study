package com.example.servlet.web.frontcontroller.v3;

import com.example.servlet.web.frontcontroller.ModelView;
import com.example.servlet.web.frontcontroller.MyView;
import com.example.servlet.web.frontcontroller.v3.controller.PhoneFormControllerV3;
import com.example.servlet.web.frontcontroller.v3.controller.PhoneListControllerV3;
import com.example.servlet.web.frontcontroller.v3.controller.PhoneSaveFormControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServiceV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/phone/new-form", new PhoneFormControllerV3());
        controllerMap.put("/front-controller/v3/phone/save", new PhoneSaveFormControllerV3());
        controllerMap.put("/front-controller/phones", new PhoneListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        ModelView modelView = controller.process(paramMap);

        //viewResolver 를 통해 물리적 이름 생성하기
        MyView myView = viewResolver(modelView);
        myView.render(modelView.getModel(), request, response);
    }

    private MyView viewResolver(ModelView modelView) {
        return new MyView("" + modelView.getViewName() + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {

        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}