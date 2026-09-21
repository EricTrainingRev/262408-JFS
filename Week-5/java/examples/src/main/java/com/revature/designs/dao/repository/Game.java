package com.revature.designs.dao.repository;

import com.revature.designs.dao.crud.Player;

import java.util.List;

/**
 * The service/client layer. It talks only to the repository — never to the
 * DAOs — so it sees a full player profile as a single unit even though saving
 * it touches both the player record and the inventory.
 */
public class Game {

    public static void main(String[] args) {
        PlayerRepository repository = new PlayerRepository(new PlayerDataDao(), new InventoryDao());

        PlayerProfile profile = new PlayerProfile(
            new Player(1, "Sally", 5, 100, 200),
            List.of("sword", "potion", "shield"));

        System.out.println("-- one repository call spans two DAOs --");
        repository.saveProfile(profile);

        PlayerProfile loaded = repository.loadProfile(1);
        System.out.println("loaded player: " + loaded.player());
        System.out.println("loaded inventory: " + loaded.inventory());
    }
}