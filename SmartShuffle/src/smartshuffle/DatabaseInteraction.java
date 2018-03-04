/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartshuffle;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author wolfej3
 */
public class DatabaseInteraction {
Connection conn;
    private final String url = "jdbc:sqlite:/Users/jwolfe/Developer/NetBeansProjects/SmartShuffle/SmartShuffle/smartshuffle.db";
    /**
     * puts a song in the Database
     * @throws java.sql.SQLException
     */
public void connect() throws SQLException{
   conn = DriverManager.getConnection(url);
   System.out.println("Connected to SQLite Database");
    
}


    protected void populateSongs() throws SQLException {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Statement stmt  = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM MasterList;");
        
    }
    
}
