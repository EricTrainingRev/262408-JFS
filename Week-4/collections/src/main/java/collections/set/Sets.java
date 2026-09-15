package collections.set;

import java.util.HashSet;
import java.util.Set;

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
        for (String element : myHashSet){
            if (element.equals("Slagathor")){
                myElement = element;
                break;
            }
        }
    }
}
