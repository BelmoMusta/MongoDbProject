package documents;

import lombok.Getter;
import lombok.Setter;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWrapper;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

@Getter
@Setter
public class UtilisateurBsonAuth implements Bson {
    private String username;
    private String password;

    @Override
    public <T> BsonDocument toBsonDocument(Class<T> tDocumentClass, CodecRegistry codecRegistry) {
        return new BsonDocumentWrapper<>(this, codecRegistry.get(UtilisateurBsonAuth.class));
    }
}
