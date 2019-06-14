package com.mygdx.game.actors;

public enum CreatureType {
    PLAYER("player", 50,40,40);

    private int width;
    private int height;
    private float weight;

    CreatureType(String id, int width, int height, float weight) {
        this.width = width;
        this.height = height;
        this.weight = weight;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }
}
