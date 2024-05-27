package com.mygdx.game.Game_Elements;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


public class Tiled
{
    HashMap<String, HashMap<String, Texture>> image;
    Array<Object> map;

    HashMap<String, Array<Rectangle>> objectLayers;
    HashMap<String, Integer> num;
    TiledMap tiledMap;
    public Tiled(String path, String level, boolean tint)
    {
        num = new HashMap<>();
        objectLayers = new HashMap<>();
        try
        {
            import_map(path, level, tint);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public HashMap<String, Array<String[]>> read_tile_info(String path, String level) throws IOException
    {
        Scanner reader = new Scanner (new File (path + "/" + level));

        HashMap<String, Array<String[]>> tile_info = new HashMap<>();
        this.image = new HashMap<>();
        this.tiledMap = new TmxMapLoader().load(String.format("%s/%s.tmx", path, level));
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
        Scanner reader = new Scanner (new File ( path + "/tile"));
        HashMap<String, Texture> tile = new HashMap<>();
        int i = 0;
        while (reader.hasNext())
        {
            try
            {
                Texture image = new Texture(path + "/" + reader.nextLine() + ".png");
                tile.put(String.valueOf(i), image);
            }
            catch (Exception e)
            {
                tile.put(String.valueOf(i), null);
            }
            i++;
        }
        num.put(type, i);
        return tile;
    }

    public void import_map(String path, String level, boolean tint) throws IOException {
        HashMap<String, Array<String[]>> info = read_tile_info(path, level);
        this.map = new Array<>();
        int q = 0;
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
                        if (tint)
                        {
                            obj.setColor(Color.BLUE);
                        }
                        map.add(obj);

                    }
                }
            }
            map.add(new Object(this.image.get(type).get("3"), new int[]{0, q}, true));
            q++;
        }
        for (MapLayer layer: tiledMap.getLayers())
        {
            Array<Rectangle> rect_info = new Array<>();
            for (MapObject object:layer.getObjects())
            {
                 Iterator<java.lang.Object> i = object.getProperties().getValues();
                 float[] properties = new float[4];
                 Rectangle rect = null;
                 objectLayers.put(object.getName(), rect_info);
                 while (i.hasNext())
                 {
                     properties[0] = (float) i.next();
                     properties[1] = (float) i.next();
                     i.next();
                     properties[2] = (float) i.next();
                     properties[3] = (float) i.next();
                     rect = new Rectangle(properties[0], properties[2], properties[3], properties[1]);
                     objectLayers.get(object.getName()).add(rect);
                 }
                 if (object.getName().equals("Spawn")) rect_info = new Array<>();
            }

        }


    }

    public Object getTile(int tileId, String type, boolean collide)
    {

        int Flipped180 = 0x60000000;
        int Flipped90 = -0x60000000;
        int Flipped270 = -0x40000000;

        int FlippedVer = -0x80000000;
        int FlippedHor = 0x40000000;
        int FlippedWeird = 0x20000000;


        if (tileId <= FlippedVer + num.get(type))
        {
            tileId -= FlippedVer;
            Object image = new Object(this.image.get(type).get(String.valueOf(tileId)),new int[]{0,0},collide);
            image.setSize(-image.getWidth(), image.getHeight());
            return image;
        }
        else if(tileId >= Flipped180){
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
        else if (tileId >= FlippedHor)
        {
            tileId -= FlippedHor;
            Object image = new Object(this.image.get(type).get(String.valueOf(tileId)),new int[]{0,0},collide);
            image.setSize(image.getWidth(), -image.getHeight());
            return image;
        }
        else if(tileId <= -FlippedWeird + num.get(type))
        {
            tileId += FlippedWeird;
            Object image = new Object(this.image.get(type).get(String.valueOf(tileId)),new int[]{0,0},collide);
            image.setRotation(90);
            return image;
        }
        else if (tileId >= FlippedWeird)
        {
            tileId -= FlippedWeird;
            Object image = new Object(this.image.get(type).get(String.valueOf(tileId)),new int[]{0,0},collide);
            image.setSize(-image.getWidth(), -image.getHeight());
            return image;
        }
        return new Object(this.image.get(type).get(String.valueOf(tileId)),new int[]{0,0},collide);

    }

}
