package mylittlepony.mongodb;

import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.sun.org.apache.xpath.internal.SourceTree;
import mylittlepony.domain.Counter;
import org.bson.Document;

public class MongoDB {

    static MongoClient mongoClient=new MongoClient( "Localhost" , 27017 );

    static MongoDatabase database = mongoClient.getDatabase("mylittlepony");

    private static MongoCollection<Document> getCollectionCounter(){return database.getCollection("counter");}

    public static MongoCollection<Document> getCollectionPonies() {
        return database.getCollection("ponies");
    }

    public static MongoCollection<Document> getCollectionQuestion() { return database.getCollection("question");};

    public static MongoCollection<Document> getCollectionAnswer() {return database.getCollection("answer");}

    public static String getNextSequence(String name) throws Exception {
        Document findQuery =new Document("_id", new Document("$eq",name));
        Document find = new Document(getCollectionCounter().find(findQuery).first());
        Document updateQuery = new Document("$inc",new Document("sequence",1));
        getCollectionCounter().updateOne(find,updateQuery);
        find = getCollectionCounter().find(findQuery).first();
        return String.valueOf(Math.round(find.getDouble("sequence")));
    }
}
