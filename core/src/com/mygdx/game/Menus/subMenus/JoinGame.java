package com.mygdx.game.Menus.subMenus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Menus.MainMenu;
import com.mygdx.game.Multiplayer.ClientInterface;
import com.mygdx.game.Multiplayer.MPHandle;
import com.mygdx.game.Multiplayer.MPInterface;
import com.mygdx.game.ui.widgets.AutoFocusScrollpane;
import com.mygdx.game.ui.widgets.Switcher;
import com.mygdx.game.ui.widgets.menus.*;
import com.ray3k.stripe.FreeTypeSkin;

import java.io.IOException;

/**
 * controls logic and drawing of the join game screen
 */
public class JoinGame implements Screen {
    final CPTGame game;

    private final Music music;
    private final Stage stage;
    private final Image background;
    private final SelectionBar selectionBar;

    private final Array<Button> otherButtons;
    private final Switcher switcher;
    private final ButtonGroup<serverSelection> buttonGroup;

    private final ClientInterface client;

    private String ip;

    /**
     * Initializes the Join game class and sets up all labels and buttons to be drawn to screen.
     * @param Game game data
     * @param menuMusic music
     */
    public JoinGame(CPTGame Game, Music menuMusic){
        this.game = Game;

        music = menuMusic;

        otherButtons = new Array<>();

        stage = new Stage(new FitViewport(1920,1080), game.batch);
        Gdx.input.setInputProcessor(stage);

        background = new Image(new Texture("Menu/Menu2.png"));
        background.setPosition(0, 0);
        background.setSize(1920, 1080);
        stage.addActor(background);

        //sets up a switcher widget and selection bar
        selectionBar = new SelectionBar(1080/3,100,"Online", "LAN");
        selectionBar.getTable().setPosition(1920/3.3f,1080/8f*7);

        Array<SelectionButton> buttons = selectionBar.getButtons();

        for (int i = 0; i < buttons.size; i++) {
            System.out.println(i);
            buttons.get(i).addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    switcher.switchFocused(selectionBar.getCheckedIndex());
                }
            });


        }

        Button backButton = new MenuButton("back", .8f);
        otherButtons.add(backButton);

        backButton.setPosition(1920/4.5f, 30);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenu(game,music));
            }
        });

        Button refreshButton = new MenuButton("refresh", .8f);
        refreshButton.setPosition(1920/9f*4+150, 1080/4f*3-25);
        refreshButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                client.lanServers();
            }
        });
        otherButtons.add(refreshButton);

        Button joinButton = new MenuButton("Join Game", .8f);
        joinButton.setPosition(1920/4.5f*3.1f, 30);
        joinButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(switcher.getFocusedIndex()==1&&buttonGroup.getButtons().size >0){
                    client.sendTCP(new MPInterface.connectionDetails("test", MPHandle.getLocalIP(), true));
                    client.bindFunction((connection, object)-> {
                        MPInterface.serverDetails serverDetails = (MPInterface.serverDetails) object;
                        if(serverDetails.confirmed) {
                            ip = serverDetails.ip;
                        }
                    }, MPInterface.serverDetails.class);
                }
            }
        });

        otherButtons.add(joinButton);

        //initializes the client function and searches for all open servers on the LAN
        client = new ClientInterface(MPInterface.serverDetails.class, MPInterface.connectionDetails.class);
        client.init();
        client.lanServers();

        Label onlineLabel = new Label("Unfortunately, online play is currently disabled, please play LAN", new FreeTypeSkin(Gdx.files.internal("Menu/Skins/MenuInteractables.json")));

        onlineLabel.setPosition(0, 1080/2f);
        onlineLabel.setWidth(1920/5f*3);
        onlineLabel.setAlignment(Align.center);
        onlineLabel.setFontScale(.5f);

        buttonGroup = new ButtonGroup<>();
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(1);

        switcher = new Switcher(onlineLabel, updateLan());

        switcher.setPosition(0, 1080/4f);
        switcher.setWidth(1920/5f*3);
        switcher.setHeight(1080/2f+25);
        //switcher.debug();



        stage.addActor(selectionBar.getTable());
        stage.addActor(switcher);
        for (Button button:new Array.ArrayIterator<>(otherButtons)){
            stage.addActor(button);
        }

    }

    /**
     * creates a table of all found servers with data sent over from the client
     * @return A table containing all discovered servers, or a label saying no servers found
     */
    Actor updateLan(){
        Array< MPInterface.serverDetails> arr = client.getAvailibleServerDetails();
        if(arr.size > 0) {
            VerticalGroup table = new VerticalGroup();
            buttonGroup.clear();
            for (MPInterface.serverDetails s : new Array.ArrayIterable<>(arr)) {
                if (s.serverOpen) {
                    serverSelection selection = new serverSelection(s);
                    table.addActor(selection);
                    buttonGroup.add(selection);
                }
            }
            table.align(Align.top);

            AutoFocusScrollpane pane = new AutoFocusScrollpane(table);
            pane.setPosition(0,1080/4f);
            pane.setHeight(1080/2f+25);
            pane.setWidth(1920/5f*3);
            pane.setFadeScrollBars(false);
            //pane.debug();

            return pane;
        } else {
            Label serversFound = new Label("No Servers found", new FreeTypeSkin(Gdx.files.internal("Menu/Skins/MenuInteractables.json")));

            serversFound.getStyle().fontColor = new Color(0,0,0,1);
            serversFound.setPosition(0, 0);
            serversFound.setWidth(1920/5f*3);
            serversFound.setAlignment(Align.center);
            serversFound.setFontScale(.5f);
            //serversFound.debug();

            return serversFound;
        }
    }

    @Override
    public void show() {
        music.play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

        if(ip != null){
            game.setScreen(new characterSelection(game, ip));
        }

        if(switcher.getFocusedIndex()==1 && client.isNewServerDetailsAvailable()){
            switcher.updateFocused(updateLan());
            client.setNewServerDetailsAvailable(false);
        }

        stage.act(delta);

        stage.draw();

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
        music.pause();
    }

    @Override
    public void dispose() {
        try {
            client.dispose();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        music.stop();
        music.dispose();
        stage.dispose();
        otherButtons.clear();

    }

    class serverSelection extends TextButton {
        String name;
        String hostName;
        int completion;


        Array<Label> labels = new Array<>();

        serverSelection(MPInterface.serverDetails serverDetails){
            super(serverDetails.name, new FreeTypeSkin(Gdx.files.internal("Menu/Skins/MenuInteractables.json")), "SelectionButton");
            name = serverDetails.name;
            hostName = serverDetails.hostName;
            completion = serverDetails.completion;

            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Menu/tempus_sans_itc.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

            parameter.size = 48;
            BitmapFont font = generator.generateFont(parameter);
            labels.add(newLabel(name, new Label.LabelStyle(font, getStyle().fontColor)));
            setLabel(labels.get(0));

            labels.add(newLabel(hostName, new Label.LabelStyle(font, getStyle().fontColor)));
            labels.add(newLabel(String.valueOf(completion), new Label.LabelStyle(font, getStyle().fontColor)));

            add(labels.get(1), labels.get(2));

            setWidth(1920/5f*3);
            setHeight(100);
        }

        public Array<Label> getLabels() {
            return labels;
        }
    }
}
