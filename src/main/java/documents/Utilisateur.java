package documents;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Utilisateur extends EntiteAbstraite {
    public static final String NOM_COLLECTION = "utilisateur";
    private String username;
    private String password;
    private String nom;
    private String prenom;
    private List<String> roles;
}
