package com.mygdx.game.Menus.subMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.SaveFile;
import com.mygdx.game.ui.widgets.menus.MenuButton;
import com.ray3k.stripe.FreeTypeSkin;

import java.io.IOException;

/**
 * controls functionality for creating a new save.
 */

public class newSave implements Screen {
    final CPTGame game;

    Music music;

    Image background;
    Stage stage;
    MenuButton hostGameButton;
    private final Array<Button> otherButtons;

    TextField nameInput;

    /**
     * Initializes screen drawing as well as all background logic
     * @param Game game data
     * @param menuMusic music
     */
    public newSave(CPTGame Game, Music menuMusic){
        game = Game;

        music = menuMusic;

        otherButtons = new Array<>();

        stage = new Stage(new FitViewport(1920,1080), game.batch);
        Gdx.input.setInputProcessor(stage);

        background = new Image(new Texture(Gdx.files.internal("Menu/Menu1.png")));
        background.setPosition(0, 0);
        background.setSize(1920, 1080);
        stage.addActor(background);

        Button cancelButton = new MenuButton("cancel", .8f);
        otherButtons.add(cancelButton);

        cancelButton.setPosition(1920/4.5f, 30);
        cancelButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    game.setScreen(new HostGame(game, music));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        hostGameButton = new MenuButton("Host Game", .8f);
        otherButtons.add(hostGameButton);

        hostGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!hostGameButton.isDisabled()) {
                    game.setScreen(new connectionMenu(game, NewSave(), music));
                }
            }
        });
        hostGameButton.setPosition(1920/4.5f*3.1f, 30);

        nameInput = new TextField("", new FreeTypeSkin(Gdx.files.internal("Menu/Skins/MenuInteractables.json")));
        nameInput.setMaxLength(15);
        nameInput.setMessageText("name:");
        nameInput.setPosition(1920/2f-1920/6f, 1080/2f);
        nameInput.setWidth(1920/3f);
        nameInput.setHeight(50);
        nameInput.debug();
        nameInput.setAlignment(Align.center);

//        Label label = new Label("Please input a save file name:",new FreeTypeSkin(Gdx.files.internal("Menu/Skins/MenuInteractables.json")));
//        label.setPosition(1920/3,1080/2+50);
//        label.debug();

        stage.addActor(nameInput);
        //stage.addActor(label);
        for (Button button: new Array.ArrayIterator<>(otherButtons)){
            stage.addActor(button);
        }

    }

    /**
     * Creates and saves a new save file, writing is currently disabled
     * @return save file that was created
     */
    SaveFile NewSave(){
        String savename = nameInput.getText();

        Json json = new Json();
        SaveFile saveFile = new SaveFile(savename);
        String savedata = json.toJson(saveFile);

        FileHandle save = Gdx.files.local("Saves/" + savename + ".eye");
        //save.writeString(savedata, false);
        return saveFile;

    }

    @Override
    public void show() {
        music.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

        String savename = nameInput.getText();
        hostGameButton.setDisabled(savename.isEmpty() || Gdx.files.local("Saves/" + savename + ".eye").exists());


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
        music.pause();
    }

    @Override
    public void dispose() {
        stage.dispose();
        otherButtons.clear();
    }
}
