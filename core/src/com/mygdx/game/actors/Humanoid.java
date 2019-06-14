package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Humanoid extends Creature {
    protected Sprite head;
    protected Sprite body;
    protected Sprite leftArm;
    protected Sprite rightArm;
    protected Sprite leftLeg;
    protected Sprite rightLeg;

    protected Sprite testPlayer;
    protected ShapeRenderer shape;
    protected OrthographicCamera camera;

    private String vertexShader;
    private String fragmentShader;
    private ShaderProgram shaderProgram;

    public Humanoid(float x, float y, Stage s) {
        super(x,y,s, CreatureType.PLAYER);
        init();
        shape = new ShapeRenderer();
    }

    protected void init() {

        testPlayer = new Sprite(new Texture(Gdx.files.internal("images/actors/testplayer.png")));

        vertexShader = Gdx.files.internal("shaders/basicLight.vs.glsl").readString();
        fragmentShader = Gdx.files.internal("shaders/basicLight.fs.glsl").readString();
        shaderProgram = new ShaderProgram(vertexShader, fragmentShader);

        if(!shaderProgram.isCompiled()) System.out.println(shaderProgram.getLog());
    }

    private void initSprites() {
        rightArm.setOrigin(.25f, 1f);
        rightArm.setSize(3f, 8);
        rightArm.setScale(1, 1);

        leftLeg.setOrigin(.25f, 1f);
        leftLeg.setSize(3f, 8);
        leftLeg.setScale(1, 1);

        rightLeg.setOrigin(.25f, 1f);
        rightLeg.setSize(3f, 8);
        rightLeg.setScale(1, 1);

        body.setOrigin(.5f, 2.1f);
        body.setSize(5f, 8);
        body.setScale(1, 1);

        leftArm.setOrigin(.25f, 1f);
        leftArm.setSize(1.5f, 8);
        leftArm.setScale(1, 1);

        head.setOrigin(.5f, .5f);
        head.setSize(5f, 5);
        head.setScale(1, 1);
    }

    @Override
    protected void checkDirection() {
        if(direction == Direction.LEFT) {
            rightArm.setFlip(false, false);
            leftArm.setFlip(false, false);
            leftLeg.setFlip(false, false);
            rightLeg.setFlip(false, false);
            body.setFlip(false, false);
            head.setFlip(false, false);
            testPlayer.setFlip(true, false);
//            if(anyItem || block) currentItem.setFlip(false, false);
        }
        else if(direction == Direction.RIGHT) {
            rightArm.setFlip(true, false);
            leftArm.setFlip(true, false);
            leftLeg.setFlip(true, false);
            rightLeg.setFlip(true, false);
            body.setFlip(true, false);
            head.setFlip(true, false);
            testPlayer.setFlip(false, false);
//            if(anyItem || block) currentItem.setFlip(true, false);
        }
    }

    private void idle() {
        if(direction == Direction.LEFT) {
//            emptyFullHand();

            leftLeg.setPosition(this.getX() - .38f, this.getY() - 1.38f);
            leftLeg.setRotation(0);

            rightLeg.setPosition(this.getX() - .38f, this.getY() - 1.38f);
            rightLeg.setRotation(0);
//            System.out.println(getX() + " " + getY());

            body.setPosition(this.getX() - .5f, this.getY() - .38f);
            body.setRotation(0);

            leftArm.setPosition(this.getX() - .25f, this.getY() - .38f);
            leftArm.setRotation(0);
        }

        else if(direction == Direction.RIGHT) {
//            emptyFullHand();

            leftLeg.setPosition(this.getX() - .13f, this.getY() - 1.38f);
            leftLeg.setRotation(0);

            rightLeg.setPosition(this.getX() - .13f, this.getY() - 1.38f);
            rightLeg.setRotation(0);

            body.setPosition(this.getX() - .25f, this.getY() - .38f);
            body.setRotation(0);

            leftArm.setPosition(this.getX() - .25f, this.getY() - .38f);
            leftArm.setRotation(0);
        }
        head.setPosition(this.getX() - .5f, this.getY() + .37f);
        head.setRotation(0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        checkDirection();
        idle();
        initSprites();

//        System.out.println(leftLeg.getY() + " left leg");
//        System.out.println(body.getY() + " body");
//        System.out.println(head.getY() + " head");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
//        if(anyItem) currentItem.draw(batch);
//        else rightArm.draw(batch);
//        rightArm.draw(batch);
//        leftLeg.draw(batch);
//        rightLeg.draw(batch);
//        body.draw(batch);
//        leftArm.draw(batch);
//        head.draw(batch);

//        if(camera!=null) shaderProgram.setUniformMatrix("u_projTrans", camera.combined);
//        shaderProgram.setUniformf("u_lightPos", new Vector2(getX(), getY()));
//        batch.setShader(shaderProgram);

        testPlayer.setColor(.5f,.5f,.5f, 1f);
        testPlayer.setPosition(getX(), getY());
        testPlayer.setSize(getEntityWidth(), getEntityHeight());
        testPlayer.draw(batch);
    }

}
