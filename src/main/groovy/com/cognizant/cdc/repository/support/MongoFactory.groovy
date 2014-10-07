package com.cognizant.cdc.repository.support

import com.mongodb.DB
import com.mongodb.Mongo
import com.mongodb.MongoClient
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class MongoFactory {

    private static Logger log = Logger.getLogger(MongoFactory.class)
    private static int DEFAULT_DB_PORT = 27017

    @Value('${db.server}')
    String server

    @Value('${db.port}')
    String port

    @Value('${db.name}')
    String dbName

    Mongo m = null

    @SuppressWarnings("GroovyAccessToStaticFieldLockedOnInstance")
    public synchronized DB getDB() {
        if (!m) {
            int port = DEFAULT_DB_PORT
            try {
                port = Integer.parseInt(this.port)
            } catch (NumberFormatException e) {
                log.error("Failed to get the port from configuration file, use default value ${port}", e)
            }
            m = new MongoClient(server, port)
        }
        m.getDB(dbName)
    }
}
