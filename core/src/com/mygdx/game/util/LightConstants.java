package com.mygdx.game.util;

import com.mygdx.game.actors.Item;

public class LightConstants {
    private static float lightBlockyness = 0;

    public static float getLightBlockyness(Item.BlockType blockType) {
        switch (blockType) {
            case GROUND:
                lightBlockyness = 0.1f;
                break;
            case GROUND_GRASS:
                lightBlockyness = 0.1f;
                break;
            case GROUND_GRAVEL:
                lightBlockyness = 0.1f;
                break;
            case STONE:
                lightBlockyness = 0.1f;
                break;
            case STONE_BROWN_IRON:
                lightBlockyness = 0.15f;
                break;
            case STONE_COAL:
                lightBlockyness = 0.15f;
                break;
            case STONE_SILVER:
                lightBlockyness = 0.15f;
                break;
            case STONE_GOLD:
                lightBlockyness = 0.15f;
                break;
            case STONE_IRON:
                lightBlockyness = 0.15f;
                break;
            case STONE_DIAMOND:
                lightBlockyness = 0.1f;
                break;
            case STONE_GRASS:
                lightBlockyness = 0.1f;
                break;
            case GREYSTONE:
                lightBlockyness = 0.2f;
                break;
            case GREYSTONE_RUBY:
                lightBlockyness = 0.15f;
                break;
            case HOLLOW:
                lightBlockyness = 0.05f;
                break;
            case HOLLOW_TORCH_CENTER:
                lightBlockyness = 0.05f;
                break;
            case HOLLOW_TORCH_LEFT:
                lightBlockyness = 0.05f;
                break;
            case HOLLOW_TORCH_RIGHT:
                lightBlockyness = 0.05f;
                break;
            case WATER:
                lightBlockyness = 0.02f;
                break;
            default:
                lightBlockyness = 0;
                break;
        }
        return lightBlockyness;
    }
}
