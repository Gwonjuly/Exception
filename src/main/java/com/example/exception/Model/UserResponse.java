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
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)//Json을 snake 형태로 받음
@Builder //객체를 생성할 때 builder 패턴을 사용
public class UserResponse {
    private String id;
    private String name;
    private Integer age;
}
