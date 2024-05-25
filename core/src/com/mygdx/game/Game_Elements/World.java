package com.mygdx.game.Game_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
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
    public int[] scroll;
    HashMap<String, Array<Rectangle>> rectangleHashMap;
    public float[] true_scroll;



Stage stage;
    public World(String path, String level, Stage stage) throws IOException
    {
        super(new FitViewport(1920,1080));
        this.stage = stage;
        map(path, level);
        String playerPath = "assets/Images/Players/";
        int[] spawn = new int[]{(int) ((int) rectangleHashMap.get("Spawn").get(0).getX() + rectangleHashMap.get("Spawn").get(0).getWidth() / 2), (int) ((int) rectangleHashMap.get("Spawn").get(0).getY() + rectangleHashMap.get("Spawn").get(0).getWidth() / 2)};
        player1 = new Player (playerPath, "Player1", spawn);

        colorList = new Color[]{Color.BLACK,Color.GREEN,Color.BLUE,Color.YELLOW,Color.ROYAL,Color.ORANGE,Color.CORAL,Color.RED};

        scroll = new int[]{0, 0};
        true_scroll = new float[]{0, 0};

    }

    public void map(String path, String level)
    {
        Tiled map = new Tiled(path, level);
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
        scroll[0] = (int) true_scroll[0]; scroll[1] = (int) true_scroll[1];
    }


    public void run()
    {
        run(true);
    }

    public void run(boolean top){
        //only allows player to move if on top

        allowMovement = top;
        scrolling();
        if (allowMovement) {
            player1.update(Gdx.graphics.getDeltaTime(), scroll);
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

        getBatch().begin();
        player1.draw((SpriteBatch) getBatch());
        getBatch().end();

        player1.isCollidingX = false;
        player1.collision_detectionx(getActors(), rectangleHashMap.get("Collide"));
        player1.isCollidingY = false;
        player1.collision_detectiony(getActors(), rectangleHashMap.get("Collide"));

    }
}
