package com.mygdx.game.Levels.Screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.ui.stackableScreen;

public class pauseMenu implements stackableScreen {

    Stage stage;
    Texture background;

    public pauseMenu(){
        background = new Texture("Images/screenBlocker.png");
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
        this.stage = stage;
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }


}
