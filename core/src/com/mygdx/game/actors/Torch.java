package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Torch extends MainActor {
    private int id;
    private float posX, posY;
    private boolean active;
    private Sprite torchSprite;
    public Torch(float x, float y, Stage s) {
        super(x, y, s);
        posX = x;
        posY = y;
        active = true;
        torchSprite = new Sprite(new Texture(Gdx.files.internal("images/actors/torch.png")));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Sprite getTorchSprite() {
        return torchSprite;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(active) {
            torchSprite.setColor(1,1,1,1);
            torchSprite.setPosition(posX, posY);
            torchSprite.setSize(15,20);
            torchSprite.draw(batch);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
