package documents;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Oeuvre extends EntiteAbstraite {
    public static final String NOM_COLLECTION = "oeuvre";
    private String titre;
    private List<String> auteurs;
    private Integer pages;
    private Date publication;
    private String theme;
    private List<String> universites;
    private List<String> formations;
    private String contenu;
    private List<String> roles;
}
