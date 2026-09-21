package com.revature.designs.dao.repository;

import com.revature.designs.dao.crud.Player;

import java.util.List;

/**
 * The domain-facing layer above the DAOs. It composes two low-level DAOs into
 * one PlayerProfile, so callers get "a player's whole profile" from a single
 * method instead of juggling tables. This is the repository's reason to exist.
 */
public class PlayerRepository {

    private final PlayerDataDao playerDataDao;
    private final InventoryDao inventoryDao;

    public PlayerRepository(PlayerDataDao playerDataDao, InventoryDao inventoryDao) {
        this.playerDataDao = playerDataDao;
        this.inventoryDao = inventoryDao;
    }

    public PlayerProfile loadProfile(int playerId) {
        Player player = playerDataDao.findById(playerId).orElseThrow();
        List<String> inventory = inventoryDao.findByPlayerId(playerId);
        return new PlayerProfile(player, inventory);
    }

    public void saveProfile(PlayerProfile profile) {
        playerDataDao.upsert(profile.player());
        inventoryDao.replace(profile.player().id(), profile.inventory());
    }
}