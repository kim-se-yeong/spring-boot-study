package com.example.servlet.web.springmvc.v1;

import com.example.servlet.domain.Phone;
import com.example.servlet.domain.PhoneRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SpringPhoneListControllerV1 {

    private PhoneRepository phoneRepository = PhoneRepository.getInstance();

    @RequestMapping("/springmvc/v1/phones")
    public ModelAndView process() {

        List<Phone> phones = phoneRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("phones");
        modelAndView.addObject("phones", phones);
        return modelAndView;
    }
}
