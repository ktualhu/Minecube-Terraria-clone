package com.mygdx.game.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.util.Constants;

import java.util.ArrayList;

public class Assets implements AssetErrorListener, Disposable {
    private static final String tag = Assets.class.getName();
    public static final Assets instance = new Assets();
    private AssetManager assetManager;

    // Tiles Objects
    public EmptyBlock emptyBlock;
    public GroundBlock groundBlock;
    public GroundGrassBlock groundGrassBlock;
    public GroundGravelBlock groundGravelBlock;
    public HollowBlock hollowBlock;
    public HollowBlockTorchCenter hollowBlockTorchCenter;
    public HollowBlockTorchLeft hollowBlockTorchLeft;
    public HollowBlockTorchRight hollowBlockTorchRight;
    public StoneBlock stoneBlock;
    public StoneBrownIronBlock stoneBrownIronBlock;
    public StoneCoalBlock stoneCoalBlock;
    public StoneDiamondBlock stoneDiamondBlock;
    public StoneDirtBlock stoneDirtBlock;
    public StoneGoldBlock stoneGoldBlock;
    public StoneGrassBlock stoneGrassBlock;
    public StoneIronBlock stoneIronBlock;
    public StoneSilverBlock stoneSilverBlock;
    public GreyStoneBlock greyStoneBlock;
    public GreyStoneRubyBlock greyStoneRubyBlock;
    public WaterBlock waterBlock;
    public TreeBottomBlock treeBottomBlock;
    public TreeMiddleBlock treeMiddleBlock;
    public LeavesTransparentBlock leavesTransparentBlock;
    public GrassBlock grassBlock;

    // weapon
    public Pick pick;
    public Sword sword;

    // Actors Objects
    public FemaleSprite femaleSprite;
    public SkeletonSprite skeletonSprite;

    // Fonts
    public AssetFont font;

    private Assets() {}

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
        assetManager.finishLoading();

        Gdx.app.debug(tag, "Assets loaded: " + assetManager.getAssetNames().size);
        for(String s : assetManager.getAssetNames()) Gdx.app.debug(tag, "asset: " + s);

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
        for(Texture t : atlas.getTextures()) {
            t.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        }

        // Create Tiles Objects
        emptyBlock = new EmptyBlock(atlas);
        groundBlock = new GroundBlock(atlas);
        groundGrassBlock = new GroundGrassBlock(atlas);
        groundGravelBlock = new GroundGravelBlock(atlas);
        hollowBlock = new HollowBlock(atlas);
        hollowBlockTorchCenter = new HollowBlockTorchCenter(atlas);
        hollowBlockTorchLeft = new HollowBlockTorchLeft(atlas);
        hollowBlockTorchRight = new HollowBlockTorchRight(atlas);
        stoneBlock = new StoneBlock(atlas);
        stoneBrownIronBlock = new StoneBrownIronBlock(atlas);
        stoneCoalBlock = new StoneCoalBlock(atlas);
        stoneDiamondBlock = new StoneDiamondBlock(atlas);
        stoneDirtBlock = new StoneDirtBlock(atlas);
        stoneGoldBlock = new StoneGoldBlock(atlas);
        stoneGrassBlock = new StoneGrassBlock(atlas);
        stoneIronBlock = new StoneIronBlock(atlas);
        stoneSilverBlock = new StoneSilverBlock(atlas);
        greyStoneBlock = new GreyStoneBlock(atlas);
        greyStoneRubyBlock = new GreyStoneRubyBlock(atlas);
        waterBlock = new WaterBlock(atlas);
        treeBottomBlock = new TreeBottomBlock(atlas);
        treeMiddleBlock = new TreeMiddleBlock(atlas);
        leavesTransparentBlock = new LeavesTransparentBlock(atlas);
        grassBlock = new GrassBlock(atlas);

        // weapon
        pick = new Pick(atlas);
        sword = new Sword(atlas);

        //Create Actors Objects
        femaleSprite = new FemaleSprite(atlas);
        skeletonSprite = new SkeletonSprite(atlas);

