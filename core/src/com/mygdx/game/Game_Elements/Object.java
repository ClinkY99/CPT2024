package com.mygdx.game.Game_Elements;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Object
{
    public Vector2 position;
    public Sprite object;
    Rectangle object_rect;
    public Object(Texture img, int[] location)
    {
        object = new Sprite(img);

        position = new Vector2(location[0] * object.getWidth(), location[1] * object.getWidth());
        object.setPosition(position.x, position.y);

        object_rect = new Rectangle(position.x, position.y, object.getWidth(), object.getHeight());
    }

    public float getWidth()
    {
        return object.getWidth();
    }
    public void setColor(Color color) {
        object.setColor(color);
    }

    public Rectangle getObject_rect()
    {
        return object_rect;
    }
    public void draw(SpriteBatch batch)
    {
        object.draw(batch);
    }

    public void updatex(int scroll)
    {
        object_rect.x -= scroll;
        object.setPosition(object_rect.x, object_rect.y);
    }

    public void updatey(int scroll)
    {
        object_rect.y -= scroll;
        object.setPosition(object_rect.x, object_rect.y);
    }
}