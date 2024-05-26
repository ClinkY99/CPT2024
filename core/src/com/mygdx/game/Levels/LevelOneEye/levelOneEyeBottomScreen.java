package com.mygdx.game.Levels.LevelOneEye;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.Puzzle_Elements.KeyPad;
import com.mygdx.game.Game_Elements.World;
import com.mygdx.game.Levels.Screens.pauseMenu;
import com.mygdx.game.ui.ScreenStack;
import com.mygdx.game.ui.stackableScreen;

import java.io.IOException;

public class levelOneEyeBottomScreen implements stackableScreen {
    World levelWorld;
    Stage stage;
    CPTGame game;
    ScreenStack stack;
    Music music;
    public levelOneEyeBottomScreen(ScreenStack stack, CPTGame game) throws IOException {
        this.stack = stack;
        this.game = game;
        stage = new Stage(new FitViewport(1920,1080));
        levelWorld = new World("Levels/Level_1_1","player2", "Level_1_1",stage, true);

        KeyPad puzzleKeyPad = new KeyPad(new int[]{1,2,3,4},new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/Pedastle Keypad.png")),stack);
        puzzleKeyPad.setPosition(800,200);

        stage.addActor(puzzleKeyPad);

        music = Gdx.audio.newMusic(Gdx.files.internal("Music/Level 1/Library_2.wav"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta, boolean top) {
        levelWorld.run(top);
        if (top) {
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                stack.push(new pauseMenu(stack,game));
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
