package com.example.test.Controllers.RequestBodies;



public class SurveyBody {

    private Long id;


    private String title;


    private String start;


    private String finish;



    private Boolean activity;

    public Long getId() {
        return id;
    }

    public Boolean getActivity() {
        return activity;
    }

    public String getFinish() {
        return finish;
    }

    public String getTitle() {
        return title;
    }

    public String getStart() {
        return start;
    }

    public void setActivity(Boolean activity) {
        this.activity = activity;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
