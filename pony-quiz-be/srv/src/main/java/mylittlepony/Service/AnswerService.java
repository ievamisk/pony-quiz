package mylittlepony.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import mylittlepony.domain.Answer;
import mylittlepony.mongodb.MongoDB;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import javax.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Projections.excludeId;

public class    AnswerService {

    public boolean answerExists(String id){
        FindIterable<Document> documents = MongoDB.getCollectionAnswer()
                .find( new Document("_id", new Document("$eq",id)));
        return documents.first() !=null;
    }

    public void insertAnswer(Answer answer){
        Document result = new Document();
        try {
            MongoDB.getCollectionAnswer().insertOne(answer.intoDocument(
                    MongoDB.getNextSequence("answers")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Document getAnswerById(String id) {
        Document query = new Document("_id", new Document("$eq",id));
        FindIterable<Document> documents = MongoDB.getCollectionAnswer().find(query).limit(1);
        return new Document(documents.first());
    }

    public BasicDBObject allAnswers(){
        BasicDBObject result = new BasicDBObject();
        BasicDBObject questionObject;
        List<BasicDBObject> questionList = new ArrayList<>();
        FindIterable<Document> allQuestion = MongoDB.getCollectionAnswer().find();
        for(Document document: allQuestion){
            questionObject = new BasicDBObject(document);
            questionList.add(questionObject);
        }
        result.put("question",questionList);
        return result;
    }

    public Document getAnswersByPonyIdAndQuestionId(String ponyId, String questionId){
        Document query = new Document();
        query.append("ponyId",  new Document("$eq",ponyId))
                .append("questionId", new Document("$eq",questionId));
        FindIterable<Document> result = MongoDB.getCollectionAnswer().find(query);
        return  result.first();
    }

    public void updateAnswer(Answer answer) {
        Document result;
        System.out.println(answerExists(answer.get_id()));
        if(answerExists(answer.get_id())){
            result = answer.intoDocumentUpdate();
            MongoDB.getCollectionAnswer().replaceOne(getAnswerById(answer.get_id()),result);
        }
    }

    public void deleteAnswersByPonyID(String id) {
        Document query = new Document("ponyId", new Document("$eq",id));
        FindIterable<Document> answersByPonyId = MongoDB.getCollectionAnswer().find(query);
        for(Document document : answersByPonyId){
            MongoDB.getCollectionAnswer().findOneAndDelete(document);
        }
    }

    public BasicDBObject getAnswersByQuestionId(String id) {
        Document query = new Document("questionId", new Document("$eq",id));
        BasicDBObject result = new BasicDBObject();
        List<Document> answerList = new ArrayList<>();
        for(Document document: MongoDB.getCollectionAnswer().find(query)){
            answerList.add(document);
        }
        result.put("answers",answerList);
        return result;
    }

    public void deleteAnswersByQuestionId(String id) {
        Document query = new Document("questionId", new Document("$eq",id));
        FindIterable<Document> answersByPonyId = MongoDB.getCollectionAnswer().find(query);
        for(Document document : answersByPonyId){
            MongoDB.getCollectionAnswer().findOneAndDelete(document);
        }

    }
}
