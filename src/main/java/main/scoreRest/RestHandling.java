package main.scoreRest;

import com.google.gson.Gson;
import entities.Score;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Questa classe mette a disposizione dell'utente due metodi statici pubblici che consentono di ottenere la classifica dei punteggi per un gioco
 * oppure di salvare un nuovo punteggio.<br>
 * Come si può notare questa è solo una simulazione di un servizio di API REST, in realtà il server dovrebbe essere sempre attivo e pronto a ricevere delle richieste
 * ma in questo caso, il server "parte" ogni volta che si richiede una delle due operazioni sopra citate e, il server viene "spento" non appena la richiesta è stata soddisfatta.
 */
public class RestHandling {
    public static void saveScore(Score score) {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(4321).build();
        ResourceConfig config = new ResourceConfig(ScoreService.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        try {
            server.start();
            requestOfAdd(score);
            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Score> getScore() {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(4321).build();
        ResourceConfig config = new ResourceConfig(ScoreService.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, config);
        List<Score> scores = new ArrayList<>();
        Score tempScore;
        int i = 1;
        try {
            server.start();
            while ((tempScore = requestOfGet(i++)) != null) {
                scores.add(tempScore);
            }
            server.shutdown();
            return scores.stream().sorted().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

    private static void requestOfAdd(Score score) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:4321");

        Gson gson = new Gson();
        Response resp = target.path("score/add").request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(gson.toJson(score), MediaType.APPLICATION_JSON));

    }

    private static Score requestOfGet(int id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:4321");

        Response resp = target.path("score/get/" + id).request(MediaType.APPLICATION_JSON).get();
        Score score = null;
        String scoreStr = (resp.readEntity(String.class));
        if (scoreStr != null && !scoreStr.equals("")) {
            score = new Gson().fromJson(scoreStr, Score.class);
        }
        return score;
    }
}
