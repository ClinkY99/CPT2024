package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import  com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public class DragDropObject {
    Sprite spriteThatGoesOverRectangle;
    public com.badlogic.gdx.math.Rectangle actualRectangle;
    float changeInYPosition;
    public float lastYPosition;
    Texture image;
    public DragDropObject(Texture img) {
        image = img;
        actualRectangle= new Rectangle(900,500, img.getWidth(), img.getHeight());
    }


    public void render(Batch batch) {
        batch.draw(image,actualRectangle.x,actualRectangle.y - (changeInYPosition*2) + actualRectangle.height/2,actualRectangle.width,actualRectangle.height);
    }
}
