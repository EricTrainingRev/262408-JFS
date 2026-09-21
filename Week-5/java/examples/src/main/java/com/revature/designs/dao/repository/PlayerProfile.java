package com.revature.designs.dao.repository;

import com.revature.designs.dao.crud.Player;
import java.util.List;

/**
 * The domain aggregate the repository hands to callers: a player together with
 * everything tied to them that the game treats as one unit. It is assembled
 * from more than one DAO underneath.
 */
public record PlayerProfile(Player player, List<String> inventory) {
}