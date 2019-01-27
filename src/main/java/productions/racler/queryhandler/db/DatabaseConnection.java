package productions.racler.queryhandler.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import productions.racler.queryhandler.db.settings.DatabaseSettings;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Tim Stachorra 23.03.2017
 * <p>
 * Database connection
 */
public class DatabaseConnection {

    private Type type;

    private DatabaseSettings settings;

    private HikariDataSource dataSource;

    /**
     * Initialize the database connection
     *
     * @param settings the settings of the database
     */
    public DatabaseConnection(DatabaseSettings settings) {

        this.type = settings.type();
        this.settings = settings;

        setup();
    }

    /**
     * Set up the hikari database connection
     */
    private void setup() {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName(type.getDriver());
        if (type == Type.MYSQL) {
            config.setJdbcUrl("jdbc:mysql://" + settings.host() + ":" + settings.port() + "/" + settings.database() + "?serverTimezone=UTC");
        } else if (type == Type.SQLITE) {
            config.setJdbcUrl("jdbc:sqlite:" + settings.database());

            String path = settings.database().substring(0, settings.database().lastIndexOf("/"));

            File pathFile = new File(path);
            if (!pathFile.exists()) {
                if (!new File(path).mkdirs()) {
                    System.err.println("Error while creating path to database file! " + path);
                }
            }
        }
        config.setUsername(settings.user());
        config.setPassword(settings.password());
        config.setPoolName(settings.poolName());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setMaximumPoolSize(settings.poolSize());
        config.setConnectionTimeout(settings.getQueryTimeout());

        dataSource = new HikariDataSource(config);
    }

    /**
     * Get the connection
     *
     * @return the database connection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {

        return dataSource.getConnection();
    }

    /**
     * Close the data source
     */
    public void close() {

        dataSource.close();
    }

    /**
     * Check if the data source is connected
     *
     * @return
     */
    public boolean isConnected() {

        return !dataSource.isClosed();
    }
}
