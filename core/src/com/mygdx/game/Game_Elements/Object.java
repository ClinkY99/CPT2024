package com.mygdx.game.Game_Elements;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


public class Object extends Image {
    Rectangle object_rect;

    public Object(Texture img, int[] location) {
        super(img);
        setSize(150 ,150);

        setPosition(location[0] * getWidth(), location[1] * getHeight());



        object_rect = new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public Rectangle getObject_rect() {
        return object_rect;
    }

    public void updatex(int scroll) {
        object_rect.x -= scroll;
        setX(object_rect.x);
    }

    public void updatey(int scroll) {
        object_rect.y -= scroll;
        setY(object_rect.y);

    }
}
