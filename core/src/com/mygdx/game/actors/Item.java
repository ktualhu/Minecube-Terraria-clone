package com.mygdx.game.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Item extends MainActor {

    public enum BlockType {
        EMPTY,
        GROUND,
        GROUND_GRASS,
        GROUND_GRAVEL,
        STONE,
        STONE_BROWN_IRON,
        STONE_COAL,
        STONE_SILVER,
        STONE_GOLD,
        STONE_IRON,
        STONE_DIAMOND,
        STONE_GRAVEL,
        STONE_GRASS,
        GREYSTONE,
        GREYSTONE_RUBY,
        HOLLOW,
        HOLLOW_TORCH_CENTER,
        HOLLOW_TORCH_LEFT,
        HOLLOW_TORCH_RIGHT,
        WATER,
        BOTTOM_TREE,
        MIDDLE_TREE,
        LEAVES_TRANSPARENT,
        GRASS1,
        GRASS2,
        GRASS3,
        GRASS4,
        // 27

        PICK,
        SWORD,
        AXE,
        HOE,
        HUMMER,
        BOW,
        FLAIL;
    }

    public Item(float x, float y, Stage s) {
        super(x,y,s);
    }

}
