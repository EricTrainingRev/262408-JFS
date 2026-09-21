package com.revature.designs.dao.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A low-level DAO for a player's inventory, keyed by player id. It is
 * storage-shaped and knows nothing about the player record DAO.
 */
public class InventoryDao {

    private final Map<Integer, List<String>> storage = new HashMap<>();

    public List<String> findByPlayerId(int playerId) {
        return storage.getOrDefault(playerId, List.of());
    }

    public void replace(int playerId, List<String> inventory) {
        storage.put(playerId, new ArrayList<>(inventory));
    }
}