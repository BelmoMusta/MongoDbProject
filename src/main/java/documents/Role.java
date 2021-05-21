package documents;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role extends EntiteAbstraite {
    public static final String NOM_COLLECTION = "role";
    private String nom;
}
