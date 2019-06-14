package com.mygdx.game.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.actors.Item;
import com.mygdx.game.asset.Assets;

public class Tiles {
    public static final TextureRegion EMPTY = Assets.instance.emptyBlock.empty;
    public static final TextureRegion GROUND = Assets.instance.groundBlock.ground;
    public static final TextureRegion GROUND_GRASS = Assets.instance.groundGrassBlock.groundGrass;
    public static final TextureRegion GROUND_GRAVEL = Assets.instance.groundGravelBlock.groundGravel;
    public static final TextureRegion STONE = Assets.instance.stoneBlock.stone;
    public static final TextureRegion STONE_BROWN_IRON = Assets.instance.stoneBrownIronBlock.stoneBrownIron;
    public static final TextureRegion STONE_COAL = Assets.instance.stoneCoalBlock.stoneCoal;
    public static final TextureRegion STONE_SILVER = Assets.instance.stoneSilverBlock.stoneSilver;
    public static final TextureRegion STONE_GOLD = Assets.instance.stoneGoldBlock.stoneGold;
    public static final TextureRegion STONE_IRON = Assets.instance.stoneIronBlock.stoneIron;
    public static final TextureRegion STONE_DIAMOND = Assets.instance.stoneDiamondBlock.stoneDiamond;
    public static final TextureRegion STONE_GRASS = Assets.instance.stoneGrassBlock.stoneGrass;
    public static final TextureRegion GREYSTONE = Assets.instance.greyStoneBlock.greyStone;
    public static final TextureRegion GREYSTONE_RUBY = Assets.instance.greyStoneRubyBlock.greyStoneRuby;
    public static final TextureRegion HOLLOW = Assets.instance.hollowBlock.hollow;
    public static final TextureRegion HOLLOW_TORCH_CENTER = Assets.instance.hollowBlockTorchCenter.hollowTorchCenter;
    public static final TextureRegion HOLLOW_TORCH_LEFT = Assets.instance.hollowBlockTorchLeft.hollowTorchLeft;
    public static final TextureRegion HOLLOW_TORCH_RIGHT = Assets.instance.hollowBlockTorchRight.hollowTorchRight;
    public static final TextureRegion WATER = Assets.instance.waterBlock.water;
    public static final TextureRegion BOTTOM_TREE = Assets.instance.treeBottomBlock.treeBottom;
    public static final TextureRegion MIDDLE_TREE = Assets.instance.treeMiddleBlock.treeMiddle;
    public static final TextureRegion LEAVES_TRANSPARENT = Assets.instance.leavesTransparentBlock.leavesTransparent;
    public static final TextureRegion GRASS1 = Assets.instance.grassBlock.grass.get(0);
    public static final TextureRegion GRASS2 = Assets.instance.grassBlock.grass.get(1);
    public static final TextureRegion GRASS3 = Assets.instance.grassBlock.grass.get(2);
    public static final TextureRegion GRASS4 = Assets.instance.grassBlock.grass.get(3);

    private Tiles() {}

    public static Sprite getSprite(Item.BlockType blockType) {
        Sprite s;
        switch (blockType) {
            case GROUND:
                s = new Sprite(GROUND);
                break;
            case GROUND_GRASS:
                s = new Sprite(GROUND_GRASS);
                break;
            case GROUND_GRAVEL:
                s = new Sprite(GROUND_GRAVEL);
                break;
            case STONE:
                s = new Sprite(STONE);
                break;
            case STONE_BROWN_IRON:
                s = new Sprite(STONE_BROWN_IRON);
                break;
            case STONE_COAL:
                s = new Sprite(STONE_COAL);
                break;
            case STONE_SILVER:
                s = new Sprite(STONE_SILVER);
                break;
            case STONE_GOLD:
                s = new Sprite(STONE_GOLD);
                break;
            case STONE_IRON:
                s = new Sprite(STONE_IRON);
                break;
            case STONE_DIAMOND:
                s = new Sprite(STONE_DIAMOND);
                break;
            case STONE_GRASS:
                s = new Sprite(STONE_GRASS);
                break;
            case GREYSTONE:
                s = new Sprite(GREYSTONE);
                break;
            case GREYSTONE_RUBY:
                s = new Sprite(GREYSTONE_RUBY);
                break;
            case HOLLOW:
                s = new Sprite(HOLLOW);
                break;
            case HOLLOW_TORCH_CENTER:
                s = new Sprite(HOLLOW_TORCH_CENTER);
                break;
            case HOLLOW_TORCH_LEFT:
                s = new Sprite(HOLLOW_TORCH_LEFT);
                break;
            case HOLLOW_TORCH_RIGHT:
                s = new Sprite(HOLLOW_TORCH_RIGHT);
                break;
            case WATER:
                s = new Sprite(WATER);
                break;
            case BOTTOM_TREE:
                s = new Sprite(BOTTOM_TREE);
                break;
            case MIDDLE_TREE:
                s = new Sprite(MIDDLE_TREE);
                break;
            case LEAVES_TRANSPARENT:
                s = new Sprite(LEAVES_TRANSPARENT);
                break;
            case GRASS1:
                s = new Sprite(GRASS1);
                break;
            case GRASS2:
                s = new Sprite(GRASS2);
                break;
            case GRASS3:
                s = new Sprite(GRASS3);
                break;
            case GRASS4:
                s = new Sprite(GRASS3);
                break;
            default:
                s = new Sprite(EMPTY);
                break;
        }
        return s;
    }

}
