import java.io.Console;

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
		Shuffler shuffle = new Shuffler();
		shuffle.initialize();

		System.out.println(shuffle.songQueue.size());
		
		Song test = new Song("test", new CategoryHolder.Tag[]{new CategoryHolder.Tag("Pop", .2),new CategoryHolder.Tag("Rock", .5), new CategoryHolder.Tag("Jazz", .3)});
		Song test1 = new Song("test1", new CategoryHolder.Tag[]{new CategoryHolder.Tag("Classical", .2), new CategoryHolder.Tag("Jazz", .5), new CategoryHolder.Tag("Swing", .7)});
		shuffle.songQueue.add(test);
		shuffle.songQueue.add(test1);
		
		System.out.println(shuffle.songQueue.size());
		
		shuffle.Upvote();
		
	}
}
