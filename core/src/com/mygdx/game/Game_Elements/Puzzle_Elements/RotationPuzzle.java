package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.ui.ScreenStack;
import com.mygdx.game.ui.stackableScreen;
import sun.rmi.transport.DGCImpl_Skel;

import java.awt.*;
import java.util.zip.DeflaterInputStream;

public class RotationPuzzle extends ImagePuzzleButton {
    // this class takes a button texture, which is what the button that summons the lock will appear as.
    // it takes an array of disk textures, which will rotate.
    // it takes a code, with every disk having 4 states (symbols) on it. these range from 0-3, with 0 being its starting
    // position, one being it having been rotated/clicked once, etc.
    // the int[] numberOfStates variable is associated according to index to a texture, it tells the class
    // how many states that specific circle has
    Boolean isCompleted;
    int[] numberOfStates;
    TextureRegion region;
    public RotationPuzzle(Texture buttonTexture, Array<Texture> diskPictures, int[][] code, ScreenStack stack, int[] numberOfStates) {
        super(buttonTexture,2);
        isCompleted = false;
        this.numberOfStates = numberOfStates;

        combinationLockScreen lockScreen = new combinationLockScreen(diskPictures,code,stack);

        region = new TextureRegion(buttonTexture);
        setBounds(region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight());
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("SUS");
                stack.push(lockScreen);

            }
        });
    }
    public class combinationLockScreen implements stackableScreen {
        Array<ImagePuzzleButton> diskButtons;
        int[][] code;
        Stage stage;
        ScreenStack stack;
        ImagePuzzleButton screenBlocker;
        public combinationLockScreen(Array<Texture> diskPictures,int[][] code,ScreenStack stack) {
            this.stack = stack;
            this.code = code;
            stage = new Stage(new FitViewport(1920,1080));
            Texture screenBlockerTexture = new Texture(Gdx.files.internal("Images/screenBlocker.png"));
            screenBlocker = new ImagePuzzleButton(screenBlockerTexture,0);
            screenBlocker.setPosition(-10,0);
            screenBlocker.setSize(1928,1080);

            stage.addActor(screenBlocker);
            diskButtons = new Array<>();

            for (int i = 0; i < diskPictures.size;i++) {
                ImagePuzzleButton tempButton = new ImagePuzzleButton(diskPictures.get(i),2);
                tempButton.data = 0;
                int stateNum = numberOfStates[i];
                tempButton.setSize(diskPictures.get(i).getWidth(),diskPictures.get(i).getHeight());
                tempButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (tempButton.data < stateNum-1) {
                            tempButton.data++;
                        } else {
                            tempButton.data = 0;
                        }
                        tempButton.setTransform(true);
                        tempButton.rotateBy(-(360F/stateNum));


                    }
                });
                diskButtons.add(tempButton);
            }



            for (int i = 0; i < diskButtons.size;i++) {
                diskButtons.get(i).setPosition(500,500);
                if (i != 0) {
                    diskButtons.get(i).moveBy(diskButtons.get(0).getWidth()/2 -diskButtons.get(i).getWidth()/2,diskButtons.get(0).getHeight()/2 - diskButtons.get(i).getHeight()/2);
                }
                diskButtons.get(i).setOrigin(diskButtons.get(i).getOriginX() + diskButtons.get(i).getWidth()/2,diskButtons.get(i).getOriginY() + diskButtons.get(i).getHeight()/2);
                stage.addActor(diskButtons.get(i));
            }

        }

        @Override
        public void show() {

        }

        @Override
        public void render(float delta, boolean top) {
            if (top) {
                stage.act(delta);
            }
            stage.draw();
            switch(checkIfWon()) {
                case -1:
                    //nothing
                    break;
                case 0:
                    System.out.println("What the sigma?");
                    break;
                case 1:
                    System.out.println("Among Us");
                    break;


            }
            if (isCompleted) {
                System.out.println("job done");
            }

            if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                stack.remove(this);
            }


        }
        public int checkIfWon() {
            for (int j = 0; j < code.length;j++) {
                if (didDone(code[j],0)) {
                    return j;
                }
            }
            return -1;
        }
        public boolean didDone(int[] array,int iterator) {
            if (iterator >= diskButtons.size ) {
                return true;
            }

            if (array[iterator] != diskButtons.get(iterator).data) {
                return false;
            }

            return didDone(array,iterator + 1);

        }
        @Override
        public void resize(int width, int height) {

        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }

        @Override
        public void hide() {

        }

        @Override
        public void dispose() {

        }

        @Override
        public void setStage(Stage stage) {

        }

        @Override
        public Stage getStage() {
            return stage;
        }
    }

}
