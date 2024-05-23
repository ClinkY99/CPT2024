package com.mygdx.game.Game_Elements;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class Tiled
{
    HashMap<String, HashMap<String, Texture>> image;
    Array<Object> map;
    HashMap<String, Integer> num;
    public Tiled(String path, String level)
    {
        num = new HashMap<>();
        try
        {
            import_map(path, level);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public HashMap<String, Array<String[]>> read_tile_info(String path, String level) throws IOException
    {
        Scanner reader = new Scanner (new File (path + "/" + level));
        HashMap<String, Array<String[]>> tile_info = new HashMap<>();
        this.image = new HashMap<>();
        while (reader.hasNext())
        {
            String type = reader.nextLine();
            tile_info.put(type, import_csv_files(path + "/" + level, type));
            this.image.put(type, woodcutting(("assets/Images/tiles" + "/" + type), type));
        }
        return tile_info;
    }
    public Array<String[]> import_csv_files(String path, String type) throws IOException
    {
        Array<String[]> game_map = new Array<>();
        Scanner reader = new Scanner(new File (path + "_" + type + ".csv"));
        while (reader.hasNext())
        {
            game_map.add(reader.nextLine().split(",", 0));
        }
        return game_map;
    }

    public HashMap<String, Texture> woodcutting(String path, String type) throws FileNotFoundException {
        Scanner reader = new Scanner (new File (path + "/tile"));
        HashMap<String, Texture> tile = new HashMap<>();
        int i = 0;
        while (reader.hasNext())
        {
            Texture image = new Texture(path + "/" + reader.nextLine() + ".png");
            tile.put(String.valueOf(i), image);
            i++;
        }
        num.put(type, i);
        return tile;
    }

    public void import_map(String path, String level) throws IOException {
        HashMap<String, Array<String[]>> info = read_tile_info(path, level);
        this.map = new Array<>();

        for (String type: info.keySet())
        {

            int size = info.get(type).size;

            for (int i = 0; i < size; i++)
            {
                for (int j = info.get(type).get(i).length - 1; j > -1; j--)
                {
                    String num = info.get(type).get(i)[j];
                    if (!num.equals("-1")) {
                        Object obj;
                        if (type.equals("Floors"))
                        {
                            obj = getTile(Integer.parseInt(num), type, false);
                        }
                        else
                        {
                            obj = getTile(Integer.parseInt(num), type, true);
                        }
                        obj.setPosition(j,(size-1)-i);
                        map.add(obj);

                    }
                }
            }
        }
    }

    public Object getTile(int tileId, String type, boolean collide)
    {

        int Flipped180 = 0x60000000;
        int Flipped90 = -0x60000000;
        int Flipped270 = -0x40000000;

        if(tileId >= Flipped180){
            tileId = tileId - Flipped180;
            Object image = new Object(this.image.get(type).get(String.valueOf(tileId)),new int[]{0,0},collide);
            image.setRotation(90);
            return image;
        }
        else if(tileId <= Flipped90 + num.get(type)){
            tileId-= Flipped90;
            Object image = new Object(this.image.get(type).get(String.valueOf(tileId)),new int[]{0,0},collide);
            image.setRotation(270);
            return image;
        }
        else if (tileId <= Flipped270 + num.get(type)){
            tileId-= Flipped270;
            Object image = new Object(this.image.get(type).get(String.valueOf(tileId)),new int[]{0,0},collide);
            image.setRotation(180);
            return image;
        }
        return new Object(this.image.get(type).get(String.valueOf(tileId)),new int[]{0,0},collide);

    }

}
