package mylittlepony.rest;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import mylittlepony.Service.AnswerService;
import mylittlepony.Service.PonyService;
import mylittlepony.Service.QuestionService;
import mylittlepony.Service.ValidateService;
import mylittlepony.domain.Answer;
import mylittlepony.domain.Question;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import restx.annotations.*;
import restx.factory.Component;
import restx.security.PermitAll;

import java.util.ArrayList;
import java.util.List;


@Component
@RestxResource
public class QuestionResource {

    private PonyService ponyService = new PonyService();
    private QuestionService questionService = new QuestionService();
    private AnswerService answerService = new AnswerService();
    private ValidateService validateService = new ValidateService();
    /**
     * fill question insert form
     * @return pony information to fill question insert from
     */
    @GET("/question/ponies")
    @PermitAll
    public BasicDBObject poniesForQuestionInsert ()
    {
        BasicDBObject result = new BasicDBObject();
        JSONObject pony;
        List<BasicDBObject> editedPonyList = new ArrayList<>();
        BasicDBObject ponyObject;
        try {
            JSONObject ponies = new JSONObject(ponyService.allPonies());
            JSONArray ponyArray = ponies.getJSONArray("ponies");
            for (int i=0; i<ponyArray.length(); i++){
                pony = ponyArray.getJSONObject(i);
                ponyObject = new BasicDBObject();
                ponyObject.put("_id",pony.getString("_id"));
                ponyObject.put("name", pony.getString("name"));
                editedPonyList.add(ponyObject);
            }
            result.put("ponies", editedPonyList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("Isvedimas " + result);
        return result;
    }

    /**
     * Insert new pony
     * @param request - json object with pony and answers information
     * @return all ponies in json format
     */
    @POST("/question")
    @PermitAll
    public BasicDBObject insertQuestionAndAnswers(Document request)
    {
        JSONObject rootObject = new JSONObject(request);
        JSONObject answer;
        Answer newAnswer;
        Question question = new Question();
        try {
            if(validateService.requestHaveEmptyValues(request)!=true) {
                JSONArray answerArray = rootObject.getJSONArray("answers");
                question.setQuestion(rootObject.getString("question"));
                question.setImage(rootObject.getString("image"));
                questionService.insertQuestion(question);
                for (int i = 0; i < answerArray.length(); i++) {
                    answer = answerArray.getJSONObject(i);
                    newAnswer = new Answer();
                    newAnswer.setAnswer(answer.getString("answer"));
                    newAnswer.setPonyId(answer.getString("ponyId"));
                    newAnswer.setQuestionId(question.get_id());
                    answerService.insertAnswer(newAnswer);
                }
            }
            else {
                return new BasicDBObject("error", "some fields are empty");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return questionService.allQuestions();
    }

    /**
     * update existing pony
     * @param request - json object with pony and answers information
     * @return all ponies in json format
     */
    @PUT("/question")
    @PermitAll
    public BasicDBObject updateQuestionsAndAnswers(Document request)
    {
        System.out.println("update " + request);
        JSONObject rootObject = new JSONObject(request);
        JSONObject answer;
        Answer updateAnswer;
        Question question = new Question();
        try {
            if(validateService.requestHaveEmptyValues(request)!=true) {
                JSONArray answerArray = rootObject.getJSONArray("answers");
                question.set_id(rootObject.getString("_id"));
                question.setQuestion(rootObject.getString("question"));
                question.setImage(rootObject.getString("image"));
                questionService.updateQuestion(question);
                for (int i = 0; i < answerArray.length(); i++) {
                    answer = answerArray.getJSONObject(i);
                    updateAnswer = new Answer();
                    updateAnswer.set_id(answer.getString("_id"));
                    updateAnswer.setPonyId(answer.getString("ponyId"));
                    updateAnswer.setQuestionId(question.get_id());
                    updateAnswer.setAnswer(answer.getString("answer"));
                    answerService.updateAnswer(updateAnswer);
                }
            } else {
                return new BasicDBObject("error", "some fields are empty");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return questionService.allQuestions();
    }

    /**
     * get data to fill update question form
     * @param id question id
     * @return question information
     */
    @GET("/question/{id}")
    @PermitAll
    public BasicDBObject fillQuestionUpdateForm(String id){
        BasicDBObject results = new BasicDBObject(questionService.getQuestionById(id));
        BasicDBObject answerAndPony;
        JSONObject rootObject = new JSONObject(answerService.getAnswersByQuestionId(id));
        Document pony;
        List<BasicDBObject> answerAndPonyList = new ArrayList<>();
        JSONObject answer;
        try {
            JSONArray answerArray = rootObject.getJSONArray("answers");
            for(int i=0; i<answerArray.length(); i++)
            {
                answerAndPony = new BasicDBObject();
                answer = answerArray.getJSONObject(i);
                pony = ponyService.getPonyById(answer.getString("ponyId"));
                answerAndPony.put("_id",answer.getString("_id"));
                answerAndPony.put("ponyId",answer.getString("ponyId"));
                answerAndPony.put("answer",answer.getString("answer"));
                answerAndPony.put("ponyName", pony.get("name"));
                answerAndPonyList.add(answerAndPony);
            }
            results.put("answerAndPony", answerAndPonyList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("-------");
        System.out.println(results);
        return results;
    }

    /**
     * get data for quiz form
     * @param id question id
     * @return return question for quiz
     */
    @GET("/quiz/question/{id}")
    @PermitAll
    public BasicDBObject getQuestionForQuiz(String id){
        BasicDBObject results = new BasicDBObject(questionService.getQuestionById(id));
        BasicDBObject answerObject;
        JSONObject rootObject = new JSONObject(answerService.getAnswersByQuestionId(id));
        List<BasicDBObject> answerList = new ArrayList<>();
        JSONObject answer;
        try {
            JSONArray answerArray = rootObject.getJSONArray("answers");
            for(int i=0; i<answerArray.length(); i++)
            {
                answerObject = new BasicDBObject();
                answer = answerArray.getJSONObject(i);
                answerObject.put("_id",answer.getString("_id"));
                answerObject.put("answer",answer.getString("answer"));

                answerList.add(answerObject);
            }
            results.put("answerAndPony", answerList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * delete question
     * @param id question id
     * @return all ponies
     */
    @DELETE("/question/{id}")
    @PermitAll
    public BasicDBObject deleteQuestionWithAnswerById (String id){
        questionService.deleteQuestionById(id);
        answerService.deleteAnswersByQuestionId(id);
        return questionService.allQuestions();
    }

    /**
     * all questions
     * @return all questions
     */
    @GET("/questions")
    @PermitAll
    public BasicDBObject allQuestions(){
        return questionService.allQuestions();
    }

    @GET("/questions/ids")
    @PermitAll
    public BasicDBObject getQuestionsIds (){
        BasicDBObject questionsIds = questionService.getQuestionsIds();
        return questionsIds;
    }

}
