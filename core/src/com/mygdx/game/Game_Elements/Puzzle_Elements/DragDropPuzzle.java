package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Game_Elements.World;
import com.mygdx.game.ui.stackableScreen;


import java.util.ArrayList;
import java.util.Stack;

public class DragDropPuzzle {

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

    public DragDropPuzzle() {
        // define 'goals' here'

        Goal = new DragDropGoal(basketBallHoopPicture);
        Goal.setPosition(600,600);
    }
    public void render(Array<DragDropObject> dragdropObjectList, Stage stage) {
            stage.addActor(Goal);
            for (int i = 0; i < dragdropObjectList.size; i++) {
                stage.addActor(dragdropObjectList.get(i));
            }

            // this is collision detection

    }

}
