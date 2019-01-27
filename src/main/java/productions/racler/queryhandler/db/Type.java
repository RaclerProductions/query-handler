package productions.racler.queryhandler.db;

/**
 * Modified by Tim Stachorra 23.03.2017
 * <p>
 * The Connection type
 */
public enum Type {
    MYSQL("com.mysql.jdbc.Driver"), SQLITE("org.sqlite.JDBC");

    private String driver;

    /**
     * Setup the driver of the type
     *
     * @param driver
     */
    Type(String driver) {
        this.driver = driver;
    }

    /**
     * Get the driver of a type
     *
     * @return the driver as string
     */
    public String getDriver() {
        return driver;
    }
}
