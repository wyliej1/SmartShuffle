/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smartshuffle;

import java.util.LinkedList;

/**
 *
 * @author Alex
 */
class CategoryHolder {
    LinkedList<Category> categories = new LinkedList();

    private static class Category {
        String CategoryName;
        LinkedList<Tag> tags;

        private Category() {
            CategoryName = null;
            tags = null;
        }
        public Category(String name) {
            CategoryName = name;
            tags = new LinkedList();
        }
        
        /**
         * Add a new Tag to the category.
         * @param tag 
         */
        public void addTag(Tag tag) {
            tags.add(tag);
        }
    }

    private static class Tag {
        String name;
        int upvotes = 0;
        int downvotes = 0;

        private Tag() {
            this.name = null;
        }
        public Tag(String name) {
            this.name = name;
        }
        
        /**
         * returns the score of the tag upvotes - downvotes
         * @return 
         */
        protected int getScore() {
            return upvotes - downvotes;
        }
        
        /**
         * returns the total number of votes both up and down.
         * @return 
         */
        protected int getTotalVotes() {
            return upvotes + downvotes;
        }
    }
}
