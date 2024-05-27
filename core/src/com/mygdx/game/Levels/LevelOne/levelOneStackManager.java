package com.mygdx.game.Levels.LevelOne;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.Puzzle_Elements.BookShelf;
import com.mygdx.game.Game_Elements.Puzzle_Elements.KeyPad;
import com.mygdx.game.Game_Elements.Puzzle_Elements.TileDragPuzzle;
import com.mygdx.game.Game_Elements.SaveFile;
import com.mygdx.game.Levels.LevelOneEye.levelOneEyeBottomScreen;
import com.mygdx.game.Levels.baseLevel;
import com.mygdx.game.Multiplayer.MPHandle;

import javax.imageio.IIOException;
import java.io.IOException;

public class levelOneStackManager extends baseLevel {


    /**
     * Default constructor for the base level
     *
     * @param game      Passes game controller to the class
     * @param c         Passes MP connection to the class allowing for MP support
     * @param s         Passes save file to the class
     * @param Character Passes character number the class, allowing the class to confirm that the right player is on the right level
     * @param levelNum  tells the level how far along it is
     */

    public levelOneStackManager(CPTGame game, MPHandle c, SaveFile s, int Character, int levelNum) throws IOException {
        super(game, c, s, Character, levelNum);
        if(Character ==0) {
            screenStack.push(new levelOneBottomScreen(screenStack, game, this));
        } else{
            screenStack.push(new levelOneEyeBottomScreen(screenStack, game));
        }
    }


    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void completed() {

    }

}
