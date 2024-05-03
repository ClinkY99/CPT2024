package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Game_Elements.World;


import java.util.ArrayList;
import java.util.Stack;

public class DragDropPuzzle {
    World world;

    public Rectangle mouseCheckerWorkAround = new Rectangle(10000F,10000F,20F,20F); // being lazy

    public Sprite backDrop;
    public boolean isLoaded;
    public boolean hasLoadedOnce;
    Batch batch;
    boolean isTouching;
    int whichDragDropObject;
    public boolean isSolved;
    Texture basketBallHoopPicture;
    DragDropGoal Goal;

    public DragDropPuzzle(World world, Batch batch) {
        this.world = world;
        this.batch = batch;
        backDrop = new Sprite(new Texture(Gdx.files.internal("Images/idle_0.png")));
        backDrop.setColor(255,255,255,0.5F);
        backDrop.setScale(200,200);
        backDrop.setPosition(500,500);
        // define 'goals' here'

        basketBallHoopPicture = new Texture(Gdx.files.internal("Images/Psyche-Lock.png"));
        Goal = new DragDropGoal(basketBallHoopPicture);
        Goal.setPosition(600,600);
    }
    public void render(Array<DragDropObject> dragdropObjectList, Batch batch, Stage stage) {

        if (isLoaded) {

            world.allowMovement = false;
            backDrop.draw(batch);

            stage.addActor(Goal);

            for (int i = 0; i < dragdropObjectList.size; i++) {
                stage.addActor(dragdropObjectList.get(i));
            }

            // this is collision detection
            for (int i = 0; i < dragdropObjectList.size; i++) {
                // big line to make 2 rectangles and check for collision
                if (new Rectangle(dragdropObjectList.get(i).getX(),dragdropObjectList.get(i).getY(),dragdropObjectList.get(i).getWidth(),dragdropObjectList.get(i).getHeight()).overlaps(new Rectangle(Goal.getX(),Goal.getY(),Goal.getWidth(),Goal.getHeight())) && !Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    Goal.remove();
                }
            }


        }
        else {
            Goal.remove();
            for (int i = 0; i < dragdropObjectList.size; i++) {
                dragdropObjectList.get(i).remove();
            }
        }
    }
}
