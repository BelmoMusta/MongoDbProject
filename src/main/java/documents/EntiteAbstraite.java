package documents;

import lombok.Getter;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Getter
@Setter
public abstract class EntiteAbstraite {
    @BsonProperty("_id")
    @BsonId
    private ObjectId id;
    private String identifiant;
}
