package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import  com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public class DragDropObject {
    Sprite spriteThatGoesOverRectangle;
    public com.badlogic.gdx.math.Rectangle actualRectangle;
    public DragDropObject(Texture img) {
        spriteThatGoesOverRectangle = new Sprite(img);
        actualRectangle= new Rectangle(500,500, spriteThatGoesOverRectangle.getWidth(), spriteThatGoesOverRectangle.getHeight());
    }


    public void render(Batch batch) {

        spriteThatGoesOverRectangle.setPosition(actualRectangle.getX(),actualRectangle.getY());
        System.out.println(spriteThatGoesOverRectangle.getX() + " " + spriteThatGoesOverRectangle.getY() + " : " + actualRectangle.x + " " + actualRectangle.y);
        spriteThatGoesOverRectangle.draw(batch);

    }
}
