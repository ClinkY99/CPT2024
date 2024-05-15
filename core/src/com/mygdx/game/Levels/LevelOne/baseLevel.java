package com.mygdx.game.Levels.LevelOne;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.SaveFile;
import com.mygdx.game.Multiplayer.MPHandle;
import com.mygdx.game.ui.ScreenStack;

/**
 * This class is the backbone behind all future level classes.
 * Holds all the rendering logic as well as screen stacking logic to make level creation efficient
 */

public abstract class baseLevel implements Screen {

    final CPTGame game;
    final MPHandle connection;
    final Stage stage;
    final SaveFile save;
    int Character;
    int levelNum;

    final ScreenStack screenStack;

    /**
     * Default constructor for the base level
     *
     * @param game Passes game controller to the class
     * @param c Passes MP connection to the class allowing for MP support
     * @param s Passes save file to the class
     * @param Character Passes character number the class, allowing the class to confirm that the right player is on the right level
     * @param levelNum tells the level how far along it is
     */

    protected baseLevel(CPTGame game, MPHandle c, SaveFile s, int Character, int levelNum) {
        this.game = game;
        connection = c;
        stage = new Stage(new FitViewport(1920, 1080));
        save = s;
        this.Character = Character;
        this.levelNum = levelNum;

        screenStack = new ScreenStack();
        game.pause.setStage(stage);
    }

    @Override
    public void show() {
        screenStack.show();
    }

    @Override
    public void render(float delta) {
        screenStack.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        screenStack.resize(width, height);
    }

    @Override
    public void pause() {
        screenStack.push(game.pause);
        screenStack.pause();
    }

    @Override
    public void resume() {
        screenStack.remove(game.pause);
        screenStack.resume();
    }

    @Override
    public void hide() {
        screenStack.hide();
    }

    public abstract void completed();

    @Override
    public void dispose() {
        screenStack.dispose();
    }
}
