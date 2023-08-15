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
import com.mongodb.client.MongoDatabase;
import lombok.Getter;

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
}