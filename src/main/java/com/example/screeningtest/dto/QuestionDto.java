package com.example.screeningtest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    @SerializedName("id")
    @JsonProperty("question_id")
    private Integer id;

    @SerializedName("question")
    @JsonProperty("question")
    private String question;
}
