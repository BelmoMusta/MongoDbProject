package query;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import connection.Connection;

public abstract class AbstractQuery<T> {

    public MongoCollection<T> getCollection() {
        MongoDatabase mongoDatabase = Connection.connectToDataBase();
        MongoCollection<T> collection = mongoDatabase.getCollection(getNomCollection(), getaClass());
        return collection;
    }

    public void insert(T t){
        MongoCollection<T> collection = getCollection();
        collection.insertOne(t);
    }

    protected abstract String getNomCollection();

    protected abstract Class<T> getaClass();
}
