package com.revature.designs.dao.crud;

/**
 * A record is the ideal "plain data object" for a DAO to carry in and out: it
 * bundles the player's fields with zero boilerplate, and is immutable by design.
 */
public record Player(int id, String name, int level, int health, int gold) {
}