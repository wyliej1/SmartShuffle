/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author laubera1
 */
public interface IPlaylist {

    public Boolean isEmpty();
    
    public Song requestNextSong(); //selects from algorithm

    //public void add(Song song); 

    public Song top(); //selects from top of queue

    public void upvote();

    public void downvote();

    public void skip();
}
