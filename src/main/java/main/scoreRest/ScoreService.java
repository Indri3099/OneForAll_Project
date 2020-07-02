package main.scoreRest;

import com.google.gson.Gson;
import entities.Score;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("score")
public class ScoreService {

    @PUT
    @Path("/add")
    @Consumes("application/json")
    @Produces("application/json")
    public Response add(String json) {
        Gson gson = new Gson();
        Score score = gson.fromJson(json, Score.class);
        DBManager db = DBManager.getInstance();

        try {
            db.insertScore(score);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Response.ok().build();
    }

    @GET
    @Path("/get/{scoreid}")
    @Produces("application/json")
    public Response item(@PathParam("scoreid") Integer scoreid) {
        Score score;
        try {
            score = DBManager.getInstance().getScore(scoreid);
        } catch (SQLException ex) {
            return Response.serverError().build();
        }
        if (score != null) {
            Gson gson = new Gson();
            String jsonString = gson.toJson(score);
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.serverError().build();
        }
    }
}
