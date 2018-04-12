import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author rudnicka1
 * @recent_version_date: 27/03/17
 */
public class Shuffler {
	
	Queue<Song> songQueue;
	Playlist history, unplayed;
	CategoryHolder.Category main;
	
    public void initialize() {
        DatabaseInteraction datab = new DatabaseInteraction();
        songQueue = new LinkedList<Song>();
        //datab.populateSongs();
        //UI ui = new UI();
        //ui.initialize();
        history = new Playlist();
        unplayed = new Playlist();
        main = new CategoryHolder.Category();
    }
    
    // Master function called for adding another song to shuffle queue
    public void Shuffle() 
    {        
        // Pull current unplayed playlist

        // Sort playlist by each category 

            // For each category, sort (see Playlist.CategorySort)

        // Add top value to song queue
    }
    
    //upvote
    public void Upvote()
    {
        //grab song at top of songQueue
        Song current = songQueue.peek();
        
        System.out.println(current.title);

        //for each tag in song.tags, check if it exists
        
        for(CategoryHolder.Tag t : current.tags)
        {
        	boolean exist = false;
        	int index_of_i = 0;
            //if not there, add
            for(CategoryHolder.Tag j : main.tags)
            {
                if(j.getName().compareTo(t.getName()) == 1)
                {
                    exist = true;
                    index_of_i = main.tags.indexOf(j);
                    break;
                }
            }
            
            //if not, make it so
            if(!exist)
            {
                main.tags.add(new CategoryHolder.Tag(t.getName()));
                index_of_i = main.tags.size() - 1;
            }

            //take song.tag.score and tick up
            ((CategoryHolder.Tag)(main.tags.get(index_of_i))).setScore(t.getScore() + ((CategoryHolder.Tag)(main.tags.get(index_of_i))).getScore());
        } 
    }
        
      
    //downvote
    public void Downvote()
    {    
    	//grab song at top of songQueue
        Song current = songQueue.peek();

        //for each tag in song.tags, check if it exists
        
        for(CategoryHolder.Tag t : current.tags)
        {
        	boolean exist = false;
        	int index_of_i = 0;
            //if not there, add
            for(CategoryHolder.Tag j : main.tags)
            {
                if(j.getName().compareTo(t.getName()) == 1)
                {
                    exist = true;
                    index_of_i = main.tags.indexOf(j);
                    break;
                }
            }
            
            //if not, make it so
            if(!exist)
            {
                main.tags.add(new CategoryHolder.Tag(t.getName()));
                index_of_i = main.tags.size() - 1;
            }

            //take song.tag.score and tick up
            ((CategoryHolder.Tag)(main.tags.get(index_of_i))).setScore(t.getScore() - ((CategoryHolder.Tag)(main.tags.get(index_of_i))).getScore());
        }
    }
}