/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartshuffle;

import java.util.Queue;

/**
 *
 * @author laubera1
 */
class Playlist implements IPlaylist{

    /**
     * Retrieves the top song in the queue that is next to be played on on screen
     * @return nextSong
     */
    @Override
    public Song top() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Upvotes the current song on the player updating the tags in the Category Holder class
     */
    @Override
    public void upvote() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Downvotes the current song on the player updating the tags in the Category Holder class
     */
    @Override
    public void downvote() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Skips the remainder of the currently playing song. Also calls downvote the current song on the player updating the tags in the Category Holder class
     */
    @Override
    public void skip() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Checks to see if the playlist is empty
     * @return isEmpty Boolean
     */
    @Override
    public Boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Song requestNextSong() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
