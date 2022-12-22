package com.example.study.servlet.web.frontcontroller.v4.controller;

import com.example.study.servlet.domain.Phone;
import com.example.study.servlet.domain.PhoneRepository;
import com.example.study.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class PhoneSaveFormControllerV4 implements ControllerV4 {

    private PhoneRepository phoneRepository = PhoneRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        String name = paramMap.get("name");
        int price = Integer.parseInt(paramMap.get("price"));

        Phone phone = new Phone(name, price);
        phoneRepository.save(phone);

        model.put("phone", phone);
        return "save-result";
    }
}
