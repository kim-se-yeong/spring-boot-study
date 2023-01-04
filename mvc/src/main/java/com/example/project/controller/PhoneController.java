package com.example.project.controller;

import com.example.project.domain.Phone;
import com.example.project.repository.PhoneRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/phones")
public class PhoneController {

    //생성자 주입
    private final PhoneRepository phoneRepository;

    public PhoneController(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @GetMapping
    //view return
    public String getList(Model model) {
        List<Phone> phones = phoneRepository.findAll();
        model.addAttribute("phones", phones);
        return "phone/list";
    }

    @PostMapping
    public String save() {
        return ""; // redirect
    }
}