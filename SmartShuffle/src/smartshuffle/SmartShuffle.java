/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartshuffle;

import java.sql.SQLException;

/**
 *
 * @author laubera1
 */
public class SmartShuffle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Shuffler shuffle = new Shuffler();
        shuffle.initialize();
        shuffle.Shuffle();
    }
    
}
