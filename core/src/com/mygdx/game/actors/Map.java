package com.mygdx.game.actors;

import box2dLight.DirectionalLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.util.Constants;
import com.mygdx.game.util.LightUtil;
import com.mygdx.game.util.MapGeneration.NoiseMap;
import com.mygdx.game.util.MapGeneration.Topmap;
import com.mygdx.game.util.Tiles;

import java.util.ArrayList;
import java.util.Random;

public class Map extends MainActor {
    private int width;
    private int height;
    private int topmapHeight;
    private int topmapMaxHeight;
    private double random = 0;

    private float playerPosX;
    private float playerPosY;

    private Topmap topmap;
    private NoiseMap undergroundMap;

    private double[] topmapDefault; // contains top map vertexes
    private float[][] undergroundMapDefault;
    private Item.BlockType[][] topmapUpdated;
    private Item.BlockType[][] undergroundMapUpdated;
    private double[] vertexes;

    private Item.BlockType[][] map;

    private Player player;

    private String vertexShader;
    private String fragmentShader;
    private ShaderProgram shaderProgram;

    // light
    private float[][] mapLight;
    private LightUtil lightUtil;
    private float lightValue;
    private boolean torch = false;
    private ArrayList<Torch> torches;
    private RayHandler rayHandler;
    private DirectionalLight pointLight;
    private World world;

    public Map(float x, float y, Stage s, int width, int height, int topmapHeight) {
        super(x,y,s);
        this.width = width;
        this.height = height;
        this.topmapHeight = topmapHeight;
        topmapMaxHeight = topmapHeight + 200;
        init();
    }

    private void init() {
        playerPosX = 1000;
        playerPosY = 1000;

        topmap = new Topmap(width);
        undergroundMap = new NoiseMap(width,height,1);

        topmapDefault = topmap.getResult();
        undergroundMapDefault = undergroundMap.getMap();

        vertexes = new double[width];

        topmapUpdated = new Item.BlockType[width][topmapMaxHeight];
        undergroundMapUpdated = new Item.BlockType[width][height];

        topmapUpdated = rebuildTopmap(topmapDefault);
        undergroundMapUpdated = rebuildMap(undergroundMapDefault);

        map = new Item.BlockType[width][height + topmapMaxHeight];
        map = combineMaps(topmapUpdated, undergroundMapUpdated);

        topmapUpdated = null;
        undergroundMapUpdated = null;
        undergroundMapDefault = null;

//        vertexShader = Gdx.files.internal("shaders/basicLight.vs.glsl").readString();
//        fragmentShader = Gdx.files.internal("shaders/basicLight.fs.glsl").readString();
//        shaderProgram = new ShaderProgram(vertexShader, fragmentShader);



        lightUtil = new LightUtil(width, height + topmapMaxHeight);
        lightUtil.fillMapLight(map, vertexes);
        mapLight = lightUtil.getMapLight();
        torches = new ArrayList<Torch>();


        // init box2dlight
//        world = new World(new Vector2(0,-10), true);
//        rayHandler = new RayHandler(world);
//        rayHandler.setAmbientLight(0,0,0,.2f);
//        pointLight = new DirectionalLight(rayHandler, 5000, new Color(1,1,1, .5f), 230);
//        pointLight.setXray(true);

    }

    private Item.BlockType[][] rebuildTopmap(double[] topmap) {
        for(int i = 0; i < width; i++) {
            if(topmap[i] < 0) topmap[i] *= -1;
            for(int j = 0; j < topmapMaxHeight; j++) { ;
                topmapUpdated[i][j] = Item.BlockType.EMPTY;
            }
        }

        for(int i = 0; i < width; i++) {
            if(topmap[i] < 0) topmap[i] *= -1;
            for(int j = 0; j <= (int)topmap[i]; j++) {
                if(j == (int)topmap[i]) {
                    random = new Random().nextDouble();
                    setSideObjects(i,j,random);
                    if(random < .005f) topmapUpdated[i][j] = Item.BlockType.STONE_GRASS;
                    else topmapUpdated[i][j] = Item.BlockType.GROUND_GRASS;
                    vertexes[i] = (int)topmap[i] + Constants.MAP_HEIGHT;
                }
                else if(j < topmap[i]) {
                    random = new Random().nextDouble();
                    if(random < .005f) topmapUpdated[i][j] = Item.BlockType.STONE;
                    else if(random < .2f) topmapUpdated[i][j] = Item.BlockType.GROUND_GRAVEL;
                    else topmapUpdated[i][j] = Item.BlockType.GROUND;
                }
                else topmapUpdated[i][j] = Item.BlockType.EMPTY;
            }
        }
        return topmapUpdated;
    }

