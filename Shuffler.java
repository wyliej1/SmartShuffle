/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartshuffle;

/**
 *
 * @author laubera1
 */
public class Shuffler {
    public void initialize() {
        DatabaseInteraction datab = new DatabaseInteraction();
        datab.populateSongs();
        UI ui = new UI();
        ui.initialize();
        Playlist playlist = new Playlist();
        CategoryHolder Categories = new CategoryHolder();
    }
    
    public void Shuffle() {
        
        //do the shuffle
    }
}