        // fonts
        font = new AssetFont();
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(tag, "Could not load asset " + asset.fileName + ". EXCEPTION: " + (Exception)throwable);
    }

    // Tiles Objects
    public class EmptyBlock {
        public final AtlasRegion empty;

        public EmptyBlock(TextureAtlas atlas) {
            empty = atlas.findRegion("empty");
        }
    }

    public class GroundBlock {
        public final AtlasRegion ground;

        public GroundBlock(TextureAtlas atlas) {
            ground = atlas.findRegion("ground");
        }
    }

    public class GroundGrassBlock {
        public final AtlasRegion groundGrass;

        public GroundGrassBlock(TextureAtlas atlas) {
            groundGrass = atlas.findRegion("ground_grass");
        }
    }

    public class GroundGravelBlock {
        public final AtlasRegion groundGravel;

        public GroundGravelBlock(TextureAtlas atlas) {
            groundGravel = atlas.findRegion("ground_gravel");
        }
    }

    public class HollowBlock {
        public final AtlasRegion hollow;

        public HollowBlock(TextureAtlas atlas) {
            hollow = atlas.findRegion("hollow");
        }
    }

    public class HollowBlockTorchCenter {
        public final AtlasRegion hollowTorchCenter;

        public HollowBlockTorchCenter(TextureAtlas atlas) {
            hollowTorchCenter = atlas.findRegion("hollow_torch_center");
        }
    }

    public class HollowBlockTorchLeft {
        public final AtlasRegion hollowTorchLeft;

        public HollowBlockTorchLeft(TextureAtlas atlas) {
            hollowTorchLeft = atlas.findRegion("hollow_torch_left");
        }
    }

    public class HollowBlockTorchRight {
        public final AtlasRegion hollowTorchRight;

        public HollowBlockTorchRight(TextureAtlas atlas) {
            hollowTorchRight = atlas.findRegion("hollow_torch_right");
        }
    }

    public class StoneBlock {
        public final AtlasRegion stone;

        public StoneBlock(TextureAtlas atlas) {
            stone = atlas.findRegion("stone");
        }
    }

    public class StoneBrownIronBlock {
        public final AtlasRegion stoneBrownIron;

        public StoneBrownIronBlock(TextureAtlas atlas) {
            stoneBrownIron = atlas.findRegion("stone_browniron");
        }
    }

    public class StoneCoalBlock {
        public final AtlasRegion stoneCoal;

        public StoneCoalBlock(TextureAtlas atlas) {
            stoneCoal = atlas.findRegion("stone_coal");
        }
    }

    public class StoneDiamondBlock {
        public final AtlasRegion stoneDiamond;

        public StoneDiamondBlock(TextureAtlas atlas) {
            stoneDiamond = atlas.findRegion("stone_diamond");
        }
    }

    public class StoneDirtBlock {
        public final AtlasRegion stoneDirt;

        public StoneDirtBlock(TextureAtlas atlas) {
            stoneDirt = atlas.findRegion("stone_dirt");
        }
    }

    public class StoneGoldBlock {
        public final AtlasRegion stoneGold;

        public StoneGoldBlock(TextureAtlas atlas) {
            stoneGold = atlas.findRegion("stone_gold");
        }
    }

    public class StoneGrassBlock {
        public final AtlasRegion stoneGrass;

        public StoneGrassBlock(TextureAtlas atlas) {
            stoneGrass = atlas.findRegion("stone_grass");
        }
    }

    public class StoneIronBlock {
        public final AtlasRegion stoneIron;

        public StoneIronBlock(TextureAtlas atlas) {
            stoneIron = atlas.findRegion("stone_iron");
        }
    }

    public class StoneSilverBlock {
        public final AtlasRegion stoneSilver;

        public StoneSilverBlock(TextureAtlas atlas) {
            stoneSilver = atlas.findRegion("stone_silver");
        }
    }

    public class GreyStoneBlock {
        public final AtlasRegion greyStone;

        public GreyStoneBlock(TextureAtlas atlas) {
            greyStone = atlas.findRegion("greystone");
        }
    }

    public class GreyStoneRubyBlock {
        public final AtlasRegion greyStoneRuby;

        public GreyStoneRubyBlock(TextureAtlas atlas) {
            greyStoneRuby = atlas.findRegion("greystone_ruby");
        }
    }

    public class WaterBlock {
        public final AtlasRegion water;

        public WaterBlock(TextureAtlas atlas) {
            water = atlas.findRegion("water");
        }
    }

    public class TreeBottomBlock {
        public final AtlasRegion treeBottom;

        public TreeBottomBlock(TextureAtlas atlas) {
            treeBottom = atlas.findRegion("trunk_bottom");
        }
    }

    public class TreeMiddleBlock {
        public final AtlasRegion treeMiddle;

        public TreeMiddleBlock(TextureAtlas atlas) {
            treeMiddle = atlas.findRegion("trunk_mid");
        }
    }

    public class LeavesTransparentBlock {
        public final AtlasRegion leavesTransparent;

        public LeavesTransparentBlock(TextureAtlas atlas) {
            leavesTransparent = atlas.findRegion("leaves_transparent");
        }
    }

    public class GrassBlock {
        public final ArrayList<AtlasRegion> grass = new ArrayList<AtlasRegion>();

        public GrassBlock(TextureAtlas atlas) {
            AtlasRegion grass1 = atlas.findRegion("grass1");
            AtlasRegion grass2 = atlas.findRegion("grass2");
            AtlasRegion grass3 = atlas.findRegion("grass3");
            AtlasRegion grass4 = atlas.findRegion("grass4");

            grass.add(grass1);
            grass.add(grass2);
            grass.add(grass3);
            grass.add(grass4);
        }
    }

    public class Pick {
        public final AtlasRegion pick;

        public Pick(TextureAtlas atlas) {
            pick = atlas.findRegion("pick_silver");
        }
    }

    public class Sword {
        public final AtlasRegion sword;

        public Sword(TextureAtlas atlas) {
            sword = atlas.findRegion("sword_silver");
        }
    }

    // Actors Objects
    public class  FemaleSprite {
        public final ArrayList<AtlasRegion> female = new ArrayList<AtlasRegion>();

        public FemaleSprite(TextureAtlas atlas) {
            AtlasRegion atlasRegionArm = atlas.findRegion("female_arm");
            female.add(0, atlasRegionArm);
            AtlasRegion atlasRegionBody = atlas.findRegion("female_body");
            female.add(1, atlasRegionBody);
            AtlasRegion atlasRegionHead = atlas.findRegion("female_head");
            female.add(2, atlasRegionHead);
            AtlasRegion atlasRegionLeg = atlas.findRegion("female_leg");
            female.add(3, atlasRegionLeg);
            AtlasRegion atlasRegionHandPickSilver = atlas.findRegion("hand_pick_silver");
            female.add(4, atlasRegionHandPickSilver);
            AtlasRegion atlasRegionHandSwordSilver = atlas.findRegion("hand_sword_silver");
            female.add(5, atlasRegionHandSwordSilver);
        }

    }

    public class SkeletonSprite {
        public final ArrayList<AtlasRegion> skeleton = new ArrayList<AtlasRegion>();

        public SkeletonSprite(TextureAtlas atlas) {
            AtlasRegion atlasRegionArm = atlas.findRegion("skeleton_arm");
            skeleton.add(0, atlasRegionArm);
            AtlasRegion atlasRegionBody = atlas.findRegion("skeleton_body");
            skeleton.add(1, atlasRegionBody);
            AtlasRegion atlasRegionHead = atlas.findRegion("skeleton_head");
            skeleton.add(2, atlasRegionHead);
            AtlasRegion atlasRegionLeg = atlas.findRegion("skeleton_leg");
            skeleton.add(3, atlasRegionLeg);
//            AtlasRegion atlasRegionHandPickSilver = atlas.findRegion("hand_pick_silver");
//            skeleton.add(4, atlasRegionHandPickSilver);
//            AtlasRegion atlasRegionHandSwordSilver = atlas.findRegion("hand_sword_silver");
//            skeleton.add(5, atlasRegionHandSwordSilver);
        }

    }

//    public AssetFonts fonts;

    public class AssetFont {
        public BitmapFont small;

        public AssetFont() {
            small = new BitmapFont(Gdx.files.internal("images/ui/Kenney Future Narrow.fnt"), false);
            small.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
    }
}
