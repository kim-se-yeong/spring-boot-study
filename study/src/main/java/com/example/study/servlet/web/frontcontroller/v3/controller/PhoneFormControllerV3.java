package com.example.study.servlet.web.frontcontroller.v3.controller;

import com.example.study.servlet.web.frontcontroller.ModelView;
import com.example.study.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class PhoneFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}