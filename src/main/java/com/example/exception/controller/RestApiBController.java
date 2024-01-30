package com.example.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/api/b")
public class RestApiBController {
    //http://localhost:8080/api/b/hello
    @GetMapping("/hello")
    public void hello(){
        throw new NumberFormatException("number format exception");//예외 발생 시, RestApiExceptionHandler에서 감지, RestControllerAdvicer가 있는 컨트롤러에서 감지
    }

    @ExceptionHandler(value = {NumberFormatException.class})//감지할 클래스 명시, 예외 발생 시 RestApiBController에서 감지
    public ResponseEntity NumberFormatExceptionc(
            NumberFormatException e
    ){
        log.error("RestApiBController",e);
        return ResponseEntity.ok().build();
    }
}
