package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.World;
import com.ray3k.stripe.FreeTypeSkin;

import java.util.ArrayList;

public class KeyPad {

        public PuzzleTable keyPadTable;
        int gridData[][];
        public boolean isShown;
        Sprite whiteRectangleKeyPad;
        public boolean textLoaded;
        public float lastX;
        public float lastY;
        public boolean correctCodeInputted = false;
        public ArrayList<PuzzleButton> buttons = new ArrayList<>();
        int length;
        int[] correctCode;
        int currentNumForKeyPadConstruction = 1;
        int[] currentCode;
        TextButton displayCurrentCode;
    PuzzleButton codeDisplay;
    int codeIterator = 0;
    OrthographicCamera camera;
    Stage stage;
    FreeTypeSkin globalSkin;

        public KeyPad(PuzzleTable table, int[] correctCodeNew) {
            keyPadTable = table;
            correctCode = correctCodeNew;
            currentCode = new int[correctCode.length];
            codeDisplay = new PuzzleButton("",2,table, Color.RED, (new FreeTypeSkin(Gdx.files.internal("Menu/Skins/Button.json"))));
            codeDisplay.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    for (int i = 0; i < currentCode.length;i++) {
                        currentCode[i] = 0;
                        codeIterator = 0;
                    }
                }


            });
            table.add(codeDisplay);
            table.row();

            for (int i = 0; i < currentCode.length;i++) {
                currentCode[i] = 0;
            }



            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    PuzzleButton buttonToAdd;
                    int data;
                    if (currentNumForKeyPadConstruction == 10|| currentNumForKeyPadConstruction == 11 || currentNumForKeyPadConstruction==12) {
                         buttonToAdd = new PuzzleButton(String.valueOf(0),2,table, Color.RED, (new FreeTypeSkin(Gdx.files.internal("Menu/Skins/Button.json"))));
                         data = 0;
                    } else {
                         buttonToAdd = new PuzzleButton(String.valueOf(currentNumForKeyPadConstruction), 2, table, Color.RED, (new FreeTypeSkin(Gdx.files.internal("Menu/Skins/Button.json"))));
                         data = currentNumForKeyPadConstruction;
                    }
                    currentNumForKeyPadConstruction++;
                    int finalI = i;
                    int finalJ = j;
                        buttonToAdd.addListener(new ClickListener() {
                            public void clicked(InputEvent event, float x, float y) {
                                if ((codeIterator >= currentCode.length)) {
                                    codeIterator = 0;
                                    for (int i = 0; i < currentCode.length; i++) {
                                        currentCode[i] = 0;
                                    }
                                } else {
                                    currentCode[codeIterator] = data;
                                    codeIterator++;
                                }

                            }


                        });
                        buttons.add(buttonToAdd);


                }
                table.row();
            }
            keyPadTable.setPosition(100000,100000);
        }
        public void updateKeyPad(Batch batch,World world)

         {
             displayCurrentCode = new TextButton("",new FreeTypeSkin(Gdx.files.internal("Menu/Skins/Button.json")));
             String textCode = "";

                 whiteRectangleKeyPad = new Sprite(new Texture(Gdx.files.internal("Images/idle_0.png")));
                 //whiteRectangleKeyPad.setScale(20,25);
             whiteRectangleKeyPad.setScale(500,500);
             whiteRectangleKeyPad.setPosition(keyPadTable.getX()-40,keyPadTable.getY());

             batch.enableBlending();
             whiteRectangleKeyPad.setColor(255,255,255, 0.5F);
             whiteRectangleKeyPad.draw(batch,0.5F);

             //checks if keypad is done
             correctCodeInputted = true;
            for (int i = 0; i < correctCode.length; i++) {
                if (correctCode[i] != currentCode[i]) {
                    correctCodeInputted = false;
                }
            }
            for (int i = 0; i < currentCode.length;i++) {
                textCode = textCode + currentCode[i];
            }
                codeDisplay.setText(textCode);
            //displays current code
             if (isShown) {
                 world.shouldAct = false;
             } else {
                 world.shouldAct = true;
             }



        }
        public void printCurrentCode() {
            for (int i = 0;i < currentCode.length;i++) {
                System.out.print(currentCode[i]);

            }
        }
    }


