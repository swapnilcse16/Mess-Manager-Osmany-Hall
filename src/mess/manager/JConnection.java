package mess.manager;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
 
/**
 *
 * @author sqlitetutorial.net
 */
/*public class JConnection {
     
     
    public static Connection ConnecrDb() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/sqlite/db/swapnil.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            //System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        return conn;
    }
}*/

/*

public class JConnection {
    public static Connection ConnecrDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/swapnil","root","");
            ///System.out.println("Connected");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}

*/


public class JConnection {
    public static Connection ConnecrDb(){
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("org.sqlite.JDBC");
            
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/sqlite3/osmanyhall.db","root","");
            //System.out.println("Connected");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    
    public static Connection secondDb(){
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/sqlite3/osmanyhall(Female).db","root","");
            //System.out.println("Connected");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    
    
    
    
    
    public static Connection syncDb(){
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/sqlite3/Payment/osmanyhall.db","root","");
            //System.out.println("Connected");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
}

//