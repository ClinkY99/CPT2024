package com.mygdx.game.Levels.LevelOne;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Game_Elements.Puzzle_Elements.*;
import com.mygdx.game.Game_Elements.World;
import com.mygdx.game.ui.ScreenStack;
import com.mygdx.game.ui.stackableScreen;

import java.io.IOException;


public class levelOneBottomScreen implements stackableScreen {
        Stage stage;
        BookShelf testShelf;
    KeyPad puzzleKeyPad;
    RotationPuzzle lockPuzzle;
    InvisibleMazePuzzle mazePuzzle;
        TileDragPuzzle testDrag;
        World levelWorld;

        public levelOneBottomScreen(ScreenStack stack) throws IOException {
            stage = new Stage(new FitViewport(1920,1080));
            testShelf = new BookShelf(10,"",stack);
            testShelf.setPosition(300,300);
            stage.addActor(testShelf);

            levelWorld = new World("assets/Levels/Level_1","Level_1",stage);
            Array<Texture> realTileArray = new Array<>();
            for (int i = 0; i < 8; i++) {
                realTileArray.add(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 1/Tiles/Tile_00"+(i+1)+".png")));
            }

            Texture goalTexture = new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 1/Tiles/Goal.png"));
            Texture statueTexture = new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 1/Statue.png"));
            ImagePuzzleButton statue1 = new ImagePuzzleButton(statueTexture,2);

            statue1.setSize(statueTexture.getWidth(),statueTexture.getHeight());
            statue1.setPosition(600,3000);
            stage.addActor(statue1);
            ImagePuzzleButton statue2 = new ImagePuzzleButton(statueTexture,2);
            statue2.setSize(statueTexture.getWidth(),statueTexture.getHeight());
            statue2.setPosition(1000,3000);
            stage.addActor(statue2);
            Texture dragTexture = new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 1/Pedastle.png"));
            testDrag = new TileDragPuzzle(dragTexture,realTileArray,goalTexture,8,new int[][]{},stack);
            testDrag.setPosition(800,3000);
            testDrag.setSize(dragTexture.getWidth(),dragTexture.getHeight());

            puzzleKeyPad = new KeyPad(new int[]{1,2,3,4},new Texture(Gdx.files.internal("Images/keyPadSprite.png")),stack);
            puzzleKeyPad.setPosition(800,200);
            stage.addActor(testDrag);
            stage.addActor(puzzleKeyPad);


            Array<Texture> combinationLockPictures = new Array<>();
            combinationLockPictures.add(new Texture(Gdx.files.internal("Images/OuterButton.png")));
            combinationLockPictures.add(new Texture(Gdx.files.internal("Images/OuterOuterInner.png")));
            combinationLockPictures.add(new Texture(Gdx.files.internal("Images/InnerOuterButton.png")));
            combinationLockPictures.add(new Texture(Gdx.files.internal("Images/rotationPuzzleWheelExample.png")));

            lockPuzzle = new RotationPuzzle(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 1/Statue.png")),combinationLockPictures,new int[][]{{0,1,2,3},{3,4,3,2}},stack, new int[]{16,12,8,1});
            stage.addActor(lockPuzzle);
            lockPuzzle.setPosition(5700,3000);

            Texture mazeButtonTexture = new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 1/Statue.png"));
            mazePuzzle = new InvisibleMazePuzzle(mazeButtonTexture,new Texture(Gdx.files.internal("Images/idle_0.png")),new Texture("Levels/Level_1/arrow.png"),stack,new int[][]{{2,2}},new int[][]{{1,2}}, new int[]{4,4});
            mazePuzzle.setSize(mazeButtonTexture.getWidth(),mazeButtonTexture.getHeight());
            stage.addActor(mazePuzzle);
            mazePuzzle.setPosition(0,800);


            Array<Texture> tiles = new Array<>();
            tiles.add(new Texture("Images/tiles/Level1/Puzzles/Puzzle 2/desk.png"));
            Texture deskTexture = new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 2/desk.png"));
            Desk deskPuzzle = new Desk(deskTexture,stack,tiles);
            deskPuzzle.setSize(deskTexture.getWidth(),deskTexture.getHeight());
            deskPuzzle.setPosition(10300,3000);
            stage.addActor(deskPuzzle);
        }

        @Override
        public void show() {

        }

        @Override
        public void render(float delta, boolean top) {

            levelWorld.run(top);
            if(top) {
                stage.act(delta);
            }
            stage.draw();
            if (mazePuzzle.isWon && puzzleKeyPad.isSolved && lockPuzzle.isCompleted) {

            }

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
