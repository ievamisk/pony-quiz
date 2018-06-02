
package mylittlepony.Service;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SubmitService {

    private PonyService ponyService= new PonyService();
    AnswerService answerServcice = new AnswerService();
    HashMap<Integer,Integer> ponyHashList;


    public Document CalculateTestResults(Document results){

        ponyHashList = new HashMap<>();
        String jsonResultsString = results.toJson();
        JSONObject jsonResultsObject,currentObject;
        JSONArray jsonResultsArray;
        int answerId,ponyId;
        Document answerDoc;
        try{
            jsonResultsObject = new JSONObject(jsonResultsString);
            jsonResultsArray = jsonResultsObject.getJSONArray("results");
            for(int i = 0; i < jsonResultsArray.length();i++){
                currentObject = (JSONObject) jsonResultsArray.get(i);
                answerId = Integer.parseInt(currentObject.getString("answerId"));
                answerDoc = GetAnswerDocByID( answerId);
                ponyId = GetPonyIdFromDoc(answerDoc);
                SetUpHashMap(ponyId);
            }
            Document winnerPony = CheckPonyWinner();
            return winnerPony;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public Document GetAnswerDocByID(int answerID){
        String id = Integer.toString(answerID);
        Document answerDocument = answerServcice.getAnswerById(id);
        return answerDocument;
    }

    public int GetPonyIdFromDoc(Document answerDoc){
        String answerStrJson = answerDoc.toJson();
        int ponyId;
        try{
            JSONObject answerJSONObj = new JSONObject(answerStrJson);
            ponyId = Integer.parseInt(answerJSONObj.getString("ponyId"));
            return ponyId;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return 0;
    }



    public void SetUpHashMap(int ponyId){

        if(ponyHashList.size() == 0){
            ponyHashList.put(ponyId,1);
        }else {
            try{
                int counter = ponyHashList.get(ponyId);
                ponyHashList.put(ponyId,counter + 1);
            }catch (Exception ex){
                ponyHashList.put(ponyId,1);
            }
        }
    }

    public Document CheckPonyWinner(){
        int maxValueInMap=(Collections.max(ponyHashList.values()));
        String ponyWinnerID = null;
        for (Map.Entry<Integer, Integer> entry : ponyHashList.entrySet()) {  // Itrate through hashmap
            if (entry.getValue()==maxValueInMap) {
                ponyWinnerID = entry.getKey().toString();     // Print the key with max value
                break;
            }
        }
        Document ponyWinner = ponyService.getPonyById(ponyWinnerID);
        return ponyWinner;
    }
}

