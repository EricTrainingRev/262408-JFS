package collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Iterators {
    /*
        An iterator is simply an object that allows you to iterate through a collection.
        There are shorthand ways of accessing iterators (think enhanced for loops) but
        sometimes it can be useful to create an iterator directly and go through the
        collection one at a time
     */
    public static void main(String[] args) {
        ArrayList<String> people = new ArrayList<>(List.of("Slagathor", "Billy", "sally"));
        // the code below will trigger a ConcurrentModificationException. Note this is JDK dependent
//        for(String person : people){
//            if(person.equals("Slagathor")){
//                people.remove("Slagathor");
//            }
//        }
        Iterator<String> peopleIterator = people.iterator();
        while(peopleIterator.hasNext()){
            String person = peopleIterator.next();
            if(person.equals("Slagathor")){
                peopleIterator.remove();
            }
        }
    }
}
