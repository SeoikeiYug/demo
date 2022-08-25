package com.genius.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @RequestMapping("/recursivePrint")
    public void recursivePrint(@RequestParam Integer num) {
        System.out.println("ExceptionController Number: " + num);

        if(num != 0) {
            recursivePrint(++num);
        }
    }

}
