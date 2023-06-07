package com.example.screeningtest.repository;

import com.example.screeningtest.modal.QuestionModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionModal, Integer> {


}
