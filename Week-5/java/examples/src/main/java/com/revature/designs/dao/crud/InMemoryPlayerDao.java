package com.revature.designs.dao.crud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A concrete DAO backed by a Map instead of a real database. It exists so you
 * can see that swapping storage — here, to anything else — never touches the
 * callers of PlayerDao.
 */
public class InMemoryPlayerDao implements PlayerDao {

    private final Map<Integer, Player> storage = new HashMap<>();

    @Override
    public void insert(Player player) {
        storage.put(player.id(), player);
    }

    @Override
    public Optional<Player> findById(int id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Player> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void update(Player player) {
        storage.put(player.id(), player);
    }

    @Override
    public void delete(int id) {
        storage.remove(id);
    }
}