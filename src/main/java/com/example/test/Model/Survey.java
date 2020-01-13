package com.example.test.Model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "surveys")
public class Survey {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;


    @Column(name = "start")
    private LocalDateTime start;


    @Column(name = "finish")
    private LocalDateTime finish;


    @Column(name = "activity")
    private Boolean activity;

    public Long getId() {
        return id;
    }

    public Boolean getActivity() {
        return activity;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setActivity(Boolean activity) {
        this.activity = activity;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
