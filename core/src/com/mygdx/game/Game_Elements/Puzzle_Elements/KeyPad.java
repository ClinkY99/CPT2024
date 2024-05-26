package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import com.mygdx.game.ui.ScreenStack;
import com.mygdx.game.ui.stackableScreen;

import java.util.ArrayList;
// this class creates a keypad that the player must input a certain code into to continue

public class KeyPad extends ImagePuzzleButton {
    public keyPadScreen padScreen;
    TextureRegion region;
    public boolean isSolved;

    public KeyPad(int[] code,Texture buttonTexture,ScreenStack stack) {
        super(buttonTexture,2);

        padScreen = new keyPadScreen(code,stack);
        region = new TextureRegion(buttonTexture);
        setBounds(region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight());
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("SUS");
                stack.push(padScreen);

            }
        });

    }

    public class keyPadScreen implements stackableScreen {
        Stage stage;
        ImagePuzzleButton screenBlocker;
        ArrayList<ImagePuzzleButton> buttonsOnScreen;
        ArrayList<Integer> currentInputtedCode;
        int[] code;
        ScreenStack stack;

        public keyPadScreen(int[] code, ScreenStack stack) {
            buttonsOnScreen = new ArrayList<>();
            stage = new Stage(new FitViewport(1920,1080));
            Texture screenBlockerTexture = new Texture(Gdx.files.internal("Images/screenBlocker.png"));
            screenBlocker = new ImagePuzzleButton(screenBlockerTexture,0);
            screenBlocker.setPosition(-10,0);
            screenBlocker.setSize(1928,1080);
            stage.addActor(screenBlocker);
            Texture keypadBackTexture = new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/Pedastle Screen.png"));
            ImagePuzzleButton keyPadBack = new ImagePuzzleButton(keypadBackTexture,0);
            keyPadBack.setPosition(Gdx.graphics.getWidth()/2 - keypadBackTexture.getWidth()/2,Gdx.graphics.getHeight()/2 - keypadBackTexture.getHeight()/2);
            stage.addActor(keyPadBack);
            this.code = code;
            currentInputtedCode = new ArrayList<>();
            this.stack=stack;

            ImagePuzzleButton resetCodeButton = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/resetButtonImage.png")),2);
            resetCodeButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                        currentInputtedCode.clear();
                }
            });
            TextureRegion region = new TextureRegion(resetCodeButton.getTexture());
            resetCodeButton.setBounds(region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight());

            ImagePuzzleButton button1 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/Keys/Key1_Down.png")),2);
            button1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(1);
                }
            });
            ImagePuzzleButton button2 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/Keys/Key2_Down.png")),2);
            button2.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(2);
                }
            });
            ImagePuzzleButton button3 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/Keys/Key3_Down.png")),2);
            button3.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(3);
                }
            });
            ImagePuzzleButton button4 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/Keys/Key4_Down.png")),2);
            button4.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(4);
                }
            });
            ImagePuzzleButton button5 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/Keys/Key5_Down.png")),2);
            button5.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(5);
                }
            });
            ImagePuzzleButton button6 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/Keys/Key6_Down.png")),2);
            button6.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(6);
                }
            });
            ImagePuzzleButton button7 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/Keys/Key7_Down.png")),2);
            button7.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(1);
                }
            });
            ImagePuzzleButton button8 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/Keys/Key8_Down.png")),2);
            button8.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(8);
                }
            });
            ImagePuzzleButton button9 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/Keys/Key9_Down.png")),2);
            button9.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(9);
                }
            });
            ImagePuzzleButton button0 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/Keys/Key1_Down.png")),2);
            button0.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(0);
                }
            });
            resetCodeButton.setPosition(500,900);
            button1.setPosition(840,595);
            button2.setPosition(964,595);
            button3.setPosition(1090,595);
            button4.setPosition(840,470);
            button5.setPosition(964,470);
            button6.setPosition(1090,470);
            button7.setPosition(840,345);
            button8.setPosition(964,345);
            button9.setPosition(1090,345);
            button0.setPosition(964,202);
            button0.setSize(100,100);
            button1.setSize(100,100);
            button2.setSize(100,100);
            button3.setSize(100,100);
            button4.setSize(100,100);
            button5.setSize(100,100);
            button6.setSize(100,100);
            button7.setSize(100,100);
            button8.setSize(100,100);
            button9.setSize(100,100);
            stage.addActor(resetCodeButton);
            stage.addActor(button1);
            stage.addActor(button2);
            stage.addActor(button3);
            stage.addActor(button4);
            stage.addActor(button5);
            stage.addActor(button6);
            stage.addActor(button7);
            stage.addActor(button8);
            stage.addActor(button9);
           // stage.addActor(button0);
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

            if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                stack.remove(this);
            }
            if (checkIfCodesAreSame()) {
                isSolved = true;
            }
            for (int i = 0; i < currentInputtedCode.size();i++) {
                        ImagePuzzleButton temp = displayCode(currentInputtedCode.get(i));
                        temp.setPosition(780 + i*135,710 + temp.getHeight());
                        buttonsOnScreen.add(temp);
                        stage.addActor(temp);
            }
        }

        public ImagePuzzleButton displayCode(int a) {
           ImagePuzzleButton tempButton = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/Keys/Key" + (a) +"_Down.png")),2);
           return tempButton;
        }
        public boolean checkIfCodesAreSame() {
            int i = 0;
            System.out.println(currentInputtedCode);
            if (currentInputtedCode != null && currentInputtedCode.size() == code.length) {
                while (i < currentInputtedCode.size() && i < code.length) {
                    if (currentInputtedCode.get(i) != code[i]) {
                        currentInputtedCode.clear();
                            for (int k = 0; k < buttonsOnScreen.size(); k++) {
                                stage.getActors().removeValue(buttonsOnScreen.get(k), true);
                            }

                        return false;
                    }
                    i++;
                }
                    return true;


            }
            return false;
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
            this.stage = stage;
        }

        @Override
        public Stage getStage() {
            return stage;
        }
    }

}

