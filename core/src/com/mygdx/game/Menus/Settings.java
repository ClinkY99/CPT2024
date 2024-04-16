package com.mygdx.game.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Menus.Interactive.MenuButton;
import com.mygdx.game.Menus.Interactive.SelectionBar;
import jdk.tools.jmod.Main;

import java.awt.*;

public class Settings implements Screen {
    final CPTGame game;

    Texture SettingsBackground;
    Stage stage;
    SelectionBar selectionMenu;

    MenuButton backButton;


    public Settings(CPTGame Game){
        game = Game;

        SettingsBackground = new Texture(Gdx.files.internal("Menu/Settings.png"));
        stage = new Stage(new FitViewport(1920,1080), game.batch);
        Gdx.input.setInputProcessor(stage);
        selectionMenu = new SelectionBar("Graphics", "Audio", "Multiplayer");
        backButton = new MenuButton("Back",1);
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game));
            }
        });
        backButton.setPosition(200,850);
        selectionMenu.getTable().setPosition(1920/2-25, 1080/6*5);

        stage.addActor(selectionMenu.getTable());
        stage.addActor(backButton);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        stage.act(delta);

        stage.getBatch().begin();
        stage.getBatch().draw(SettingsBackground, 0,0,1920,1080);
        stage.getBatch().end();

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
}
