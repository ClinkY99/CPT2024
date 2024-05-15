package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;

public class ColorGridPuzzle {
    //0 = red, 1 = green
    int[][] gridData;
    boolean puzzleWon;
    ArrayList<ImagePuzzleButton> buttons = new ArrayList<>();



    public ColorGridPuzzle(PuzzleTable table) {
        gridData = new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ImagePuzzleButton buttonToAdd = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/idle_0_red.png")), 2);
                buttonToAdd.isLoaded = true;
                int finalI = i;
                int finalJ = j;
                    buttonToAdd.addListener(new ClickListener() {
                        public void clicked(InputEvent event, float x, float y) {
                            if (!puzzleWon) {
                                gridData[finalI][finalJ] = (gridData[finalI][finalJ] == 1) ? 0 : 1;
                                if (buttonToAdd.data == 0) {
                                    buttonToAdd.data = 1;
                                } else {
                                    buttonToAdd.data = 0;
                                }

                                buttonToAdd.updateTexture( (buttonToAdd.data == 1) ? new Texture(Gdx.files.internal("Images/idle_0_green.png")): new Texture(Gdx.files.internal("Images/idle_0_red.png")));
                                System.out.println(convertToColor(buttonToAdd.data));
                            }
                        }

                    });
                    table.add(buttonToAdd);
                    buttons.add(buttonToAdd);
                }
                table.row();

            }
        }


    public void updateGrid(){
        for (int i = 0;i < buttons.size();i++) {
            if (buttons.get(i).data == 0) {
                return;
            }
        }
        puzzleWon = true;
    }
    public boolean isCompleted() {
        return puzzleWon;
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

}
