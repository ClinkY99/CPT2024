package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Menus.Interactive.MenuButton;
import com.ray3k.stripe.FreeTypeSkin;

import java.awt.*;
import java.util.ArrayList;

public class ColorGridPuzzle {
    //0 = red, 1 = green
    int[][] gridData;
    public boolean ipsumLoaded;
    boolean puzzleWon;
    public float lastX;
    public float lastY;
    ArrayList<PuzzleButton> buttons = new ArrayList<>();
    public PuzzleTable ipsumHolder;
    public Sprite ipsum = new Sprite(new Texture(Gdx.files.internal("Images/Lorem Ipsum.png")));

    public ColorGridPuzzle(PuzzleTable table,Stage stage) {
        ipsumHolder = new PuzzleTable(200,300,stage);
        ipsumHolder.setDebug(true);
        ipsum.setPosition(10000,10000);
        ipsumLoaded = false;
        gridData = new int[][]{
                {0,0,0},
                {0,0,0},
                {0,0,0}
        };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                PuzzleButton buttonToAdd = new PuzzleButton("A",2,table,Color.RED, (new FreeTypeSkin(Gdx.files.internal("skin/vhs-ui.json"))));
                int finalI = i;
                int finalJ = j;
                buttonToAdd.addListener(new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                        if (!puzzleWon) {
                            gridData[finalI][finalJ] = (gridData[finalI][finalJ] == 1) ? 0 : 1;
                            if (ipsumLoaded) {
                                ipsumLoaded = false;
                                ipsum.setPosition(10000,10000);

                            } else {
                                lastX = buttonToAdd.getX()+200;
                                lastY = buttonToAdd.getY()+300;
                                //sends it far off screen
                                ipsumLoaded = true;
                            }

                        }
                    }
                });
                buttons.add(buttonToAdd);
            }
            table.row();
        }
    }
    public void updateGridColor(){
        if (!puzzleWon) {
            for (int i = 0; i < 9; i++) {
                if (buttons.get(i).getColor() != convertToColor(gridData[convertToCoordinates(i)[0]][convertToCoordinates(i)[1]])) {
                    buttons.get(i).setColor(convertToColor(gridData[convertToCoordinates(i)[0]][convertToCoordinates(i)[1]]));
                }
            }
        }
        int k = 0;
        for (int i = 0; i < 9; i++) {
            if (convertToColor(gridData[convertToCoordinates(i)[0]][convertToCoordinates(i)[1]]) != Color.GREEN) {
                k = 1;
            }
        }
        if (k == 0) {
            for (int i = 0; i < 9; i++) {
                buttons.get(i).setColor(Color.GOLD);
            }
            for (int i = 0; i < 9; i++) {
                buttons.get(i).remove();
            }

        }

    }
    public boolean isCompleted() {
        if (puzzleWon) {
            return true;
        }
        return false;
    }
    public Color convertToColor(int num) {
        if (num == 1) {
            return Color.GREEN;
        }
        else if (num == 0) {
            return Color.RED;
        }
        return null;
    }
    public int[] convertToCoordinates(int num) {
        switch (num) {
            case 0:
                return new int[]{0,0};
            case 1:
                return new int[]{0,1};
            case 2:
                return new int[]{0,2};
            case 3:
                return new int[]{1,0};
            case 4:
                return new int[]{1,1};
            case 5:
                return new int[]{1,2};
            case 6:
                return new int[]{2,0};
            case 7:
                return new int[]{2,1};
            case 8:
                return new int[]{2,2};
            default:
                return null;
        }
    }
}
