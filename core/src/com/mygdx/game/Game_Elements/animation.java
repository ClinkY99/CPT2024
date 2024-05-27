package com.mygdx.game.Game_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class animation {

    HashMap<String, Animation<TextureRegion>> animation;
    SpriteBatch spriteBatch;
    Texture animSheet;
    HashMap<String, int[]> anim_size;
    float stateTime;
    public animation(String path, String type) throws IOException
    {
        this.animation = load_images(path, type, set_up_states(path, type));
        stateTime=0f;
    }

    public HashMap<String, float[]> set_up_states(String path, String type) throws IOException {
        HashMap<String, float[]> anim_info = new HashMap<>();
        this.anim_size = new HashMap<>();
        Scanner reader = new Scanner(new File(String.format("assets/%s%s/animation", path, type)));
        while (reader.hasNext()) {
            String state = reader.next();
            float duration = reader.nextInt();
            float length = reader.nextFloat();
            int[] size = new int[]{reader.nextInt(), reader.nextInt()};
            float[] info = {duration, length};
            anim_size.put(state, size);
            anim_info.put(state, info);
        }
        return anim_info;
    }

    public HashMap<String, Animation<TextureRegion>> load_images(String path, String type, HashMap<String, float[]> anim_info)
    {
        HashMap<String, Animation<TextureRegion>> animations = new HashMap<>();
        for (String state: anim_info.keySet())
        {
            animSheet = new Texture(Gdx.files.internal(String.format("%s/%s/%s.png", path, type, state)));
            TextureRegion[][] tmp = TextureRegion.split(animSheet, (int) (animSheet.getWidth() / anim_info.get(state)[0]), animSheet.getHeight());
            TextureRegion[] anim_frames = new TextureRegion[(int) anim_info.get(state)[0]];
            if ((int) anim_info.get(state)[0] >= 0)
                System.arraycopy(tmp[0], 0, anim_frames, 0, (int) anim_info.get(state)[0]);
            Animation<TextureRegion> animation = new Animation<>(anim_info.get(state)[1], anim_frames);
            animations.put(state, animation);
        }
        return animations;
    }

    public void render(String state, SpriteBatch batch, boolean loop)
    {
        spriteBatch = batch;
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
        int mid = 885;
        int midy = 465;
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, loop);
        spriteBatch.draw(currentFrame, mid, midy);

    }
    public void dispose() { // SpriteBatches and Textures must always be disposed
        spriteBatch.dispose();
        animSheet.dispose();
    }




}
