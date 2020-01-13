package com.example.test.Repositories;

import com.example.test.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findAllBySurveyIdOrderByIdAsc(Long surveyId);
}
