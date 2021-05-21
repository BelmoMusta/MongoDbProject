package documents;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Universite extends EntiteAbstraite {
    public static final String NOM_COLLECTION = "universite";
    private String nom;
}
