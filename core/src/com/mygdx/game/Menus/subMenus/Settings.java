package com.mygdx.game.Menus.subMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Menus.widgets.MenuButton;
import com.mygdx.game.Menus.widgets.SelectionBar;
import com.mygdx.game.Menus.MainMenu;
import com.mygdx.game.Menus.widgets.SelectionButton;


public class Settings implements Screen {
    final CPTGame game;

    Music music;

    Texture SettingsBackground;
    Stage stage;
    SelectionBar selectionMenu;
    private final Array<Button> otherButtons;


    public Settings(CPTGame Game, Music menuMusic){
        game = Game;

        music = menuMusic;

        otherButtons = new Array<>();

        SettingsBackground = new Texture(Gdx.files.internal("Menu/Menu1.png"));

        stage = new Stage(new FitViewport(1920,1080), game.batch);
        Gdx.input.setInputProcessor(stage);

        selectionMenu = new SelectionBar(1080/3, 100,"Graphics", "Audio", "Controls", "Multiplayer");

        selectionMenu.getTable().setPosition((float) (1920/2), 1080/8f*7);

        Array<SelectionButton> buttons = selectionMenu.getButtons();

        for(Button button: new Array.ArrayIterator<>(buttons)){
            button.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {

                }
            });
        }

        Button backButton = new MenuButton("back", .8f);
        otherButtons.add(backButton);

        backButton.setPosition(1920/4.5f, 30);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game, music));
            }
        });

        stage.addActor(selectionMenu.getTable());
        for (Button button: new Array.ArrayIterator<>(otherButtons)){
            stage.addActor(button);
        }

    }


    @Override
    public void show() {
        music.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

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
        music.pause();
    }

    @Override
    public void dispose() {
        stage.clear();
        SettingsBackground.dispose();

    }
}
