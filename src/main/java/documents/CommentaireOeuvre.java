package documents;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentaireOeuvre extends EntiteAbstraite {
    public static final String NOM_COLLECTION = "commentaire_oeuvre";
    private String oeuvre;
    private Integer note;
    private List<String> commentaire;
    private String utilisateur; // auteur du commentaire
}
