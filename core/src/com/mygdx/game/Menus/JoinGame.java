package com.mygdx.game.Menus;

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
import com.mygdx.game.CPTGame;
import com.mygdx.game.Menus.Interactive.MenuButton;
import com.mygdx.game.Menus.Interactive.SelectionBar;

public class JoinGame implements Screen {
    final CPTGame game;

    private final Music music;
    private final Stage stage;
    private final Texture background;
    private final SelectionBar selectionBar;

    private final Array<Button> otherButtons;

    public JoinGame(CPTGame Game, Music menuMusic){
        this.game = Game;

        music = menuMusic;

        otherButtons = new Array<>();

        background = new Texture("Menu/Menu2.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        selectionBar = new SelectionBar(1080/3,100,"Online", "LAN");
        selectionBar.getTable().setPosition(1920/3.3f,1080/8f*7);

        //Array<Button> buttons = selectionBar.getButtons();

        Button backButton = new MenuButton("back", .8f);
        otherButtons.add(backButton);

        backButton.setPosition(1920/4.5f, 30);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game,music));
            }
        });

        Button joinButton = new MenuButton("Join Game", .8f);
        otherButtons.add(joinButton);

        joinButton.setPosition(1920/4.5f*3.1f, 30);


        stage.addActor(selectionBar.getTable());
        for (Button button:new Array.ArrayIterator<>(otherButtons)){
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
        stage.getBatch().draw(background, 0, 0);
        stage.getBatch().end();

        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

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

    }
}
