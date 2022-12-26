package com.example.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/phones")
public class MappingClassController {

    @GetMapping
    public String phones() {
        return "get phones";
    }

    @PostMapping
    public String addPhone() {
        return "post phone";
    }

    @GetMapping("/{phoneId}")
    public String findPhone(@PathVariable String phoneId) {
        return "get phoneId = " + phoneId;
    }

    @PatchMapping("/{phoneId}")
    public String updatePhone(@PathVariable String phoneId) {
        return "update phoneId = " + phoneId;
    }

    @DeleteMapping("/{phoneId}")
    public String deletePhone(@PathVariable String phoneId) {
        return "delete phoneId = " + phoneId;
    }
}