    private Item.BlockType[][] rebuildMap(float[][] map) {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                if((map[i][j] > 84 && map[i][j] <= 160)) {
                    random = new Random().nextDouble();
                    if(random < 0.0005) undergroundMapUpdated[i][j] = Item.BlockType.GREYSTONE_RUBY;
                    else if(random < 0.0009) undergroundMapUpdated[i][j] = Item.BlockType.GREYSTONE;
                    else if(random < 0.005) undergroundMapUpdated[i][j] = Item.BlockType.STONE_DIAMOND;
                    else if(random < 0.008) undergroundMapUpdated[i][j] = Item.BlockType.STONE_GOLD;
                    else if(random < 0.01) undergroundMapUpdated[i][j] = Item.BlockType.STONE_SILVER;
                    else if(random < 0.02) undergroundMapUpdated[i][j] = Item.BlockType.STONE_BROWN_IRON;
                    else if(random < 0.03) undergroundMapUpdated[i][j] = Item.BlockType.STONE_IRON;
                    else if(random < 0.08) undergroundMapUpdated[i][j] = Item.BlockType.STONE_COAL;
                    else if(random < 0.1) undergroundMapUpdated[i][j] = Item.BlockType.STONE_GRAVEL;
                    else if(random < 0.5) undergroundMapUpdated[i][j] = Item.BlockType.STONE;
                    else undergroundMapUpdated[i][j] = Item.BlockType.GROUND_GRAVEL;

                }

                else if((map[i][j] >= 185 && map[i][j] <= 255) || (map[i][j] >= 0 && map[i][j] <= 14) || (map[i][j] >= 40 && map[i][j] <= 84)) {
                    // ground
                    random = new Random().nextDouble();
                    if(random < 0.00005) undergroundMapUpdated[i][j] = Item.BlockType.GREYSTONE_RUBY;
                    else if(random < 0.00007) undergroundMapUpdated[i][j] = Item.BlockType.GREYSTONE;
                    else if(random < 0.005) undergroundMapUpdated[i][j] = Item.BlockType.STONE;
                    else if(random < 0.015) undergroundMapUpdated[i][j] = Item.BlockType.STONE_COAL;
                    else if(random < 0.08) undergroundMapUpdated[i][j] = Item.BlockType.GROUND_GRAVEL;
                    else undergroundMapUpdated[i][j] = Item.BlockType.GROUND;
                }
                else if(map[i][j] > 160 && map[i][j] <= 184) {
                    // water
                    undergroundMapUpdated[i][j] = Item.BlockType.HOLLOW;

                }
                else if(map[i][j] >= 15 && map[i][j] <= 39) {
                    undergroundMapUpdated[i][j] = Item.BlockType.HOLLOW;
                }
                else undergroundMapUpdated[i][j] = Item.BlockType.HOLLOW;
            }
        }
        return undergroundMapUpdated;
    }

    private Item.BlockType[][] combineMaps(Item.BlockType[][] topmap, Item.BlockType[][] undergroundMap) {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height + topmapMaxHeight; j++) {
                if(j < height) map[i][j] = undergroundMap[i][j];
                else if(j >= height && j <= height + topmapMaxHeight) map[i][j] = topmap[i][j - height];
            }
        }
        return map;
    }

    private void setSideObjects(int x, int y, double random) {
        if(random < 0.08 && (x > 5) && (x % 3 == 0) && (x != Constants.MAP_WIDTH - 2)) {
            setTree(x, y);
        }
        else if(random < 0.5 && x % 2 == 0) {
            setGrass(x,y);
        }
        else if(random < 0.001 && x % 5 == 0) {
//            setEnemies(x, y);
        }
    }

    private void setTree(int x, int y) {
        topmapUpdated[x][y+1] = Item.BlockType.BOTTOM_TREE;
        int rand = MathUtils.random(5,10);
        for(int i = y + 2; i <= y + 1 + rand; i++) {
            topmapUpdated[x][i] = Item.BlockType.MIDDLE_TREE;
        }
        for(int i = x - 1; i <= x + 1; i++) {
            for(int j = y + rand; j < y + rand + 3; j++) {
                topmapUpdated[i][j] = Item.BlockType.LEAVES_TRANSPARENT;
            }
        }
    }

    private void setGrass(int x, int y) {
        int rgrass = MathUtils.random(1,4);
        switch (rgrass) {
            case 1:
                topmapUpdated[x][y+1] = Item.BlockType.GRASS1;
                break;
            case 2:
                topmapUpdated[x][y+1] = Item.BlockType.GRASS2;
                break;
            case 3:
                topmapUpdated[x][y+1] = Item.BlockType.GRASS3;
                break;
            case 4:
                topmapUpdated[x][y+1] = Item.BlockType.GRASS4;
                break;
        }
    }

    public void setPlayerPosX(float playerPosX) {
        this.playerPosX = playerPosX;
    }

    public void setPlayerPosY(float playerPosY) {
        this.playerPosY = playerPosY;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isTorch() {
        return torch;
    }

    public void setTorch(boolean torch) {
        this.torch = torch;
    }

    public void setTorches(int x, int y) {
//        System.out.println(torch.getPosX() + " " + torch.getPosY());
//        Torch torch = new Torch(x,y,getStage());
//        torches.add(torch);
//        System.out.println(torches.size());
        if(map[x][y] == Item.BlockType.HOLLOW) {
            if(map[x-1][y] != Item.BlockType.HOLLOW) map[x][y] = Item.BlockType.HOLLOW_TORCH_LEFT;
            else if(map[x+1][y] != Item.BlockType.HOLLOW) map[x][y] = Item.BlockType.HOLLOW_TORCH_RIGHT;
            else map[x][y] = Item.BlockType.HOLLOW_TORCH_CENTER;
        }
    }

    public boolean isBlockCollidable(float playerPosX, float playerPosY, int width, int height) {
        for(int i = (int)playerPosX / Constants.BLOCK_SIZE; i < Math.ceil((playerPosX + width) / Constants.BLOCK_SIZE); i++) {
            for(int j = (int)playerPosY / Constants.BLOCK_SIZE; j < Math.ceil((playerPosY + height) / Constants.BLOCK_SIZE); j++) {
                if(map[i][j] != Item.BlockType.EMPTY && map[i][j] != Item.BlockType.HOLLOW && map[i][j] != Item.BlockType.GRASS1
                    && map[i][j] != Item.BlockType.GRASS2 && map[i][j] != Item.BlockType.GRASS3 && map[i][j] != Item.BlockType.GRASS4
                    && map[i][j] != Item.BlockType.LEAVES_TRANSPARENT && map[i][j] != Item.BlockType.BOTTOM_TREE
                    && map[i][j] != Item.BlockType.MIDDLE_TREE) return true;
            }
        }
        return false;
    }

    public void deleteBlock(int x, int y) {
        if(map[x][y] != Item.BlockType.EMPTY && map[x][y] != Item.BlockType.HOLLOW) {
            if(y > height) {
                map[x][y] = Item.BlockType.EMPTY;
                double vertex = vertexes[x];
                vertex--;
                vertexes[x] = vertex;
            }
            else {
                if(map[x][y] == Item.BlockType.HOLLOW_TORCH_CENTER || map[x][y] == Item.BlockType.HOLLOW_TORCH_LEFT ||
                        map[x][y] == Item.BlockType.HOLLOW_TORCH_RIGHT) {
                    lightUtil.repealTorchLight(map, x,y);
                }
                map[x][y] = Item.BlockType.HOLLOW;
            }
        }
    }

    @Override
    public void act(float delta) {
        player.update(delta, -9.8f);
        lightUtil.fillMapLight(map, vertexes, player.getX(), player.getY());
        if(torch) {
            if(player.isMoving() || !player.grounded) {
                lightUtil.repealTorchLight(map, (int)player.getX() / Constants.BLOCK_SIZE, (int)player.getY() / Constants.BLOCK_SIZE);
                lightUtil.fillMapLight(map, (int)player.getX() / Constants.BLOCK_SIZE, (int)player.getY() / Constants.BLOCK_SIZE);
            }
            else {
                lightUtil.fillMapLight(map, (int)player.getX() / Constants.BLOCK_SIZE, (int)player.getY() / Constants.BLOCK_SIZE);
            }
//            lightUtil.fillMapLight(map, (int)player.getX() / Constants.BLOCK_SIZE, (int)player.getY() / Constants.BLOCK_SIZE);
        }
//        pointLight.setPosition(player.getX(), player.getY());
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for(int i = ((int)playerPosX - (100 * Constants.BLOCK_SIZE))/Constants.BLOCK_SIZE; i < (playerPosX + (100 * Constants.BLOCK_SIZE))/Constants.BLOCK_SIZE; i++) {
            for(int j = ((int)playerPosY - (100 * Constants.BLOCK_SIZE))/Constants.BLOCK_SIZE; j < (playerPosY + (100 * Constants.BLOCK_SIZE))/Constants.BLOCK_SIZE; j++) {
                lightValue = mapLight[i][j];
////                batch.setColor(0,1,0, lightValue);
                batch.setColor(lightValue, lightValue, lightValue, 1f);

                if(map[i][j] == Item.BlockType.GROUND) batch.draw(Tiles.GROUND, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.GROUND_GRAVEL) batch.draw(Tiles.GROUND_GRAVEL, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.STONE) batch.draw(Tiles.STONE, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.STONE_COAL) batch.draw(Tiles.STONE_COAL, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.STONE_GOLD) batch.draw(Tiles.STONE_GOLD, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.STONE_DIAMOND) batch.draw(Tiles.STONE_DIAMOND, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.STONE_BROWN_IRON) batch.draw(Tiles.STONE_BROWN_IRON, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.STONE_IRON) batch.draw(Tiles.STONE_IRON, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.STONE_SILVER) batch.draw(Tiles.STONE_SILVER, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.GREYSTONE) batch.draw(Tiles.GREYSTONE, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.GREYSTONE_RUBY) batch.draw(Tiles.GREYSTONE_RUBY, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.WATER) batch.draw(Tiles.WATER, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.GROUND_GRASS) batch.draw(Tiles.GROUND_GRASS, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.STONE_GRASS) batch.draw(Tiles.STONE_GRASS, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.BOTTOM_TREE) batch.draw(Tiles.BOTTOM_TREE, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.MIDDLE_TREE) batch.draw(Tiles.MIDDLE_TREE, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.LEAVES_TRANSPARENT) batch.draw(Tiles.LEAVES_TRANSPARENT, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.GRASS1) batch.draw(Tiles.GRASS1, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.GRASS2) batch.draw(Tiles.GRASS2, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.GRASS3) batch.draw(Tiles.GRASS3, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.GRASS4) batch.draw(Tiles.GRASS4, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.HOLLOW) batch.draw(Tiles.HOLLOW, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                else if(map[i][j] == Item.BlockType.HOLLOW_TORCH_CENTER || map[i][j] == Item.BlockType.HOLLOW_TORCH_LEFT ||
                        map[i][j] == Item.BlockType.HOLLOW_TORCH_RIGHT) {
                    lightUtil.fillMapLight(map, i,j);
                    lightValue = mapLight[i][j];
                    batch.setColor(lightValue, lightValue, lightValue, 1f);
                    if(map[i][j] == Item.BlockType.HOLLOW_TORCH_CENTER) batch.draw(Tiles.HOLLOW_TORCH_CENTER, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                    else if(map[i][j] == Item.BlockType.HOLLOW_TORCH_LEFT) batch.draw(Tiles.HOLLOW_TORCH_LEFT, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                    else if(map[i][j] == Item.BlockType.HOLLOW_TORCH_RIGHT) batch.draw(Tiles.HOLLOW_TORCH_RIGHT, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
                }
                else batch.draw(Tiles.EMPTY, i*Constants.BLOCK_SIZE,j*Constants.BLOCK_SIZE,Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
            }
        }
    }
}
