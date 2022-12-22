package com.example.study.servlet.web.frontcontroller.v3.controller;

import com.example.study.servlet.domain.Phone;
import com.example.study.servlet.domain.PhoneRepository;
import com.example.study.servlet.web.frontcontroller.ModelView;
import com.example.study.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class PhoneSaveFormControllerV3 implements ControllerV3 {

    private PhoneRepository phoneRepository = PhoneRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String name = paramMap.get("name");
        int price = Integer.parseInt(paramMap.get("price"));

        Phone phone = new Phone(name, price);
        phoneRepository.save(phone);

        ModelView modelView = new ModelView("save-result");
        modelView.getModel().put("phone", phone);
        return modelView;

    }
}
