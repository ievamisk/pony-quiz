package mylittlepony.rest;


import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import mylittlepony.Service.PonyService;
import mylittlepony.domain.Pony;
import org.bson.Document;
import org.springframework.web.bind.annotation.CrossOrigin;
import restx.annotations.DELETE;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

@PermitAll
@Component
@RestxResource
public class BasicPonyResource {

//    private PonyService ponyService= new PonyService();;
//
//    @POST("/pony")
//    @PermitAll
//    public Pony insertPony (Pony pony){
//        return ponyService.insertPony(pony);
//    }


//    @GET("/ponies/{id}")
//    @PermitAll
//    public Document getPony(String id){
//        return ponyService.getPonyById(id);
//    }
//
//    @DELETE("/ponies/{id}")
//    @PermitAll
//    public BasicDBObject deletePony (String id){
//        ponyService.deletePony(id);
//        return ponyService.allPonies();
//    }
//
//    @POST("/updatePony")
//    @PermitAll
//    public BasicDBObject update (Pony pony){
//        ponyService.updatePony(pony);
//        return ponyService.allPonies();
//    }

}
