package collections.ordering;


import java.util.Set;
import java.util.TreeSet;

/*
    If we ever intend to allow for the natural ordering of our custom classes we need
    to implement the Comparable interface. This interface gives access to the
    compareTo method which we override with our own comparison algorithm
 */
public class Person implements Comparable<Person> {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public int compareTo(Person o) {
        // this tells our code that we want to naturally organize
        // the Person class by the name value
        // return this.name.compareTo(o.name);
        int ageGap = this.age - o.age;
//        if(ageGap == 0){
//            return this.name.compareTo(o.name);
//        }
        return ageGap;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        Person slagathor = new Person("Slagathor", 1000);
        Person billy = new Person("Billy", 20);
        Person sally = new Person("Sally", 20);

        /*
            NOTE: because we do not handle ages being the same our set will not include
            sally here due to her being added in after billy, and since they have the same age they
            are "equal" as far as ordering goes, and Sets do not allow duplicates
         */
        TreeSet<Person> peopleOrganizedByAge = new TreeSet<>(Set.of(slagathor,billy,sally));
        System.out.println(peopleOrganizedByAge);

        /*
            Because we are ordering by name instead of age no values will be calculated as equal and therfore
            all three Persons will be included in our TreeSet
         */
        TreeSet<Person> peopleOrganizedByName = new TreeSet<>(new SortByName());
        peopleOrganizedByName.addAll(Set.of(slagathor,sally,billy));
        sally.age = 19;
        System.out.println(peopleOrganizedByName);
    }
}
