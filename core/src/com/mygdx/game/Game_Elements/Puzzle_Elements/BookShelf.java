package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.World;
import com.ray3k.stripe.FreeTypeSkin;

import java.util.ArrayList;

public class BookShelf {
    public PuzzleTable textHolder;
    int gridData[][];
    public boolean textLoaded;
    public float lastX;
    public float lastY;
    ArrayList<PuzzleButton> buttons = new ArrayList<>();
    public Sprite text = new Sprite(new Texture(Gdx.files.internal("Images/among us.png")));


    public BookShelf(PuzzleTable table, Stage stage, boolean showImage, Texture image, int rows, int columns) {
        textLoaded = false;
        text = new Sprite(image);
        textHolder = new PuzzleTable(table.X,table.Y,stage);
        textHolder.setDebug(true);
        unloadText();

        gridData = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j ++) {
                gridData[i][j] = 0;
            }
        }

        for (int i = 0; i < gridData.length; i++) {
            for (int j = 0; j < gridData[0].length; j++) {

                PuzzleButton buttonToAdd = new PuzzleButton("B",2,table, Color.RED, (new FreeTypeSkin(Gdx.files.internal("Menu/Skins/Button.json"))));

                int finalI = i;
                int finalJ = j;
                if (showImage) {
                    buttonToAdd.addListener(new ClickListener() {
                        public void clicked(InputEvent event, float x, float y) {
                                gridData[finalI][finalJ] = (gridData[finalI][finalJ] == 1) ? 0 : 1;

                                if (textLoaded) {
                                } else {
                                    lastX = buttonToAdd.getX() + 200;
                                    lastY = buttonToAdd.getY() + 300;
                                    textHolder.setPosition(lastX,lastY);
                                    text.setPosition(textHolder.getX(),textHolder.getY());
                                    textLoaded = true;
                                }

                            }


                    });
                    buttons.add(buttonToAdd);
                }

            }
            table.row();
        }
    }
    /*
    public void updateShelf(CPTGame game) {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            this.unloadText();
        }
        if (TestBookShelf.textLoaded) {
            LevelWorld.allowMovement = false;
        } else {
            LevelWorld.allowMovement = true;
        }
        TestBookShelf.text.draw(game.batch);


    }*/
    public void unloadText() {
        textLoaded = false;
        text.setPosition(10000,10000);
    }
}
