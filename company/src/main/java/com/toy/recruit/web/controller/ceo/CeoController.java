package com.toy.recruit.web.controller.ceo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ceo")
@RequiredArgsConstructor
public class CeoController {

    @GetMapping
    public String main(Model model) {
        return "ceo/dashboard";
    }
}
