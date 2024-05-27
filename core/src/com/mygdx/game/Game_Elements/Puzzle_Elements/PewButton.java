package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.ui.ScreenStack;
import com.mygdx.game.ui.stackableScreen;

public class PewButton extends ImagePuzzleButton {
    // this class creates a pew that displays an image when clicked on
    public PewScreen pewThing;
    public PewButton(ScreenStack stack,Texture text) {
        super(new Texture(Gdx.files.internal("Images/Church_Pew.png")),2);
        pewThing = new PewScreen(stack,text);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stack.push(pewThing);
            }
        });

    }
    class PewScreen implements stackableScreen {
        Stage stage;
        ScreenStack stack;
        public ImagePuzzleButton textButton;
        public PewScreen(ScreenStack stack, Texture text) {
            stage = new Stage(new FitViewport(1928,1080));
            this.stack = stack;
            Texture screenBlockerTexture = new Texture(Gdx.files.internal("Images/screenBlocker.png"));
            ImagePuzzleButton screenBlocker = new ImagePuzzleButton(screenBlockerTexture,0);
            screenBlocker.setPosition(-10,0);
            screenBlocker.setSize(1928,1080);
            stage.addActor(screenBlocker);

            textButton = new ImagePuzzleButton(text,2);
            textButton.setSize(800,1000);
            textButton.setPosition(Gdx.graphics.getWidth()/2 - textButton.getWidth()/2,Gdx.graphics.getHeight()/2-textButton.getHeight()/2);
            stage.addActor(textButton);
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
            if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
                stack.remove(this);
            }
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
