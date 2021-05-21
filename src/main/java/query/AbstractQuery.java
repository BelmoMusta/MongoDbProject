package query;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import connection.Connection;
import org.bson.conversions.Bson;

public abstract class AbstractQuery<T> {

    public MongoCollection<T> getCollection() {
        MongoDatabase mongoDatabase = Connection.connectToDataBase();
        MongoCollection<T> collection = mongoDatabase.getCollection(getNomCollection(), getaClass());
        return collection;
    }

    public void insert(T t) {
        MongoCollection<T> collection = getCollection();
        collection.insertOne(t);
    }

    public T find(Bson bson) {
        MongoCollection<T> collection = getCollection();
        FindIterable<T> ts = collection.find(bson);
        MongoCursor<T> cursor = ts.cursor();
        if (cursor.hasNext()) {
            return cursor.next();
        }
        return null;

    }

    protected abstract String getNomCollection();

    protected abstract Class<T> getaClass();
}
