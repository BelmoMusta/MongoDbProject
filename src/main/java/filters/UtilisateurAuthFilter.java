package filters;

import lombok.Getter;
import lombok.Setter;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;


@Getter
@Setter
public class UtilisateurAuthFilter {

    public static Bson userFilter(String username, String password) {
        return and(eq("username", username), eq("password", password));
    }
}
