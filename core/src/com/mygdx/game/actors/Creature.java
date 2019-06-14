package com.mygdx.game.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Creature extends MainActor {

    public enum Direction {
        LEFT,
        RIGHT
    }

    protected boolean flip;
    protected boolean isMove;
    protected boolean rotated;

    protected float velocityY = 0;
    protected float velocityX = 0;
    protected boolean grounded = false;
    protected boolean moving;

    protected CreatureType creatureType;

    protected Direction direction;

    protected Map map;

    public Creature(float x, float y, Stage s, CreatureType creatureType) {
        super(x,y,s);
        direction = Direction.LEFT;
        flip = false;
        isMove = false;
        rotated = false;
        moving = false;

        this.creatureType = creatureType;

    }

    protected abstract void checkDirection();

    public void update(float delta, float gravity) {
//        System.out.println(moving);
        float newY = getY();

        this.velocityY += gravity * getEntityWeight() * delta;
        newY += this.velocityY * delta;

        if(map.isBlockCollidable(getX() , newY, getEntityWidth() / 2, getEntityHeight())) {
            if(this.velocityY < 0) {
                this.setY((float)Math.floor(this.getY()));
                grounded = true;
            }
            this.velocityY = 0;
//            else this.setY(newY);

        }
        else {
            this.setY(newY);
            grounded = false;
        }
    }

    protected void moveX(float amount) {
        float newX = this.getX() + amount;
        if(!map.isBlockCollidable(newX, getY(), getEntityWidth() / 2, getEntityHeight())) {
            this.setX(newX);
        }
    }

    public int getEntityWidth() {
        return creatureType.getWidth();
    }

    public int getEntityHeight() {
        return creatureType.getHeight();
    }

    public float getEntityWeight() {
        return creatureType.getWeight();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public boolean isMoving() {
        return moving;
    }

    public float getVelocityY() {
        return velocityY;
    }
}
