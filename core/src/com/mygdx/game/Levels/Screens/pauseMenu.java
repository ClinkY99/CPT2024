package com.mygdx.game.Levels.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.Puzzle_Elements.ImagePuzzleButton;
import com.mygdx.game.Levels.baseLevel;
import com.mygdx.game.Menus.MainMenu;
import com.mygdx.game.ui.ScreenStack;
import com.mygdx.game.ui.stackableScreen;
import com.mygdx.game.ui.transitions.transitionScreen;
import com.mygdx.game.ui.transitions.transitions;

import java.io.IOException;

public class pauseMenu implements stackableScreen {

    Stage stage;
    Texture background;
ScreenStack stack;

    ImagePuzzleButton warning = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/quitWarningMessage.png")),2);
    public pauseMenu(ScreenStack stack, CPTGame game){
        stack.pause();

        pauseMenu thisMenu = this;
        this.stack = stack;
        stage = new Stage(new FitViewport(1920,1080));
        background = new Texture("Images/screenBlocker.png");
        ImagePuzzleButton backgroundButton = new ImagePuzzleButton(background,2);
        backgroundButton.setPosition(0,0);

        stage.addActor(backgroundButton);

        ImagePuzzleButton pausedText = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/pausedText.png")),2);
        pausedText.setPosition(100,800);
        ImagePuzzleButton resumeText = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/resumeText.png")),2);
        resumeText.setPosition(100,400);

        warning.setPosition(Gdx.graphics.getWidth()/2 - warning.getWidth()/2,Gdx.graphics.getHeight()/2 - warning.getHeight()/2);
        resumeText.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stack.resume();
                stack.remove(thisMenu);
            }
        });
        ImagePuzzleButton quitText = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/quitText.png")),2);
        quitText.setPosition(100,300);
        quitText.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                warning.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        baseLevel levelRef = (baseLevel) game.getScreen();
                        if(levelRef.connection != null){
                        levelRef.connection.close();
                        }
                        System.exit(0);
                    }
                });
                stage.addActor(warning);
            }
        });

        ImagePuzzleButton mainMenuText = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/mainMenuText.png")),2);
        mainMenuText.setPosition(100,200);

        mainMenuText.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                warning.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        baseLevel levelRef = (baseLevel) game.getScreen();
                        if(levelRef.connection != null) {
                            levelRef.connection.close();
                        }
                        Music MainMenuMusic;
                        MainMenuMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Menus/mainMenu.wav"));
                        game.setScreen(new transitionScreen(game.getScreen(), () -> new MainMenu(game, MainMenuMusic),game));
                    }
                });
                stage.addActor(warning);

            }
        });


            stage.addActor(pausedText);
            stage.addActor(resumeText);
            stage.addActor(quitText);
            stage.addActor(mainMenuText);

        }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta, boolean top) {
        if (top) {
            stage.act(delta);
        }
        stage.draw();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && top) {
            if(warning.getStage()!=null) {
                warning.remove();
            }
        }

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

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }


}
