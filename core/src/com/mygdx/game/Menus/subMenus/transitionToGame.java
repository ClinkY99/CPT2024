package com.mygdx.game.Menus.subMenus;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.SaveFile;

public class transitionToGame implements Screen {
    final CPTGame game;

    Music music;

    Texture SettingsBackground;
    Stage stage;
    private final Array<Button> otherButtons;

    private final SaveFile saveFile;



    public transitionToGame(CPTGame game, SaveFile saveFile) {
        this.game = game;

        this.saveFile = saveFile;

        otherButtons = new Array<>();






    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
}
