package com.example.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiController {
    @GetMapping(path="")//("")도 가능
    public void hello(){
        List<String> list=new ArrayList<>(); //var list= List.of("hello");
        list=List.of("hello");//index=0
        String str=list.get(1);//index=1, Internal Server Error
        log.info("str:{}",str);
        /*throw new RuntimeException("run time exception call");
        RESPONSE CODE 500
        서버에서 에러 발생(시스템 에러에 해당)
         */
    }
}
