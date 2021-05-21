package documents;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Utilisateur extends EntiteAbstraite {
    public static final String NOM_COLLECTION = "utilisateur";
    public static final String ADMIN = "4";

    private String username;
    private String password;
    private String nom;
    private String prenom;
    private List<String> roles;

    public boolean estAdmin(){
        return roles!= null && roles.contains(ADMIN);
    }
}
