package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Game_Elements.Object;

public class TileDragPuzzle {
    // the index of the real tiles for goals will be the goal it needs to be dragged to
    Array<DragDropObject> tilesToDrag;
    Array<DragDropGoal> goals;
    PuzzleTable goalTable;
    PuzzleTable tileTable;
    float displaceNum;

    public TileDragPuzzle(Array<Texture> fakeTilesToDrag, Array<Texture> realTiles, Texture goalTexture, int numberOfGoals, Stage stage) {
        tilesToDrag = new Array<>();
        goals = new Array<>();
        tileTable = new PuzzleTable(500, 500, stage);
        goalTable = new PuzzleTable(500, 800, stage);
        for (int i = 0; i < fakeTilesToDrag.size; i++) {
            DragDropObject fakeTile = new DragDropObject(fakeTilesToDrag.get(i));
            fakeTile.data = -1;
            tilesToDrag.add(fakeTile);
        }

        for (int j = 0; j < realTiles.size; j++) {
            DragDropObject realTile = new DragDropObject(realTiles.get(j));
            realTile.data = j;
            tilesToDrag.add(realTile);
        }
        tilesToDrag.shuffle();
        //Array of tiles to add to tile table

        for (int i = 0; i < numberOfGoals; i++) {
            DragDropGoal goalObject = new DragDropGoal(goalTexture);
            goalObject.data = i;
            goals.add(goalObject);
            //goals array shuffled
        }

        for (int i = 0; i <tilesToDrag.size;i++) {
            stage.addActor(tilesToDrag.get(i));
            tilesToDrag.get(i).setPosition(300 + displaceNum,500);
            displaceNum += 50;
        }
        for (int i = 0; i <goals.size;i++) {
            stage.addActor(goals.get(i));
            goals.get(i).setPosition(displaceNum,800);
            displaceNum+= 50;
        }

    }
    public void render(Object object) {


        for (int i = 0; i < goals.size;i++) {
            for (int j = 0; j < tilesToDrag.size;j++) {
                if (goals.get(i).bounds.overlaps(tilesToDrag.get(j).bounds)) {
                    System.out.println(goals.get(i).data + " " + tilesToDrag.get(j).data);
                    if (tilesToDrag.get(j).data == goals.get(i).data) {
                        goals.get(i).touchingCorrectTile = true;
                    }

                }
            }
        }



    }
    public boolean areAllSolved() {
        for(int i = 0; i < goals.size;i++) {
            if (!goals.get(i).touchingCorrectTile) {
                return false;
            }
        }
        return true;
    }
}