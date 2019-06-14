package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.game.MainGame;

public class MenuScreen extends MainScreen {
    @Override
    public void init() {

    }

    @Override
    public void update(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            System.out.println("GO TO GAME SCREEN");
            MainGame.setActiveScreen(new GameScreen());
        }
    }
}
