
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Collection;
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

    public void initialize(ArrayList<Song> songs) {
        DatabaseInteraction datab = new DatabaseInteraction();
        songQueue = new LinkedList<Song>();
        //UI ui = new UI();
        //ui.initialize();
        history = new LinkedList();
        unplayed = new LinkedList();
        main = new CategoryHolder.Category();

        //for each song in database add to shuffle algorithm
//        ArrayList songs = getSongsFromDB();
        addSongsToQueues(songs);
        
        for (CategoryHolder.Tag t : main.tags) {
            t.setScore(1.0);
        }
        normalize();
        
        songQueue.add(getSongFWQ());//first Song added
    }

    // Master function called for adding another song to shuffle queue
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

    //upvote
    public void Upvote() {
        //grab song at top of songQueue
        Song current = songQueue.peek();
        System.out.println("Upvoting "+current.getSongInfo());

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
            ((CategoryHolder.Tag) (main.tags.get(index_of_i))).setScore(((CategoryHolder.Tag) (main.tags.get(index_of_i))).getScore()+ t.getScore());
            normalize();
        }
    }

    //downvote
    public void Downvote() {
        //grab song at top of songQueue
        Song current = songQueue.peek();
        System.out.println("Downvoting "+current.getSongInfo());

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
        if(sum <= 0) return;
        for (CategoryHolder.Tag t : main.tags) {
            t.setScore(t.getScore()/sum*main.tags.size());
        }
    }

    private ArrayList getSongsFromDB() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
