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
import com.mygdx.game.Levels.TestLevel;
import com.ray3k.stripe.FreeTypeSkin;

import java.awt.*;
import java.awt.image.ImagingOpException;
import java.util.ArrayList;

public class BookShelf {
    public PuzzleTable textHolder;
    int gridData[][];
    public boolean textLoaded;
    public float lastX;
    public float lastY;
    ArrayList<ImagePuzzleButton> buttons = new ArrayList<>();
    ImagePuzzleButton text;


    public BookShelf(PuzzleTable table, Stage stage, boolean showImage, Texture image, int rows, int columns, TestLevel level, World world) {
        textLoaded = false;

        textHolder = new PuzzleTable(500,500,stage);
        text = new ImagePuzzleButton(image,2);
        textHolder.setPosition(1000,10000);
        textHolder.add(text);

        gridData = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j ++) {
                gridData[i][j] = 0;
            }
        }

        for (int i = 0; i < gridData.length; i++) {
            for (int j = 0; j < gridData[0].length; j++) {

                ImagePuzzleButton buttonToAdd = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/bookSpriteTest.png")),3);
                table.add(buttonToAdd);
                buttonToAdd.isLoaded = true;
                if (showImage) {
                    buttonToAdd.addListener(new ClickListener() {
                        public void clicked(InputEvent event, float x, float y) {

                                if (textLoaded) {

                                } else {
                                    textLoaded = true;
                                    world.allowMovement = false;
                                    textHolder.setPosition(500,500);

                                }

                            }


                    });

                    buttons.add(buttonToAdd);
                }

            }
            table.row();
        }
    }

    public void unloadText() {
        textLoaded = false;
        textHolder.setPosition(10000,10000);
    }
}
