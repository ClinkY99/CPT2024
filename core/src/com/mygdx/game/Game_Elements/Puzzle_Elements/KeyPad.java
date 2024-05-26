package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.World;
import com.mygdx.game.ui.ScreenStack;
import com.mygdx.game.ui.stackableScreen;
import com.ray3k.stripe.FreeTypeSkin;

import java.util.ArrayList;

public class KeyPad extends ImagePuzzleButton {
    keyPadScreen padScreen;
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
        ArrayList<Integer> currentInputtedCode;
        int[] code;
        ScreenStack stack;

        public keyPadScreen(int[] code, ScreenStack stack) {
            stage = new Stage(new FitViewport(1920,1080));
            Texture screenBlockerTexture = new Texture(Gdx.files.internal("Images/screenBlocker.png"));
            screenBlocker = new ImagePuzzleButton(screenBlockerTexture,0);
            screenBlocker.setPosition(-10,0);
            screenBlocker.setSize(1928,1080);
            stage.addActor(screenBlocker);
            Texture keypadBackTexture = new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/Pedastle Keypad.png"));
            ImagePuzzleButton keyPadBack = new ImagePuzzleButton(keypadBackTexture,0);
            keyPadBack.setPosition(500,400);
            stage.addActor(keyPadBack);
            this.code = code;

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

            ImagePuzzleButton button1 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Numbers/Numbers 1-9/Number1.png")),2);
            button1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(1);
                }
            });
            ImagePuzzleButton button2 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Numbers/Numbers 1-9/Number2.png")),2);
            button1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(2);
                }
            });
            ImagePuzzleButton button3 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Numbers/Numbers 1-9/Number3.png")),2);
            button1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(3);
                }
            });
            ImagePuzzleButton button4 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Numbers/Numbers 1-9/Number4.png")),2);
            button1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(4);
                }
            });
            ImagePuzzleButton button5 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Numbers/Numbers 1-9/Number5.png")),2);
            button1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(5);
                }
            });
            ImagePuzzleButton button6 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Numbers/Numbers 1-9/Number6.png")),2);
            button1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(6);
                }
            });
            ImagePuzzleButton button7 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Numbers/Numbers 1-9/Number7.png")),2);
            button1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(1);
                }
            });
            ImagePuzzleButton button8 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Numbers/Numbers 1-9/Number8.png")),2);
            button1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(8);
                }
            });
            ImagePuzzleButton button9 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Numbers/Numbers 1-9/Number9.png")),2);
            button1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(9);
                }
            });
            ImagePuzzleButton button0 = new ImagePuzzleButton(new Texture(Gdx.files.internal("Numbers/Numbers 1-9/Number0.png")),2);
            button1.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    currentInputtedCode.add(0);
                }
            });
            resetCodeButton.setPosition(500,900);
            button1.setPosition(500,850);
            button2.setPosition(550,850);
            button3.setPosition(600,850);
            button4.setPosition(500,800);
            button5.setPosition(550,800);
            button6.setPosition(600,800);
            button7.setPosition(500,750);
            button8.setPosition(550,750);
            button9.setPosition(600,750);
            button0.setPosition(550,700);
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
            stage.addActor(button0);
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
        }
        public boolean checkIfCodesAreSame() {
            int i = 0;

            if (currentInputtedCode != null) {
                while (i < currentInputtedCode.size() && i < code.length) {
                    if (currentInputtedCode.get(i) != code[i]) {
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

