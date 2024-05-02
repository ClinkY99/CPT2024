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

    public Rectangle mouseCheckerWorkAround = new Rectangle(10000F,10000F,10F,10F); // being lazy

    public Sprite backDrop;
    public boolean isLoaded;
    public boolean hasLoadedOnce;
    Batch batch;
    boolean isTouching;
    int whichDragDropObject;
    public boolean isSolved;
    Texture basketBallHoopPicture;
    Rectangle Goal;

    public DragDrop(World world,Batch batch) {
        this.world = world;
        this.batch = batch;
        backDrop = new Sprite(new Texture(Gdx.files.internal("Images/idle_0.png")));
        backDrop.setColor(255,255,255,0.5F);
        backDrop.setScale(200,200);
        backDrop    .setPosition(500,500);
        // define 'goals' here'

        basketBallHoopPicture = new Texture(Gdx.files.internal("Images/Psyche-Lock.png"));
        Goal = new Rectangle(400,400,basketBallHoopPicture.getWidth(),basketBallHoopPicture.getHeight());
    }

    public void render(ArrayList<DragDropObject> dragdropObjectList, Batch batch) {
        mouseCheckerWorkAround.setPosition(Gdx.input.getX(),Gdx.input.getY());

        if (isLoaded) {
            if (hasLoadedOnce) {
                for (int i = 0; i < dragdropObjectList.size(); i++) {
                    if (dragdropObjectList.get(i).actualRectangle.y != (dragdropObjectList.get(i).lastYPosition)) {
                        dragdropObjectList.get(i).changeInYPosition += dragdropObjectList.get(i).actualRectangle.y - dragdropObjectList.get(i).lastYPosition;
                    }
                }
            }
            hasLoadedOnce = true;
            world.allowMovement = false;


            backDrop.draw(batch);

            // THIS FOR LOOP DRAWS THE DRAG-DROP OBJECTS
            for (int i = 0; i < dragdropObjectList.size(); i++) {
                dragdropObjectList.get(i).lastYPosition = dragdropObjectList.get(i).actualRectangle.y;
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
            batch.draw(basketBallHoopPicture,Goal.x,Goal.y - (Goal.getHeight())/2,Goal.width,Goal.height);
            if (!Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                for (int i = 0; i < dragdropObjectList.size();i++) {
                    if (dragdropObjectList.get(i).actualRectangle.overlaps(Goal)) {
                        System.out.println("Nice Shot!");
                        Goal.setPosition(10000,10000);
                        isSolved = true;
                    }
                }
            }
            for (int i = 0; i < dragdropObjectList.size(); i++) {
                dragdropObjectList.get(i).render(batch);
            }
        }
    }
}
