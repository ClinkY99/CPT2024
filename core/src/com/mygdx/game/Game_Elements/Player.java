package com.mygdx.game.Game_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Player
{
    public Vector2 position, positionChange;
    public Sprite player;
    public float speed = 2000;
    public float move;
    String path;
    Rectangle player_rect;

    public boolean isCollidingX;
    public boolean isCollidingY;
    public boolean loop;
    public animation animation;
    public String state;
    public String type;
    public int[] size;



    public Player(String path, String type) throws IOException
    {
        this.type = type;
        this.size = new int[]{150, 150};
        this.path = path;
        this.loop = true;
        animation = new animation(path, type);
        state = "Idle/Forward";
        int mid = 885;
        int midy = 465;
        position = new Vector2(mid, midy);
        positionChange = new Vector2(0, 0);
        player = new Sprite();
        // need to subtract the width and height divided by 2 to put player in middle
        player.setPosition(position.x, position.y);
        player_rect = new Rectangle(mid , midy, size[0], size[1]);

    }



    public void move(float deltaTime)
    {

        move = speed * deltaTime;
        boolean press = false;
        boolean diagonal = false;
        positionChange.x = positionChange.y = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.A))
        {
            positionChange.x = -1;
            state = "Running/left";
            this.loop = true;
            diagonal = true;
            press = true;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.D))
        {
            positionChange.x = 1;
            state = "Running/right";
            this.loop = true;
            diagonal = true;
            press = true;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.W))
        {
            positionChange.y = 1;
            state = "Running/Back";
            this.loop = true;
            press = true;
            if (diagonal)
            {
                positionChange.x = 0.71f * positionChange.x;
                positionChange.y = 0.71f;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S))
        {
            positionChange.y = -1;
            state = "Running/Forward";
            this.loop = true;
            press = true;
            if (diagonal)
            {
                positionChange.x = 0.71f * positionChange.x;
                positionChange.y = -0.71f;
            }
        }

        if (!press)
        {
            state = "Idle/Forward";
        }

    }

    public void collision_detectionx(Array<Actor> tiles)
    {
        position.x += move * positionChange.x;
        player_rect.setX((int) position.x);
        for (Actor tileActor: tiles)
        {
            Object tile = (Object) tileActor;
            if (tile.get_collide()) {
                if (player_rect.overlaps(tile.getObject_rect())) {
                    isCollidingX = true;
                    float left = tile.getObject_rect().getX();
                    float right = left + tile.getWidth();


                    if (positionChange.x < 0) {
                        position.x = right;
                        player_rect.setX(position.x);
                    } else if (positionChange.x > 0) {
                        position.x = left - player_rect.getWidth();
                        player_rect.setX(position.x);
                    }
                }
            }
        }
    }

    public void collision_detectiony(Array<Actor> tiles) {
        position.y += move * positionChange.y;
        player_rect.setY((int) position.y);
        for (Actor tileActor: tiles) {
            Object tile = (Object) tileActor;
            if (tile.get_collide()) {
                if (tile.getObject_rect().overlaps(player_rect)) {
                    float bottom = tile.getObject_rect().getY();
                    float top = bottom + tile.getHeight();

                    isCollidingY = true;
                    if (positionChange.y > 0) {
                        position.y = bottom - player_rect.getHeight();
                        player_rect.setY(position.y);

                    } else if (positionChange.y < 0) {
                        position.y = top;
                        player_rect.setY(position.y);

                    }
                }
            }
        }
    }
    public void draw(SpriteBatch batch)
    {
        animation.render(state, batch, this.loop);
    }
    public void update(float deltaTime, int[] scroll)
    {
        move(deltaTime);

        position.x -= scroll[0];
        position.y -= scroll[1];

        player_rect.setY(position.y);
        player_rect.setX(position.x);
    }
}
