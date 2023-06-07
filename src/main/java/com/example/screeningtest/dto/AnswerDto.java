package com.example.screeningtest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {

    @JsonProperty("question_id")
    private Integer questionId;

    private String answer;
}
