package com.mygdx.game.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.SaveFile;
import com.mygdx.game.Levels.TestLevel;
import com.mygdx.game.Menus.Interactive.MenuButton;
import com.ray3k.stripe.FreeTypeSkin;

public class newSave implements Screen {
    final CPTGame game;

    Music music;

    Texture SettingsBackground;
    Stage stage;
    private final Array<Button> otherButtons;

    TextField nameInput;


    public newSave(CPTGame Game, Music menuMusic){
        game = Game;

        music = menuMusic;

        otherButtons = new Array<>();

        SettingsBackground = new Texture(Gdx.files.internal("Menu/Menu1.png"));

        stage = new Stage(new FitViewport(1920,1080), game.batch);
        Gdx.input.setInputProcessor(stage);

        Button cancelButton = new MenuButton("cancel", .8f);
        otherButtons.add(cancelButton);

        cancelButton.setPosition(1920/4.5f, 30);
        cancelButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game, music));
            }
        });

        MenuButton hostGameButton = new MenuButton("Host Game", .8f);
        otherButtons.add(hostGameButton);

        hostGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String savename = nameInput.getText();
                if(!savename.isEmpty()){
                    Json json = new Json();
                    String savedata = json.toJson(new SaveFile(savename));

                    FileHandle save = Gdx.files.local("Saves/"+savename+".eye");
                    save.writeString(savedata,false);

                    game.setScreen(new TestLevel(game, new Texture(Gdx.files.internal("Images/whiteRectangle.png"))));
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
        for (Button button: new Array.ArrayIterator<Button>(otherButtons)){
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

    }
}
