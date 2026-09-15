package collections.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Lists {
    public static void main(String[] args) {
        /*
            Lists are going to be your most generic, universally useful data
            structure. Lists have a couple of features that make them so flexible:
            - lists are indexable: elements can be accessed by referencing their index position (starting at 0)
            - lists maintain order of insertion
            - lists allow duplicate values
         */

        /*
            When creating Lists we need to specify the type that is stored within the list. If you look at the
            code for List you will see it declared as  List<E>. "E" is a generic that tells the compiler "this will
            have a concrete type at some point when it is used". We provide the concrete type when we make a List
            object of our choosing
         */
        List<String> myArrayList = new ArrayList<>();
        // the type must be a class, so for storing primitives reference the Class version and let Java Autobox the values for you
        List<Integer> myIntegers = new ArrayList<>();
        // You can make the generic the base Object class, be careful if you do so because it will require you to make more checks
        // on the data when you try to access it
        List<Object> myObjects = new ArrayList<>();


        myArrayList.add("Billy");
        myArrayList.add("Sally");
        myArrayList.add("Slagathor");

        System.out.println(myArrayList);

        myArrayList.add(1,"Teddy");

        System.out.println(myArrayList);

        String myElement = myArrayList.get(1);
        System.out.println(myElement);

        myArrayList.remove("Teddy");

        System.out.println(myArrayList);
        System.out.println(myElement);

        myArrayList.getFirst();
        myArrayList.getLast();

        myArrayList.addAll(List.of("Sarah","Katie","Dusty","Slagathor with a steel chair!"));

        System.out.println(myArrayList);

        /*
            ArrayLists are backed by an Array, LinkedLists are a collection of nodes.
         */
        LinkedList<String> myLinkedList = new LinkedList<>();
        myLinkedList.add("Billy");
        myLinkedList.add("Sally");
        myLinkedList.add("Slagathor");
        System.out.println(myLinkedList);

        String linkedElement = myLinkedList.get(1);
        System.out.println(linkedElement);

        myLinkedList.addFirst("Teddy");

        System.out.println(myLinkedList);

//        for(String element: myArrayList){
//            System.out.println(element);
//        }
//
//        for(String element: myLinkedList){
//            System.out.println(element);
//        }

        myLinkedList.pollLast();
        myLinkedList.pollFirst();
        myLinkedList.peek();

        LinkedList<String> dmvWaitList = new LinkedList<>(List.of("Billy", "Sally", "Slagathor"));

        while(!dmvWaitList.isEmpty()){
            String customer = dmvWaitList.pollFirst();
            System.out.println("Now helping " + customer);
        }
        /*
            Both structures are efficient in different types of operations:
            - ArrayLists are really good for accessing data in the center of the collection
            - LinkedLists are really good for performing operations at the beginning and end of the collection
         */
    }
}
