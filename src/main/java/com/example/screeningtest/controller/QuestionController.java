package com.example.screeningtest.controller;

import com.example.screeningtest.dto.AnswerDto;
import com.example.screeningtest.dto.JsonResponse;
import com.example.screeningtest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/play")
    public JsonResponse getQuestionController(){
        return questionService.getQuestion();
    }

    @PostMapping("/next")
    public JsonResponse submitQuestionController(@RequestBody AnswerDto answerDto){
        return questionService.submitQuestion(answerDto);
    }
}
