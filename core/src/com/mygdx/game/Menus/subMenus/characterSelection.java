package com.mygdx.game.Menus.subMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.SaveFile;
import com.mygdx.game.Multiplayer.MPHandle;
import com.mygdx.game.Multiplayer.MPInterface;

public class characterSelection implements Screen {
    final CPTGame game;

    Music music;

    Texture SettingsBackground;
    Stage stage;
    ButtonGroup<Button> buttonGroup;
    Table table;

    boolean isHost;

    private MPHandle connection;
    private String serverIP;
    private SaveFile saveFile;

    public characterSelection(CPTGame game, SaveFile saveFile) {
        this(game);
        this.saveFile = saveFile;
    }

    public characterSelection(CPTGame game, String serverIP){
        this(game);
        this.serverIP = serverIP;
    }

    public characterSelection(CPTGame game) {
        this.game = game;



        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();

        buttonGroup = new ButtonGroup<>();

        Button leftEye = new Button(new Skin(Gdx.files.internal("Menu/Skins/MenuInteractables.json")), "eye_Left");
        leftEye.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        table.add(leftEye);

        Button rightEye = new Button(new Skin(Gdx.files.internal("Menu/Skins/MenuInteractables.json")), "eye_Right");
        rightEye.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        table.add(rightEye);
        table.setPosition(1920/2,1080/2);
        table.align(Align.center);
        table.debug();

        buttonGroup.add(leftEye);
        buttonGroup.add(rightEye);

        buttonGroup.setMaxCheckCount(1);

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);

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
