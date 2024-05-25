package com.mygdx.game.Levels.LevelOneEye;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Game_Elements.World;
import com.mygdx.game.Levels.Screens.pauseMenu;
import com.mygdx.game.ui.ScreenStack;
import com.mygdx.game.ui.stackableScreen;

import java.io.IOException;

public class levelOneEyeBottomScreen implements stackableScreen {
    World levelWorld;
    Stage stage;
    ScreenStack stack;
    public levelOneEyeBottomScreen(ScreenStack stack) throws IOException {
        this.stack = stack;
        stage = new Stage(new FitViewport(1920,1080));
        levelWorld = new World("assets/Levels/Level_1","Level_1_Eye",stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta, boolean top) {
        if (top) {
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                stack.push(new pauseMenu(stack));
            }
            stage.act(delta);
        }
        stage.draw();
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
