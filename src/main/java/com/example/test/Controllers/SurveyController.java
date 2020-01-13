package com.example.test.Controllers;


import com.example.test.Controllers.RequestBodies.SurveyBody;
import com.example.test.Model.Survey;
import com.example.test.Repositories.SurveyRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class SurveyController  {

    private SurveyRepository surveyRepository;

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    public SurveyController(SurveyRepository surveyRepository){
        this.surveyRepository = surveyRepository;
    }

    @GetMapping("surveys")
    public List<Survey> getSurveys(){
        return surveyRepository.findAll();
    }

    /**
     * @return List of surveys that has param 'start' before date in request **/
    @GetMapping(value = "surveys", params = {"startBefore","sort"})
    public List<Survey> getAllSurveysStartedBefore(@RequestParam("startBefore") String startBefore,
                                                     @RequestParam("sort")String sort){

        if(sort.equals("ASC")) return surveyRepository.findAllByStartBeforeOrderByStartAsc(
                                                                LocalDateTime.parse(startBefore,dateFormat));

        return surveyRepository.findAllByStartBeforeOrderByStartDesc(LocalDateTime.parse(startBefore,dateFormat));

    }

    /**
     * @return List of surveys that has param 'start' after date in request **/
    @GetMapping(value = "surveys", params = {"startAfter","sort"})
    public List<Survey> getAllSurveysStartedAfter(@RequestParam("startAfter") String startAfter,
                                               @RequestParam("sort")String sort){

        if(sort.equals("ASC")) return surveyRepository.findAllByStartAfterOrderByStartAsc(LocalDateTime.parse(startAfter,dateFormat));

        return surveyRepository.findAllByStartAfterOrderByStartDesc(LocalDateTime.parse(startAfter,dateFormat));

    }

    /**
     * @return List of surveys that has param 'finish' before date in request **/
    @GetMapping(value = "surveys", params = {"endBefore","sort"})
    public List<Survey> getAllSurveysFinishedBefore(@RequestParam("endBefore") String endBefore,
                                                  @RequestParam("sort")String sort){

        if(sort.equals("ASC")) return surveyRepository.findAllByFinishBeforeOrderByStartAsc(LocalDateTime.parse(endBefore,dateFormat));

        return surveyRepository.findAllByFinishBeforeOrderByStartDesc(LocalDateTime.parse(endBefore,dateFormat));

    }

    /**
     * @return List of surveys that has param 'finish' after date in request **/
    @GetMapping(value = "surveys", params = {"endAfter","sort"})
    public List<Survey> getAllSurveysFinishedAfter(@RequestParam("endAfter") String endAfter,
                                                    @RequestParam("sort")String sort){

        if(sort.equals("ASC")) return surveyRepository.findAllByFinishAfterOrderByStartAsc(LocalDateTime.parse(endAfter,dateFormat));

        return surveyRepository.findAllByFinishAfterOrderByStartDesc(LocalDateTime.parse(endAfter,dateFormat));

    }


    /**
     * @return List of surveys that title has phrase from request  **/
    @GetMapping(value = "surveys", params = {"title","sort"})
    public List<Survey> getAllSurveysByTitle(@RequestParam("title") String title,
                                             @RequestParam("sort")String sort){

        if(sort.equals("ASC")) return surveyRepository.findAllByTitleOrderByTitleAsc(title);

        return surveyRepository.findAllByTitleOrderByTitleDesc(title);

    }

    /**
     * @return List of surveys that has param 'activity' is equals param in request **/
    @GetMapping(value = "surveys", params = "activity")
    public List<Survey> getAllSurveysHasActivity(@RequestParam("activity") Boolean activity,
                                                 @RequestParam("sort")String sort){
              if(sort.equals("ASC")) return surveyRepository.findAllByActivityOrderByIdAsc(activity);

              return surveyRepository.findAllByActivityOrderByIdDesc(activity);

    }

    @PostMapping(value = "surveys")
    public ResponseEntity<Object> addSurvey(@RequestBody SurveyBody surveyBody){


        Survey survey = new Survey();

      if(surveyBody.getTitle()!=null){

          survey.setTitle(surveyBody.getTitle());

          survey.setStart(LocalDateTime.parse(surveyBody.getStart(),dateFormat));

          survey.setFinish(LocalDateTime.parse(surveyBody.getFinish(),dateFormat));

          survey.setActivity(surveyBody.getActivity());

          surveyRepository.save(survey);

          return new ResponseEntity<>("Survey created", HttpStatus.OK);
      }

      else return new ResponseEntity<>("Title must not be null", HttpStatus.NOT_IMPLEMENTED);

    }

    @PutMapping("surveys")
    public ResponseEntity<Object> editSurvey(@RequestBody SurveyBody surveyBody){

        Survey survey = surveyRepository.getOne(surveyBody.getId());

        if(survey!=null){

            if(surveyBody.getStart()!=null)
                survey.setStart(LocalDateTime.parse(surveyBody.getStart(),dateFormat));

            if(surveyBody.getFinish()!=null)
                survey.setFinish(LocalDateTime.parse(surveyBody.getFinish(),dateFormat));

            surveyRepository.save(survey);
            return new ResponseEntity<>("Survey was updated", HttpStatus.OK);
        }
        else return new ResponseEntity<>("Survey does not exists in DataBase",HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("surveys")
    public ResponseEntity<Object> deleteSurvey(@RequestParam("id") Long id){
        try{
            surveyRepository.deleteById(id);
        }catch (Exception e){
            return new ResponseEntity<>("Error deleting survey",HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Survey deleted",HttpStatus.OK);
    }

}
