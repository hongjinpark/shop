package com.example.hong.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
public class ApiController {

    @PostMapping("/test")
    public void apiTest(@RequestBody Map<String, Object> params) throws UnsupportedEncodingException {

        params.put("name", "james");
        params.put("email", "james@example.com");
        params.put("reply_to_thread", 10394);
        params.put("message", "Hello World");

        StringBuilder postData = new StringBuilder();
        for(Map.Entry<String,Object> param : params.entrySet()) {
            if(postData.length() != 0) postData.append('&');
            postData.append(param.getKey());
            postData.append('=');
            postData.append(param.getValue());
        }


        System.out.println(params);
        System.out.println("postData = " + postData);


    }


}
