package mylittlepony.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import com.mongodb.client.FindIterable;
import mylittlepony.domain.Question;
import mylittlepony.mongodb.MongoDB;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuestionService {

    public boolean questionExists(String id){
        FindIterable <Document> result = MongoDB.getCollectionQuestion()
                .find( new Document("_id", new Document("$eq",id)));
        return result.first() != null;
    }
    public void insertQuestion(Question question){
        try {
            MongoDB.getCollectionQuestion().insertOne(question.intoDocument(
                    MongoDB.getNextSequence("questions")
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BasicDBObject getQuestionsIds(){
        QuestionService questionService = new QuestionService();
        String allQuestionsStr = questionService.allQuestions().toJson();
        BasicDBObject questionObject;
        BasicDBObject result = new BasicDBObject();
        List<BasicDBObject> questionObjectsList = new ArrayList<>();
        try {
            JSONObject allQuestionsJson = new JSONObject(allQuestionsStr);
            JSONObject currQuestionElement;
            JSONArray questionsArray = allQuestionsJson.getJSONArray("questions");
            JSONArray questionsIDs = new JSONArray();
            String questionId;
            for(int i = 0; questionsArray.length() > i; i++){
                currQuestionElement = (JSONObject) questionsArray.get(i);
                questionId = currQuestionElement.getString("_id");
                questionObject = new BasicDBObject();
                questionObject.put("_id",questionId);
                questionObjectsList.add(questionObject);
            }
            result.put("questionsIds",questionObjectsList);
            return result;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public BasicDBObject allQuestions(){
        FindIterable<Document> questions = MongoDB.getCollectionQuestion().find();
        BasicDBObject results = new BasicDBObject();
        List<BasicDBObject> questionList = new ArrayList<>();
        for (Document document : questions)
        {
            questionList.add(new BasicDBObject(document));
        }
        results.put("questions",questionList);
        return results;
    }

    public void deleteQuestionById(String id){
        if (questionExists(id)){
            MongoDB.getCollectionQuestion().findOneAndDelete(new Document("_id", new Document("$eq",id)));
        }
    }


    public FindIterable<Document> allQuestionsDocument() {
        FindIterable<Document> questions = MongoDB.getCollectionQuestion().find();
        return questions;
    }

    public void updateQuestion(Question question) {
        if(questionExists(question.get_id())){
            MongoDB.getCollectionQuestion().replaceOne(getQuestionById(question.get_id()),question.intoDocumentUpdate());
        }
    }

    public Document getQuestionById(String id) {
        Document query = new Document("_id", new Document("$eq",id));
        FindIterable<Document> documents = MongoDB.getCollectionQuestion().find(query).limit(1);
        return documents.first();
    }
}
