package com.revature.designs.dao.crud;

/**
 * Runs the full CRUD cycle against the in-memory DAO. The caller (Game) only
 * knows the PlayerDao interface — it never sees how the data is stored.
 */
public class Game {

    public static void main(String[] args) {
        PlayerDao dao = new InMemoryPlayerDao();

        System.out.println("-- create --");
        dao.insert(new Player(1, "Billy", 5, 100, 50));
        dao.insert(new Player(2, "Sally", 3, 80, 20));

        System.out.println("-- read --");
        System.out.println("found id=1: " + dao.findById(1).orElseThrow());
        System.out.println("players in store: " + dao.findAll().size());

        System.out.println("-- update --");
        dao.update(new Player(1, "Billy Supreme", 6, 100, 200));
        System.out.println("after update, id=1: " + dao.findById(1).orElseThrow());

        System.out.println("-- delete --");
        dao.delete(2);
        System.out.println("players in store after delete: " + dao.findAll().size());
    }
}