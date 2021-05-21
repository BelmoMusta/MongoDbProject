package query;

import documents.Oeuvre;
import documents.Utilisateur;

public class UtilisateurQuery extends AbstractQuery<Utilisateur> {
    @Override
    protected String getNomCollection() {
        return Utilisateur.NOM_COLLECTION;
    }

    @Override
    protected Class<Utilisateur> getaClass() {
        return Utilisateur.class;
    }
}
