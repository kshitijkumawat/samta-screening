package com.example.screeningtest.service;

import com.example.screeningtest.dto.AnswerDto;
import com.example.screeningtest.dto.CorrectAnswerDto;
import com.example.screeningtest.dto.JsonResponse;
import com.example.screeningtest.dto.QuestionDto;
import com.example.screeningtest.modal.QuestionModal;
import com.example.screeningtest.repository.QuestionRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    private static List<Integer> questionIds = new ArrayList<>();
    private int playCounter = 0;

    @Scheduled(fixedDelay = Long.MAX_VALUE)
    public void saveFiveQuestions() throws Exception {
        try {
            Gson gson =new Gson();
            for(int i=0; i<5; i++)
            {
                final String uri = "http://jservice.io/api/random";
                RestTemplate restTemplate = new RestTemplate();
                List<QuestionModal> questionModals = restTemplate.getForObject(uri, List.class);
                QuestionModal questionModal = gson.fromJson(gson.toJson(questionModals.get(0)), QuestionModal.class);
                questionRepository.save(questionModal);
                questionIds.add(questionModal.getId());
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public JsonResponse getQuestion() {
        JsonResponse jsonResponse = new JsonResponse<>();
        Gson gson = new Gson();
        try {
            QuestionModal questionModal = questionRepository.findById(questionIds.get(playCounter)).orElse(null);
            if (questionModal!=null) {
                QuestionDto questionDto = gson.fromJson(gson.toJson(questionModal), new TypeToken<QuestionDto>(){}.getType());
                if (playCounter<4) {
                    playCounter++;
                }
                else {
                    playCounter = 0;
                }
                jsonResponse.setData(questionDto);
            }
            else {
                jsonResponse.setData("No questions exists in Database!");
            }
            jsonResponse.setSuccess(true);
            jsonResponse.setStatus(HttpStatus.OK);
            return jsonResponse;
        } catch (Exception e) {
            jsonResponse.setSuccess(false);
            jsonResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            jsonResponse.setData("Something went wrong, your issue reported!");
            return jsonResponse;
        }
    }

    public JsonResponse submitQuestion(AnswerDto answerDto) {
        JsonResponse jsonResponse = new JsonResponse<>();
        CorrectAnswerDto correctAnswerDto = new CorrectAnswerDto();
        try {
            QuestionModal questionModal = questionRepository.findById(answerDto.getQuestionId()).orElse(null);
            if (questionModal!=null)
            {
                Gson gson = new Gson();
                correctAnswerDto.setCorrectAnswer(questionModal.getAnswer());

                int nextCounter = questionIds.indexOf(questionModal.getId()) + 1;
                if (nextCounter>4) {
                    nextCounter = 0;
                }

                QuestionModal nextQuestion = questionRepository.findById(questionIds.get(nextCounter)).orElse(null);
                QuestionDto questionDto = gson.fromJson(gson.toJson(nextQuestion), new TypeToken<QuestionDto>(){}.getType());
                correctAnswerDto.setNextQuestion(questionDto);
                jsonResponse.setData(correctAnswerDto);
            }
            else {
                jsonResponse.setData("No question exist in Database with given question ID!");
            }
            jsonResponse.setSuccess(true);
            jsonResponse.setStatus(HttpStatus.OK);
            return jsonResponse;
        } catch (Exception e) {
            jsonResponse.setSuccess(false);
            jsonResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            jsonResponse.setData("Something went wrong, your issue reported!");
            return jsonResponse;
        }
    }
}
