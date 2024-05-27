package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.ui.ScreenStack;
import com.mygdx.game.ui.stackableScreen;
import com.ray3k.stripe.FreeTypeSkin;

import java.awt.print.Book;

public class Desk extends ImagePuzzleButton {
    public DeskScreen deskScreen;

    public boolean isComplete = false;

    boolean switchImage = false;

    public Desk(Texture deskTexture, ScreenStack stack, Array<Texture> tiles) {
        super(deskTexture, 2);
        deskScreen = new DeskScreen(tiles,stack,this);
        TextureRegion region = new TextureRegion(deskTexture);
        setBounds(region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight());
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("SUS");
                stack.push(deskScreen);
                if (deskScreen.switchImage) {
                    stack.push(deskScreen.mazePuzzle.mazeScreen);
                    deskScreen.switchImage(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 2/Maze Stuff/DeskScreenMaze.png")));
                }
            }
        });

        //should have a
        //when clicked opens screen containing folder which opens tile drag puzzle
        //when code inputted on eye side have function to change desk to a different image, and add a new screen

    }

    public class DeskScreen implements stackableScreen {
        Stage stage;
        int state = 0;
        ScreenStack stack;
         DragDropScreen dropScreen;
        InvisibleMazePuzzle mazePuzzle;
        public ImagePuzzleButton screenBlocker;
        ImagePuzzleButton folderButton;

        public boolean switchImage;

        ImagePuzzleButton superButton;

        public DeskScreen(Array<Texture> tiles,ScreenStack stack, ImagePuzzleButton superButton) {
            this.superButton = superButton;
            this.stack = stack;
            stage = new Stage(new FitViewport(1920, 1080));
            Texture screenBlockerTexture = new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 2/DeskScreen.png"));
            screenBlocker = new ImagePuzzleButton(screenBlockerTexture, 0);
            screenBlocker.setPosition(-10, 0);
            screenBlocker.setSize(1928, 1080);
            Texture folderTexture = new Texture("Images/tiles/Level1/Puzzles/Puzzle 2/File.png");
            folderButton = new ImagePuzzleButton(folderTexture,2);
            folderButton.setSize(folderTexture.getWidth(),folderTexture.getHeight());
            folderButton.setPosition(500,350);


            mazePuzzle = new InvisibleMazePuzzle(new Texture("Images/tiles/Level1/Puzzles/Puzzle 2/Lore Papers.png"),new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 2/Maze Stuff/MazePlayer.png")),new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 2/Maze Stuff/Arrow.png")),stack,new int[][]{{5,0},{7,0},{8,0},{2,1},{6,1},{8,1},{0,2},{2,2},{3,2},{4,2},{0,3},{2,3},{4,3},{6,3},{8,3},{4,4},{5,4},{6,4},{7,4},{8,4},{1,5},{2,5},{3,5},{4,5},{8,5},{2,6},{6,6},{9,6},{0,7},{2,7},{4,7},{6,7},{7,7},{9,7},{2,8},{4,8},{8,8},{9,8},{0,9},{5,9},{6,9}},new int[][]{{1,2}}, new int[]{9,9});
            mazePuzzle.setPosition(20000,20000);
            folderButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    stack.push(dropScreen);
                    //switchImage(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 2/Maze Stuff/DeskScreenMaze.png")));
                }
            });
            stage.addActor(screenBlocker);



            stage.addActor(folderButton);
           dropScreen = new DragDropScreen(tiles,stack);

            for (int i = 0; i < tiles.size; i++) {
                DragDropObject tempObject = new DragDropObject(tiles.get(i));
                tempObject.setPosition(500, 800);
                tempObject.setSize(tiles.get(i).getWidth(),tiles.get(i).getHeight());
            }

        }

        @Override
        public void show() {

        }

        @Override
        public void render(float delta, boolean top) {
            if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT) && top) {

                stack.remove(this);
            }

            if(top){
                stage.act(delta);
            }
            stage.draw();

            Gdx.input.getInputProcessor();
            if (mazePuzzle.isWon){
                isComplete = true;
            }
            return;
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
            return this.stage;
        }

        public void switchImage(Texture tex) {
            deskScreen.screenBlocker.updateTexture(tex);
            folderButton.setPosition(10000,100000);
            ImagePuzzleButton mazePuzzleBase = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 2/Maze Stuff/Invisible Maze Button.png")),2);
            mazePuzzleBase.setPosition(730,235);
            superButton.updateTexture(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 2/Maze Stuff/DeskMazeRevealed.png")));

            mazePuzzleBase.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    stack.push(mazePuzzle.mazeScreen);
                }
            });
            stage.addActor(mazePuzzleBase);
            switchImage = true;

        }
    }



    class DragDropScreen implements stackableScreen {
        Stage stage;
        ImagePuzzleButton screenBlocker;
        ScreenStack stack;

        public DragDropScreen(Array<Texture> realTiles, ScreenStack stack) {
            this.stack = stack;
            stage = new Stage(new FitViewport(1920, 1080));

            Texture screenBlockerTexture = new Texture(Gdx.files.internal("Images/screenBlocker.png"));

            screenBlocker = new ImagePuzzleButton(screenBlockerTexture, 0);
            screenBlocker.setPosition(0, 0);
            screenBlocker.setSize(1928, 1080);
            stage.addActor(screenBlocker);

            Array<DragDropObject> tilesToDrag = new Array<>();
            for (int j = 0; j < realTiles.size; j++) {
                DragDropObject realTile = new DragDropObject(realTiles.get(j));
                realTile.data = j;
                tilesToDrag.add(realTile);
            }
            tilesToDrag.shuffle();
            for (int i = 0; i < tilesToDrag.size; i++) {
                stage.addActor( tilesToDrag.get(i));
                (tilesToDrag.get(i)).setPosition(i * 50 + 500, 700);
            }

        }

        @Override
        public void show() {

        }

        @Override
        public void render(float delta, boolean top) {
             if (deskScreen.mazePuzzle != null) {
                 isComplete = deskScreen.mazePuzzle.isWon;
             }
            if (top) {
                stage.act(delta);
            }
            stage.draw();

            if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)  && top) {
                stack.remove(this);
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

        }

        @Override
        public Stage getStage() {
            return stage;
        }
    }

}