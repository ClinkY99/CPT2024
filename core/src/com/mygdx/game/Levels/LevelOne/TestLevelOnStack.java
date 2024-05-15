package com.mygdx.game.Levels.LevelOne;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.Puzzle_Elements.BookShelf;
import com.mygdx.game.Game_Elements.Puzzle_Elements.TileDragPuzzle;
import com.mygdx.game.Game_Elements.SaveFile;
import com.mygdx.game.Levels.LevelOne.baseLevel;
import com.mygdx.game.Multiplayer.MPHandle;

public class TestLevelOnStack extends baseLevel {


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
    TileDragPuzzle testDrag;
    public TestLevelOnStack(CPTGame game, MPHandle c, SaveFile s, int Character, int levelNum) {
        super(game, c, s, Character, levelNum);

        testShelf = new BookShelf(new Texture(Gdx.files.internal("Images/idle_0.png")),2,new Texture(Gdx.files.internal("Images/key.png")),screenStack);
        testShelf.setPosition(300,300);
        testShelf.setSize(30,30);
        stage.addActor(testShelf);

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

        Texture goalTexture = new Texture(Gdx.files.internal("Images/idle_0_green.png"));


        testDrag = new TileDragPuzzle(new Texture(Gdx.files.internal("Images/idle_0_red.png")),fakeTileArray,realTileArray,goalTexture,1,screenStack);
        testDrag.setPosition(700,200);
        testDrag.setSize(400,400);

        stage.addActor(testDrag);
    }


    public void render(float delta) {
        super.render(delta);
        stage.draw();
        stage.act();
    }

    @Override
    public void completed() {

    }

}
