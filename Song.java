/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author laubera1
 */
public class Song {
    String title;
    String artist;
    String MP4Refernce; //path to MP4. -- Could be in database idk?
    int duration; //in seconds
    ArrayList<CategoryHolder.Tag> tags = new ArrayList<CategoryHolder.Tag>();
    
    public Song(String n, CategoryHolder.Tag[] t){
    	title = n;
    	for(CategoryHolder.Tag tag : t){
    		tags.add(tag);
    	}
	}
    protected ArrayList<CategoryHolder.Tag> getTags(){
        return tags;
    }
    
    protected String getSongInfo() {
        return title + artist;
    }
}
