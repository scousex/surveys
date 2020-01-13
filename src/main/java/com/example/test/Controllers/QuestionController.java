package com.example.test.Controllers;

        import com.example.test.Model.Question;
        import com.example.test.Repositories.QuestionRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import javax.xml.ws.Response;
        import java.sql.SQLException;
        import java.util.List;

@RestController
public class QuestionController {

    private QuestionRepository questionRepository;

    @Autowired
    public QuestionController(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    @GetMapping("questions")
    public List<Question> getAllQuestions(@RequestParam(value = "surveyid", required = true) Long surveyId){
        return questionRepository.findAllBySurveyIdOrderByIdAsc(surveyId);
    }

    @PostMapping("questions")
    public ResponseEntity<Object> addQuestion(@RequestBody Question question){

        try {
            questionRepository.save(question);
            return new ResponseEntity("Question saved",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error saving question\n" + e.getMessage(), HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @PutMapping("questions")
    public ResponseEntity<Object> editQuestion(@RequestBody Question question){
        try {
            /*If surveyId for editable question is not specified, call surveyId from DB*/
            if(question.getSurveyId()!=null){
                question.setSurveyId(
                        questionRepository.getOne(question.getId()).getSurveyId());
            }
            questionRepository.save(question);
            return new ResponseEntity("Question saved",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error saving question\n" + e.getMessage(), HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("questions")
    public ResponseEntity<Object> removeQuestion(@RequestParam Long id){
        try {
            questionRepository.deleteById(id);
            return new ResponseEntity("Question deleted",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Error deleting question\n" + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
