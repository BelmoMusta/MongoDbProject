package documents;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormationUtilisateur {
    public static final String NOM_COLLECTION = "formation_utilisateur";
    private String utilisateur;
    private String formation;
    private Integer anneeEntree;
    private Integer anneeSortie;
}
