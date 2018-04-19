
import java.io.Console;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

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
public class SmartShuffle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> titles = new ArrayList();
        titles.add("q");
        titles.add("w");
        titles.add("e");
        titles.add("r");
        titles.add("t");
        titles.add("y");
        titles.add("u");
        titles.add("i");
        titles.add("i");
        titles.add("o");
        titles.add("p");
        titles.add("a");
        titles.add("s");
        titles.add("d");
        titles.add("f");

        ArrayList<String> artists = new ArrayList();
        artists.add("1");
        artists.add("2");
        artists.add("3");
        artists.add("4");
        artists.add("5");
        artists.add("6");
        artists.add("7");
        artists.add("8");
        artists.add("9");
        artists.add("10");
        artists.add("11");
        artists.add("12");
        artists.add("13");
        artists.add("14");
        artists.add("15");

        ArrayList<String> tags = new ArrayList();
        tags.add("jazz-.6;oldies-.2;blues-.15;easy listening-.05;");
        tags.add("jazz-.4;blues-.35;sad-.15;instrumental-.1;");
        tags.add("jazz-.35;instrumental-.3;happy-.2;oldies-.15;");
        tags.add("jazz-.5;instrumental-.3;oldies-.15;acoustic-.05;");
        tags.add("jazz-.5;chill-.3;blues-.15;oldies-.05;");
        tags.add("jazz-.4;instrumental-.2;chill-.2;easy listening-.2;");
        tags.add("blues-.4;jazz-.25;oldies-.2;easy listening-.15;");
        tags.add("jazz-.45;easy listening-.25;happy-.15;instrumental-.2;dance-.05;");
        tags.add("jazz-.3;catchy-.2;pop-.25;oldies-.15;hip hop-.1;");
        tags.add("jazz-.4;instrumental-.3;oldies-.15;happy-.1;dance-.05;");
        tags.add("jazz-.35;blues-.2;happy-.15;oldies-.3;");
        tags.add("jazz-.3;experimental-.2;oldies-.2;happy-.15;blues-.1;hip hop-.05;");
        tags.add("jazz-.5;instrumental-.2;blues-.1;dance-.1;easy listening-.1;");
        tags.add("jazz-.35;oldies-.25;chill-.15;happy-.1;acoustic-.1;sad-.05;");
        tags.add("jazz-.3;easy listening-.25;instrumental-.2;oldies-.15;blues-.1;");

        ArrayList<ArrayList> theArrays = new ArrayList();
        theArrays.add(titles);
        theArrays.add(artists);
        theArrays.add(tags);
        
        Shuffler shuffle = new Shuffler();
        shuffle.initialize(theArrays);

        System.out.println(shuffle.songQueue.size());
        for (CategoryHolder.Tag t : shuffle.main.tags) {
            System.out.println(t.name + "     \t" + t.getScore());
        }
        System.out.println((shuffle.RequestNextSong()).getSongInfo());

        System.out.println(shuffle.songQueue.size());

        shuffle.Downvote();
        for (CategoryHolder.Tag t : shuffle.main.tags) {
            System.out.println(t.name + "     \t" + t.getScore());
        }
        System.out.println(shuffle.RequestNextSong().getSongInfo());
        shuffle.Downvote();
        for (CategoryHolder.Tag t : shuffle.main.tags) {
            System.out.println(t.name + "     \t" + t.getScore());
        }
        System.out.println(shuffle.RequestNextSong().getSongInfo());
        shuffle.Downvote();
        for (CategoryHolder.Tag t : shuffle.main.tags) {
            System.out.println(t.name + "     \t" + t.getScore());
        }
        System.out.println(shuffle.RequestNextSong().getSongInfo());
        shuffle.Upvote();
        for (CategoryHolder.Tag t : shuffle.main.tags) {
            System.out.println(t.name + "     \t" + t.getScore());
        }
        System.out.println(shuffle.RequestNextSong().getSongInfo());
        shuffle.Upvote();
        for (CategoryHolder.Tag t : shuffle.main.tags) {
            System.out.println(t.name + "     \t" + t.getScore());
        }
        System.out.println(shuffle.RequestNextSong().getSongInfo());
        shuffle.Upvote();
        for (CategoryHolder.Tag t : shuffle.main.tags) {
            System.out.println(t.name + "     \t" + t.getScore());
        }
        /*
        System.out.println(shuffle.RequestNextSong().getSongInfo());
        shuffle.Upvote();
        for (CategoryHolder.Tag t : shuffle.main.tags) {
            System.out.println(t.name + "     \t" + t.getScore());
        }
        System.out.println(shuffle.RequestNextSong().getSongInfo());
        shuffle.Upvote();
        for (CategoryHolder.Tag t : shuffle.main.tags) {
            System.out.println(t.name + "     \t" + t.getScore());
        }
        //*/
        System.out.println("Top 3");
        ArrayList<CategoryHolder.Tag> top5 = shuffle.DisplayTopFive();
        System.out.println(top5.get(0).getName()+" "+top5.get(0).getScore());
        System.out.println(top5.get(1).getName());
        System.out.println(top5.get(2).getName());
        System.out.println("bottom 3");
        ArrayList<CategoryHolder.Tag> bot5 = shuffle.DisplayBottomFive();
        System.out.println(bot5.get(0).getName());
        System.out.println(bot5.get(1).getName());
        System.out.println(bot5.get(2).getName());
        
        
        ArrayList<ArrayList> displays = shuffle.DisplayQueues();
        for (ArrayList<Song> s : displays) {
            System.out.print("top three for this tag: ");
            for (Song i : s) {
                System.out.print(i.getSongInfo() + " :: ");
            }
            System.out.println();
        }
    }
}
