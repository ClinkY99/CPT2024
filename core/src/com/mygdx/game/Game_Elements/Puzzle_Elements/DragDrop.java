package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Game_Elements.World;

import java.awt.*;
import java.util.ArrayList;

public class DragDrop {
    World world;

    public Rectangle mouseCheckerWorkAround = new Rectangle(10000F,10000F,2F,2F); // being lazy

    Sprite backDrop;
    public boolean isLoaded;
    Batch batch;
    boolean isTouching;
    int whichDragDropObject;


    public DragDrop(World world,Batch batch) {
        this.world = world;
        this.batch = batch;
        backDrop = new Sprite(new Texture(Gdx.files.internal("Images/idle_0.png")));
        backDrop.setColor(255,255,255,0.5F);
        backDrop.setScale(200,200);
        backDrop    .setPosition(500,500);
    }

    public void render(ArrayList<DragDropObject> dragdropObjectList) {
        mouseCheckerWorkAround.setPosition(Gdx.input.getX(),Gdx.input.getY());

        if (isLoaded) {
            world.allowMovement = false;

            // PUT WHATEVER UNIQUE SPRITES YOU WANT TO DRAG TO HERE
            backDrop.draw(batch);

            // THIS FOR LOOP DRAWS THE DRAG-DROP OBJECTS
            for (int i = 0; i < dragdropObjectList.size(); i++) {
                dragdropObjectList.get(i).render(batch);
            }


            for (int i = 0; i < dragdropObjectList.size(); i++) {
                if (mouseCheckerWorkAround.overlaps(dragdropObjectList.get(i).actualRectangle)) {
                    //confirmed that mouse overlaps with object
                    if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                        dragdropObjectList.get(i).actualRectangle.setCenter(mouseCheckerWorkAround.getX(),mouseCheckerWorkAround.getY());
                    }
                    isTouching = true;
                    whichDragDropObject = i;

                } else {
                    isTouching = false;
                }
            }
        }
    }
}
