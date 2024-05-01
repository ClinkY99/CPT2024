package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import  com.badlogic.gdx.math.Rectangle;

import java.awt.*;

public class DragDropObject {
    Sprite spriteThatGoesOverRectangle;
    public com.badlogic.gdx.math.Rectangle actualRectangle;
    Texture image;
    public DragDropObject(Texture img) {
        image = img;
        actualRectangle= new Rectangle(500,500, img.getWidth(), img.getHeight());
    }


    public void render(Batch batch) {
        batch.draw(image,actualRectangle.x,actualRectangle.y,actualRectangle.width,actualRectangle.height);
    }
}
