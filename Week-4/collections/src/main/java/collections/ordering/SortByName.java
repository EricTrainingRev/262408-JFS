package collections.ordering;

import java.util.Comparator;

/*
    You can use the Comparator interface to set up custom rules for organizing your classes. This can be passed
    to any method or collection that does natural ordering and it will use this compare method instead of the
    default compareTo of the sorted objects to facilitate the sorting
 */
public class SortByName implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.name.compareTo(o2.name);
    }
}
