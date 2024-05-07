package com.mygdx.game.Game_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Player
{
    public Vector2 position, positionChange;
    public Sprite player;
    public HashMap<String, ArrayList<String>> State;
    public float speed = 2000;
    public float move;
    String path;
    Rectangle player_rect;
    int[] data;
    public boolean isCollidingX;
    public boolean isCollidingY;
    public animation animation;
    public String state;



    public Player(String path) throws IOException
    {

        this.path = path;
        animation = new animation(path, "player",  0.1f);
        state = "Idle/Forward";
        int mid =+ Gdx.graphics.getWidth() / 2;
        int midy = Gdx.graphics.getHeight() / 2;
        position = new Vector2(mid, midy);
        positionChange = new Vector2(0, 0);
        player = new Sprite();
        data = new int[]{0, 0, 0, 0};
        // need to subtract the width and height divided by 2 to put player in middle
        player.setPosition(position.x, position.y);
        System.out.println(player.getWidth());
        player_rect = new Rectangle(position.x , position.y, 150, 150);

    }



    public void move(float deltaTime)
    {
        move = speed * deltaTime;
        boolean press = false;
        positionChange.x = positionChange.y = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.A))
        {
            positionChange.x = -1;
            state = "Running/left";
            press = true;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.D))
        {
            positionChange.x = 1;
            state = "Running/right";
            press = true;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.W))
        {
            positionChange.y = 1;
            state = "Running/Back";
            press = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S))
        {
            positionChange.y = -1;
            state = "Running/Forward";
            press = true;
        }

        if (!press)
        {
            state = "Idle/Forward";
        }

    }

    public void collision_detectionx(ArrayList<Object> tiles, int[] scroll)
    {
        position.x += move * positionChange.x;
        player_rect.setX((int) position.x);
        for (Object tile: tiles)
        {
            if (player_rect.overlaps(tile.getObject_rect()))
            {
                isCollidingX = true;
                float right = tile.getObject_rect().getX() + tile.getWidth();
                float left = tile.getObject_rect().getX() - tile.getWidth();

                if (positionChange.x < 0)
                {
                    position.x = right;
                    player_rect.setX(position.x);
                }
                else if (positionChange.x > 0)
                {
                    position.x = left;
                    player_rect.setX(position.x);
                }
            }
        }
    }

    public void collision_detectiony(ArrayList<Object> tiles, int[] scroll) {
        position.y += move * positionChange.y;
        player_rect.setY((int) position.y);
        for (Object tile : tiles) {

            if (player_rect.overlaps(tile.getObject_rect())) {
                float top = tile.getObject_rect().getY() + tile.getHeight();
                float bottom = tile.getObject_rect().getY() - tile.getHeight();
                isCollidingY = true;
                if (positionChange.y > 0) {
                    position.y = bottom;
                    player_rect.setY(position.y);

                } else if (positionChange.y < 0) {
                    position.y = top;
                    player_rect.setY(position.y);

                }
            }
        }
    }
    public void draw(SpriteBatch batch)
    {
        animation.render(state, batch);
    }
    public void update(float deltaTime, ArrayList<Object> tiles, int[] scroll)
    {
        move(deltaTime);
        position.x -= scroll[0];
        position.y -= scroll[1];

        player_rect.setY(position.y);
        player_rect.setX(position.x);
    }
}
