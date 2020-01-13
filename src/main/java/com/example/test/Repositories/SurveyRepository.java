package com.example.test.Repositories;

import com.example.test.Model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey,Long> {


    public List<Survey> findAllByOrderById();

    @Query("SELECT s FROM Survey s WHERE s.start < ?1 ORDER BY s.start ASC")
    public List<Survey> findAllByStartBeforeOrderByStartAsc(LocalDateTime startBefore);

    @Query("SELECT s FROM Survey s WHERE s.start < ?1 ORDER BY s.start DESC")
    public List<Survey> findAllByStartBeforeOrderByStartDesc(LocalDateTime startBefore);


    @Query("SELECT s FROM Survey s WHERE s.start > ?1 OR s.start = ?1 ORDER BY s.start ASC")
    public List<Survey> findAllByStartAfterOrderByStartAsc(LocalDateTime startAfter);

    @Query("SELECT s FROM Survey s WHERE s.start > ?1 OR s.start = ?1 ORDER BY s.start DESC")
    public List<Survey> findAllByStartAfterOrderByStartDesc(LocalDateTime startAfter);

    @Query("SELECT s FROM Survey s WHERE s.finish < ?1 ORDER BY s.start ASC")
    public List<Survey> findAllByFinishBeforeOrderByStartAsc(LocalDateTime finishBefore);

    @Query("SELECT s FROM Survey s WHERE s.finish < ?1 ORDER BY s.start DESC")
    public List<Survey> findAllByFinishBeforeOrderByStartDesc(LocalDateTime finishBefore);

    @Query("SELECT s FROM Survey s WHERE s.finish > ?1 OR s.finish = ?1 ORDER BY s.start ASC")
    public List<Survey> findAllByFinishAfterOrderByStartAsc(LocalDateTime finishAfter);

    @Query("SELECT s FROM Survey s WHERE s.finish > ?1 OR s.finish = ?1 ORDER BY s.start DESC")
    public List<Survey> findAllByFinishAfterOrderByStartDesc(LocalDateTime finishAfter);

    @Query("SELECT s FROM Survey s WHERE s.activity=?1 order by s.id ASC")
    public List<Survey> findAllByActivityOrderByIdAsc(Boolean activity);

    @Query("SELECT s FROM Survey s WHERE s.activity=?1 order by s.id desc")
    public List<Survey> findAllByActivityOrderByIdDesc(Boolean activity);

    @Query("SELECT s From Survey s Where s.title LIKE %?1% order by s.title ASC")
    public List<Survey> findAllByTitleOrderByTitleAsc(String title);

    @Query("SELECT s From Survey s Where s.title LIKE %?1% order by s.title desc")
    public List<Survey> findAllByTitleOrderByTitleDesc(String title);





}