package collections.maps;

import java.util.*;
import java.util.Map.Entry;

public class Maps {
    /*
        Maps are the Java way of handling key-value pairs. Similar to the other types
        we have looked at in the collections API there are differing implementations for
        how the data structure stores and manages its data, but all of your Maps will
        fundamentally be key-value systems for storing your data
     */

    public static void main(String[] args) {
        // hashmap has string keys and string value
        HashMap<String,String> employees = new HashMap<>();
        // hashmap has integer keys and string values
        HashMap<Integer, String> anotherExample = new HashMap<>();

        // this will create OR update the value of a key
        employees.put("CEO","Slagathor");
        // this will create the key-value pair if the key does not already have a value
        employees.putIfAbsent("VP", "Sally");
        employees.put("Janitor", "Billy");

        // get either returns the value or null if the key does not exist
        System.out.println(employees.get("CEO"));
        // if you are not sure if the key exists you can use getOrDefault to return a default value if the key is not present
        System.out.println(employees.getOrDefault("CFO","Still need to hire"));

        employees.put("CFO", "Godzilla");
        employees.remove("CFO");

        /*
            You won't always know what keys/values you have access to, so it is sometimes necessary to iterate over
            your key-value pairs, or some parts of those parings, for code to function properly
         */

        Set<Entry<String,String>> entries = employees.entrySet();
        for(Entry<String,String> entry : entries){
            System.out.println();
            System.out.println(entry);
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            entry.setValue("Ditto");
        }
        for (Entry<String,String> entry : entries){
            System.out.println(entry);
        }

        System.out.println();

        Set<String> keys = employees.keySet();
        for(String key : keys){
            System.out.println(key);
        }

        System.out.println();

        Collection<String> values = employees.values();
        for(String value: values){
            System.out.println(value);
        }

        // if the ordering of your key-value pairs is important you can use a TreeMap instead
        TreeMap<String, String> suspects = new TreeMap<>();
        suspects.put("Highly suspicious", "Meowth");
        suspects.put("Medium suspicious", "James");
        suspects.put("Low suspicious", "Jessie");

        System.out.println(suspects);

    }
}
