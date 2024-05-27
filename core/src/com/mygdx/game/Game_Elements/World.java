package com.mygdx.game.Game_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;


import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class World extends Stage
{
    public String printOut;
    public static float[] TableScroll = new float[2];
    public Player player1;
    public Boolean allowMovement = true;
    Color[] colorList;
    public float[] scroll;
    HashMap<String, Array<Rectangle>> rectangleHashMap;
    public float[] true_scroll;
    SpriteBatch batch;

    Stage renderBeforePlayerstage;


Stage stage;
    public int[] spawn;

    public World(String path, String player, String level, Stage stage, boolean tint) throws IOException {
        this(path,player,level,stage,tint,null);
    }

    public World(String path, String player, String level, Stage stage, boolean tint, Stage renderBeforePlayerStage) throws IOException
    {
        super(new FitViewport(1920,1080));
        this.stage = stage;
        map(path, level, tint);
        batch = (SpriteBatch) getBatch();
        String playerPath = "Images/Players/";
        spawn = new int[]{(int) ((int) rectangleHashMap.get("Spawn").get(0).getX() + rectangleHashMap.get("Spawn").get(0).getWidth() / 2), (int) ((int) rectangleHashMap.get("Spawn").get(0).getY() + rectangleHashMap.get("Spawn").get(0).getWidth() / 2)};
        player1 = new Player (playerPath, player, spawn);

        colorList = new Color[]{Color.BLACK,Color.GREEN,Color.BLUE,Color.YELLOW,Color.ROYAL,Color.ORANGE,Color.CORAL,Color.RED};

        scroll = new float[]{0, 0};
        true_scroll = new float[]{0, 0};

        this.renderBeforePlayerstage = renderBeforePlayerStage;
    }

    public void map(String path, String level, boolean tint)
    {
        Tiled map = new Tiled(path, level, tint);
        for (Object tile: map.map)
        {
            addActor(tile);
        }
        rectangleHashMap = map.objectLayers;
    }
    public void scrolling()
    {
        true_scroll[0] += (player1.position.x - true_scroll[0] - 885);
        TableScroll[0] = true_scroll[0];
        true_scroll[1] += (player1.position.y- true_scroll[1] - 465);
        TableScroll[1] = true_scroll[1];
        scroll[0] =  true_scroll[0]; scroll[1] =  true_scroll[1];
    }


    public void run()
    {
        run(true);
    }

    public void run(boolean top){
        //only allows player to move if on top

        allowMovement = top;


        if (allowMovement) {
            scrolling();
            player1.update(Gdx.graphics.getDeltaTime(), scroll);
        } else {
            scroll[0] = 0;
            scroll[1] = 0;
        }

        if (printOut != null) {
            System.out.println(printOut);
        }
        for (Actor tile: getActors())
        {
            Object tileCast = (Object) tile;

            tileCast.updatex(scroll[0]);
            tileCast.updatey(scroll[1]);
        }
        for (String key: rectangleHashMap.keySet())
        {

            for (Rectangle rect: rectangleHashMap.get(key)) {
                rect.x -= scroll[0];
                rect.y -= scroll[1];
            }

        }
        stage.getRoot().moveBy(-scroll[0],-scroll[1]);
        draw();
        if(renderBeforePlayerstage != null) {
            renderBeforePlayerstage.getRoot().moveBy(-scroll[0],-scroll[1]);
            renderBeforePlayerstage.act(Gdx.graphics.getDeltaTime());
            renderBeforePlayerstage.draw();
        }
        batch.begin();
        player1.draw(batch);
        batch.end();

        if(allowMovement) {
            player1.isCollidingX = false;
            player1.collision_detectionx(getActors(), rectangleHashMap.get("Collide"));
            player1.isCollidingY = false;
            player1.collision_detectiony(getActors(), rectangleHashMap.get("Collide"));
        }
    }

}
