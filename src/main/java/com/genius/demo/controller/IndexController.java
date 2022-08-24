package com.genius.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class IndexController {

    @RequestMapping("/partner/oauth/token")
    public String token(HttpServletRequest request) {
        ServletInputStream is = null;
        try {
            is = request.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len));
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "{\"access_token\": \"123456\", \"token_type\": \"token\", \"expires_in\": \"7200\", \"scope\": \"ALL\", \"jti\": \"jti\"}";
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
        ServletInputStream is = null;
        try {
            is = request.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len));
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "{\"PAYACCEPT\": \"S\", \"PAYACCEPTTIME\": \"2022-06-07 21:30:00\", \"REMARKS\": \"SUCCESS\"}";
    }

}
