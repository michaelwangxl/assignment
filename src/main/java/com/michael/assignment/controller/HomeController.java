package com.michael.assignment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author michaelwang on 2021-08-18
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(){
        return "home";
    }
}
