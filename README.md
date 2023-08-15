# MongoDB API

*A simple MongoDB API I use for my projects.*
*This API was coded by the **[MTR Development Team](https://mtrdevelopment.xyz/)**.*

**How to use?**
**public void connectMongo() {  
if(databaseFile.getBoolean("MONGO.URI-MODE")) {  
mongoCredentials = new MongoCredentials(databaseFile.getString("MONGO.URI"), databaseFile.getString("MONGO.DATABASE"), databaseFile.getBoolean("MONGO.URI-MODE"));  
} else {  
mongoCredentials = new MongoCredentials(host,database,port,auth,user,password);
}  
mongoAPI = new MongoAPI(mongoCredentials);
}**