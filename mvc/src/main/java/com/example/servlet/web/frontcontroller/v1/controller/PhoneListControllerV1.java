package com.example.servlet.web.frontcontroller.v1.controller;

import com.example.servlet.domain.Phone;
import com.example.servlet.domain.PhoneRepository;
import com.example.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PhoneListControllerV1 implements ControllerV1 {

    private PhoneRepository phoneRepository = PhoneRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Phone> phones = phoneRepository.findAll();

        request.setAttribute("phones", phones);

        String viewPath = "/jsp-path/phones.jsp"; //실제 JSP 경로 입력해주기
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
