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

public class PuzzleStatue extends ImagePuzzleButton{
    // a statue that adds a sword around it when clicked on
    public PuzzleStatue(ScreenStack stack,int degree) {
        super(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 3/Statue.png")),2);
        statueScreen statueScreenHere = new statueScreen(stack,this,degree);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stack.push(statueScreenHere);
            }
        });
    }




    public class statueScreen implements stackableScreen {
        Stage stage;
        ImagePuzzleButton screenBlocker;
        ScreenStack stack;
        public statueScreen(ScreenStack stack, ImagePuzzleButton superbutton, int degrees) {
            this.stack = stack;
            stage = new Stage(new FitViewport(1920,1080));
            Texture screenBlockerTexture = new Texture(Gdx.files.internal("Images/screenBlocker.png"));
            screenBlocker = new ImagePuzzleButton(screenBlockerTexture,0);
            screenBlocker.setPosition(0,0);
            screenBlocker.setSize(1928,1080);
            stage.addActor(screenBlocker);


            ImagePuzzleButton newStatue = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 3/StatueScreen.png")),2);
            newStatue.setPosition(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);

            ImagePuzzleButton sword = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 3/Sword.png")),2);
            sword.setOrigin(superbutton.getX() + superbutton.getWidth()/2,superbutton.getY() + superbutton.getHeight()/2);
            sword.setPosition(-100,0);
            sword.setRotation((degrees > 4) ? 195 - degrees*15:90 - degrees*15);
            stage.addActor(newStatue);
            stage.addActor(sword);
        }
        @Override
        public void show() {

        }

        @Override
        public void render(float delta, boolean top) {
            stage.draw();
            if (top) {
                stage.act();
            }
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

