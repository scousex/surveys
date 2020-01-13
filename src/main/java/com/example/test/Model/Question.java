package com.example.test.Model;


import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "questions")
public class Question implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "survey_id")
    private Long surveyId;

    @Column(name = "text")
    private String text;

    public Long getId() {
        return id;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

}
