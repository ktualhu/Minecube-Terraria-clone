package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.asset.Assets;

public class Player extends Humanoid {

    private static final int SPEED = 200;
    private static final int JUMP_VELOCITY = 5;

    private OrthographicCamera camera;
    private Viewport viewport;

    public Player(float x, float y, Stage s) {
        super(x,y,s);
        init();
    }

    protected void init() {
        super.init();
        setSprites();

    }

    private void setSprites() {
        setRegions();
        leftArm = new Sprite(regions.get(0));
        rightArm = new Sprite(regions.get(0));
        body = new Sprite(regions.get(1));
        head = new Sprite(regions.get(2));
        leftLeg = new Sprite(regions.get(3));
        rightLeg = new Sprite(regions.get(3));
    }

    private void setRegions() {
        regions.add(Assets.instance.femaleSprite.female.get(0));
        regions.add(Assets.instance.femaleSprite.female.get(1));
        regions.add(Assets.instance.femaleSprite.female.get(2));
        regions.add(Assets.instance.femaleSprite.female.get(3));
        regions.add(Assets.instance.femaleSprite.female.get(4));
        regions.add(Assets.instance.femaleSprite.female.get(5));
    }

    protected void cameraAlign() {
        camera = (OrthographicCamera)this.getStage().getCamera();
        viewport = this.getStage().getViewport();

        camera.position.set(this.getX() + this.getEntityWidth() / 2, this.getY() + this.getEntityHeight() / 2, 0);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCameraZoom(float zoom) {
        camera.zoom = zoom;
    }

    public float getCameraZoom() {
        return camera.zoom;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        cameraAlign();
        camera.update();
        moving = false;

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && grounded) {
            super.velocityY += JUMP_VELOCITY * getEntityWeight();
        }
        if(!grounded && this.velocityY > 0) {
            super.velocityY += JUMP_VELOCITY * getEntityWeight() * delta;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            setDirection(Direction.LEFT);
            moveX(-SPEED * delta);
            moving = true;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            setDirection(Direction.RIGHT);
            moveX(SPEED * delta);
            moving = true;
        }
    }

}
