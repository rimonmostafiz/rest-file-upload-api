package com.rimonmostafiz.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rimon Mostafiz
 */

@RestController
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "Home Page";
    }

}
