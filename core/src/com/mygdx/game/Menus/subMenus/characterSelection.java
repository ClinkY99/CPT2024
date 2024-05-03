package com.mygdx.game.Menus.subMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.SaveFile;
import com.mygdx.game.Levels.TestLevel;
import com.mygdx.game.Menus.widgets.MenuButton;
import com.mygdx.game.Multiplayer.MPHandle;
import com.mygdx.game.Multiplayer.MPInterface;
import com.ray3k.stripe.FreeTypeSkin;

import java.io.IOException;

public class characterSelection implements Screen {
    final CPTGame game;

    Music music;

    Stage stage;
    ButtonGroup<Button> buttonGroup;
    private final MenuButton confirmButton = new MenuButton("Confirm");
    private final Array<Label> labels;
    Table table;

    boolean isHost;

    private MPHandle connection;
    private String serverIP;
    private SaveFile saveFile;

    private int characterSelected = -1;
    private boolean confirmed = false;
    private boolean otherReady = false;

    public characterSelection(CPTGame game, SaveFile saveFile) {
        this(game,true);
        this.saveFile = saveFile;
    }

    public characterSelection(CPTGame game, String serverIP){
        this(game,false);
        this.serverIP = serverIP;
    }

    public characterSelection(CPTGame game, boolean isHost) {
        this.game = game;

        table = new Table();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        buttonGroup = new ButtonGroup<>();


        Button leftEye = new Button(new FreeTypeSkin(Gdx.files.internal("Menu/Skins/MenuInteractables.json")), "eye_Left");
        Button rightEye = new Button(new FreeTypeSkin(Gdx.files.internal("Menu/Skins/MenuInteractables.json")), "eye_Right");

        confirmButton.getStyle().fontColor = new Color(.3f,.3f,.3f,1);
        confirmButton.setPosition(1920/2f-confirmButton.getWidth()/2, 1080/3f);
        confirmButton.align(Align.center);
        confirmButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                confirmCheck();
            }
        });

        leftEye.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!leftEye.isDisabled()) {
                    buttonGroup.setMinCheckCount(1);
                    stage.addActor(confirmButton);
                    characterSelected = 0;
                }
            }
        });

        rightEye.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!rightEye.isDisabled()) {
                    buttonGroup.setMinCheckCount(1);
                    stage.addActor(confirmButton);
                    characterSelected = 1;
                }
            }
        });

        buttonGroup.add(leftEye);
        buttonGroup.add(rightEye);
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(0);
        buttonGroup.setUncheckLast(true);
        leftEye.setChecked(false);
        rightEye.setChecked(false);

        refreshTable();


        //table.debug();

        this.isHost = isHost;
        initMP();
        connection.bindFunction((connection, object) -> {
            MPInterface.characterSelection selection = (MPInterface.characterSelection) object;
            if(selection.confirmed) {
                if (isHost && !confirmed || isHost && confirmed && selection.character != characterSelected) {
                    connection.sendTCP(new MPInterface.confirm(true));
                    confirmEye(selection.character,false);
                } else if (isHost) {
                    connection.sendTCP(new MPInterface.confirm());
                    return;

                } else {
                    confirmEye(selection.character,false);
                }
                otherReady = true;

            } else {
                cancelSelection(false);
                otherReady = false;
            }
        }, MPInterface.characterSelection.class);
        connection.bindFunction((connection, object)-> {
            MPInterface.confirm confirm = (MPInterface.confirm) object;
            if(!isHost&&confirm.confirmed){
                confirmEye(characterSelected,true);
            }
        }, MPInterface.confirm.class);

        Label label = new Label("Your Adventure is Just Beggining, Remember: \n Ignorance is Bliss", new FreeTypeSkin(Gdx.files.internal("Menu/Skins/MenuInteractables.json")),"characterSelectionLabel");
        label.setFontScale(.9f);
        label.setPosition(1920/2f-label.getWidth()/2, 1080/3f*2);
        label.setAlignment(Align.center);

        Label label1 = new Label("(Choose your Idol)", new FreeTypeSkin(Gdx.files.internal("Menu/Skins/MenuInteractables.json")),"characterSelectionLabel");
        label1.setFontScale(.6f);
        label1.setPosition(1920/2f-label1.getWidth()/2, 1080/3f*2-label.getStyle().font.getLineHeight());
        label1.setAlignment(Align.center);

        Label waiting = new Label("waiting for other player to make selection...", new FreeTypeSkin(Gdx.files.internal("Menu/Skins/MenuInteractables.json")),"characterSelectionLabel");
        waiting.setFontScale(.6f);
        waiting.setPosition(1920/2f-waiting.getWidth()/2, 1080/4f);
        waiting.setAlignment(Align.center);
        waiting.setVisible(false);

        labels = new Array<>();

        labels.add(label);
        labels.add(label1);
        labels.add(waiting);

        stage.addActor(label);
        stage.addActor(label1);
        stage.addActor(waiting);

        music = Gdx.audio.newMusic(Gdx.files.internal("Music/Menus/CharacterSelection.wav"));
        music.setLooping(true);
        music.play();
    }

    private void initMP(){
        connection = new MPHandle(isHost);
        connection.init(serverIP);
    }

    private void confirmEye(int character, boolean local){
        if(local) {
            table.getCells().removeIndex(Math.abs(character - 1));
            table.invalidate();
            buttonGroup.getButtons().get(Math.abs(character - 1)).setVisible(false);
            buttonGroup.getButtons().get(character).setDisabled(true);
            confirmButton.setText("Cancel Selection");
            confirmButton.getListeners().pop();
            confirmButton.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    cancelSelection(true);
                }
            });
            labels.get(2).setVisible(true);
            labels.get(1).setText("(Your Idol is Chosen)");
        }
        else {
            table.getCells().removeIndex(character);
            table.invalidate();
            buttonGroup.getButtons().get(character).setVisible(false);
        }
    }

    private void cancelSelection(boolean local){
        confirmButton.setText("Confirm");
        confirmButton.getListeners().pop();
        confirmButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                confirmCheck();
            }
        });

        labels.get(2).setVisible(false);
        labels.get(1).setText("(Choose your Idol)");

        table.remove();
        table.clear();
        refreshTable();

        if(local){
            connection.sendTCP(new MPInterface.characterSelection("test", characterSelected));
        }

    }

    private void confirmCheck(){
        confirmed = true;
        MPInterface.characterSelection selection = new MPInterface.characterSelection("test", characterSelected,true);
        connection.sendTCP(selection);
        if(isHost){
            confirmEye(characterSelected,true);
        }
    }

    private void refreshTable(){
        for(Button b: new Array.ArrayIterator<>(buttonGroup.getButtons())){
            table.add(b).width(1920/3f).maxWidth(b.getStyle().up.getMinWidth());
            b.setVisible(true);
            b.setDisabled(false);
        }

        table.setPosition(1920/2f,1080/2f);
        table.align(Align.center);

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(otherReady && confirmed){
            try {
                game.setScreen(new TestLevel(game, new Texture(Gdx.files.internal("Images/whiteRectangle.png"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

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
        music.stop();
        music.dispose();
    }
}
