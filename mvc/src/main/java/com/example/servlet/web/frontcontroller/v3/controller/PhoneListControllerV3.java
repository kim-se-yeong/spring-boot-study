package com.example.servlet.web.frontcontroller.v3.controller;

import com.example.servlet.domain.Phone;
import com.example.servlet.domain.PhoneRepository;
import com.example.servlet.web.frontcontroller.ModelView;
import com.example.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class PhoneListControllerV3 implements ControllerV3 {

    private PhoneRepository phoneRepository = PhoneRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Phone> phones = phoneRepository.findAll();

        ModelView modelView = new ModelView("phones");
        modelView.getModel().put("phones", phones);

        return modelView;
    }
}
