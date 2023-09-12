package xyz.mtrdevelopment.mongoapi.mongoclass;

/*
 *
 * MongoAPI is a property of MTR-Development-Team
 * It was created @ 12/09/2023
 * Coded by the founder of MTR-Development-Team
 * EmpireMTR
 *
 */

import org.bson.Document;

import java.util.UUID;

public interface MongoClass {
    String getCollection();
    UUID getUUID();
    Document toBson();
    void fromBson(Document document);
}