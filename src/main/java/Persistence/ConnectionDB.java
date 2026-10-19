package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class ConnectionDB {
    
    Connection connect = null;
    
    String user = "postgres";
    String password = "0725kevin";
    String db = "antbuddy";
    String ip = "localhost";
    String port = "5432";
    
    String string = "jdbc:postgresql://"+ip+":"+port+"/"+db;
    
    /**
     * Establishes the connection to the database.
     * 
     * @return returns a connection variable.
     */
    public Connection establishConnection(){
        
        try {
            
            connect = DriverManager.getConnection(string, user, password);
            Class.forName("org.postgresql.Driver");
            JOptionPane.showMessageDialog(null, "Successful connection to the database");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: "+e.toString());
        }
        
        return connect;
    }
}
