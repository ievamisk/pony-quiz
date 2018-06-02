package mylittlepony.rest;

import mylittlepony.Service.SubmitService;
import org.bson.Document;
import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

@Component
@RestxResource
public class SubmitResource {
    private SubmitService submitServcice = new SubmitService();

    @POST("/submit")
    @PermitAll
    public Document calculateResults(Document results){
        Document winnerPony = submitServcice.CalculateTestResults(results);
        return winnerPony;
    }

}
