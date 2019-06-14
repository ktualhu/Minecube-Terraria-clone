package com.mygdx.game.game;

import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.game.asset.Assets;
import com.mygdx.game.screen.GameScreen;

public class MinecubeGame extends MainGame {
    @Override
    public void create() {
        Assets.instance.init(new AssetManager());
        setActiveScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        super.dispose();

    }
}
