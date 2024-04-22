package com.mygdx.game.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

    private Stage stage;
    private Texture background;
    private SelectionBar selectionBar;

    private Array<Button> otherButtons;

    public JoinGame(CPTGame Game){
        this.game = Game;

        otherButtons = new Array<>();

        background = new Texture("Menu/JoinGame.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        selectionBar = new SelectionBar(1080/3,100,"Online", "LAN");
        selectionBar.getTable().setPosition(1920/3+125,1080/8*7);

        Array<Button> buttons = selectionBar.getButtons();

        Button backButton = new MenuButton("back", .9f);
        otherButtons.add(backButton);

        backButton.setPosition(1920/4.5f, 30);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game));
            }
        });

        stage.addActor(selectionBar.getTable());
        stage.addActor(backButton);

    }

    @Override
    public void show() {

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

    }

    @Override
    public void dispose() {

    }
}
