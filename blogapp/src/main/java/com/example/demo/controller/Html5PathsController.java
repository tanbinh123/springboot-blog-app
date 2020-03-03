package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Html5PathsController implements ErrorController {

    private static final Logger LOG = LoggerFactory.getLogger(Html5PathsController.class);
    private static final String PATH = "/error";

    @GetMapping("/{[path:[^\\.]*}")
    public String redirect() {
        LOG.info("Unknow path, HTML5Controller redirecting..");
        return "forward:/";
    }

    @GetMapping(PATH)
    public String error() {
        return "Error handling";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}