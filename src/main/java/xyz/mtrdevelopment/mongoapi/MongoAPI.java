package xyz.mtrdevelopment.mongoapi;

/*
 *
 * MongoAPI is a property of MTR-Development-Team
 * It was created @ 06/08/2023
 * Coded by the founder of MTR-Development-Team
 * EmpireMTR
 *
 */

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Getter;
import org.bson.Document;
import xyz.mtrdevelopment.mongoapi.mongoclass.MongoClass;

import java.util.Collections;

@Getter
public class MongoAPI {

    private final MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private final MongoCredentials credentials;

    public MongoAPI(MongoCredentials credentials) {
        this.credentials = credentials;
        if (credentials.isUriMode()) {
            mongoClient = new MongoClient(new MongoClientURI(credentials.getUri()));
        } else {
            if (credentials.isAuth()) {
                MongoCredential credential = MongoCredential.createCredential(credentials.getUser(), credentials.getDatabase(), credentials.getPassword().toCharArray());
                mongoClient = new MongoClient(new ServerAddress(credentials.getHost(), credentials.getPort()), Collections.singletonList(credential));
            } else {
                mongoClient = new MongoClient(credentials.getHost(), credentials.getPort());
            }
            mongoDatabase = mongoClient.getDatabase(credentials.getDatabase());
        }
    }

    public MongoCollection<Document> getCollection(String name) {
        return this.mongoDatabase.getCollection(name);
    }

    public FindIterable<Document> find(String collectionName) {
        return this.getCollection(collectionName).find();
    }

    public void save(MongoClass mongoClass) {
        MongoCollection<Document> mongoCollection = getCollection(mongoClass.getCollection());

        Document document = new Document("_id", mongoClass.getUUID().toString());
        document.putAll(mongoClass.toBson());

        mongoCollection.replaceOne(Filters.eq("_id", mongoClass.getUUID().toString()), document,
                new ReplaceOptions().upsert(true));
    }

    public boolean load(MongoClass mongoClass) {
        MongoCollection<Document> mongoCollection = getCollection(mongoClass.getCollection());

        Document document = mongoCollection.find(Filters.eq("_id", mongoClass.getUUID().toString())).first();

        if (document == null) {
            return false;
        }

        mongoClass.fromBson(document);
        return true;
    }
}