package com.example.screeningtest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorrectAnswerDto {

    @JsonProperty("correct_answer")
    private String correctAnswer;

    @JsonProperty("next_question")
    private QuestionDto nextQuestion;
}
