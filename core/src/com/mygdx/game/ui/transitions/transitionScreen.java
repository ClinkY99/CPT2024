package com.mygdx.game.ui.transitions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;

/**
 * transition screen, used to fade between 2 screens as well as to wait for screen to load
 */
public class transitionScreen implements Screen {

    Screen current;
    Screen next;

    transitions load;

    Stage stage;
    CPTGame game;

    transitions.fadeOutTransitionScreen fadeOut;
    transitions.fadeInTransitionScreen fadeIn;

    public transitionScreen(Screen c, transitions load, CPTGame game){
        this(c,load,game,0.5f);
    }

    public transitionScreen(Screen c, transitions load, CPTGame game, float duration){
        this.current = c;

        this.game = game;

        this.load = load;

        stage = new Stage(new FitViewport(1920, 1080), game.batch);
        Gdx.input.setInputProcessor(stage);

        Image loading = new Image(new Texture(Gdx.files.internal("Images/ftb.png")));
        loading.setColor(Color.BLACK);
        loading.setPosition(0, 0);
        loading.setSize(1920,1080);
        stage.addActor(loading);

        fadeOut = new transitions.fadeOutTransitionScreen(duration, current, loading);
        fadeIn = new transitions.fadeInTransitionScreen(duration, next, loading);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        if(!fadeOut.isFinished()) {
            fadeOut.render(delta);
        } else if (next != null) {
            fadeIn.render(delta);
            if (fadeIn.isFinished()) {
                game.setScreen(next);
            }
        } else {
            next = load.load();
            fadeIn.current = next;
        }
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
