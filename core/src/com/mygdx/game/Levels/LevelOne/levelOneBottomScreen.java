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
import com.mygdx.game.Game_Elements.Puzzle_Elements.BookShelf;
import com.mygdx.game.Game_Elements.Puzzle_Elements.KeyPad;
import com.mygdx.game.Game_Elements.Puzzle_Elements.RotationPuzzle;
import com.mygdx.game.Game_Elements.Puzzle_Elements.TileDragPuzzle;
import com.mygdx.game.Game_Elements.World;
import com.mygdx.game.ui.ScreenStack;
import com.mygdx.game.ui.stackableScreen;

import java.io.IOException;


public class levelOneBottomScreen implements stackableScreen {
        Stage stage;
        BookShelf testShelf;
        TileDragPuzzle testDrag;

    World levelWorld;

        public levelOneBottomScreen(ScreenStack stack) throws IOException {
            stage = new Stage(new FitViewport(1920,1080));
            testShelf = new BookShelf(new Texture(Gdx.files.internal("Images/idle_0.png")),2,new Texture(Gdx.files.internal("Images/key.png")),stack);
            testShelf.setPosition(300,300);
            testShelf.setSize(30,30);
            stage.addActor(testShelf);

            levelWorld = new World("Levels/Level_1","Level_1",stage);

            Array<Texture> fakeTileArray = new Array<>();
            fakeTileArray.add(new Texture(Gdx.files.internal("Images/idle_0.png")));
            fakeTileArray.add(new Texture(Gdx.files.internal("Images/idle_0.png")));
            fakeTileArray.add(new Texture(Gdx.files.internal("Images/idle_0.png")));
            fakeTileArray.add(new Texture(Gdx.files.internal("Images/idle_0.png")));

            Array<Texture> realTileArray = new Array<>();
            realTileArray.add(new Texture(Gdx.files.internal("Images/idle_0_red.png")));
            realTileArray.add(new Texture(Gdx.files.internal("Images/idle_0_red.png")));
            realTileArray.add(new Texture(Gdx.files.internal("Images/idle_0_red.png")));
            realTileArray.add(new Texture(Gdx.files.internal("Images/idle_0_red.png")));

            Texture goalTexture = new Texture(Gdx.files.internal("Images/gale.jpeg"));

            testDrag = new TileDragPuzzle(new Texture(Gdx.files.internal("Images/idle_0_red.png")),fakeTileArray,realTileArray,goalTexture,2,new int[][]{{300,700},{600,700}},stack);
            testDrag.setPosition(700,200);
            testDrag.setSize(400,400);

            KeyPad puzzleKeyPad = new KeyPad(new int[]{1,2,3,4},new Texture(Gdx.files.internal("Images/keyPadSprite.png")),stack);
            puzzleKeyPad.setPosition(800,200);
            stage.addActor(testDrag);
            stage.addActor(puzzleKeyPad);

            Array<Texture> combinationLockPictures = new Array<>();
            combinationLockPictures.add(new Texture(Gdx.files.internal("Images/OuterButton.png")));
            combinationLockPictures.add(new Texture(Gdx.files.internal("Images/OuterOuterInner.png")));
            combinationLockPictures.add(new Texture(Gdx.files.internal("Images/InnerOuterButton.png")));
            combinationLockPictures.add(new Texture(Gdx.files.internal("Images/rotationPuzzleWheelExample.png")));

            RotationPuzzle lockPuzzle = new RotationPuzzle(new Texture(Gdx.files.internal("Images/idle_0_red.png")),combinationLockPictures,new int[][]{{0,1,2,3},{3,4,3,2}},stack, new int[]{16,12,8,1});
            stage.addActor(lockPuzzle);
            lockPuzzle.setPosition(800,800);
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
