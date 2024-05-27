package com.mygdx.game.Levels.LevelOne;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.Puzzle_Elements.*;
import com.mygdx.game.Game_Elements.World;
import com.mygdx.game.Levels.Screens.pauseMenu;
import com.mygdx.game.Levels.baseLevel;
import com.mygdx.game.Menus.CreditsScreen;
import com.mygdx.game.Multiplayer.MPInterface;
import com.mygdx.game.ui.ScreenStack;
import com.mygdx.game.ui.stackableScreen;

import java.io.IOException;
import java.util.Objects;


public class levelOneBottomScreen implements stackableScreen {
        Stage stage;
        Stage renderBeforePlayer;
        BookShelf testShelf;
    KeyPad puzzleKeyPad;
    RotationPuzzle lockPuzzle;
    InvisibleMazePuzzle mazePuzzle;
        TileDragPuzzle testDrag;
        World levelWorld;
    CPTGame game;
        ScreenStack stack;
        boolean hasLoaded = false;

        Music music;
    Array<Texture> combinationLockPictures = new Array<>();

    Desk deskPuzzle;

    baseLevel levelRef;

        public levelOneBottomScreen(ScreenStack stack, CPTGame game, baseLevel level) throws IOException {
            this.stack = stack;
            this.game = game;
            stage = new Stage(new FitViewport(1920,1080));
            renderBeforePlayer = new Stage(new FitViewport(1920,1080));

            levelWorld = new World("assets/Levels/Level_1", "player1","Level_1",stage, false,renderBeforePlayer);
            Array<Texture> realTileArray = new Array<>();
            for (int i = 0; i < 8; i++) {
                realTileArray.add(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 1/Tiles/Tile_00"+(i+1)+".png")));
            }

            Texture goalTexture = new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 1/Tiles/Goal.png"));
            Texture statueTexture = new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 3/Statue.png"));
            ImagePuzzleButton statue1 = new ImagePuzzleButton(statueTexture,2);
            
            ImagePuzzleButton underPedastle = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 1/FloorPattern.png")),2);
            underPedastle.setPosition(400,2800);
            renderBeforePlayer.addActor(underPedastle);

            statue1.setSize(statueTexture.getWidth(),statueTexture.getHeight());
            statue1.setPosition(400,3200);
            stage.addActor(statue1);
            ImagePuzzleButton statue2 = new ImagePuzzleButton(statueTexture,2);
            statue2.setSize(statueTexture.getWidth(),statueTexture.getHeight());
            statue2.setPosition(800,3200);
            stage.addActor(statue2);
            Texture dragTexture = new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 1/Pedastle.png"));
            testDrag = new TileDragPuzzle(dragTexture,realTileArray,goalTexture,8,new int[][]{},stack);
            testDrag.setPosition(600,3200);
            testDrag.setSize(dragTexture.getWidth(),dragTexture.getHeight());

            stage.addActor(testDrag);

            combinationLockPictures.add(new Texture(Gdx.files.internal("Images/OuterButton.png")));
            combinationLockPictures.add(new Texture(Gdx.files.internal("Images/OuterOuterInner.png")));
            combinationLockPictures.add(new Texture(Gdx.files.internal("Images/InnerOuterButton.png")));
            combinationLockPictures.add(new Texture(Gdx.files.internal("Images/rotationPuzzleWheelExample.png")));

            ImagePuzzleButton statuePlaceHolder = new ImagePuzzleButton(statueTexture,2);
            statuePlaceHolder.setPosition(5500,3200);
            stage.addActor(statuePlaceHolder);

            Texture deskTexture = new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 2/desk.png"));
            ImagePuzzleButton rune = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 2/DimensionRune_Unactive.png")),2);
            rune.setPosition(10200 + deskTexture.getWidth()/2 - 400,3200 - 800);
            renderBeforePlayer.addActor(rune);

            Array<Texture> tiles = new Array<>();
            for(int i = 0; i < 6;i++) {
                tiles.add(new Texture(Gdx.files.internal("Images/witness" + (i+1) + ".png")));
            }
             deskPuzzle = new Desk(deskTexture,stack,tiles);
            deskPuzzle.setSize(deskTexture.getWidth(),deskTexture.getHeight());
            deskPuzzle.setPosition(10200,2800);
            stage.addActor(deskPuzzle);


            ImagePuzzleButton statue3 = new ImagePuzzleButton(statueTexture,2);
            statue3.setPosition(10600,3400);
            ImagePuzzleButton statue4 = new ImagePuzzleButton(statueTexture,2);
            statue4.setPosition(10000,3400);
            stage.addActor(statue3);
            stage.addActor(statue4);

            PuzzleStatue statuePuzzle = new PuzzleStatue(stack,2);
            statuePuzzle.setPosition(800,3200);
            stage.addActor(statuePuzzle);

            ImagePuzzleButton chair = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 2/chair.png")),2);
            chair.setPosition(deskPuzzle.getX() + deskTexture.getWidth()/2 - 200,deskPuzzle.getY() - 200);
            stage.addActor(chair);

            music = Gdx.audio.newMusic(Gdx.files.internal("Music/Level 1/The_Library_1.wav"));
            music.setLooping(true);
            music.setVolume(0.5f);
            music.play();

            levelRef = level;
            if(levelRef.connection !=null) {
                Image playerBuddy1 = new Image(new Texture(Gdx.files.internal("Images/Players/EyeBuddy1.png")));
                playerBuddy1.setPosition(0,0);
                playerBuddy1.setSize(150,150);
                renderBeforePlayer.addActor(playerBuddy1);

                levelRef.connection.bindFunction((connection, object) -> {
                    MPInterface.confirm data = (MPInterface.confirm) object;
                    if (data.ID != null) {
                        if (data.ID.equals("Puzzle2")) {
                            deskPuzzle.deskScreen.switchImage(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 2/Maze Stuff/DeskScreenMaze.png")));
                        }
                    }
                }, MPInterface.confirm.class);

                levelRef.connection.bindFunction((connection, object) -> {
                    MPInterface.playerLoc data = (MPInterface.playerLoc) object;
                    if(data != null){
                        playerBuddy1.setPosition(-data.locx, -data.locy);
                    }
                }, MPInterface.playerLoc.class);
            } else {
                deskPuzzle.deskScreen.switchImage(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 2/Maze Stuff/DeskScreenMaze.png")));
            }



        }

        @Override
        public void show() {

        }

        @Override
        public void render(float delta, boolean top) {

            levelWorld.run(top);
            if (top) {
                stage.act(delta);
            }
            stage.draw();

            if (top) {
                if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                    stack.push(new pauseMenu(stack, game));
                }
            }
            if (deskPuzzle.isComplete && testDrag.isComplete && !hasLoaded) {
                lockPuzzle = new RotationPuzzle(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 3/Statue.png")), combinationLockPictures, new int[][]{{5, 0, 1, 0}}, stack, new int[]{8, 4, 4, 1});
                stage.addActor(lockPuzzle);
                lockPuzzle.setPosition(5500, 3200);
                hasLoaded = true;
           }
            if(lockPuzzle!=null){
                if(lockPuzzle.isCompleted){
                    if(levelRef.connection!=null){
                        levelRef.connection.sendTCP(new MPInterface.levelCompletion("", 1,true));
                    }
                    game.setScreen(new CreditsScreen(game, Gdx.audio.newMusic(Gdx.files.internal("Music/Ignorant_Lullaby.wav"))));
                }
            }
            if(levelRef.connection!=null){
                MPInterface.playerLoc data = new MPInterface.playerLoc();
                data.locx = (int) levelWorld.player1.playerPosition.x;
                data.locy = (int) levelWorld.player1.playerPosition.y;
                levelRef.connection.sendTCP(data);
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
