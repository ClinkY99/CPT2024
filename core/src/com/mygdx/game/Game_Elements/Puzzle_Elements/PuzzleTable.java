package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.Game_Elements.World;

import java.util.logging.Level;

public class PuzzleTable extends Table {
    // to hold puzzlebutton
    // to use this class, declare starting x and y when calling it (if not declaring stage make sure you use the
    // addActor function to add to stage). Then call loadPosition in the 'render' class. Make sure to
    // create a puzzle button, and use the addListener function to make it register clicks
    public float X,Y;
    public PuzzleTable (float startingX,float startingY) {
        X = startingX;
        Y = startingY;

        this.setDebug(true);
    }
    public PuzzleTable (float startingX, float startingY, Stage stage) {
        X = startingX;
        Y = startingY;
        stage.addActor(this);
        this.setDebug(true);
    }

    public void loadPosition(World LevelWorld) {
        if (!LevelWorld.player.isCollidingX) {
            X -= (float) ((float) LevelWorld.player.positionChange.x * LevelWorld.player.move);
        }

        if (!LevelWorld.player.isCollidingY) {
            Y -= (float) ((float) LevelWorld.player.positionChange.y * LevelWorld.player.move);
        }

        this.setPosition(X,Y);
    }
}
