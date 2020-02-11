package com.imshenik.tofer.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.imshenik.tofer.service.ToferService;

@RestController
@EnableAutoConfiguration
@RequestMapping("/tofer")
public class ToferController {

    private ToferService tofer = new ClassPathXmlApplicationContext("spring.xml").getBean(com.imshenik.tofer.service.ToferService.class);

    @RequestMapping()
    String status() {
        return tofer.requestStatus();
    }

    @RequestMapping("/t{number}")
    String tktByNumber(@PathVariable int number) {
        return tofer.requestTktByNumber(number);
    }

    @RequestMapping("/c{number}")
    String cnvByNumber(@PathVariable int number) {
        return tofer.requestCnvByNumber(number);
    }

    @RequestMapping("/tfirst")
    String tktFirst() {
        return tofer.requestFirstTkt();
    }

    @RequestMapping("/tlast")
    String tktLast() {
        return tofer.requestLastTkt();
    }

    @RequestMapping("/cfirst")
    String cnvFirst() {
        return tofer.requestFirstTkt();
    }

    @RequestMapping("/clast")
    String cnvLast() {
        return tofer.requestLastTkt();
    }

    @RequestMapping("/first")
    String firstNumber() {
        return "" + tofer.getFirstTktNumber();
    }

    @RequestMapping("/last")
    String lastNumber() {
        return "" + tofer.getLastTktNumber();
    }
}
