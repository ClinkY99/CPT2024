package com.mygdx.game.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Menus.Interactive.MenuButton;

import java.util.Set;

public class MainMenu implements Screen {

    final CPTGame game;
    OrthographicCamera camera;
    Music MainMenuMusic;
    Texture MenuBackground;
    Stage stage;
    Table Menulayout;

    public MainMenu(CPTGame game) {

        stage = new Stage(new FitViewport(1920,1080));

        Gdx.input.setInputProcessor(stage);

        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920,1080);



        //Ui Setup
        Menulayout = new Table();

        //Button Setup
        MenuButton HostGame = new MenuButton("Host Game");
        HostGame.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //
            }
        });
        MenuButton JoinGame = new MenuButton("Join Game", 1.25f);
        JoinGame.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //
            }
        });


        MenuButton Settings = new MenuButton( "Settings");
        Settings.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        MenuButton Quit = new MenuButton( "Quit");
        Quit.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
                System.exit(-1);
            }
        });


        Menulayout.add(HostGame).width(250).height(100);
        Menulayout.row();
        Menulayout.add(JoinGame).width(320).height(150);
        Menulayout.row();
        Menulayout.add(Settings).width(250).height(100);
        Menulayout.row();
        Menulayout.add(Quit).width(250).height(100);
        //Menulayout.debug();



        MainMenuMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Like_the_Flow_of_Time.mp3"));
        MainMenuMusic.setLooping(true);


        MenuBackground = new Texture(Gdx.files.internal("MainMenu/MainMenu.png"));


        Menulayout.setPosition(250, 1080/2 -50);
        stage.addActor(Menulayout);




    }

    @Override
    public void show() {
        MainMenuMusic.play();
    }

    @Override
    public void render(float delta){
        ScreenUtils.clear(0,0,0,1);

        stage.act(delta);

        stage.getViewport().getCamera().update();
        stage.getBatch().begin();
        stage.getBatch().draw(MenuBackground, 0,0,1920,1080);
        stage.getBatch().end();
        stage.draw();




    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
