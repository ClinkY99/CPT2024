package com.mygdx.game.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CPTGame;

public class MainMenu implements Screen {

    final CPTGame game;
    OrthographicCamera camera;
    BitmapFont MenuFont;
    Music MainMenuMusic;
    Texture MenuBackground;

    public MainMenu(CPTGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920,1080);

        MenuFont = new BitmapFont(Gdx.files.internal("MainMenu/TempusSansITC.fnt"));

        MainMenuMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Like_the_Flow_of_Time.mp3"));
        MainMenuMusic.setLooping(true);


        MenuBackground = new Texture(Gdx.files.internal("MainMenu/MainMenu.png"));
    }

    @Override
    public void show() {
        MainMenuMusic.play();
    }

    @Override
    public void render(float delta){
        ScreenUtils.clear(0,0,0,1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(MenuBackground, 0,0);
        MenuFont.draw(game.batch, "Host Game", 331,496);
        MenuFont.draw(game.batch, "Join Game", 325,533,72,72,true);
        MenuFont.draw(game.batch, "Settings", 331,601);
        MenuFont.draw(game.batch, "Quit", 331, 658);
        game.batch.end();


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
    public void dispose(){

    }
}
