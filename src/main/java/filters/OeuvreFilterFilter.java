package filters;

import lombok.Getter;
import lombok.Setter;
import org.bson.conversions.Bson;

import java.util.Date;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;


@Getter
@Setter
public class OeuvreFilterFilter {

    public static Bson oeuvreParTitre(String titreOeuvre) {
        return eq("titre", titreOeuvre);
    }
    public static Bson oeuvreParTitreEtDatePublication(String titreOeuvre, Date datePublication) {
        return and(eq("titre", titreOeuvre), eq("publication", datePublication));
    }

    public static Bson oeuvreParTheme(String theme) {
        return eq("theme", theme);
    }

    public static Bson oeuvreParContenu(String motContenu) {
        return regex("contenu", ".*" + Pattern.quote(motContenu) + ".*");
    }
}
