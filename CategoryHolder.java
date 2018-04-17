/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author Alex
 */
class CategoryHolder {
    LinkedList<Category> categories = new LinkedList();

    public static class Category {
        String CategoryName;
        LinkedList<Tag> tags = new LinkedList<Tag>();
        LinkedList<PriorityQueue> queues = new LinkedList<PriorityQueue>();
        double sum = 1.0;

        public Category() {
        	
        }
        
        public Category(String name) {
            CategoryName = name;
        }
        
        /**
         * Add a new Tag to the category.
         * @param tag 
         */
        public void addTag(Tag tag) {
            PriorityQueue genreQueue = new PriorityQueue();
            queues.add(genreQueue);
            tags.add(tag);
        }
    }

    public static class Tag {
        String name;
        private double score = 0;

        int totalVotes = 0;
        
        public Tag()
        {
        	name = "";
        	score = 0;
        }
        
		public Tag(String name2, double s) {
			this.name = name2;
            score = s;
		}

		public Tag(String name2) {
			// TODO Auto-generated constructor stub
			name = name2;
			score = 0;
		}

		public void setScore(double d)
        {
        	score = d;
        }
        
        /**
         * returns the score of the tag upvotes - downvotes
         * @return 
         */
        protected double getWeight() {
            return score / totalVotes;
        }
        
        /**
         * returns the total number of votes both up and down.
         * @return 
         */
        protected int getTotalVotes() {
            return totalVotes;
        }
        
		public String getName() {
			// TODO Auto-generated method stub
			return name;
		}
		public double getScore() {
			// TODO Auto-generated method stub
			return score;
		}
    }
}
