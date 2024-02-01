package com.example.exception.controller;

import com.example.exception.Model.Api;
import com.example.exception.Model.UserResponse;
import org.springframework.boot.context.config.ConfigDataLocationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.AccessException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private static List<UserResponse> userList= List.of(    //bilder를 통해서 객체 생성
            UserResponse.builder()                          //bilder라는 메소드로 시작해서
                    .id("1")                                //각각의 변수를 지정
                    .name("july")
                    .age(26)
                    .build()                                //build를 하게 되면 해당 객체가 생성
            ,
            UserResponse.builder()
                    .id("2")
                    .name("minju")
                    .age(24)
                    .build()
    );

    @GetMapping("/id/{userId}")                             //GET Method와 매칭, Path Variable 방식, user에 대해 특정 아이디를 가지는 사용자를 내림
    public Api<UserResponse> getUser(                        //Api로 감싸진 user라는 응답을 내림
            @PathVariable String userId                     //위 {userId}와 동일
    ){
        if(true)
            throw new RuntimeException("message");//global exception을 위해 임의의 에러 유발

             var user=userList.stream() //var user=userList.get(userId); get()은 리스트의 인덱스가 들어와서 사용 못함
                .filter(it -> it.getId().equals(userId))    //it: userList에 있는 객체들(현재 2개임)
                .findFirst()                               //user는 옵셔널한 데이터
                .get();//해당 userId가 같은 userList의 요소를 가져옴(null일 수도 있음)

        Api<UserResponse> response=Api.<UserResponse>builder()
                .resultCode(String.valueOf(HttpStatus.OK.value()))//value가 int형이라 문자열로 형 변환
                .resultMessage(HttpStatus.OK.name())
                .data(user) //user=userList의 it, 위의 경우 data가 있을 수도 있고 없을 수도 있음
                .build();

        return response;
        /*
        http://localhost:8080/api/user/id/1 send 시
       {
        "result_code": null,
        "result_message": null,
        "data": { 이 데이터가 private T data;
            "id": "1",
            "name": "july",
            "age": 26
                }
        }
        resultCode와 message 값을 넣어주면
        {
    "result_code": "200", :String.valueOf(HttpStatus.OK.value())
    "result_message": "OK", :HttpStatus.OK.name()
    "data": {
        "id": "1",
        "name": "july",
        "age": 26
    }
}
id: 10일 경우
1. java.util.NoSuchElementException: No value present
2. package exception에서 예외처리 시,
    {
    "result_code": "404",
    "result_message": "Not Found",
    "data": null
} body의 내용은 응답이 내려왔고 Talend의 응답 코드는 200이라고 옴, result_code의 404와 다름
3. Talend의 응답 코드 404
{
    "result_code": "404",
    "result_message": "Not Found",
    "data": null
}

        */
    }
}
