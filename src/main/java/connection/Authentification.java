package connection;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import documents.Utilisateur;
import org.bson.conversions.Bson;
import org.bson.json.JsonObject;

public class Authentification {
    public static void authentifier(String username, String password) {
        final MongoDatabase mongoDatabase = Connection.connectToDabase();
        MongoCollection<Utilisateur> utilisateur = mongoDatabase.getCollection(Utilisateur.UTILISATEUR, Utilisateur.class);
        Bson bson = new JsonObject(String.format("{\"username\":\"%s\",  \"password\":\"%s\"}", username, password));
        Utilisateur next = utilisateur.find(bson).cursor().next();
        System.out.println(next);


    }
}
