package com.kien.babee.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @GetMapping("/")
    public ModelAndView index() {
        LOG.info("Index ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/catch-the-words")
    public ModelAndView catchTheWords() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("catchWords");
        return modelAndView;
    }


}
