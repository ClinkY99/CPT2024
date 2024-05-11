package com.mygdx.game.Game_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;


public class Tiled
{
    HashMap<String, Texture> image;
    Array<Object> map;
    public Tiled(String path, String level)
    {
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
            String type = reader.next();
            tile_info.put(type, import_csv_files(path + "/" + level, type));
            this.image.put(type, nocuttingtoday("Images/tiles" + "/" + type + ".png"));
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

    public Texture nocuttingtoday(String path)
    {
        Texture image = new Texture(Gdx.files.internal(path));
        return image;
    }

    public Array<Object> import_map(String path, String level) throws IOException {
        HashMap<String, Array<String[]>> info = read_tile_info(path, level);
        this.map = new Array<>();

        for (String type: info.keySet())
        {

            int size = info.get(type).size;

            for (int i = 0; i < size; i++)
            {
                for (int j = info.get(type).get(i).length - 1; j > -1; j--)
                {
                    if (!info.get(type).get(i)[j].equals("-1"))
                    {
                        map.add(new Object(this.image.get(type), new int[]{j, (size - 1) - i}));
                    }
                }
            }
        }

        return map;
    }
}
