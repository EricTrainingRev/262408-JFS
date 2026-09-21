package com.revature.designs.factory.method;

/**
 * A concrete creator: decides that createLevel() builds a BattleLevel.
 */
public class BattleLevelFactory extends LevelFactory {

    private String terrain;
    private int waves;

    public BattleLevelFactory(String terrain, int waves) {
        this.terrain = terrain;
        this.waves = waves;
    }

    @Override
    public Level createLevel() {
        return new BattleLevel(terrain,waves);
    }
}
