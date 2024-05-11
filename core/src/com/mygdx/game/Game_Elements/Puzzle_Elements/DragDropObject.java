package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import  com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import java.awt.*;

public class DragDropObject extends Actor {
    Sprite spriteThatGoesOverRectangle;
    public com.badlogic.gdx.math.Rectangle actualRectangle;
    float changeInYPosition;
    public int data;
    Texture image;
    Rectangle bounds;
    public DragDropObject(Texture img) {
        setWidth(img.getWidth());
        setHeight(img.getHeight());
        image = img;
        addListener(new DragListener() {
            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                moveBy(x,y);
            }
        });
        DragDropObject object = this;
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (getStage() != null) {
                    Stage stage= getStage();
                    remove();
                    stage.addActor(object);

                }

            }
        });
        setPosition(500,500);

        bounds=new Rectangle((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image,getX(),getY(),getWidth(),getHeight());
        this.bounds.set(getX(), getY(), getWidth(), getHeight());

    }

}
