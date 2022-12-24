package com.example.servlet.web.springmvc.v1;

import com.example.servlet.domain.Phone;
import com.example.servlet.domain.PhoneRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SpringPhoneSaveControllerV1 {

    private PhoneRepository phoneRepository = PhoneRepository.getInstance();

    @RequestMapping("/springmvc/v1/phone/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));

        Phone phone = new Phone(name, price);
        phoneRepository.save(phone);

        ModelAndView modelView = new ModelAndView("save-result");
        modelView.addObject("phone", phone);
        return modelView;

    }
}
