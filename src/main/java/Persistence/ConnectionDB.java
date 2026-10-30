package Persistence;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class ConnectionDB {

    private static final String CONFIG_FILE = "config.properties";

    Connection connect = null;

    /**
     * Establishes the connection to the database.
     *
     * @return returns a connection variable.
     */
    public Connection establishConnection(){

        try {

            Properties props = new Properties();
            try (FileInputStream in = new FileInputStream(CONFIG_FILE)) {
                props.load(in);
            }

            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection(
                    props.getProperty("db.url"),
                    props.getProperty("db.user"),
                    props.getProperty("db.password"));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: "+e.toString());
        }

        return connect;
    }
}