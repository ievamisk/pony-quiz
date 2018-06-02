package mylittlepony.Service;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ValidateService {

    public Boolean requestHaveEmptyValues(Document request){
        JSONObject rootObject = new JSONObject(request);
        String name;
        try {
            JSONArray jsonArray = rootObject.getJSONArray("answers");
            for(int i=0; i<rootObject.names().length(); i++)
            {
                name=rootObject.names().getString(i);
                if(rootObject.getString(name)==null || rootObject.getString(name).isEmpty()){
                    return true;
                }

            }
            for (int i=0; i<jsonArray.length(); i++){
                for (int j=0; j<jsonArray.getJSONObject(i).names().length(); j++){
                    name = jsonArray.getJSONObject(i).names().getString(j);
                    if(jsonArray.getJSONObject(i).getString(name)==null ||
                            jsonArray.getJSONObject(i).getString(name).isEmpty()){
                        return true;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
