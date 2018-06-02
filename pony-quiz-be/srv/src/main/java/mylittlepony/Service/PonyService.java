package mylittlepony.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import mylittlepony.domain.Pony;
import mylittlepony.mongodb.MongoDB;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class PonyService {

    public boolean ponyExists(String id){
        FindIterable <Document> result = MongoDB.getCollectionPonies()
                .find( new Document("_id", new Document("$eq",id)));
        return result.first() != null;
    }

    public Pony insertPony (Pony pony){
        try {
            MongoDB.getCollectionPonies().insertOne(pony.intoDocument(
                    MongoDB.getNextSequence("ponies")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pony;
    }

    public BasicDBObject allPonies(){
        BasicDBObject result = new BasicDBObject();
        BasicDBObject ponyObject;
        List<BasicDBObject> ponyList = new ArrayList<>();
        FindIterable<Document> allPonies = MongoDB.getCollectionPonies().find();
        for(Document document : allPonies){
            ponyObject = new BasicDBObject(document);
            ponyList.add(ponyObject);
        }
        result.put("ponies", ponyList);
        return result;
    }

    public Document getPonyById(String id){
        Document result;
        Document query = new Document("_id", new Document("$eq",id));
        FindIterable<Document> documents = MongoDB.getCollectionPonies().find(query).limit(1);
        result = new Document(documents.first());
        return result;
    }

    public void deletePony(String id){
        if(ponyExists(id)){
            MongoDB.getCollectionPonies().findOneAndDelete(new Document("_id", new Document("$eq",id)));
        }
    }

    public void updatePony(Pony pony){
        Document result;
        if(ponyExists(pony.get_id())){
            result = pony.intoDocumentUpdate();
            System.out.println(result);
            MongoDB.getCollectionPonies().replaceOne(getPonyById(pony.get_id()),result);
        }
    }
}
