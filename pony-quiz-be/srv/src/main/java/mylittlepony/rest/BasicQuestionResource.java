package mylittlepony.rest;


import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import mylittlepony.Service.QuestionService;
import mylittlepony.domain.Question;
import org.bson.Document;
import restx.annotations.DELETE;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

@Component
@RestxResource
public class BasicQuestionResource {

    private QuestionService questionService = new QuestionService();

//    @POST("/question")
//    @PermitAll
//    public BasicDBObject insertQuestion (Question question){
//        questionService.insertQuestion(question);
//        return questionService.allQuestions();
//    }





//    @DELETE("/question/{id}")
//    @PermitAll
//    public BasicDBObject deleteQuestionById(String id){
//        questionService.deleteQuestionById(id);
//        return questionService.allQuestions();
//    }



}
