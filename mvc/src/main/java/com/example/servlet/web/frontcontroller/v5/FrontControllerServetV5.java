package com.example.servlet.web.frontcontroller.v5;

import com.example.servlet.web.frontcontroller.ModelView;
import com.example.servlet.web.frontcontroller.MyView;
import com.example.servlet.web.frontcontroller.v3.controller.PhoneFormControllerV3;
import com.example.servlet.web.frontcontroller.v3.controller.PhoneListControllerV3;
import com.example.servlet.web.frontcontroller.v3.controller.PhoneSaveFormControllerV3;
import com.example.servlet.web.frontcontroller.v4.controller.PhoneFormControllerV4;
import com.example.servlet.web.frontcontroller.v4.controller.PhoneListControllerV4;
import com.example.servlet.web.frontcontroller.v4.controller.PhoneSaveFormControllerV4;
import com.example.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import com.example.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServetV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServetV5() {
        //v3, v4 를 유연하게 사용할 수 있도록 제공
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/phone/new-form", new PhoneFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/phone/save", new PhoneSaveFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/phones", new PhoneListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/phone/new-form", new PhoneFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/phone/save", new PhoneSaveFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/phones", new PhoneListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_FOUND);
            return;
        }

        //adapter 가져오기
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        ModelView modelView = adapter.handle(request, response, handler);

        //viewResolver 를 통해 물리적 이름 생성하기
        MyView myView = viewResolver(modelView.getViewName());
        myView.render(modelView.getModel(), request, response);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter 를 찾을 수 없습니다.");
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        Object controller = handlerMappingMap.get(requestURI);
        return controller;
    }

    private MyView viewResolver(String viewName) {
        return new MyView("" + viewName + ".jsp");
    }
}
