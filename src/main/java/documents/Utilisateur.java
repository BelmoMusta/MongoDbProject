package documents;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Utilisateur {
    public static final String UTILISATEUR = "utilisateur";
    private String username;
    private String password;
    private List<String> roles;
}
