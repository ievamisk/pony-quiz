package mylittlepony.rest;



import com.mongodb.BasicDBObject;

import mylittlepony.Service.AnswerService;
import mylittlepony.Service.PonyService;
import mylittlepony.Service.QuestionService;

import mylittlepony.Service.ValidateService;
import mylittlepony.domain.Answer;
import mylittlepony.domain.Pony;
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
public class PonyResource {

    private PonyService ponyService = new PonyService();
    private QuestionService questionService = new QuestionService();
    private AnswerService answerService = new AnswerService();
    private ValidateService validateService = new ValidateService();

    /**
     * insert pony
     * @param request data for pony insert
     * @return all ponies
     */
    @POST("/pony")
    @PermitAll
    public BasicDBObject insertPonyAndAnswer(Document request){
        JSONObject jsonAnswer;
        Answer answer;
        try {
            if(validateService.requestHaveEmptyValues(request)!=true){
                JSONObject rootObject = new JSONObject(request);
                JSONArray answers = rootObject.getJSONArray("answers");
                Pony pony = new Pony();
                pony.setName(rootObject.getString("name"));
                pony.setDescription(rootObject.getString("description"));
                pony.setImage(rootObject.getString("image"));
                ponyService.insertPony(pony);

                for(int i=0; i<answers.length(); i++){
                    jsonAnswer = answers.getJSONObject(i);
                    answer = new Answer();
                    answer.setPonyId(pony.get_id());
                    answer.setQuestionId(jsonAnswer.getString("questionId"));
                    answer.setAnswer(jsonAnswer.getString("answer"));
                    answerService.insertAnswer(answer);
                }

            } else
            {
                return new BasicDBObject("error", "some fields are empty");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ponyService.allPonies();
    }

    /**
     * get data to fill pony insert form
     * @return questions for pony insert form
     */
    @GET("/pony/questions")
    @PermitAll
    public BasicDBObject getQuestionsForInsertPonyForm()
    {
        BasicDBObject result = new BasicDBObject();
        List<BasicDBObject> questionList = new ArrayList<>();
        BasicDBObject questionObject;
        for (Document document : questionService.allQuestionsDocument())
        {
            questionObject = new BasicDBObject();
            questionObject.put("_id", document.get("_id"));
            questionObject.put("question", document.get("question"));
            questionList.add(questionObject);
        }
        result.put("questions",questionList);
        return result;
    }


    /**
     * get data to fill update pony form
     * @param id pony id
     * @return pony data
     */
    @GET("/question/pony/{id}")
    @PermitAll
    public BasicDBObject getFullInfoRelatedToPony (String id){
        BasicDBObject result = new BasicDBObject(ponyService.getPonyById(id));
        List<BasicDBObject> questionList = new ArrayList<>();
        BasicDBObject answerObject;
        BasicDBObject questionObject;
        Document answerDoc;
        for (Document document : questionService.allQuestionsDocument()) {
            answerObject = new BasicDBObject();
            questionObject = new BasicDBObject();
            answerDoc = answerService.getAnswersByPonyIdAndQuestionId(id,
                    (String) document.get("_id"));
            if(answerDoc!=null){
                answerObject.put("_id",answerDoc.get("_id"));
                answerObject.put("answer",answerDoc.get("answer"));
            }else {
                answerObject.append("answer","N/A");
            }
            questionObject.put("_id",document.get("_id"));
            questionObject.put("question", document.get("question"));
            questionObject.put("answer", answerObject);
            questionList.add(questionObject);
        }
        result.put("questions",questionList);
        return result;
    }


    /**
     * Update pony
     * @param request data about pony
     * @return all ponies
     */
    @PUT("/pony")
    @PermitAll
    public BasicDBObject updatePonyWithQuestions (Document request)
    {
        JSONObject jsonAnswer;
        Answer answer;

        try {
            if(validateService.requestHaveEmptyValues(request)!=true)
            {
                JSONObject rootObject = new JSONObject(request);
                JSONArray answers = rootObject.getJSONArray("answers");
                Pony pony = new Pony();
                pony.set_id(rootObject.getString("_id"));
                pony.setName(rootObject.getString("name"));
                pony.setDescription(rootObject.getString("description"));
                pony.setImage(rootObject.getString("image"));
                ponyService.updatePony(pony);
                for(int i=0; i<answers.length(); i++){
                    jsonAnswer = answers.getJSONObject(i);
                    answer = new Answer();
                    answer.set_id(jsonAnswer.getString("_id"));
                    answer.setPonyId(pony.get_id());
                    answer.setQuestionId(jsonAnswer.getString("questionId"));
                    answer.setAnswer(jsonAnswer.getString("answer"));
                    answerService.updateAnswer(answer);
                }
            }else {
                return new BasicDBObject("error", "some fields are empty");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ponyService.allPonies();
    }

    /**
     * delete existing pony
     * @param id pony id
     * @return all ponies
     */
    @DELETE("/pony/{id}")
    @PermitAll
    public BasicDBObject deletePonyAndAnswer (String id){
        ponyService.deletePony(id);
        answerService.deleteAnswersByPonyID(id);
        return ponyService.allPonies();
    }

    @GET("/ponies")
    @PermitAll
    public BasicDBObject allPonies(){
        return ponyService.allPonies();
    }



}
