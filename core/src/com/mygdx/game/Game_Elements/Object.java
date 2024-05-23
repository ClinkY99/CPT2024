package com.mygdx.game.Game_Elements;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import javax.swing.*;


public class Object extends Image {
    Rectangle object_rect;
    boolean collide;

    public Object(Texture img, int[] location, boolean collide) {
            super(img);
        setSize(getWidth() ,getHeight());

        setOrigin(getWidth()/2,getHeight()/2);

        super.setPosition(location[0] * 200, location[1] * 200);
        this.collide = collide;
        object_rect = new Rectangle(getX(), getY(), Math.abs(getWidth()), Math.abs(getHeight()));

    }

    public Rectangle getObject_rect() {
        return object_rect;
    }

    public void updatex(int scroll) {
        object_rect.x -= scroll;
        setX(getWidth()>0?object_rect.x:object_rect.x+200);
    }

    public void updatey(int scroll) {
        object_rect.y -= scroll;
        setY(getHeight()>0?object_rect.y:object_rect.y+200);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(getWidth() < 0) {
//            ShapeRenderer shapeRenderer = new ShapeRenderer();
//            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
//            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//            shapeRenderer.setColor(Color.RED);
//            shapeRenderer.rect(object_rect.x, object_rect.y, object_rect.getWidth(), object_rect.getHeight());
//            shapeRenderer.end();
        }
    }

    public boolean get_collide()
    {
        return this.collide;
    }


    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x*200, y*200);
        object_rect.setPosition(getX()-Math.abs(getWidth()/2),getY()-Math.abs(getHeight()/2));
    }
}
