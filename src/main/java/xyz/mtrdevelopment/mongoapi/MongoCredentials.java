package xyz.mtrdevelopment.mongoapi;

/*
 *
 * MongoAPI is a property of MTR-Development-Team
 * It was created @ 06/08/2023
 * Coded by the founder of MTR-Development-Team
 * EmpireMTR
 *
 */

import lombok.Getter;

@Getter
public class MongoCredentials {
    private String uri;
    private boolean uriMode;
    private String host;
    private int port;
    private final String database;
    private boolean auth;
    private String user;
    private String password;

    public MongoCredentials(String uri, String database, boolean isURI) {
        this.uri = uri;
        this.uriMode = isURI;
        this.database = database;
    }

    public MongoCredentials(String host, String database, int port, boolean isAuth, String user, String password) {
        this.host = host;
        this.database = database;
        this.port = port;
        this.auth = isAuth;
        this.user = user;
        this.password = password;
    }
}