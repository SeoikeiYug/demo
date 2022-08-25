package com.genius.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class ExceptionController {

    private static int count = 0;

    @RequestMapping("/stackOverflow")
    public String stackOverflow (HttpServletRequest request) {
        test();
        return "stackOverflow";
    }

    public static void test(){
        System.out.println(String.format("调用深度：%d，id：%s", count, UUID.randomUUID().toString()));
        count++;
        test();
    }

    @RequestMapping("/outOfMemory")
    public String outOfMemory(HttpServletRequest request) {
        try {
            List<User> list = new ArrayList<User>();
            //- 循环创建的实例对象被list引用，所以无法被GC回收
            for (int i = 0; ; i++) {
                System.out.println("轮次：" + i);
                list.add(new User(UUID.randomUUID().toString()));
            }
        } catch (Exception e) {

        }
        return "outOfMemory";
    }

    public static class User {
        private String id;

        public User(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }
}
