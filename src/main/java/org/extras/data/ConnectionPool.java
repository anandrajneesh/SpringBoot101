package org.extras.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.Semaphore;

/**
 * Created by Anand_Rajneesh on 3/3/2017.
 */
public class ConnectionPool {

    private Semaphore semaphore = new Semaphore(10);
    private TreeSet<ConnectionWrapper> connections = new TreeSet<ConnectionWrapper>();

    private static class ConnectionWrapper implements Comparable<ConnectionWrapper> {
        private Connection connection;
        private boolean used;

        private ConnectionWrapper(Connection connection) {
            this.connection = connection;
            this.used = false;
        }

        public int compareTo(ConnectionWrapper o) {
            return this.used ? o.used ? 0 : 1 : -1;
        }
    }

    public ConnectionPool(Map<String, String> config) {
        String accessPath = config.get("path");
        String username = config.get("user");
        String password = config.get("pwd");
        String connectionString = "jdbc:ucanaccess://" + accessPath;
        if (username != null && password != null) {
            //FIXME add credentials to connectionString
        }

        for (int i = 0; i < 10; i++) {
            try {
                connections.add(new ConnectionWrapper(DriverManager.getConnection(connectionString)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection get() {
        try {
            semaphore.acquire();
            synchronized (this) {
                ConnectionWrapper node = connections.first();
                if (!node.used) {
                    node.used = true;
                    connections.remove(node);
                    return node.connection;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void release(Connection connection) {
        ConnectionWrapper node = new ConnectionWrapper(connection);
        connections.add(node);
        semaphore.release();
    }

    public void close() {
        semaphore.drainPermits();
        for (ConnectionWrapper nodes : connections) {
            try {
                nodes.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
