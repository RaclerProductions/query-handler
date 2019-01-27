package productions.racler.queryhandler.db.settings;

import productions.racler.queryhandler.db.Type;

/**
 * Created by Tim Stachorra 23.03.2017
 * <p>
 * Database settings
 */
public class DatabaseSettings {

    private String host = "localhost";

    private int port = 3306;

    private String user = "root";

    private String password = "";

    private String database = "db";

    private int poolSize = 10;

    private Type type = Type.MYSQL;

    private String poolName;

    private boolean printExceptions = false;

    private long queryTimeout = 2000;

    /**
     * Empty default constructor
     */
    public DatabaseSettings() {

    }

    /**
     * Initialize the database settings
     *
     * @param host            the host
     * @param port            the port
     * @param user            the username
     * @param password        the password
     * @param database        the database
     * @param poolSize        the pool site
     * @param type            the type
     * @param poolName        the pool name
     * @param printExceptions if exceptions should be printed
     * @param queryTimeout    the query timeout in ms
     */
    public DatabaseSettings(String host, int port, String user, String password, String database, int poolSize, Type type, String poolName, boolean printExceptions, long queryTimeout) {

        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.database = database;
        this.poolSize = poolSize;
        this.type = type;
        this.poolName = poolName;
        this.printExceptions = printExceptions;
        this.queryTimeout = queryTimeout;
    }

    /**
     * Gets the host
     *
     * @return the host
     */
    public String host() {

        return host;
    }

    /**
     * Gets the porst
     *
     * @return the port
     */
    public int port() {

        return port;
    }

    /**
     * Gets the user
     *
     * @return the user
     */
    public String user() {

        return user;
    }

    /**
     * Gets the password
     *
     * @return the password
     */
    public String password() {

        return password;
    }

    /**
     * Gets the database
     *
     * @return the database
     */
    public String database() {

        return database;
    }

    /**
     * Gets the pool size
     *
     * @return the pool size
     */
    public int poolSize() {

        return poolSize;
    }

    /**
     * Gets the type
     *
     * @return the type
     */
    public Type type() {

        return type;
    }

    /**
     * Gets the pool name
     *
     * @return the pool name
     */
    public String poolName() {

        return poolName;
    }

    /**
     * Return exceptions should printed
     *
     * @return true if the exceptions should printed
     */
    public boolean printExceptions() {

        return printExceptions;
    }

    /**
     * Gets the query timeout
     *
     * @return the query timeout
     */
    public long getQueryTimeout() {

        return queryTimeout;
    }

    /**
     * The database settings builder
     */
    public static class Builder {

        private DatabaseSettings settings = new DatabaseSettings();

        /**
         * Sets the host
         *
         * @param host the host
         * @return the Builder
         */
        public Builder host(String host) {

            settings.host = host;

            return this;
        }

        /**
         * Sets the port
         *
         * @param port the port
         * @return the Builder
         */
        public Builder port(int port) {

            settings.port = port;

            return this;
        }

        /**
         * Sets the username
         *
         * @param user the username
         * @return the Builder
         */
        public Builder user(String user) {

            settings.user = user;

            return this;
        }

        /**
         * Sets the password
         *
         * @param password the password
         * @return the password
         */
        public Builder password(String password) {

            settings.password = password;

            return this;
        }

        /**
         * Sets the database
         *
         * @param database the database
         * @return the Builder
         */
        public Builder database(String database) {

            settings.database = database;

            return this;
        }

        /**
         * Sets the pool size
         *
         * @param size the pool size
         * @return the Builder
         */
        public Builder poolSize(int size) {

            settings.poolSize = size;

            return this;
        }

        /**
         * Sets the type
         *
         * @param type the connection type
         * @return the Builder
         */
        public Builder type(Type type) {

            settings.type = type;

            return this;
        }

        /**
         * Sets the pool name
         *
         * @param name the pool name
         * @return the Builder
         */
        public Builder poolName(String name) {

            settings.poolName = name;

            return this;
        }

        /**
         * Set if exceptions should be printed
         *
         * @param value true if the exception should be printed
         * @return the Builder
         */
        public Builder printExceptions(boolean value) {

            settings.printExceptions = value;

            return this;
        }

        /**
         * Sets the query timeout
         *
         * @param queryTimeoutMs the timeput in milliseconds
         * @return the Builder
         */
        public Builder queryTimeout(long queryTimeoutMs) {

            settings.queryTimeout = queryTimeoutMs;

            return this;
        }

        /**
         * Get the finished settings
         *
         * @return the settings
         */
        public DatabaseSettings build() {

            return settings;
        }
    }
}
