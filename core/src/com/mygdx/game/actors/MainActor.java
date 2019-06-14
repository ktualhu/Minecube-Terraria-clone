package com.mygdx.game.actors;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public abstract class MainActor extends Actor {
    protected Array<TextureRegion> regions;

    public MainActor(float x, float y, Stage s) {
        super();
        setPosition(x,y);
        s.addActor(this);
        regions = new Array<TextureRegion>();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
