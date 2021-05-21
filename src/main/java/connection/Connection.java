package connection;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class Connection {
    public static final String CONNECTION_STRING = "mongodb://localhost:27017/?readPreference=primary&appname=MongoDB%20Compass&ssl=false";
    public static final String DATABASE_NAME = "oeuvres_universitaires";
    private static MongoDatabase DATABASE;

    public static MongoDatabase connectToDataBase() {
        if (DATABASE == null) {
            final ConnectionString connString = new ConnectionString(CONNECTION_STRING);
            final CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                    CodecRegistries.fromProviders(PojoCodecProvider.builder()
                            .automatic(true)
                            .build()));

            final MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connString)
                    .retryWrites(true)
                    .build();
            final MongoClient mongoClient = MongoClients.create(settings);
            DATABASE = mongoClient.getDatabase(DATABASE_NAME).withCodecRegistry(pojoCodecRegistry);
        }

        return DATABASE;
    }
}
