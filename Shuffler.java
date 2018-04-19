
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
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
    LinkedList<Song> history, unplayed;
    CategoryHolder.Category main;

//    public void initialize(ArrayList<Song> songs) {
    public void initialize(ArrayList<ArrayList> unProccSongs) {
        DatabaseInteraction datab = new DatabaseInteraction();
        songQueue = new LinkedList<Song>();
        //UI ui = new UI();
        //ui.initialize();
        
        //-------------------------------------------------
        
        history = new LinkedList();
        unplayed = new LinkedList();
        main = new CategoryHolder.Category();

        //for each song in database add to shuffle algorithm
        //ArrayList<ArrayList> unProccSongs = datab.getSongs();
        ArrayList songs = getSongsFromDB(unProccSongs);
        addSongsToQueues(songs);

        for (CategoryHolder.Tag t : main.tags) {
            t.setScore(1.0);
        }
        normalize();
        main.sum = main.tags.size();
        songQueue.add(getSongFWQ());//first 5 Songs added
        songQueue.add(getSongFWQ());
        songQueue.add(getSongFWQ());
        songQueue.add(getSongFWQ());
        songQueue.add(getSongFWQ());
    }

    //Returns a song object that has functions for get title get artist and get tags.
    public Song RequestNextSong() {
        history.add(songQueue.remove());
        while (songQueue.size() < 5) {
            // Fair Weighted Queueing selection
            Song choosenSong = getSongFWQ();
            songQueue.add(choosenSong);
        }
        // return
        return songQueue.element();
    }

    //upvote returns nothing
    public void Upvote() {
        //grab song at top of songQueue
        Song current = songQueue.peek();
        System.out.println("Upvoting " + current.getSongInfo());

        //for each tag in song.tags, check if it exists
        for (CategoryHolder.Tag t : current.tags) {
            boolean exist = false;
            int index_of_i = 0;
            //if not there, add
            for (CategoryHolder.Tag j : main.tags) {
                if (j.getName().equals(t.getName())) {
                    exist = true;
                    index_of_i = main.tags.indexOf(j);
                    break;
                }
            }

            //if not, make it so
            if (!exist) {
                main.addTag(new CategoryHolder.Tag(t.getName()));
                index_of_i = main.tags.size() - 1;
            }

            //take song.tag.score and tick up
            ((CategoryHolder.Tag) (main.tags.get(index_of_i))).setScore(((CategoryHolder.Tag) (main.tags.get(index_of_i))).getScore() + t.getScore());
            normalize();
        }
    }

    //downvote returns nothing
    public void Downvote() {
        //grab song at top of songQueue
        Song current = songQueue.peek();
        System.out.println("Downvoting " + current.getSongInfo());

        //for each tag in song.tags, check if it exists
        for (CategoryHolder.Tag t : current.tags) {
            int index_of_i = 0;
            //if not there, add
            for (CategoryHolder.Tag j : main.tags) {
                if (j.getName().equals(t.getName())) {
                    index_of_i = main.tags.indexOf(j);
                    break;
                }
            }
            //take song.tag.score and tick up
            double newscore = ((CategoryHolder.Tag) (main.tags.get(index_of_i))).getScore() - t.getScore();
            if (newscore <= 0) {
                ((CategoryHolder.Tag) (main.tags.get(index_of_i))).setScore(.001);
            } else {
                ((CategoryHolder.Tag) (main.tags.get(index_of_i))).setScore(newscore);
            }
            normalize();
        }
    }

    private Song getSongFWQ() {
        //select a value
        double rand = Math.random() * main.sum;
        System.out.println(rand);
        double tagRun = 0;
        Iterator<CategoryHolder.Tag> tagIter = main.tags.iterator();
        Iterator<PriorityQueue> queueIter = main.queues.iterator();
        while (tagIter.hasNext() && queueIter.hasNext()) {
            CategoryHolder.Tag aTag = tagIter.next();
            PriorityQueue<Song> aQueue = queueIter.next();
            tagRun = tagRun + aTag.getScore();
            if (tagRun > rand) {
                try {
                    aQueue = reSort(aQueue, aTag.name);
                    Song potentialSong = aQueue.remove();
                    while (potentialSong.choosen) {
                        potentialSong = aQueue.remove();
                    }
                    potentialSong.choosen = true;
                    return potentialSong;
                } catch (NoSuchElementException ex) {
                    aTag.setScore(0);
                    normalize();
                    //call select a vlaue again.
                    //change sum
                    //maybe re-normalize
                    return getSongFWQ();
                }
            }
        }
        //Throw out of songs exception
        return null;
    }

    private PriorityQueue<Song> reSort(PriorityQueue<Song> oldQ, String tagName) {
        PriorityQueue<Song> newQ = new PriorityQueue();
        for (Song s : oldQ) {
            for (CategoryHolder.Tag t : s.tags) {
                if (t.name.equals(tagName)) {
                    s.relevancy = t.getScore();
                }
            }
            s.relevancy = s.relevancy * findRelevancy(s.tags, main.tags);
            newQ.add(s);
        }
        return newQ;
    }

    private double findRelevancy(ArrayList<CategoryHolder.Tag> tags1, LinkedList<CategoryHolder.Tag> tags2) {
        //for (double x : tags1) {
        ListIterator<CategoryHolder.Tag> tagerator1 = tags1.listIterator();
        //ListIterator<Tag> tagerator1 = tags1.listIterator();
        ListIterator<CategoryHolder.Tag> tagerator2 = tags2.listIterator();

        double dotProduct = 0, magTags1 = 0, magTags2 = 0;

        while (tagerator1.hasNext() && tagerator2.hasNext()) {
            double G1 = tagerator1.next().getScore();
            double G2 = tagerator2.next().getScore();

            dotProduct = dotProduct + G1 * G2;
            magTags1 = magTags1 + G1 * G1;
            magTags2 = magTags2 + G2 * G2;
        }

        return dotProduct / (sqrt(magTags1) * sqrt(magTags2));
    }

    private void normalize() {
        double sum = 0.0;
        for (CategoryHolder.Tag t : main.tags) {
            sum = sum + t.getScore();
        }
        if (sum <= 0) {
            return;
        }
        for (CategoryHolder.Tag t : main.tags) {
            t.setScore(t.getScore() / sum * main.tags.size());
        }
    }

    private ArrayList<Song> getSongsFromDB(ArrayList<ArrayList> theArray) {
        ArrayList<String> titles = theArray.get(0);
        ArrayList<String> artists = theArray.get(1);
        ArrayList<String> tags = theArray.get(2);
        ArrayList<Song> songs = new ArrayList();

        Iterator<String> titleIter = titles.iterator();
        Iterator<String> artistIter = artists.iterator();
        Iterator<String> tagsIter = tags.iterator();

        while (titleIter.hasNext() && artistIter.hasNext() && tagsIter.hasNext()) {
            String aTitle = titleIter.next();
            String aArtist = artistIter.next();
            String aListOfTags = tagsIter.next();

            ArrayList<CategoryHolder.Tag> aTags = new ArrayList();
            String[] rawTags = aListOfTags.split(";");
            for (String s : rawTags) {
                String[] r = s.split("-");
                try {
                    CategoryHolder.Tag tag = new CategoryHolder.Tag(r[0], Double.parseDouble(r[1]));
                    aTags.add(tag);
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("warning song " + aTitle + " may not have any tags");
                }
            }
            Song song = new Song(aTitle, aArtist, aTags);
            songs.add(song);
        }
        return songs;
    }

    private void addSongsToQueues(ArrayList<Song> songs) {
        for (Song aSong : songs) {
            for (CategoryHolder.Tag aTag : aSong.tags) {
                if (aTag.getScore() == 0) {
                    continue;
                }

                boolean exist = false;
                CategoryHolder.Tag mainTag;
                PriorityQueue<Song> mainQueue;

                //if there, add
                Iterator<CategoryHolder.Tag> tagIter = main.tags.iterator();
                Iterator<PriorityQueue> queueIter = main.queues.iterator();
                while (tagIter.hasNext() && queueIter.hasNext()) {
                    mainTag = tagIter.next();
                    mainQueue = queueIter.next();

                    if (mainTag.getName().equals(aTag.getName())) {
                        exist = true;
                        mainQueue.add(aSong);
                        System.out.println("added " + aSong.title + " to queue for " + mainTag.getName());
                        break;
                    }
                }

                //if not, make it so
                if (!exist) {
                    main.addTag(new CategoryHolder.Tag(aTag.getName()));
                    if (tagIter.hasNext() && queueIter.hasNext()) { //should always bee true
                        mainTag = main.tags.get(main.tags.size() - 1);
                        mainQueue = main.queues.get(main.tags.size() - 1);
                        mainQueue.add(aSong);
                        System.out.println("added " + aSong.title + " to new queue for " + mainTag.getName());
                    }
                }
            }
        }
    }

    //returns an array list of CategoryHolder.Tags that have functions for get tag name and get score
    //highest First.
    public ArrayList<CategoryHolder.Tag> DisplayTopFive() {
        ArrayList<CategoryHolder.Tag> list = new ArrayList();
        for (CategoryHolder.Tag t : main.tags) {
            list.add(t);
        }
        TagComparator cmprtr = new TagComparator();
        list.sort(cmprtr);
        Collections.reverse(list);
        ArrayList<CategoryHolder.Tag> toBeReturn = new ArrayList();
        toBeReturn.add(list.get(0));
        toBeReturn.add(list.get(1));
        toBeReturn.add(list.get(2));
        toBeReturn.add(list.get(3));
        toBeReturn.add(list.get(4));
        return toBeReturn;
    }

    //returns an array list of CategoryHolder.Tags that have functions for get tag name and get score
    //lowest First.
    public ArrayList<CategoryHolder.Tag> DisplayBottomFive() {
        ArrayList<CategoryHolder.Tag> list = new ArrayList();
        for (CategoryHolder.Tag t : main.tags) {
            list.add(t);
        }
        TagComparator cmprtr = new TagComparator();
        list.sort(cmprtr);
        ArrayList<CategoryHolder.Tag> toBeReturn = new ArrayList();
        toBeReturn.add(list.get(0));
        toBeReturn.add(list.get(1));
        toBeReturn.add(list.get(2));
        toBeReturn.add(list.get(3));
        toBeReturn.add(list.get(4));
        return toBeReturn;
    }

    //Returns an ArrayList of ArrayLists. each of the lower level ArrayList 
    //represents a Tag and holds upto the top three songs for the tag
    public ArrayList<ArrayList> DisplayQueues() {
        ArrayList<ArrayList> theQueues = new ArrayList();
        for (PriorityQueue aQueue : main.queues) {
            boolean end = false;
            ArrayList<Song> songs = new ArrayList();
            PriorityQueue<Song> expendableQueue = new PriorityQueue(aQueue);
            for (int i = 0; i < 3; i++) {
                Song s = expendableQueue.poll();
                    if(s == null) {
                        end = true;
                        break;
                    }
                while(s.choosen) {
                    s = expendableQueue.poll();
                    if(s == null) {
                        end = true;
                        break;
                    }
                }
                if(!end) {
                songs.add(s);
                } else {
                    break;
                }
            }
            theQueues.add(songs);
        }
        return theQueues;
    }

    private static class TagComparator implements Comparator<CategoryHolder.Tag> {

        public TagComparator() {
        }

        @Override
        public int compare(CategoryHolder.Tag t, CategoryHolder.Tag t1) {
            double comp = t.getScore() - t1.getScore();
            if (comp < 0) {
                return -1;
            }
            return 1;
        }
    }
}
