package com.example.screeningtest.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "question_modal")
public class QuestionModal {

        @Id
        @Column(name = "id")
        private Integer id;

        @Column(name = "answer")
        private String answer;

        @Column(name = "question")
        private String question;

        @Column(name = "question_value")
        private Integer value;

        @Column(name = "airdate")
        private String airdate;

        @Column(name = "created_at")
        private String created_at;

        @Column(name = "updated_at")
        private String updated_at;

        @Column(name = "category_id")
        private Integer category_id;

        @Column(name = "game_id")
        private Integer game_id;

        @Column(name = "invalid_count")
        private Integer invalid_count;

}