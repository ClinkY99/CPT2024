package com.mygdx.game.Menus;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.ui.widgets.menus.MenuButton;
import com.mygdx.game.Menus.subMenus.HostGame;
import com.mygdx.game.Menus.subMenus.JoinGame;
import com.mygdx.game.Menus.subMenus.Settings;

import java.io.IOException;

public class MainMenu implements Screen
        {

    final CPTGame game;
    Music MainMenuMusic;

    Texture MenuBackground;
    Stage stage;
    Table Menulayout;

    public MainMenu(CPTGame game, Music mainMenuMusic) {
        this.game = game;

        stage = new Stage(new FitViewport(1920,1080), game.batch);
        Gdx.input.setInputProcessor(stage);

        //Ui Setup
        Menulayout = new Table();

        //Button Setup
        MenuButton hostGameButton = new MenuButton("Host Game");
        hostGameButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new TestLevel(game, new Texture(Gdx.files.internal("Images/whiteRectangle.png"))));
                try {
                    game.setScreen(new HostGame(game, mainMenuMusic));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        MenuButton joinGameButton = new MenuButton("Join Game", 1.25f);
        joinGameButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new JoinGame(game, mainMenuMusic));
            }
        });
        MenuButton settingsButton = new MenuButton( "Settings");
        settingsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new Settings(game, mainMenuMusic));
            }
        });
        MenuButton quitButton = new MenuButton( "Quit");
        quitButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
                System.exit(-1);
            }
        });

        Menulayout.add(hostGameButton).width(250).height(100);
        Menulayout.row();
        Menulayout.add(joinGameButton).width(320).height(150);
        Menulayout.row();
        Menulayout.add(settingsButton).width(250).height(100);
        Menulayout.row();
        Menulayout.add(quitButton).width(250).height(100);
        //Menulayout.debug();

        MainMenuMusic = mainMenuMusic;


        MenuBackground = new Texture(Gdx.files.internal("Menu/MainMenu.png"));

        Menulayout.setPosition(250, 1080/2f -50);
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
        MainMenuMusic.pause();
    }

    @Override
    public void dispose(){
        MenuBackground.dispose();
        stage.dispose();
    }
}
