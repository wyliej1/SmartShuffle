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
    protected ResultSet queryMaster(String songName) throws SQLException {
        //   Statement stmt  = conn.createStatement();
        String query = "SELECT Song from Library WHERE Song = ?";
        PreparedStatement statement2 = conn.prepareStatement(query);
        statement2.setString(1, songName);
        ResultSet rs = statement2.executeQuery();
        return rs;
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

    protected void insertLibrary(String Title, String artist,String genre, String tag, String location) throws SQLException {
        String query = "INSERT INTO `smartshuffle`.`Library` (`title`, `artist`, `genre`, `tags`, `filelocation`) VALUES (?, ?, ?, ?,?);";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, Title);
        stmt.setString(2, artist);
        stmt.setString(3, genre);
        stmt.setString(4, tag);
        stmt.setString(5, location);
        stmt.executeUpdate();
    }
}
