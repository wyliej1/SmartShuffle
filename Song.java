/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartshuffle;

import java.util.LinkedList;

/**
 *
 * @author laubera1
 */
public class Song {
    String title;
    String artist;
    String MP4Refernce; //path to MP4. -- Could be in database idk?
    int duration; //in seconds
    LinkedList<String> tags = new LinkedList();
    
    protected LinkedList getTags(){
        return tags;
    }
    
    protected void addTags(String Tag) {
        tags.add(Tag);
    }
    
    protected String getSongInfo() {
        return title + artist;
    }
}
