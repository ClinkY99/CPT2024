package com.mygdx.game.Game_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;


import com.badlogic.gdx.utils.viewport.FitViewport;

import java.io.IOException;

public class World extends Stage
{
    public String printOut;
    public static float[] TableScroll = new float[2];
    public Player player1;
    public Boolean allowMovement = true;
    Color[] colorList;
    public int[] scroll;
    public float[] true_scroll;



    public World(String path, String level) throws IOException
    {
        super(new FitViewport(1920,1080));
        String playerPath = "Images/Players/";
        player1 = new Player (playerPath, "Player2");

        colorList = new Color[]{Color.BLACK,Color.GREEN,Color.BLUE,Color.YELLOW,Color.ROYAL,Color.ORANGE,Color.CORAL,Color.RED};
        map(path, level);
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

        draw();

        getBatch().begin();
        player1.draw((SpriteBatch) getBatch());
        getBatch().end();

        player1.isCollidingX = false;
        player1.collision_detectionx(getActors());
        player1.isCollidingY = false;
        player1.collision_detectiony(getActors());

    }
}
