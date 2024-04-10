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
import com.mygdx.game.Game_Elements.Puzzle_Elements.Button;
import com.mygdx.game.Game_Elements.World;
import com.mygdx.game.Menus.Interactive.MenuButton;

import java.util.logging.Level;

public class TestLevel implements Screen {
    OrthographicCamera camera;
    Texture LevelBackground;
    Stage stage;
    FreeTypeFontGenerator LevelFont;
    Button newButton;
    final CPTGame game;
    World LevelWorld;
    Table levelTable;

    public TestLevel(CPTGame game, Texture img) {
        this.game = game;
        camera = new OrthographicCamera();
        stage = new Stage(new FitViewport(1920,1080), game.batch);
        Gdx.input.setInputProcessor(stage);
        // Table for UI Widgets
        levelTable = new Table();

        MenuButton clickMe = new MenuButton("Click Me");
        clickMe.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //
            }
        });
        levelTable.add(clickMe);
        levelTable.setPosition(250, 1080/2 -50);
        camera.setToOrtho(false, 1920,1080);
        LevelBackground = new Texture(Gdx.files.internal("Images/whiteRectangle.png"));
        FreeTypeFontGenerator LevelFont = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Menu/tempus_sans_itc.ttf"));
        LevelWorld = new World(img);
        newButton = new Button();
        stage.addActor(levelTable);

    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(LevelBackground, 0,0,1920,1080);
        //LevelFont.draw(game.batch, "SUS", 331,496);
        LevelWorld.run(game.batch);
        game.batch.end();
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
}
