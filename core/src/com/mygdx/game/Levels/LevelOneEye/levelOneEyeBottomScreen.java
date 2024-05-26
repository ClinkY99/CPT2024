package com.mygdx.game.Levels.LevelOneEye;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.Puzzle_Elements.GridButtonPuzzle;
import com.mygdx.game.Game_Elements.Puzzle_Elements.KeyPad;
import com.mygdx.game.Game_Elements.Puzzle_Elements.PewButton;
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
    KeyPad puzzleKeyPad;
    boolean hasdone = false;
    Music music;
    public levelOneEyeBottomScreen(ScreenStack stack, CPTGame game) throws IOException {
        this.stack = stack;
        this.game = game;
        stage = new Stage(new FitViewport(1920,1080));
        levelWorld = new World("assets/Levels/Level_1_1","player2", "Level_1_1",stage, true);

        puzzleKeyPad = new KeyPad(new int[]{1,2,3,4},new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/Pedastle Keypad.png")),stack);
        puzzleKeyPad.setPosition(700,1500);


        stage.addActor(puzzleKeyPad);

        music = Gdx.audio.newMusic(Gdx.files.internal("Music/Level 1/Library_2.wav"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();

        PewButton pew1 = new PewButton(stack,new Texture(Gdx.files.internal("Images/weirdLanguageHint.png")));
        pew1.setPosition(10300,2000);
        stage.addActor(pew1);

        PewButton pew2 = new PewButton(stack,new Texture(Gdx.files.internal("Images/eyePhoto.png")));
        pew2.setPosition(10300,1600);
        stage.addActor(pew2);

        PewButton pew3 = new PewButton(stack,new Texture(Gdx.files.internal("Images/circleHintExample.png")));
        pew3.setPosition(10300,1200);
        stage.addActor(pew3);
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

        if (puzzleKeyPad.isSolved && hasdone == false) {
            stack.remove(puzzleKeyPad.padScreen);
            stage.getActors().removeValue(puzzleKeyPad,true);
            hasdone = true;
            Texture gridPuzzleButton = new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/PedastleMaze.png"));
            GridButtonPuzzle gridPuzzle = new GridButtonPuzzle(stack,gridPuzzleButton);
            gridPuzzle.setPosition(700,1500);
            stage.addActor(gridPuzzle);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        music.pause();
    }

    @Override
    public void resume() {
        music.play();
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
