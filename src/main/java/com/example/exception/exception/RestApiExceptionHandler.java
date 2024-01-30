package com.example.exception.exception;

import com.example.exception.Model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice//REST API가 사용하는 곳에 예외가 일어나는 것을 감지
public class RestApiExceptionHandler {

    @ExceptionHandler(value = {Exception.class})//모든 예외를 감지하는 핸들러, {}: 예외를 감지할 클래스 명
    public ResponseEntity Exception( //이건 글로벌 예외처리임, 실전적용-2에서는 여기서 글로벌하게 사용하지 않을거라고 함
            Exception e
    ){
        log.error("RestApiExceptionHandler",e);
        return ResponseEntity.status(200).build();//body()설정 안하고 빌드 -> RESPONSE에 BODY 없음
    }

    @ExceptionHandler(value = {IndexOutOfBoundsException.class}) //IndexOutof~ 대한 예외는 이 메서드에서 잡겠다.
    public ResponseEntity OutOfBound(IndexOutOfBoundsException e){
        log.error("IndexOutOfBoundsException",e);
        return ResponseEntity.status(200).build();
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    /*public Api noSuchElement(NoSuchElementException e){ //user_id:10일 경우 (2. json) Talend code 200
        log.error("",e);
        return Api.builder()
                .resultCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .resultMessage(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build();
    }*/
    public ResponseEntity<Api> nosuch(NoSuchElementException e){ //user_id:10일 경우 (3. json) Talend code 404
        log.error("",e);
        var response=Api.builder()
                .resultCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .resultMessage(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
