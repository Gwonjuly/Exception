package com.example.exception.Model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)//json 형태를 snake로 받음
@Builder //객체를 생성할 때 builder 패턴을 사용
public class Api<T> {

    private String resultCode;
    private String resultMessage;
    private T data;//제네릭 타입으로 데이터의 형태를 바꿀 수 있도록 함
}
