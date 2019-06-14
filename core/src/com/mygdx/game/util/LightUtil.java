package com.mygdx.game.util;

import com.mygdx.game.actors.Item;

public class LightUtil {
    private int widht;
    private int height;
    private float[][] mapLight;
    private float lightBlockAmount;

    public LightUtil(int width, int height) {
        this.widht = width;
        this.height = height;
        mapLight = new float[width][height];
        initLight();
    }

    private void initLight() {
        for(int i = 0; i < widht; i++) {
            for(int j = 0; j < height; j++) {
                mapLight[i][j] = 0.03f;
            }
        }
    }

    public void fillMapLight(Item.BlockType[][] map, double[] topmap) {
        for(int i = 0; i < widht; i++) {
            for(int j = 0; j < height; j++) {
                if(j == (int)topmap[i] && (map[i][j] != Item.BlockType.EMPTY && map[i][j] != Item.BlockType.HOLLOW
                        && map[i][j] != Item.BlockType.HOLLOW_TORCH_CENTER  && map[i][j] != Item.BlockType.HOLLOW_TORCH_LEFT
                        && map[i][j] != Item.BlockType.HOLLOW_TORCH_RIGHT)) applyLightDown(map[i][j], i, j, 1);
                else if(j > (int)topmap[i]) mapLight[i][j] = 1;

            }
        }
    }

    public void fillMapLight(Item.BlockType[][] map, double[] topmap, float playerPosX, float playerPosY) {
        for(int i = ((int)playerPosX - (100 * Constants.BLOCK_SIZE))/Constants.BLOCK_SIZE; i < (playerPosX + (100 * Constants.BLOCK_SIZE))/Constants.BLOCK_SIZE; i++) {
            for(int j = ((int)playerPosY - (500 * Constants.BLOCK_SIZE))/Constants.BLOCK_SIZE; j < (playerPosY + (100 * Constants.BLOCK_SIZE))/Constants.BLOCK_SIZE; j++) {
                if(j == (int)topmap[i] && (map[i][j] != Item.BlockType.EMPTY
                        && map[i][j] != Item.BlockType.HOLLOW_TORCH_CENTER  && map[i][j] != Item.BlockType.HOLLOW_TORCH_LEFT
                        && map[i][j] != Item.BlockType.HOLLOW_TORCH_RIGHT)) applyLightDown(map[i][j], i, j, 1);
                else if(j > (int)topmap[i]) mapLight[i][j] = 1;
                else if(j < topmap[i]) {
                    if(!(mapLight[i][j] >= 0.03f)) mapLight[i][j] = .03f;
                }

            }
        }
    }

    // torch
    public void fillMapLight(Item.BlockType[][] map, int x, int y) {
        applyLightTorch(map[x][y], x,y, 1f);
    }

    public void repealTorchLight(Item.BlockType[][] map, int x, int y) {
        deleteLightTorch(map[x][y], x, y, -1f);
    }

    public void applyLightDown(Item.BlockType blockType, int x, int y, float lastLight) {
        if(x < 0 || x > widht || y < 0 || y > height) return;
        float newLight = lastLight - getLightBlockAmount(blockType);
        if(newLight <= getMapLight(x,y)) return;

        setMapLight(x,y,newLight);

        applyLightDown(blockType, x, y - 1, newLight);
    }

    public void applyLightTorch(Item.BlockType blockType, int x, int y, float lastLight) {
        if(x < 0 || x > widht || y < 0 || y > height) return;
        float newLight = lastLight - getLightBlockAmount(blockType);
        if(newLight <= getMapLight(x,y)) return;

        setMapLight(x,y,newLight);

//        applyLightTorch(blockType, x + 1, y + 1, newLight);
//        applyLightTorch(blockType, x - 1, y - 1, newLight);
//        applyLightTorch(blockType, x + 1, y - 1, newLight);
//        applyLightTorch(blockType, x - 1, y + 1, newLight);
        applyLightTorch(blockType, x + 1, y, newLight);
        applyLightTorch(blockType, x, y + 1, newLight);
        applyLightTorch(blockType, x - 1, y, newLight);
        applyLightTorch(blockType, x, y - 1, newLight);
    }

    public void deleteLightTorch(Item.BlockType blockType, int x, int y, float lastLight) {
        if(x < 0 || x > widht || y < 0 || y > height) return;
        float newLight = lastLight + getLightBlockAmount(blockType);
        if(newLight >= getMapLight(x,y)) return;

        setMapLight(x,y,newLight);

//        deleteLightTorch(blockType, x + 1, y + 1, newLight);
//        deleteLightTorch(blockType, x - 1, y - 1, newLight);
//        deleteLightTorch(blockType, x + 1, y - 1, newLight);
//        deleteLightTorch(blockType, x - 1, y + 1, newLight);
        deleteLightTorch(blockType, x + 1, y, newLight);
        deleteLightTorch(blockType, x, y + 1, newLight);
        deleteLightTorch(blockType, x - 1, y, newLight);
        deleteLightTorch(blockType, x, y - 1, newLight);
    }

    public void setMapLight(int x, int y, float lightValue) {
        mapLight[x][y] = lightValue;
    }

    public float getMapLight(int x, int y) {
        return mapLight[x][y];
    }

    public float getLightBlockAmount(Item.BlockType blockType) {
        lightBlockAmount = LightConstants.getLightBlockyness(blockType);
        return lightBlockAmount;
    }

    public float[][] getMapLight() {
        return mapLight;
    }
}
