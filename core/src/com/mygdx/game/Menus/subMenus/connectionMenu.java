package com.mygdx.game.Menus.subMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.SaveFile;
import com.mygdx.game.ui.transitions.transitionScreen;
import com.mygdx.game.ui.widgets.menus.MenuButton;
import com.mygdx.game.Menus.MainMenu;
import com.mygdx.game.Multiplayer.MPHandle;
import com.mygdx.game.Multiplayer.MPInterface;
import com.mygdx.game.Multiplayer.ServerInterface;
import com.ray3k.stripe.FreeTypeSkin;

import java.io.IOException;

/**
 * controls drawing of the awaiting connection screen, this screen is shown when a player is hosting game and there is no client connection
 */
public class connectionMenu implements Screen {
    final CPTGame game;

    Music music;

    Image SettingsBackground;
    Stage stage;
    Label label;
    private final Array<Button> otherButtons;

    private final SaveFile saveFile;

    private final ServerInterface server;

    private boolean connected = false;

    /**
     * initializes the connection menu screen which handles drawing of the awaiting connection menu as well as the server that is waiting for the MP connection.
     * @param Game game data
     * @param save save file
     * @param menuMusic music
     */
    public connectionMenu(CPTGame Game, SaveFile save, Music menuMusic) {
        game = Game;

        saveFile = save;

        music = menuMusic;

        otherButtons = new Array<>();

        stage = new Stage(new FitViewport(1920,1080), game.batch);
        Gdx.input.setInputProcessor(stage);

        SettingsBackground = new Image(new Texture(Gdx.files.internal("Menu/Menu1.png")));
        SettingsBackground.setPosition(0,0);
        SettingsBackground.setSize(1920,1080);
        stage.addActor(SettingsBackground);

        label = new Label("Awaiting Connection...", new FreeTypeSkin(Gdx.files.internal("Menu/Skins/MenuInteractables.json")));
        label.setWidth(1920/3f);
        label.setPosition(1920/2f - label.getWidth()/2, 1080/2f-label.getHeight()/2);

        Button backButton = new MenuButton("cancel", .8f);
        otherButtons.add(backButton);

        backButton.setPosition(1920/4.5f, 30);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                server.close();
                game.setScreen(new MainMenu(game, music));
            }
        });

        stage.addActor(label);
        for (Button button: new Array.ArrayIterator<>(otherButtons)){
            stage.addActor(button);
        }

        server = new ServerInterface(MPInterface.serverDetails.class, MPInterface.connectionDetails.class);

        //binds function to send server data to the client
        server.BindFunction((connection, object) -> {
            MPInterface.connectionDetails connectionDetails = (MPInterface.connectionDetails) object;

            connection.sendTCP(new MPInterface.serverDetails(save.getName(), "Test", MPHandle.getLocalIP(), true, 0, connectionDetails.confirm));
            System.out.println(MPHandle.getLocalIP());

            if(connectionDetails.confirm){
                server.close();
                connected = true;
            }


        }, MPInterface.connectionDetails.class);


        try {
            server.init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void show() {
        music.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

        //if a client connects send to the character selection screen
        if(connected){
            game.setScreen(new transitionScreen(this, () -> new characterSelection(game,saveFile), game));
        }

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
        music.stop();
        stage.dispose();
        music.dispose();
        otherButtons.clear();
        server.close();
    }
}
