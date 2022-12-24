package com.example.servlet.web.frontcontroller.v2.controller;

import com.example.servlet.domain.Phone;
import com.example.servlet.domain.PhoneRepository;
import com.example.servlet.web.frontcontroller.MyView;
import com.example.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PhoneSaveFormControllerV2 implements ControllerV2 {

    private PhoneRepository phoneRepository = PhoneRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));

        Phone phone = new Phone(name, price);
        phoneRepository.save(phone);

        //Model 에 데이터를 보관한다.
        request.setAttribute("phone", phone);

        return new MyView("/view-path/save-result.jsp");
    }
}
