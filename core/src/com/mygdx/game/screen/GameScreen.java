package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.actors.Map;
import com.mygdx.game.actors.Player;
import com.mygdx.game.actors.Torch;
import com.mygdx.game.game.MainGame;
import com.mygdx.game.util.Constants;

public class GameScreen extends MainScreen implements InputProcessor {

    private Map map;
    private Player player;
    private Vector3 mousePos;
    private Torch torch;

    @Override
    public void init() {
        map = new Map(0,0,mainStage, Constants.MAP_WIDTH, Constants.MAP_HEIGHT, Constants.TOPMAP_HEIGHT);
        player = new Player((Constants.MAP_WIDTH / 2)*Constants.BLOCK_SIZE,(Constants.MAP_HEIGHT + 30) * Constants.BLOCK_SIZE, mainStage);
        map.setPlayer(player);
        player.setMap(map);
        Gdx.input.setInputProcessor(this);
        mousePos = new Vector3(0,0,0);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.ESCAPE) MainGame.setActiveScreen(new MenuScreen());
        if(keycode == Input.Keys.C) map.deleteBlock((int)mousePos.x / Constants.BLOCK_SIZE, (int)mousePos.y / Constants.BLOCK_SIZE);
        if(keycode == Input.Keys.F) {
            map.setTorches((int)mousePos.x / Constants.BLOCK_SIZE, (int)mousePos.y / Constants.BLOCK_SIZE);
        }
        if(keycode == Input.Keys.Q) {
            map.setTorch(true);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(button == Input.Buttons.LEFT) {
//            map.deleteBlock((int)mousePos.x / Constants.BLOCK_SIZE, (int)mousePos.y / Constants.BLOCK_SIZE);
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        if(player.getCamera() != null) {
            player.getCamera().unproject(mousePos.set(screenX,screenY,0));
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if(amount == 1) player.setCameraZoom(player.getCameraZoom() + .05f);
        if(amount == -1) player.setCameraZoom(player.getCameraZoom() - .05f);
        return false;
    }

    @Override
    public void update(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.C)) map.deleteBlock((int)mousePos.x / Constants.BLOCK_SIZE, (int)mousePos.y / Constants.BLOCK_SIZE);
        map.setPlayerPosX(player.getX());
        map.setPlayerPosY(player.getY());
        map.act(dt);
//        System.out.println(Gdx.graphics.getFramesPerSecond());
    }
}
