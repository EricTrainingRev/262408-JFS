package collections.set;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Sets {
    /*
        Sets are more restrictive collections designed to work with unique entries.
        - Sets do not guarantee order of insertion is the order the elements are stored in
        - Sets do not maintain an accessible indexing system for us to retrieve the elements
        - Sets do not allow duplicates
     */

    public static void main(String[] args) {
        Set<String> myHashSet = new HashSet<>();
        myHashSet.add("Billy");
        myHashSet.add("Sally");
        myHashSet.add("Slagathor");
        myHashSet.add(null);
        System.out.println(myHashSet);
        String myElement;
//        for (String element : myHashSet){
//            if (element != null && element.equals("Slagathor")){
//                myElement = element;
//                break;
//            }
//        }

        Set<String> myTreeSet = new TreeSet<>(Set.of("Billy", "Sally", "Slagathor", "Balinda", "Gustov", "Slagathor with a steel chair!"));
        System.out.println(myTreeSet);

        myTreeSet.remove("Slagathor");
        System.out.println(myTreeSet);

//        myTreeSet.add(null); null is not supported in a tree set

        /*
            HashSets are better suited to constant adding/removing of data due to the HashMap backing it. A TreeSet is
            the better option when the natural ordering of your data is important. Keep in mind adding/removing data from
            a TreeSet will potentially trigger the reordering of data in the TreeSet, which can become very time consuming
            as the Set grows
         */


    }
}
