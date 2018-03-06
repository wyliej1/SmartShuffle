
package smartshuffle;

import java.util.LinkedList;

/**
 *
 * @author laubera1
 */
public class Song {
    private String title;
    private String artist;
    private String MP4Reference; //path to MP4. -- Could be in database idk?
    private LinkedList<String> tags = new LinkedList();
    double relevency; //all categories brought together to get final number  //*
    
    private Song(){}
    public Song(String title, String artist, String MP4Reference) { //*
        this.title = title;
        this.artist = artist;
        this.MP4Reference = MP4Reference;
    }
    public Song(String title, String artist, String MP4Reference, LinkedList<String> tags) {
        this.title = title;
        this.artist = artist;
        this.MP4Reference = MP4Reference;
        for (String i : tags) {
            addTags(i);
        }
    }
    
    protected LinkedList getTags(){
        return tags;
    }
    
    protected void addTags(String Tag) {
        tags.add(Tag);
    }
    
    protected String getSongInfo() {
        return title + artist;
    }
    
    protected String getSongLocation() {
        return MP4Reference;
    }
    
   //puts get/set for relevency  //*
}
