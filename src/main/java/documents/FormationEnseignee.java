package documents;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormationEnseignee extends EntiteAbstraite {
    public static final String NOM_COLLECTION = "formation_enseignee";
    private String nom;
    private String niveau;
    Integer nombrePlace;
}
