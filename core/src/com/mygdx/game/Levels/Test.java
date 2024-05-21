package com.mygdx.game.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Game_Elements.Puzzle_Elements.BookShelf;

public class Test implements Screen {

    Stage stage;
    BookShelf bookShelf;

    public Test(){
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        bookShelf = new BookShelf(0,"",null);
        stage.addActor(bookShelf);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,0,0,1);
        stage.act(delta);
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
