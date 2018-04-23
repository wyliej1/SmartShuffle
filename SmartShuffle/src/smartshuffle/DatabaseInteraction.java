/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartshuffle;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author wolfej3
 */
public class DatabaseInteraction {

    Connection conn;
    private final String url = "jdbc:mysql://udsmartshuffle.colzrdsvw2ok.us-east-2.rds.amazonaws.com:3306/smartshuffle";

    /**
     * puts a song in the Database
     *
     * @throws java.sql.SQLException
     */
    //Establishes connection to MySQL Database
    public void connect() throws SQLException {
        conn = DriverManager.getConnection(url, "capstoneUser", "UDflyers1");
        System.out.println("Connected to MySQL Database");

    }

    protected ResultSet populateSongs() throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Library;");
        return rs;

    }

    //query the master list and return a resultset with the query
    protected ArrayList queryLibrary() throws SQLException {
        //   Statement stmt  = conn.createStatement();
        ArrayList title = new ArrayList();
        ArrayList artist = new ArrayList();
        ArrayList tags = new ArrayList();
        ArrayList theArrays = new ArrayList();
        String query = "SELECT title,artist,tags from Library";
        PreparedStatement statement2 = conn.prepareStatement(query);
       // statement2.setString(1, songName);
        ResultSet rs = statement2.executeQuery();
        while (rs.next()) {
            title.add(rs.getString("title"));
            artist.add(rs.getString("artist"));
            tags.add(rs.getString("tags"));
        }
        theArrays.add(title);
        theArrays.add(artist);
        theArrays.add(tags);
        //Title, Artist, tags int that order coming back
        return theArrays;

    }

    //inserts a song into the song table (the queue essentially)
    protected void insertSong(String Title, String tag, String location) throws SQLException {
        String query = "INSERT INTO Song (`title`, `tag`, 'filelocation') VALUES (?, ?, ?);";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, Title);
        stmt.setString(2, tag);
        stmt.setString(3, location);
        stmt.executeUpdate();
    }
    //query a song from the dynamic Song playlist (the queue)

    protected ResultSet querySong(String songName) throws SQLException {
        String query = "SELECT * from Song WHERE genre = ?";
        PreparedStatement statement2 = conn.prepareStatement(query);
        statement2.setString(1, songName);
        ResultSet rs = statement2.executeQuery();
        return rs;
    }

    //insert new songs into Library
    protected void insertLibrary(String Title, String artist, String genre, String tag, String location) throws SQLException {
        String query = "INSERT INTO `smartshuffle`.`Library` (`title`, `artist`, `genre`, `tags`, `filelocation`) VALUES (?, ?, ?, ?,?);";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, Title);
        stmt.setString(2, artist);
        stmt.setString(3, genre);
        stmt.setString(4, tag);
        stmt.setString(5, location);
        stmt.executeUpdate();
    }
//returns AWS link to Video Location

    protected ResultSet queryLocation(String title) throws SQLException {
        String query = "SELECT filelocation FROM smartshuffle.Library WHERE title = ?;";
        PreparedStatement stmt = conn.prepareCall(query);
        stmt.setString(1, title);
        ResultSet rs = stmt.executeQuery();
        return rs;
    }

    //Returns AWS link to thumbnail

    protected ResultSet queryThumb(String title) throws SQLException {
        String query = "SELECT thumblocation FROM smartshuffle.Library WHERE title = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, title);
        ResultSet rs = stmt.executeQuery();
        return rs;
    }
    /*
     //updates the library with individual tags stored in individual columns
     protected ResultSet updateLibrary(String title){
     String query = "UPDATE smartshuffle.Library SET "
     }
     */

    //Updates the library songs with tags from the tag strings

    protected void updateLibrary(String title, String tags) throws SQLException {
        String query = "UPDATE smartshuffle.Library SET tags = ? WHERE title = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, tags);
        stmt.setString(2, title);
        stmt.executeUpdate();
    }
}
