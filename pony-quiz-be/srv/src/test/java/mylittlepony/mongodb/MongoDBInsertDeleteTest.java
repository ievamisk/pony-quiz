package mylittlepony.mongodb;

import com.mongodb.*;
import org.bson.Document;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;


public class MongoDBInsertDeleteTest {
    private DB database;
    private DBCollection collection;

    @Test
    public void shouldBeAbleToSaveAPonny() throws UnknownHostException {
        // Given
        Document document = new Document();
        document.append("_id","1")
                .append("name","name")
                .append("description", "description")
                .append("image","image");

        // When
        collection.insert(new BasicDBObject(document));

        // Then
        Assert.assertEquals(collection.find().count(), 1);


        database.dropDatabase();
    }

    @Test
    public void shouldDeleteSecondPonnyFromTheDatabase() {
         // Given
        Document document = new Document();
        document.append("_id","12")
                .append("name","name1")
                .append("description", "description1")
                .append("image","image1");

        Document document2 = new Document();
        document2.append("_id","2")
                .append("name","name2")
                .append("description", "description2")
                .append("image","image2");

        // When
        collection.insert(new BasicDBObject(document));
        collection.insert(new BasicDBObject(document2));

        BasicDBObject query = new BasicDBObject();
        query.append("name", "name2");
        collection.remove(query);

        // Then
        Assert.assertEquals(collection.find().count(), 1);

        database.dropDatabase();
    }

    @Before
    public void setUp() throws UnknownHostException {

        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        database = mongoClient.getDB("example");
        collection = database.getCollection("ponnies");
    }

    @After
    public void tearDown() {
        database.dropDatabase();
    }
}
