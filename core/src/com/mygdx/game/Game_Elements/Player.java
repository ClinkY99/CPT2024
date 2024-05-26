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



public class Player
{
    public Vector2 position, positionChange;
    public Sprite player;
    public int i = 0;
    public int speed = 2000;
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
    public int data;




    public Player(String path, String type, int[] loc) throws IOException
    {
        this.type = type;
        this.path = path;
        this.loop = true;
        this.animation = new animation(path, type);

        state = "Idle/Forward";
        this.size = animation.anim_size.get(state);
        position = new Vector2(loc[0] - size[0] / 2, loc[1] - size[1]);
        positionChange = new Vector2(0, 0);
        player = new Sprite();
        // need to subtract the width and height divided by 2 to put player in middle
        player.setPosition(position.x, position.y);
        player_rect = new Rectangle(loc[0] - size[0], loc[1] - size[1], size[0], size[1]);
        data = 0;

    }


    public void move(float deltaTime)
    {
        speed = 2000;
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
            data = -1;
            i = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D))
        {
            positionChange.x = 1;
            state = "Running/right";
            this.loop = true;
            press = true;
            diagonal = true;
            data = 1;
            i = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W))
        {
            positionChange.y = 1;
            state = "Running/Back";
            this.loop = true;
            press = true;
            if (diagonal)
            {
                speed = (int) (speed * 0.71);
            }

            i = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S))
        {
            positionChange.y = -1;
            state = "Running/Forward";
            this.loop = true;
            press = true;
            if (diagonal)
            {
                speed = (int) (speed * 0.71);
            }
            i = 0;
        }
        if (!press)
        {
            state = "Idle/Forward";
            if (i == 0)
            {
                positionChange.x = data;
                data = 0;
                speed = 0;
            }
            i = 1;
        }
        this.size = this.animation.anim_size.get(state);
        player_rect.setSize(size[0], size[1]);
        if (deltaTime > 1)
        {
            deltaTime = 0;
        }
        move = speed * deltaTime;
    }

    public void collision_detectionx(Array<Actor> tiles, Array<Rectangle> rectangle)
    {
        position.x += (int) move * positionChange.x;
        player_rect.setX((int) position.x);
        for (Actor tileActor: tiles)
        {
            Object tile = (Object) tileActor;
            if (tile.get_collide()) {
                if (player_rect.overlaps(tile.getObject_rect())) {

                    isCollidingX = true;
                    float left = tile.getObject_rect().getX();
                    float right = left + Math.abs(tile.getWidth());


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
        for (Rectangle rect: rectangle)
        {
            if (player_rect.overlaps(rect))
            {
                float left = rect.getX();
                float right = (left + Math.abs(rect.getWidth()));

                isCollidingX = true;
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

    public void collision_detectiony(Array<Actor> tiles, Array<Rectangle> rectangle) {
        position.y += (int) move * positionChange.y;
        player_rect.setY((int) position.y);
        for (Actor tileActor: tiles) {
            Object tile = (Object) tileActor;
            if (tile.get_collide()) {
                if (tile.getObject_rect().overlaps(player_rect)) {
                    float bottom = tile.getObject_rect().getY();
                    float top = bottom + Math.abs(tile.getHeight());

                    isCollidingY = true;
                    if (positionChange.y > 0) {
                        position.y = bottom - player_rect.getHeight();
                        player_rect.setY(position.y);
                    }
                    else if (positionChange.y < 0)
                    {
                        position.y = top;
                        player_rect.setY(position.y);
                    }
                }
            }
        }
        for (Rectangle rect: rectangle) {
            if (player_rect.overlaps(rect)) {
                float bottom =  rect.getY();
                float top = (bottom + Math.abs(rect.getHeight()));
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
    public void draw(SpriteBatch batch)
    {
        animation.render(state, batch, this.loop);
    }
    public void update(float deltaTime, float[] scroll)
    {
        move(deltaTime);

        position.x -= scroll[0];
        position.y -= scroll[1];

        player_rect.setY(position.y);
        player_rect.setX(position.x);
    }
}
