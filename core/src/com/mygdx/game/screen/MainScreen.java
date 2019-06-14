package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.asset.Assets;

public abstract class MainScreen implements Screen {
    protected Stage mainStage;
    protected Stage uiStage;

    public MainScreen() {
        mainStage = new Stage();
        uiStage = new Stage();
        init();
    }

    public abstract void init();

    public abstract void update(float dt);

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        uiStage.act(delta);
        mainStage.act(delta);

        update(delta);

//        Gdx.gl.glClearColor(176f/255f,233f/255f,252f/255f,1);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        Gdx.gl.glBlendFunc(Gdx.gl20.GL_ONE, Gdx.gl20.GL_ONE);

        mainStage.draw();
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        mainStage.dispose();
        uiStage.dispose();
    }
}
