package com.example.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringPhoneFormControllerV1 {

    @RequestMapping("/springmvc/v1/phones/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
