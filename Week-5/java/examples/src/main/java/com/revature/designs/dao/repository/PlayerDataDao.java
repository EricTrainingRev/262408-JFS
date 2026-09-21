package com.revature.designs.dao.repository;

import com.revature.designs.dao.crud.Player;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A low-level DAO for the player's core record. Like the CRUD example, it is
 * storage-shaped; it does not know what a full profile means.
 */
public class PlayerDataDao {

    private final Map<Integer, Player> storage = new HashMap<>();

    public Optional<Player> findById(int id) {
        return Optional.ofNullable(storage.get(id));
    }

    public void upsert(Player player) {
        storage.put(player.id(), player);
    }
}