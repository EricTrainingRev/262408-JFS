package com.revature.designs.dao.crud;

import java.util.List;
import java.util.Optional;

/**
 * The DAO contract: every operation a caller may need to persist a Player.
 * The interface says nothing about how the data is stored, which is what lets
 * callers swap databases without changing a line of business code.
 */
public interface PlayerDao {

    void insert(Player player);

    Optional<Player> findById(int id);

    List<Player> findAll();

    void update(Player player);

    void delete(int id);
}