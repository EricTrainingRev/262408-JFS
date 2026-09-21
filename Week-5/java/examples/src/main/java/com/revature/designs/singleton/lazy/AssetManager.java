package com.revature.designs.singleton.lazy;

/**
 * Lazy, thread-safe singleton via the Initialization-on-Demand Holder idiom.
 *
 * The nested holder class is not touched until getInstance() reads
 * Holder.INSTANCE, so the asset manager is only built on first use —
 * a match that never loads an asset should never build it.
 *
 * Because the JVM class loader initializes a class exactly once, no one can
 * see the holder half-built and no synchronized is needed. This is the
 * standard way to get laziness and thread safety together.
 */
public class AssetManager {

    private static class Holder {
        private static final AssetManager INSTANCE = new AssetManager();
    }

    private AssetManager() {
        // Pretend this is expensive: opening large files, decoding images, uploading to the GPU, etc
        System.out.println("AssetManager built lazily, only when getInstance() first runs");
    }

    public static AssetManager getInstance() {
        return Holder.INSTANCE;
    }

    public String loadTexture(String textureName) {
        return "texture loaded: " + textureName;
    }

    public static void main(String[] args) {
        System.out.println("-- nothing loaded yet --");

        AssetManager firstHandle = AssetManager.getInstance();
        AssetManager secondHandle = AssetManager.getInstance();

        System.out.println("-- first use has now happened --");
        System.out.println("same instance? " + (firstHandle == secondHandle));
        System.out.println(firstHandle.loadTexture("player.png"));
    }
}