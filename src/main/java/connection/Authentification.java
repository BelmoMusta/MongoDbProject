package connection;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import contexte.ContextApplicatif;
import documents.Utilisateur;
import filters.UtilisateurAuthFilter;
import org.bson.conversions.Bson;


public class Authentification {
    public static Utilisateur authentifier(String username, String password) {
        final MongoDatabase mongoDatabase = Connection.connectToDataBase();
        MongoCollection<Utilisateur> utilisateur = mongoDatabase.getCollection(Utilisateur.NOM_COLLECTION, Utilisateur.class);
        Bson userFilter = UtilisateurAuthFilter.userFilter(username, password);
        MongoCursor<Utilisateur> cursor = utilisateur.find(userFilter).cursor();
        if (cursor.hasNext()) {
            Utilisateur utilisateurConnecte = cursor.next();
            ContextApplicatif.UTILISATEUR_CONNECTE = utilisateurConnecte;
            return utilisateurConnecte;
        }
        return null;
    }

    public static void deconnecter() {
        ContextApplicatif.UTILISATEUR_CONNECTE = null;
    }

    public static boolean exisiteUserConnecte() {
        return ContextApplicatif.UTILISATEUR_CONNECTE != null;
    }

}
