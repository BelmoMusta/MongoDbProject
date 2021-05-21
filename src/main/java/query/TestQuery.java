package query;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import documents.Oeuvre;
import documents.Utilisateur;

import java.util.Arrays;

public class TestQuery {
    public static void main(String[] args) {
        select();
        /*UtilisateurQuery query = new UtilisateurQuery();
        Utilisateur utilisateur= new Utilisateur();
        utilisateur.setNom("Nom");
        utilisateur.setPassword("test");
        utilisateur.setRoles(Arrays.asList("1"));
        query.insert(utilisateur);*/
    }

    private static void select() {
        OeuvreQuery query = new OeuvreQuery();
        MongoCollection<Oeuvre> collection = query.getCollection();
        FindIterable<Oeuvre> oeuvres = collection.find();
        MongoCursor<Oeuvre> cursor = oeuvres.cursor();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }
}
