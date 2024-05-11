package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.awt.*;


public class DragDropGoal extends Actor {
    Texture img;
    public int data;

public boolean touchingCorrectTile;
    public com.badlogic.gdx.math.Rectangle bounds;
    public DragDropGoal(Texture image) {
        img = image;
        bounds =new Rectangle(getX(), getY(), getWidth(), getHeight());
        setWidth(image.getWidth());
        setHeight(image.getHeight());

    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(img,getX(),getY(),getWidth(),getHeight());
        this.bounds.set(getX(), getY(), getWidth(), getHeight());
    }

}
