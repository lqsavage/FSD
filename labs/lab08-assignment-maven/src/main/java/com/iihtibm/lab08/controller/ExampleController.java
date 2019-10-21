package com.iihtibm.lab08.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    @RequestMapping("/")
    String home() {
        return "Lab08-Assignment-Maven.";
    }
}
