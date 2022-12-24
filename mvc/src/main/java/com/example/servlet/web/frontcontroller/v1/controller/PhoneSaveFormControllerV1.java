package com.example.servlet.web.frontcontroller.v1.controller;

import com.example.servlet.domain.Phone;
import com.example.servlet.domain.PhoneRepository;
import com.example.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//회원 저장 컨트롤러
public class PhoneSaveFormControllerV1 implements ControllerV1 {

    private PhoneRepository phoneRepository = PhoneRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));

        Phone phone = new Phone(name, price);
        phoneRepository.save(phone);

        //Model 에 데이터를 보관한다.
        request.setAttribute("phone", phone);

        String viewPath = "/jsp-path/save-result.jsp"; //실제 JSP 경로 입력해주기
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
