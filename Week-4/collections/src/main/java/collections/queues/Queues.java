package collections.queues;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class Queues {
    public static void main(String[] args) {
        /*
            Queues in Java are intended to be used for managing First In First Out (FIFO)
            and Last In First Out (LIFO) collections.
         */
        ArrayDeque<String> lineToOrderFood = new ArrayDeque<>();
        // this acts basically like "add" from the List interface
        lineToOrderFood.offerLast("Billy");
        lineToOrderFood.offerLast("Sally");
        lineToOrderFood.offerLast("Slagathor");
        // can also add the element to the front of the queue
        lineToOrderFood.offerFirst("Door Dasher");

        System.out.println(lineToOrderFood);

        // retrieves but does not remove the first element
        lineToOrderFood.peekFirst();
        // retrieves and removes the first element
        // there are similar methods for the last element of the collection
        lineToOrderFood.pollFirst();

        System.out.println(lineToOrderFood);
        System.out.println();

        /*
            PriorityQueue is a class that allows you to retrieve your elements
            in their natural ordering
         */
        PriorityQueue<String> people = new PriorityQueue<>();
        people.offer("Sally");
        people.offer("Slagathor");
        people.offer("Billy");
        // note the queue does not store all the data in order, but the first element
        // is the first in natural priority
        System.out.println(people);
        // here when we go element by element they will be polled in the correct
        // order (natural ordering)
        while(!people.isEmpty()){
            System.out.println(people.poll());
        }
    }
}
