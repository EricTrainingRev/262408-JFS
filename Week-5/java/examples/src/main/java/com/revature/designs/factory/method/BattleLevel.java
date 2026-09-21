package com.revature.designs.factory.method;

/**
 * A combat level. Final, as required by the sealed contract.
 */
public final class BattleLevel extends Level {

    private String terrain;
    private int waves;

    public BattleLevel(String terrain, int waves) {
        this.terrain = terrain;
        this.waves = waves;
    }

    @Override
    public String play() {
        return "Fighting through %s terrain against %d waves of enemies.".formatted(terrain, waves);
    }
}
