package com.mygdx.game.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.Puzzle_Elements.*;
import com.mygdx.game.Game_Elements.Puzzle_Elements.PuzzleButton;
import com.mygdx.game.Game_Elements.World;
import com.ray3k.stripe.FreeTypeSkin;

import java.io.IOException;
import java.util.ArrayList;

public class TestLevel implements Screen {

    OrthographicCamera camera;
    Texture LevelBackground;
    Stage stage;
   public static BookShelf TestBookShelf;
    PuzzleTable buttonTable;
    Sprite keyPadBackDrop;
    FreeTypeFontGenerator LevelFont;
    float lastTrueScrollX = 0;
    float lastTrueScrollY = 0;
    PuzzleButton newButton;
    final CPTGame game;
    Skin globalSkin;
    PuzzleTable keyPadButtonHolder;
    PuzzleTable imageButtonHolder;
    PuzzleTable keyPadTable;
    KeyPad testKeyPad;
    World LevelWorld; Array<DragDropObject> dragDropObjectTest;

    DragDropPuzzle testDragDropPuzzle;
    boolean solved = false;
ImagePuzzleButton doorButton;
    PuzzleTable levelTable;
    PuzzleTable gridTable;
    ColorGridPuzzle testGridPuzzle;
Array<PuzzleTable> puzzleTables;
    public TestLevel(CPTGame game, Texture img) throws IOException
    {

        this.game = game;
        LevelWorld = new World(img);
        camera = new OrthographicCamera();
        stage = new Stage(new FitViewport(1920,1080), game.batch);
        globalSkin = new FreeTypeSkin(Gdx.files.internal("skin/vhs-ui.json"));
        Gdx.input.setInputProcessor(stage);
        // Table for UI Widgets

        levelTable = new PuzzleTable(640,540,stage);
        buttonTable = new PuzzleTable(1040,840,stage);
        gridTable = new PuzzleTable(3300,840,stage);
        keyPadButtonHolder = new PuzzleTable(2500,540,stage);
        imageButtonHolder = new PuzzleTable(4000,600,stage);
        keyPadTable = new PuzzleTable(1000,600,stage);


        puzzleTables = new Array<>();
        puzzleTables.add(levelTable,buttonTable,gridTable,keyPadButtonHolder);
        puzzleTables.add(imageButtonHolder);

        testGridPuzzle = new ColorGridPuzzle(gridTable,stage,this);

        PuzzleButton clickMe = new PuzzleButton("Click to Summon Drag and Drop Puzzle",2, buttonTable,globalSkin);

        clickMe.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                testDragDropPuzzle.isLoaded = true;
            }
        });

        ImagePuzzleButton summonKeypadButton = new ImagePuzzleButton(new Texture("Images/keyPadSprite.png"),2,this);
        testKeyPad = new KeyPad(keyPadTable,new int[]{1,2,3,4});

        summonKeypadButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                keyPadTable.setPosition(1000,600);
                LevelWorld.allowMovement = false;
            }
        });

        keyPadButtonHolder.add(summonKeypadButton);
        buttonTable.row();

        TestBookShelf = new BookShelf(levelTable,stage, true, new Texture(Gdx.files.internal("Images/noteForBookShelfTest.png")), 3,3,this,LevelWorld);
        camera.setToOrtho(false, 1920,1080);
        LevelBackground = new Texture(Gdx.files.internal("Images/undertaleBackground.png"));

        dragDropObjectTest = new Array<>();
        dragDropObjectTest.add(new DragDropObject(new Texture(Gdx.files.internal("Images/key.png"))));

        testDragDropPuzzle = new DragDropPuzzle(LevelWorld, game.batch);

        doorButton = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/newDoorSprite.png")),5, this);
        imageButtonHolder.add(doorButton);



    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        ScreenUtils.clear(0,0,0,1);

        for(int i = 0; i < puzzleTables.size;i++) {
            puzzleTables.get(i).loadPosition(LevelWorld,LevelWorld.objects.get(0));
        }
        if (testKeyPad.correctCodeInputted) {
            for (int i = 0; i < testKeyPad.buttons.size(); i++) {
                testKeyPad.buttons.get(i).setColor(Color.BLUE);
            }
        }

        testGridPuzzle.updateGrid();
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.draw(LevelBackground, 0,0,1920,1080);
        LevelWorld.run(game.batch);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            TestBookShelf.unloadText();
            LevelWorld.allowMovement = true;
            testDragDropPuzzle.isLoaded = false;
            testKeyPad.isShown = false;
            keyPadTable.setPosition(10000,10000);
        }


        stage.act();

        testKeyPad.updateKeyPad(game.batch,LevelWorld);
        testDragDropPuzzle.render(dragDropObjectTest, game.batch, stage);



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
