package com.example.exception.exception;

import com.example.exception.Model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/*
글로벌 Exception
exception의 global 및 restapi의 핸들러의 우선순위 결정(:order)
다른 핸들러를 통과하고 도착하면 그것에 대한 예외처리 진행(가장 마지막에 실행)
즉, restapi~handler 제일 먼저 실행/global 제일 마지막에 실행
 */
@Slf4j
@RestControllerAdvice //REST API가 사용되는 모든 곳에 예외 처리 감지
@Order(value = Integer.MAX_VALUE)//가장 먼저 실행 min, 가장 나중에 실행 max(글로벌: 가장  마지막에 실행될 최후의 수단)

public class GlobalException {
    @ExceptionHandler(value = {Exception.class})//예외들 중에서 Exception이 최상위 클래스임=예측하지 못한 발생 에러를 여기서 처리하겠다
    public ResponseEntity<Api> exception(Exception e){
        log.error("",e);
        var response=Api.builder()
                .resultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .resultMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
/*
userApiController에 runTimeException 넣어줌
{
    "result_code": "500", internal server error:500번임
    "result_message": "Internal Server Error",
    "data": null
}

 */