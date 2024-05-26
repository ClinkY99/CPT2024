package com.mygdx.game.Levels.LevelOne;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.Puzzle_Elements.*;
import com.mygdx.game.Game_Elements.World;
import com.mygdx.game.Levels.Screens.pauseMenu;
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

        Music music;
    Array<Texture> combinationLockPictures = new Array<>();

    Desk deskPuzzle;

        public levelOneBottomScreen(ScreenStack stack, CPTGame game) throws IOException {
            this.stack = stack;
            this.game = game;
            stage = new Stage(new FitViewport(1920,1080));
            renderBeforePlayer = new Stage(new FitViewport(1920,1080));

            levelWorld = new World("assets/Levels/Level_1", "player1","Level_1",stage, false);
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
            rune.setPosition(10100 + deskTexture.getWidth()/2 - 400,3200 - 400);
            renderBeforePlayer.addActor(rune);

            Array<Texture> tiles = new Array<>();
            tiles.add(new Texture("Images/tiles/Level1/Puzzles/Puzzle 2/desk.png"));
             deskPuzzle = new Desk(deskTexture,stack,tiles);
            deskPuzzle.setSize(deskTexture.getWidth(),deskTexture.getHeight());
            deskPuzzle.setPosition(10200,2800);
            stage.addActor(deskPuzzle);


            ImagePuzzleButton statue3 = new ImagePuzzleButton(statueTexture,2);
            statue3.setPosition(10600,3400);
            ImagePuzzleButton statue4 = new ImagePuzzleButton(statueTexture,2);
            statue4.setPosition(9800,3400);
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
            if (deskPuzzle.isComplete && testDrag.isComplete) {
                lockPuzzle = new RotationPuzzle(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 3/Statue.png")), combinationLockPictures, new int[][]{{0, 1, 2, 3}, {3, 4, 3, 2}}, stack, new int[]{16, 12, 8, 1});
                stage.addActor(lockPuzzle);
                lockPuzzle.setPosition(5500, 3200);
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
