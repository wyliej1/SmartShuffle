/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartshuffle;

/**
 *
 * @author rudnicka1
 * @recent_version_date: 27/03/17
 */
public class Shuffler {
    public void initialize() {
        DatabaseInteraction datab = new DatabaseInteraction();
        datab.populateSongs();
        UI ui = new UI();
        ui.initialize();
        Playlist songQueue = new Playlist();
        Playlist history = new Playlist();
        Playlist unplayed = new Playlist();
        Category main = new Category();
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
        Song current = songQueue.top();

        //for each tag in song.tags, check if it exists
        for(Pair<String, Float> i : current.tags)
        {
            bool exist = false;
            int index_of_i = null;
            //if not there, add
            for(Tag j : main.tags)
            {
                if((String)(j.getName()).compareTo(i.getName()))
                {
                    exist = true;
                    index_of_i = main.tags.indexOf(j);
                    break;
                }
            }
            //if not, make it so
            if(!exist)
            {
                main.tags.add(i)
                index_of_i = tags.size() - 1;
            }

            //take song.tag.value and tick up
            (Tag)(main.tags.get(index_of_i)).value += i.value;
        }
    }
}