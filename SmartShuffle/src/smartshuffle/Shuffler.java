/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartshuffle;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author laubera1
 */
public class Shuffler {

    public void initialize() throws SQLException {

        //Test DB Code
        DatabaseInteraction datab = new DatabaseInteraction();
        datab.connect();
        datab.insertMaster("Title", "tags", "names");
        ResultSet rs = datab.querySong("pop");
        rs.findColumn("title");
        String result = rs.getString("title");
        System.out.println(result);

        //     UI ui = new UI();
//        ui.initialize();
        Playlist playlist = new Playlist();
        CategoryHolder Categories = new CategoryHolder();
    }

    public void Shuffle() {

        //do the shuffle
    }
}
