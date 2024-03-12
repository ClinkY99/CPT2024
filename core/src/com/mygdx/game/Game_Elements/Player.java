package com.mygdx.game.Game_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Player
{
    public Vector2 position, positionChange;
    public Sprite player;
    public float speed = 150;
    public float move;
    Rectangle player_rect;
    ArrayList<Object> tiles;
    int[] data;
    public Player(Texture img)
    {
        player = new Sprite(img);
        tiles = new ArrayList<>();
        makeObject(img);
        int mid = Gdx.graphics.getWidth() / 2;

        position = new Vector2(mid, mid);
        positionChange = new Vector2(0, 0);

        data = new int[]{0, 0, 0, 0};

        player_rect = new Rectangle(position.x, position.y, player.getWidth(), player.getHeight());
    }

    public void makeObject(Texture img)
    {
        int[] loc = {0, 10};
        for (int i = 0; i < 25; i++)
        {
            loc[0] = i;
            Object tile = new Object(img, loc);
            tiles.add(tile);
        }
    }

    public void move(float deltaTime)
    {
        move = speed * deltaTime;
        positionChange.x = positionChange.y = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.A))
        {
            positionChange.x = -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D))
        {
            positionChange.x = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W))
        {
            positionChange.y = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S))
        {
            positionChange.y = -1;
        }

    }

    public void collision_detection(ArrayList<Object> tiles) //data is [left, right, up, down]
    {
        position.x += move * positionChange.x;
        player_rect.setX((int) position.x);

        for (Object tile: tiles)
        {

            if (player_rect.overlaps(tile.getObject_rect()))
            {
                System.out.println("Collided");
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

        position.y += move * positionChange.y;
        player_rect.setY((int) position.y);

        for (Object tile: tiles)
        {
            if (player_rect.overlaps(tile.getObject_rect())) {
                float top = tile.getObject_rect().getY() + tile.getWidth();
                float bottom = tile.getObject_rect().getY() - tile.getWidth();

                if (positionChange.y > 0)
                {
                    position.y = bottom;
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
    
    public void update(float deltaTime)
    {
        move(deltaTime);
    }



    public void draw(SpriteBatch batch)
    {
        update(Gdx.graphics.getDeltaTime());
        player.draw(batch);
        collision_detection(tiles);
        player.setPosition(position.x, position.y);
        for (Object tile: tiles)
        {
            tile.draw(batch);
        }
    }
}
