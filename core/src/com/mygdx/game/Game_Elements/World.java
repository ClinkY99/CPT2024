package com.mygdx.game.Game_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class World
{

    ArrayList<String> map;
    Player player;
    ArrayList<Object> objects;
    int[] scroll;

    public World(Texture img)
    {
        player = new Player (img);
        objects = new ArrayList<>();
        map(img);
    }

    public void map(Texture img)
    {
        int[] loc = {0, 0};
        for (int i = 0; i < 50; i++)
        {
            loc[0] = i;
            Object tile = new Object(img, loc);
            objects.add(tile);
        }
        loc = new int[]{0, 1};
        for (int i = 1; i < 50; i++)
        {
            loc[1] = i;
            Object tile = new Object(img, loc);
            objects.add(tile);
        }
    }
    public void scrolling()
    {
        float[] true_scroll = new float[]{0, 0};
        scroll = new int[]{0, 0};
        true_scroll[0] += (player.position.x - true_scroll[0] - (float) Gdx.graphics.getWidth() / 2);
        true_scroll[1] += (player.position.y - true_scroll[1] - (float) Gdx.graphics.getHeight() / 2);
        scroll[0] = (int) true_scroll[0]; scroll[1] = (int) true_scroll[1];
    }
    public void loadMap(String path) throws IOException
    {
        FileHandle file = Gdx.files.local(path + "level");
        String objects = file.readString();
        String[] objectList = objects.split("\n");
        String[] parts = path.split("/");
        String name = parts[1];
        for (String object: objectList)
        {
            map.add(path + name + "_" + object + ".csv");
        }
    }

    public void run(SpriteBatch batch)
    {
        scrolling();
        player.update(Gdx.graphics.getDeltaTime(), objects, scroll);
        for (Object tile: objects)
        {
            tile.updatex(scroll[0]);
            tile.draw(batch);
        }
        player.draw(batch);
        player.collision_detectionx(objects, scroll);

        for (Object tile: objects)
        {
            tile.updatey(scroll[1]);
            tile.draw(batch);
        }
        player.collision_detectiony(objects, scroll);
    }
}
