package mylittlepony.rest;


import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import mylittlepony.Service.AnswerService;
import mylittlepony.domain.Answer;
import org.bson.Document;
import org.springframework.web.bind.annotation.CrossOrigin;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

@Component
@RestxResource
public class BasicAnswerResource {

    private AnswerService answerServcice = new AnswerService();


//    @POST("/answer")
//    @PermitAll
//    public BasicDBObject insertAnswer(Answer answer) {
//        answerServcice.insertAnswer(answer);
//        return answerServcice.allAnswers();
//    }
//
//    @GET("/answers")
//    @PermitAll
//    public BasicDBObject allAnswers()
//    {
//        return answerServcice.allAnswers();
//    }
}
