package com.mygdx.game.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.Player;
import com.mygdx.game.Game_Elements.Puzzle_Elements.PuzzleButton;
import com.mygdx.game.Game_Elements.Puzzle_Elements.PuzzleButton;
import com.mygdx.game.Game_Elements.World;
import com.mygdx.game.Menus.Interactive.MenuButton;
import com.sun.source.util.TaskListener;

import java.util.logging.Level;

import static com.mygdx.game.Game_Elements.World.TableScroll;

public class TestLevel implements Screen {

    OrthographicCamera camera;
    Texture LevelBackground;
    Stage stage;
    FreeTypeFontGenerator LevelFont;
    float lastTrueScrollX = 0;
    float lastTrueScrollY = 0;
    PuzzleButton newButton;
    final CPTGame game;
    World LevelWorld;
    float LevelTableX = 300;
    float LevelTableY = 540;
    Table levelTable;

    public TestLevel(CPTGame game, Texture img) {
        this.game = game;
        camera = new OrthographicCamera();
        stage = new Stage(new FitViewport(1920,1080), game.batch);
        Gdx.input.setInputProcessor(stage);
        // Table for UI Widgets
        levelTable = new Table();

        PuzzleButton clickMe = new PuzzleButton("Click Me",5);
        clickMe.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("AAA");
            }
        });
        levelTable.add(clickMe);
        camera.setToOrtho(false, 1920,1080);
        LevelBackground = new Texture(Gdx.files.internal("Images/among us.png"));
        FreeTypeFontGenerator LevelFont = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Menu/tempus_sans_itc.ttf"));
        LevelWorld = new World(img);
        stage.addActor(levelTable);
        levelTable.setDebug(true);
    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        game.batch.begin();

        ScreenUtils.clear(0,0,0,1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        levelTable.setPosition(LevelTableX,LevelTableY);
        stage.act();
        game.batch.draw(LevelBackground, 0,0,1920,1080);

        //LevelFont.draw(game.batch, "SUS", 331,496);
        LevelWorld.run(game.batch);

        game.batch.end();
        stage.draw();
if (!LevelWorld.player.isCollidingX) {
    LevelTableX -= (float) ((float) LevelWorld.player.positionChange.x * LevelWorld.player.move);
}
if (!LevelWorld.player.isCollidingY) {
    LevelTableY -= (float) ((float) LevelWorld.player.positionChange.y * LevelWorld.player.move);

}



    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

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
}
