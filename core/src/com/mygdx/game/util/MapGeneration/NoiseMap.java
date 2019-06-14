package com.mygdx.game.util.MapGeneration;



public class NoiseMap {
    private float[][] map;
    private int widthInCell;
    private int heightInCell;

    public NoiseMap(int width, int height, int cellSize) {

        widthInCell = width / cellSize;
        heightInCell = height / cellSize;

        map = new float[widthInCell][heightInCell];

        Perlin2D perlin = new Perlin2D(5000);
        float value;
        for(int i = 0 ; i < widthInCell; i++) {
            for(int j = 0; j < heightInCell; j++) {
                    value = perlin.noise(i/100f, j/100f, 2, 0.3f);
                    map[i][j] = (int) (value * 255) & 255;
            }
        }
    }

    public float[][] getMap() {
        return map;
    }
}
