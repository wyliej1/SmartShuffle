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
public class Song implements Comparable<Song>{
    String title;
    String artist;
    String MP4Refernce; //path to MP4. -- Could be in database idk?
    ArrayList<CategoryHolder.Tag> tags = new ArrayList<CategoryHolder.Tag>();
    boolean choosen;
    double relevancy = 1;
    
    public Song(String titl, String art, ArrayList<CategoryHolder.Tag> t){
    	title = titl;
        artist = art;
        tags = t;
        choosen = false;
	}
    
    protected ArrayList<CategoryHolder.Tag> getTags(){
        return tags;
    }
    
    protected String getSongInfo() {
        return title + " " + artist;
    }

    @Override
    public int compareTo(Song t) {
        double comp = relevancy - t.relevancy;
        if(comp < 0) return -1;
        return 1;
    }
}