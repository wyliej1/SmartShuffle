import java.io.Console;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author laubera1
 * 
 */
public class SmartShuffle{

    /**
     * @param args the command line arguments
     */
	public static void main(String[] args){
                Song test1 = new Song("test1", new CategoryHolder.Tag[]{new CategoryHolder.Tag("Pop", .2),new CategoryHolder.Tag("Rock", .5)});
		Song test2 = new Song("test2", new CategoryHolder.Tag[]{new CategoryHolder.Tag("Classical", .2), new CategoryHolder.Tag("Jazz", .5), new CategoryHolder.Tag("Swing", .7)});
		Song test3 = new Song("test3", new CategoryHolder.Tag[]{new CategoryHolder.Tag("Hip Hop", .2), new CategoryHolder.Tag("Swing", .5), new CategoryHolder.Tag("Pop", .7)});
		Song test4 = new Song("test4", new CategoryHolder.Tag[]{new CategoryHolder.Tag("Pop", .2), new CategoryHolder.Tag("Jazz", .5), new CategoryHolder.Tag("Classical", .7)});
		Song test5 = new Song("test5", new CategoryHolder.Tag[]{new CategoryHolder.Tag("Country", .2), new CategoryHolder.Tag("Rock", .5), new CategoryHolder.Tag("Swing", .7)});
		Song test6 = new Song("test6", new CategoryHolder.Tag[]{new CategoryHolder.Tag("Classical", .2), new CategoryHolder.Tag("Pop", .5), new CategoryHolder.Tag("Hip Hop", .7)});
		Song test7 = new Song("test7", new CategoryHolder.Tag[]{new CategoryHolder.Tag("Hip Hop", .2), new CategoryHolder.Tag("Rock", .5), new CategoryHolder.Tag("Classical", .7)});
		Song test8 = new Song("test8", new CategoryHolder.Tag[]{new CategoryHolder.Tag("Classical", .2), new CategoryHolder.Tag("Country", .5), new CategoryHolder.Tag("Swing", .7)});
		Song test9 = new Song("test9", new CategoryHolder.Tag[]{new CategoryHolder.Tag("Hip Hop", .2), new CategoryHolder.Tag("Pop", .5), new CategoryHolder.Tag("Hip Hop", .7)});
		Song test10 = new Song("test10", new CategoryHolder.Tag[]{new CategoryHolder.Tag("Rock", .2), new CategoryHolder.Tag("Jazz", .5), new CategoryHolder.Tag("Pop", .7)});
		Song test11 = new Song("test11", new CategoryHolder.Tag[]{new CategoryHolder.Tag("Pop", .2), new CategoryHolder.Tag("Country", .5), new CategoryHolder.Tag("Swing", .7)});
		Song test12 = new Song("test12", new CategoryHolder.Tag[]{new CategoryHolder.Tag("Swing", .2), new CategoryHolder.Tag("Jazz", .5), new CategoryHolder.Tag("Classical", .7)});
		Song test13 = new Song("test13", new CategoryHolder.Tag[]{new CategoryHolder.Tag("Hip Hop", .2), new CategoryHolder.Tag("Pop", .5), new CategoryHolder.Tag("Jazz", .7)});
		Song test14 = new Song("test14", new CategoryHolder.Tag[]{new CategoryHolder.Tag("Classical", .2), new CategoryHolder.Tag("Swing", .5), new CategoryHolder.Tag("Country", .7)});
		Song test15 = new Song("test15", new CategoryHolder.Tag[]{new CategoryHolder.Tag("Rock", .2), new CategoryHolder.Tag("Classical", .5), new CategoryHolder.Tag("Country", .7)});
            
                ArrayList<Song> songs = new ArrayList();
                songs.add(test1);
                songs.add(test2);
                songs.add(test3);
                songs.add(test4);
                songs.add(test5);
                songs.add(test6);
                songs.add(test7);
                songs.add(test8);
                songs.add(test9);
                songs.add(test10);
                songs.add(test11);
                songs.add(test12);
                songs.add(test13);
                songs.add(test14);
                songs.add(test15);
                
		Shuffler shuffle = new Shuffler();
		shuffle.initialize(songs);

		System.out.println(shuffle.songQueue.size());
                for (CategoryHolder.Tag t : shuffle.main.tags){
                    System.out.println(t.name + "     \t" + t.getScore());
                }  
                System.out.println((shuffle.RequestNextSong()).getSongInfo());
                
		System.out.println(shuffle.songQueue.size());
		
		shuffle.Downvote();
                for (CategoryHolder.Tag t : shuffle.main.tags){
                    System.out.println(t.name + "     \t" + t.getScore());
                }                
                System.out.println(shuffle.RequestNextSong().getSongInfo());
                shuffle.Downvote();
                                for (CategoryHolder.Tag t : shuffle.main.tags){
                    System.out.println(t.name + "     \t" + t.getScore());
                }
                System.out.println(shuffle.RequestNextSong().getSongInfo());
                shuffle.Downvote();
                for (CategoryHolder.Tag t : shuffle.main.tags){
                    System.out.println(t.name + "     \t" + t.getScore());
                }
                System.out.println(shuffle.RequestNextSong().getSongInfo());
                shuffle.Upvote();
                for (CategoryHolder.Tag t : shuffle.main.tags){
                    System.out.println(t.name + "     \t" + t.getScore());
                }
                System.out.println(shuffle.RequestNextSong().getSongInfo());
                shuffle.Upvote();
                for (CategoryHolder.Tag t : shuffle.main.tags){
                    System.out.println(t.name + "     \t" + t.getScore());
                }
                System.out.println(shuffle.RequestNextSong().getSongInfo());
                shuffle.Upvote();
                for (CategoryHolder.Tag t : shuffle.main.tags){
                    System.out.println(t.name + "     \t" + t.getScore());
                }
                System.out.println(shuffle.RequestNextSong().getSongInfo());
                shuffle.Upvote();
                for (CategoryHolder.Tag t : shuffle.main.tags){
                    System.out.println(t.name + "     \t" + t.getScore());
                }
                System.out.println(shuffle.RequestNextSong().getSongInfo());
                shuffle.Upvote();        
                for (CategoryHolder.Tag t : shuffle.main.tags){
                    System.out.println(t.name + "     \t" + t.getScore());
                }

        }
}