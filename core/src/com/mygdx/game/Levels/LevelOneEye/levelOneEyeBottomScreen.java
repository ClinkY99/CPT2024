package com.mygdx.game.Levels.LevelOneEye;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.CPTGame;
import com.mygdx.game.Game_Elements.Puzzle_Elements.GridButtonPuzzle;
import com.mygdx.game.Game_Elements.Puzzle_Elements.ImagePuzzleButton;
import com.mygdx.game.Game_Elements.Puzzle_Elements.KeyPad;
import com.mygdx.game.Game_Elements.Puzzle_Elements.PewButton;
import com.mygdx.game.Game_Elements.World;
import com.mygdx.game.Levels.Screens.pauseMenu;
import com.mygdx.game.Levels.baseLevel;
import com.mygdx.game.Menus.CreditsScreen;
import com.mygdx.game.Menus.LostConnectionScreen;
import com.mygdx.game.Multiplayer.MPInterface;
import com.mygdx.game.ui.ScreenStack;
import com.mygdx.game.ui.stackableScreen;
import com.mygdx.game.ui.transitions.transitionScreen;

import java.io.IOException;

public class levelOneEyeBottomScreen implements stackableScreen {
    World levelWorld;
    Stage stage;
    CPTGame game;
    ScreenStack stack;
    KeyPad puzzleKeyPad;
    GridButtonPuzzle gridPuzzle;
    boolean newHasDone = false;
    boolean hasdone = false;
    Stage renderBeforePlayer;
    Music music;

    boolean goToCredits;

    baseLevel levelData;
    public levelOneEyeBottomScreen(ScreenStack stack, CPTGame game, baseLevel level) throws IOException {
        this.stack = stack;
        this.game = game;
        goToCredits = false;
        stage = new Stage(new FitViewport(1920,1080));
        renderBeforePlayer = new Stage(new FitViewport(1920,1080));

        levelWorld = new World("Levels/Level_1_1","player2", "Level_1_1",stage, true, renderBeforePlayer);

        puzzleKeyPad = new KeyPad(new int[]{2,5,7,3},new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/Pedastle Keypad.png")),stack);
        puzzleKeyPad.setPosition(700,1500);
        stage.addActor(puzzleKeyPad);

        ImagePuzzleButton underPedastle = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 1/FloorPattern.png")),2);
        underPedastle.setPosition(600,1400);
        renderBeforePlayer.addActor(underPedastle);

        music = Gdx.audio.newMusic(Gdx.files.internal("Music/Level 1/Library_2.wav"));
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();
        for (int i = 0; i < 4; i++) {
            PewButton paper = new PewButton(stack,new Texture(Gdx.files.internal("Images/journalEntry" + (i+1) + ".jpg")));
            Texture paperTexture = new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 1/Paper.png"));
            paper.updateTexture(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 1/Paper.png")));
            paper.setSize(paperTexture.getWidth(),paperTexture.getHeight());

            paper.setPosition(500 + ((i%2 == 0) ? i*200:i*100),2000 - i*30);
            stage.addActor(paper);
        }
        for (int i = 0; i < 1; i++) {
            PewButton paper = new PewButton(stack,new Texture(Gdx.files.internal("Images/article.png")));
            paper.updateTexture(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 1/Paper.png")));
            Texture paperTexture = new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 1/Paper.png"));

            paper.setSize(paperTexture.getWidth(),paperTexture.getHeight());

            paper.setPosition(500 + ((i%2 == 0) ? i*100:-i*100),1600);
            stage.addActor(paper);
        }


        PewButton pew2 = new PewButton(stack,new Texture(Gdx.files.internal("Images/eyePhoto.png")));
        pew2.setPosition(10300,1600);
        stage.addActor(pew2);

        PewButton pew3 = new PewButton(stack,new Texture(Gdx.files.internal("Images/circleHintExample.png")));
        pew3.setPosition(10300,1200);
        stage.addActor(pew3);

        levelData = level;

        if(levelData.connection!=null){
            Image playerBuddy2 = new Image(new Texture(Gdx.files.internal("Images/Players/EyeBuddy1.png")));
            playerBuddy2.setPosition(0,0);
            playerBuddy2.setSize(150,150);
            renderBeforePlayer.addActor(playerBuddy2);

            levelData.connection.bindFunction(((connection, object) ->{
                MPInterface.levelCompletion data = (MPInterface.levelCompletion) object;
                if(data.confirmed){
                    goToCredits = true;
                }
            }), MPInterface.levelCompletion.class);

            levelData.connection.bindFunction((connection, object) -> {
                MPInterface.playerLoc data = (MPInterface.playerLoc) object;
                if(data != null){
                    playerBuddy2.setPosition(levelWorld.spawn[0]+data.locx-data.spawnx-100, levelWorld.spawn[1]+data.locy-data.spawny-100);
                }
            }, MPInterface.playerLoc.class);
        }

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta, boolean top) {
        levelWorld.run(top);
        if (top) {
            if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
                stack.push(new pauseMenu(stack,game));
            }
            if (!newHasDone) {
                stage.act(delta);
            }
        }
        stage.draw();

        if (puzzleKeyPad.isSolved && hasdone == false) {
            stack.remove(puzzleKeyPad.padScreen);
            stage.getActors().removeValue(puzzleKeyPad,true);
            hasdone = true;
            Texture gridPuzzleButton = new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/PedastleMaze.png"));
            gridPuzzle = new GridButtonPuzzle(stack,gridPuzzleButton);
            gridPuzzle.setPosition(700,1500);
            stage.addActor(gridPuzzle);
            if(levelData.connection != null) {
                levelData.connection.sendTCP(new MPInterface.confirm("Puzzle2", true));
            }
        }
        if (gridPuzzle != null) {
            if (gridPuzzle.isComplete && !newHasDone) {
                PewButton finalSolution = new PewButton(stack, new Texture(Gdx.files.internal("Images/finalPuzzleSolution.png")));
                finalSolution.setPosition(5500, 2000);
                finalSolution.updateTexture(new Texture(Gdx.files.internal("Images/tiles/Level1/Puzzles/Puzzle 1/Paper.png")));
                newHasDone = true;
                stage.addActor(finalSolution);
            }
        }

        if(levelData.connection!=null){
            if(!levelData.connection.isConnected()){
                levelData.connection.close();
                levelData.connection = null;
                music.stop();
                game.setScreen(new transitionScreen(levelData, ()-> new LostConnectionScreen(game, Gdx.audio.newMusic(Gdx.files.internal("Music/mainMenu.wav"))),game));
            }
            MPInterface.playerLoc data = new MPInterface.playerLoc();
            data.locx = (int) levelWorld.player1.playerPosition.x;
            data.locy = (int) levelWorld.player1.playerPosition.y;
            data.spawnx = levelWorld.spawn[0];
            data.spawny = levelWorld.spawn[1];
            levelData.connection.sendTCP(data);
        }

        if(goToCredits){
            music.stop();
            game.setScreen(new transitionScreen(levelData, ()->new CreditsScreen(game, Gdx.audio.newMusic(Gdx.files.internal("Music/Ignorant_Lullaby.wav"))),game));
            goToCredits = false;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        music.pause();
    }

    @Override
    public void resume() {
        music.play();
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
        return stage;
    }
}
