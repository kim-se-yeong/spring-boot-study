package com.example.servlet.web.frontcontroller.v2.controller;


import com.example.servlet.domain.Phone;
import com.example.servlet.domain.PhoneRepository;
import com.example.servlet.web.frontcontroller.MyView;
import com.example.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PhoneListControllerV2 implements ControllerV2 {

    private PhoneRepository phoneRepository = PhoneRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Phone> phones = phoneRepository.findAll();

        request.setAttribute("phones", phones);

        return new MyView("/view-path/phones.jsp");
    }
}
