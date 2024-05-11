package com.mygdx.game.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.Puzzle_Elements.BookShelf;
import com.mygdx.game.Game_Elements.Puzzle_Elements.PuzzleTable;
import com.mygdx.game.Game_Elements.SaveFile;
import com.mygdx.game.Multiplayer.MPHandle;
import com.mygdx.game.ui.stackableScreen;

public class TestLevelOnStack extends baseLevel{


    /**
     * Default constructor for the base level
     *
     * @param game      Passes game controller to the class
     * @param c         Passes MP connection to the class allowing for MP support
     * @param s         Passes save file to the class
     * @param Character Passes character number the class, allowing the class to confirm that the right player is on the right level
     * @param levelNum  tells the level how far along it is
     */
    BookShelf testShelf;
    PuzzleTable shelfHolder;
    public TestLevelOnStack(CPTGame game, MPHandle c, SaveFile s, int Character, int levelNum) {
        super(game, c, s, Character, levelNum);
         shelfHolder = new PuzzleTable(600,600);
         testShelf = new BookShelf(new Texture(Gdx.files.internal("Images/whiteRectangle.png")),2,new Texture(Gdx.files.internal("Images/whiteRectangle.png")));
         Library sigma = new Library();

         screenStack.push(sigma);
    }



    @Override
    public void completed() {

    }

    class Library implements stackableScreen {
        Stage stage;
        public Library() {
            stage = new Stage();

            stage.addActor(shelfHolder);
            stage.addActor(testShelf);
        }

        @Override
        public void show() {

        }
        @Override
        public void render(float delta, boolean top) {
            ScreenUtils.clear(Color.WHITE);
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

        }

        @Override
        public Stage getStage() {
            return null;
        }
    }
}
