package com.mygdx.game.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.ray3k.stripe.FreeTypeSkin;


public class SplashScreen implements Screen {
    final CPTGame game;

    private final Music music;
    private final Stage stage;
    private final Texture background;
    private final Array<Label> labels;

    public SplashScreen(CPTGame game) {
        this.game = game;

        stage = new Stage(new FitViewport(1920,1080), game.batch);
        Gdx.input.setInputProcessor(stage);

        background = new Texture(Gdx.files.internal("Menu/SplashScreen.png"));

        music = Gdx.audio.newMusic(Gdx.files.internal("Music/Menus/mainMenu.wav"));
        music.setLooping(true);
        music.play();

        labels = new Array<>();

        Label Title = new Label("Game Title", new FreeTypeSkin(Gdx.files.internal("Menu/Skins/MenuInteractables.json")));
        Title.setPosition(0, 1080/3f);
        Title.setWidth(1920);
        Title.setAlignment(Align.center);
        labels.add(Title);

        Label credits = new Label("Game by: Kieran C, Matthew G, Eric V",new FreeTypeSkin(Gdx.files.internal("Menu/Skins/MenuInteractables.json")) );
        credits.setPosition(0, 1080/4f);
        credits.setWidth(1920);
        credits.setAlignment(Align.center);
        credits.setFontScale(.5f);
        labels.add(credits);

        Label inputPrompt = new Label("Press any Key to continue", new FreeTypeSkin(Gdx.files.internal("Menu/Skins/MenuInteractables.json")));
        inputPrompt.setPosition(0, 1080/5f);
        inputPrompt.setWidth(1920);
        inputPrompt.setAlignment(Align.center);
        inputPrompt.setFontScale(.4f);
        labels.add(inputPrompt);

        for (Label l : new Array.ArrayIterator<>(labels)) {
            stage.addActor(l);
        }

        stage.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game, music));
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0,0,0,1);

        stage.act(v);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, 1920, 1080);
        stage.getBatch().end();

        stage.draw();

        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
            game.setScreen(new MainMenu(game, music));
        }
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
        stage.dispose();
        background.dispose();
        labels.clear();
    }
}
