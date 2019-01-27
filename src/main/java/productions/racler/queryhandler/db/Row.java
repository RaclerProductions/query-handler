package productions.racler.queryhandler.db;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Tim Stachorra 23.03.2017
 * <p>
 * The result row of a select query to get specific data types
 */
public class Row extends HashMap<String, Object> {
    /**
     * Get a int from the row by the key
     *
     * @param key the key of the int
     * @return the value as int
     */
    public int getInt(String key) {
        return (int) super.get(key);
    }

    /**
     * Get a string from the row by the key
     *
     * @param key the key of the string
     * @return the value as string
     */
    public String getString(String key) {
        return (String) super.get(key);
    }

    /**
     * Get a double from the row by the key
     *
     * @param key the key of the double
     * @return the value as double
     */
    public double getDouble(String key) {
        return (double) super.get(key);
    }

    /**
     * Get a timestamp from the row by the key
     *
     * @param key the key of the timestamp
     * @return the value as timestamp
     */
    public Timestamp getTimestamp(String key) {
        return (Timestamp) super.get(key);
    }

    /**
     * Get a float from the row by the key
     *
     * @param key the key of the float
     * @return the value as float
     */
    public float getFloat(String key) {
        return (float) super.get(key);
    }

    /**
     * Get a boolean from the row by the key
     *
     * @param key the key of the timestamp
     * @return the value as boolean
     */
    public boolean getBoolean(String key) {
        return (boolean) super.get(key);
    }

    /**
     * Get a long from the row by the key
     *
     * @param key the key of the long
     * @return the value as long
     */
    public long getLong(String key) {
        return (long) super.get(key);
    }
}
