package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.ui.ScreenStack;
import com.mygdx.game.ui.stackableScreen;

public class GridButtonPuzzle extends ImagePuzzleButton{

    public GridButtonPuzzle(ScreenStack stack, Texture buttonTexture) {
        super(buttonTexture,2);
        GridButtonPuzzleScreen puzzle = new GridButtonPuzzleScreen(stack);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });
    }

    public class GridButtonPuzzleScreen implements stackableScreen {
        Stage stage;
        ScreenStack stack;
            public GridButtonPuzzleScreen(ScreenStack stack) {
                this.stage = new Stage(new FitViewport(1920,1080));
                this.stack = stack;
            }
        @Override
        public void show() {

        }

        @Override
        public void render(float delta, boolean top) {

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
            return null;
        }
    }
}
