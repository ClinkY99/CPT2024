package com.mygdx.game.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.Puzzle_Elements.ColorGridPuzzle;
import com.mygdx.game.Game_Elements.Puzzle_Elements.PuzzleButton;
import com.mygdx.game.Game_Elements.Puzzle_Elements.PuzzleTable;
import com.mygdx.game.Game_Elements.World;
import com.ray3k.stripe.FreeTypeSkin;

public class TestLevel implements Screen {

    OrthographicCamera camera;
    Texture LevelBackground;
    Stage stage;
    ColorGridPuzzle gridPuzzle;
    PuzzleTable buttonTable;
    FreeTypeFontGenerator LevelFont;
    float lastTrueScrollX = 0;
    float lastTrueScrollY = 0;
    PuzzleButton newButton;
    final CPTGame game;
    Skin globalSkin;
    World LevelWorld;

    PuzzleTable levelTable;

    public TestLevel(CPTGame game, Texture img) {

        this.game = game;
        LevelWorld = new World(img);
        camera = new OrthographicCamera();
        stage = new Stage(new FitViewport(1920,1080), game.batch);
        globalSkin = new FreeTypeSkin(Gdx.files.internal("skin/vhs-ui.json"));
        Gdx.input.setInputProcessor(stage);
        // Table for UI Widgets
        levelTable = new PuzzleTable(640,540,stage);
        buttonTable = new PuzzleTable(1040,540,stage);

        PuzzleButton clickMe = new PuzzleButton("How is it going?",2, buttonTable,globalSkin);

        clickMe.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                LevelWorld.colorSpasm = !LevelWorld.colorSpasm;
            }
        });
        buttonTable.row();

        PuzzleButton dontClickMe = new PuzzleButton("Not well",1,buttonTable,globalSkin);
        dontClickMe.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
            }
        });

        PuzzleButton doClickMe = new PuzzleButton("Well",1,buttonTable,globalSkin);
        doClickMe.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                buttonTable.row();
                PuzzleButton goodToHearThat = new PuzzleButton("Good to hear that!",2,buttonTable,globalSkin);
                goodToHearThat.addListener(new ClickListener() {
                   public void clicked(InputEvent event,float x, float y) {
                       goodToHearThat.setPosition(x,y);
                   }
                });
            }
        });


        gridPuzzle = new ColorGridPuzzle(levelTable);

        camera.setToOrtho(false, 1920,1080);
        LevelBackground = new Texture(Gdx.files.internal("Images/among us.png"));
        FreeTypeFontGenerator LevelFont = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Menu/tempus_sans_itc.ttf"));

    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        game.batch.begin();

        levelTable.loadPosition(LevelWorld,LevelWorld.objects.get(0));
        buttonTable.loadPosition(LevelWorld,LevelWorld.objects.get(0));
        ScreenUtils.clear(0,0,0,1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        stage.act();
        gridPuzzle.updateGridColor();
        game.batch.draw(LevelBackground, 0,0,1920,1080);
        //LevelFont.draw(game.batch, "SUS", 331,496);
        LevelWorld.run(game.batch);

        game.batch.end();
        stage.draw();




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
