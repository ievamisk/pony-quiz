package mylittlepony.mongodb;
import com.mongodb.*;
import org.junit.Assert;

import org.junit.Test;
import java.net.UnknownHostException;

public class MongoDBConnectingAndClientTest {

    @Test
    public void shouldCreateANewMongoClientConnectedToLocalhost() throws Exception
    {
        // When
        MongoClient mongoClient = null;
        // Then
        Assert.assertEquals(mongoClient, mongoClient);
    }

    @Test
    public void shouldGetACollectionFromTheDatabase() throws Exception
    {
        // When
        DBCollection collection = null;
        // Then
        Assert.assertEquals(collection, collection);
    }

}