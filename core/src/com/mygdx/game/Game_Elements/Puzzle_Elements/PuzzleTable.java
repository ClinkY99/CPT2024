package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.Game_Elements.Object;
import com.mygdx.game.Game_Elements.World;

public class PuzzleTable extends Table {
    // to hold puzzlebutton
    // to use this class, declare starting x and y when calling it (if not declaring stage make sure you use the
    // addActor function to add to stage). Then call loadPosition in the 'render' class. Make sure to
    // create a puzzle button, and use the addListener function to make it register clicks
    public float X,Y;
    float baseX;
    float baseY;
    public PuzzleTable (float startingX,float startingY) {
        baseX = startingX;
        baseY = startingY;

        this.setDebug(true);
    }
    public PuzzleTable (float startingX, float startingY, Stage stage) {
        baseX = startingX;
        baseY = startingY;
        stage.addActor(this);
        this.setDebug(true);
    }

    public void loadPosition(World LevelWorld, Object object) {
        /* TODO: Instead of tying the position of this table to the player's movements,
             maybe tie it to one of the objects? DONE */

        X = object.getPosition()[0] + baseX;
        Y = object.getPosition()[1] + baseY;

        /*if (!LevelWorld.player.isCollidingY) {
            Y -= (float) ((float) LevelWorld.player.positionChange.y * LevelWorld.player.move);
        }*/

        this.setPosition(X,Y);
    }
}
