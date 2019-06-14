package com.mygdx.game.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.asset.Assets;

public abstract class MainGame extends Game {
    private static MainGame game;

    public MainGame() {
        game = this;
    }

    public static void setActiveScreen(Screen s) {
        game.setScreen(s);
    }

    @Override
    public void dispose() {
        super.dispose();
        game.getScreen().dispose();
        Assets.instance.dispose();
    }
}
