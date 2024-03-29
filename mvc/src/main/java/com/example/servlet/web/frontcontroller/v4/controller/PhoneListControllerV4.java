package com.example.servlet.web.frontcontroller.v4.controller;

import com.example.servlet.domain.Phone;
import com.example.servlet.domain.PhoneRepository;
import com.example.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class PhoneListControllerV4 implements ControllerV4 {

    private PhoneRepository phoneRepository = PhoneRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        List<Phone> phones = phoneRepository.findAll();
        model.put("phones", phones);
        return "phones";
    }
}
