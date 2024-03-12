package com.mygdx.game.Game_Elements;


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
    int[] location;
    public Object(Texture img, int[] location)
    {
        this.location = location;
        object = new Sprite(img);

        position = new Vector2(location[0] * object.getWidth(), location[1] * object.getWidth());
        object.setPosition(position.x, position.y);
        object_rect = new Rectangle(position.x, position.y, object.getWidth(), object.getHeight());
    }

    public float getWidth()
    {
        return object.getWidth();
    }

    public Rectangle getObject_rect()
    {
        return object_rect;
    }
    public void draw(SpriteBatch batch)
    {
        object.draw(batch);
    }
}