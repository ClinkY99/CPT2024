package com.mygdx.game.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.Object;
import com.mygdx.game.Game_Elements.Player;
import com.mygdx.game.Game_Elements.Puzzle_Elements.*;
import com.mygdx.game.Game_Elements.Puzzle_Elements.PuzzleButton;
import com.mygdx.game.Game_Elements.World;
import com.mygdx.game.Menus.Interactive.MenuButton;
import com.ray3k.stripe.FreeTypeSkin;
import com.sun.source.util.TaskListener;

import java.util.logging.Level;

import static com.mygdx.game.Game_Elements.World.TableScroll;

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
    PuzzleTable keyPadTable;
    KeyPad testKeyPad;
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

        keyPadButtonHolder = new PuzzleTable(1500,540,stage);
        PuzzleButton summonKeypadButton = new PuzzleButton("Summon KeyPad",2,keyPadButtonHolder,globalSkin);

        summonKeypadButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                keyPadTable.setPosition(1000,600);
                testKeyPad.isShown = true;
                LevelWorld.allowMovement = false;
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


        TestBookShelf = new BookShelf(levelTable,stage, true, new Texture(Gdx.files.internal("Images/bucketPicture.jpeg")), 3,3);
        keyPadTable = new PuzzleTable(10000,10000,stage);
        testKeyPad = new KeyPad(keyPadTable,new int[]{1,2,3,4});
        camera.setToOrtho(false, 1920,1080);
        LevelBackground = new Texture(Gdx.files.internal("Images/brick.jpeg"));
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
        keyPadButtonHolder.loadPosition(LevelWorld,LevelWorld.objects.get(0));
        if (testKeyPad.correctCodeInputted) {
            for (int i = 0; i < testKeyPad.buttons.size(); i++) {
                testKeyPad.buttons.get(i).setColor(Color.BLUE);
            }
        }
        ScreenUtils.clear(0,0,0,1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.draw(LevelBackground, 0,0,1920,1080);
        LevelWorld.run(game.batch);

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            TestBookShelf.unloadText();
            LevelWorld.allowMovement = true;
            keyPadTable.setPosition(101010,10010101);
            testKeyPad.isShown = false;
        }
        if (TestBookShelf.textLoaded) {
            LevelWorld.allowMovement = false;
        }

        if (TestBookShelf.textLoaded || testKeyPad.isShown) {
            LevelWorld.shouldAct = false;
        } else {
            LevelWorld.shouldAct = true;
        }
        if (LevelWorld.shouldAct) {
            stage.act();
            TestBookShelf.text.draw(game.batch);
        } else if (TestBookShelf.textLoaded) {
            TestBookShelf.text.draw(game.batch);
        }
        testKeyPad.updateKeyPad(game.batch);
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
