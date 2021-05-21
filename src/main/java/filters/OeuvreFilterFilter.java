package filters;

import lombok.Getter;
import lombok.Setter;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;


@Getter
@Setter
public class OeuvreFilterFilter {

    public static Bson oeuvre(String titreOeuvre) {
        return eq("titre", titreOeuvre);
    }
}
