package com.mygdx.game.Game_Elements;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class animation {

    HashMap<String, Animation<TextureRegion>> animation;
    SpriteBatch spriteBatch;
    Texture animSheet;
    float stateTime;
    public animation(String path, String type, float length) throws IOException
    {
        this.animation = load_images(path, type, set_up_states(path, type), length);
        stateTime=0f;
    }

    public HashMap<String, Integer> set_up_states(String path, String type) throws IOException {
        HashMap<String, Integer> anim_info = new HashMap<>();
        Scanner reader = new Scanner(new File(path + type + "/animation"));
        while (reader.hasNext()) {
            String state = reader.next();
            int duration = reader.nextInt();
            anim_info.put(state, duration);
        }
        return anim_info;
    }

    public HashMap<String, Animation<TextureRegion>> load_images(String path, String type, HashMap<String, Integer> anim_info, float length)
    {
        HashMap<String, Animation<TextureRegion>> animations = new HashMap<>();
        for (String state: anim_info.keySet())
        {
            animSheet = new Texture(Gdx.files.internal(path + "/" + type + "/" + state + ".png"));
            TextureRegion[][] tmp = TextureRegion.split(animSheet, animSheet.getWidth() / anim_info.get(state), animSheet.getHeight());
            TextureRegion[] anim_frames = new TextureRegion[anim_info.get(state)];
            for (int i = 0; i < anim_info.get(state); i++)
            {
                anim_frames[i] = tmp[0][i];
            }
            Animation<TextureRegion> animation = new Animation<>(length, anim_frames);
            animations.put(state, animation);
        }
        return animations;
    }

    public void render(String state, SpriteBatch batch)
    {
        spriteBatch =batch;
        Animation<TextureRegion> animation = null;
        try
        {
            animation = this.animation.get(state);
        }
        catch (Exception e)
        {
            System.out.println("Hello World!");
            return;
        }
        stateTime += Gdx.graphics.getDeltaTime();
        int mid =+ Gdx.graphics.getWidth() / 2;
        int midy = Gdx.graphics.getHeight() / 2;
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        spriteBatch.draw(currentFrame, mid, midy);
    }
    public void dispose(String state) { // SpriteBatches and Textures must always be disposed
        spriteBatch.dispose();
        animSheet.dispose();
    }




}
