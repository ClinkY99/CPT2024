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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.SaveFile;
import com.mygdx.game.Levels.LevelOne.levelOneStackManager;
import com.mygdx.game.ui.transitions.transitionScreen;
import com.mygdx.game.ui.widgets.menus.MenuButton;
import com.mygdx.game.Multiplayer.MPHandle;
import com.mygdx.game.Multiplayer.MPInterface;
import com.ray3k.stripe.FreeTypeSkin;

import java.io.IOException;

/**
 * handles drawing and main functionality of the character selection class, It also handels MP Functionality and character selection
 */

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

    /**
     * Constructor used when the player entering is the host player
     * @param game game data
     * @param saveFile save data
     */
    public characterSelection(CPTGame game, SaveFile saveFile) {
        this(game);
        this.saveFile = saveFile;
        this.isHost = true;
        initMP();
    }

    /**
     * Constructor used when the player entering is the client
     * @param game game data
     * @param serverIP IP of the server trying to be connected to
     */

    public characterSelection(CPTGame game, String serverIP){
        this(game);
        this.serverIP = serverIP;
        this.isHost = false;
        initMP();
    }

    /**
     * Default constructor, should only be used if forcing single player, or within the character selection class
     * @param game game data
     */

    characterSelection(CPTGame game) {
        this.game = game;

        table = new Table();

        stage = new Stage(new FitViewport(1920,1080), game.batch);
        Gdx.input.setInputProcessor(stage);

        buttonGroup = new ButtonGroup<>();

        //Initializes drawing and controls images for buttons drawn on screen

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
                //makes sure the eye is not disabled before calling necessary checks
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
                //makes sure the eye is not disabled before calling necessary checks
                if(!rightEye.isDisabled()) {
                    buttonGroup.setMinCheckCount(1);
                    stage.addActor(confirmButton);
                    characterSelected = 1;
                }
            }
        });

        //initializes the button group and confirms buttons are in the correct configuration, also adds them to the onscreen table to control drawing
        buttonGroup.add(leftEye);
        buttonGroup.add(rightEye);
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(0);
        buttonGroup.setUncheckLast(true);
        leftEye.setChecked(false);
        rightEye.setChecked(false);

        refreshTable();


        //initializes the on screen text and adds them to the stage.
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

        //starts the ambiance
        music = Gdx.audio.newMusic(Gdx.files.internal("Music/Menus/CharacterSelection.wav"));
        music.setLooping(true);
        music.play();
    }

    /**
     * called to initialize multiplayer, this must be called after server IP is set if it is the client
     */

    private void initMP(){
        connection = new MPHandle(isHost);
        connection.init(serverIP);

        //bind functions on receiving of the character selection class.
        connection.bindFunction((connection, object) -> {
            MPInterface.characterSelection selection = (MPInterface.characterSelection) object;
            //if the selection is confirmed
            if(selection.confirmed) {
                //this confirms that the item selected is not the same as the one selected by the host if it is confirmed
                if (isHost && !confirmed || isHost && confirmed && selection.character != characterSelected) {
                    connection.sendTCP(new MPInterface.confirm(true));
                    confirmEye(selection.character,false);
                //otherwise tell the client that it cant select
                } else if (isHost) {
                    connection.sendTCP(new MPInterface.confirm());
                    return;
                //if it is the client updates screen
                } else {
                    confirmEye(selection.character,false);
                }
                otherReady = true;
            //if the selection is canceled
            } else {
                cancelSelection(false);
                otherReady = false;
            }
        }, MPInterface.characterSelection.class);
        //bind function on recieving confirmation class
        connection.bindFunction((connection, object)-> {
            MPInterface.confirm confirm = (MPInterface.confirm) object;
            //if this is recived by the client confirms that it is allowed to select said screen and updates
            if(!isHost&&confirm.confirmed){
                confirmEye(characterSelected,true);
            }
        }, MPInterface.confirm.class);
    }

    /**
     * called when eye confirmation is confirmed, functions differently if it is being called on local device or remote device
     * @param character character being selected
     * @param local is this being called locally
     */
    private void confirmEye(int character, boolean local){
        if(local) {
            //updates screen changing the text labels and showing selected eye
            confirmed = true;
            if(table.getCells().size > 1) {
                table.getCells().removeIndex(Math.abs(character - 1));
            }

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
            //updates screen removing the character selected
            if(table.getCells().size > 1) {
                table.getCells().removeIndex(character);
            }
            table.invalidate();
            buttonGroup.getButtons().get(character).setVisible(false);
        }
    }

    /**
     * called to reset buttons and labels to default position and text
     * @param local is this function being called locally
     */
    private void cancelSelection(boolean local){
        confirmed = false;
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

        //if it is called locally send connection information to other player to update their screens.
        if(local){
            connection.sendTCP(new MPInterface.characterSelection("test", characterSelected));
        }

    }

    /**
     * called when the confirm button is selected, makes sure that the server is confirming the selection
     */
    private void confirmCheck(){
        MPInterface.characterSelection selection = new MPInterface.characterSelection("test", characterSelected,true);
        connection.sendTCP(selection);
        if(isHost){
            confirmEye(characterSelected,true);
        }
    }

    /**
     * resets table to default position
     */
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
        //if both client and server are ready load the level

        if(otherReady && confirmed){
            game.setScreen(new transitionScreen(this, () -> new levelOneStackManager(game,connection,null,characterSelected, 1), game));
            confirmed = false;
        }

        //draws to screen
        ScreenUtils.clear(0,0,0,1);


        if(connection.isConnected()) {
            stage.act(delta);

            stage.draw();
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
        music.stop();
        music.dispose();
    }
}
