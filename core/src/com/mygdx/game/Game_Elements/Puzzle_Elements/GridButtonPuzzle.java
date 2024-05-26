package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.ui.ScreenStack;
import com.mygdx.game.ui.stackableScreen;
import java.util.ArrayList;
//this class creates a grid where you need to select the correct tiles that the other player has moved over or it won't work
public class GridButtonPuzzle extends ImagePuzzleButton{

    public GridButtonPuzzle(ScreenStack stack, Texture buttonTexture) {
        super(buttonTexture,2);
        GridButtonPuzzleScreen puzzle = new GridButtonPuzzleScreen(stack);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stack.push(puzzle);
            }
        });
    }

    public class GridButtonPuzzleScreen implements stackableScreen {
        Stage stage;
        ScreenStack stack;
        Array<ImagePuzzleButton> buttons;
        ArrayList<int[]> clickedButtons = new ArrayList<>();
        ImagePuzzleButton[][] buttonGrid = new ImagePuzzleButton[10][10];
            public GridButtonPuzzleScreen(ScreenStack stack) {
                this.stage = new Stage(new FitViewport(1920,1080));
                this.stack = stack;
                Texture buttonTextureOne = new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/Tile1.png"));
                Texture buttonTextureTwo = new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/Tile2.png"));
                Texture screenBlockerTexture = new Texture(Gdx.files.internal("Images/screenBlocker.png"));
                ImagePuzzleButton screenBlocker = new ImagePuzzleButton(screenBlockerTexture,0);
                screenBlocker.setPosition(-10,0);
                screenBlocker.setSize(1928,1080);

                ImagePuzzleButton background = new ImagePuzzleButton(new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/PedastleMazeScreen.png")),2);

                stage.addActor(screenBlocker);
                stage.addActor(background);
                for(int i = 0;i < 10;i++) {
                    for(int j = 0; j < 10; j++) {
                        if ((i+j)%2 == 0 && isAllowed(i,j)) {
                            ImagePuzzleButton tempButton = new ImagePuzzleButton(buttonTextureOne,2);
                            tempButton.setPosition( 475+ i*buttonTextureOne.getWidth(),139+j*buttonTextureOne.getHeight());
                            int finalJ = j;
                            int finalI = i;
                            tempButton.addListener(new ClickListener() {
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    if (clickedButtons.size() < 4) {
                                        clickedButtons.add(new int[]{finalI, finalJ});
                                        tempButton.updateTexture(new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/TileSelected.png")));
                                    } else {
                                        for (int k = 0; k < clickedButtons.size();k++) {
                                            buttonGrid[clickedButtons.get(k)[0]][clickedButtons.get(k)[1]].updateTexture(((clickedButtons.get(k)[0]+clickedButtons.get(k)[1])%2 == 0) ? buttonTextureOne:buttonTextureTwo);
                                        }
                                        clickedButtons.clear();
                                        clickedButtons.add(new int[]{finalI, finalJ});
                                        tempButton.updateTexture(new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/TileSelected.png")));
                                    }
                                }
                            });
                            stage.addActor(tempButton);
                            buttonGrid[i][j] = tempButton;
                        } else if (isAllowed(i,j)) {
                            ImagePuzzleButton tempButton = new ImagePuzzleButton(buttonTextureTwo,2);
                            tempButton.setPosition(475 + i*buttonTextureOne.getWidth(),139+j*buttonTextureOne.getHeight());
                            int finalJ = j;
                            int finalI = i;
                            tempButton.addListener(new ClickListener() {
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    if (clickedButtons.size() < 4) {
                                        clickedButtons.add(new int[]{finalI, finalJ});
                                        tempButton.updateTexture(new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/TileSelected.png")));
                                    } else {
                                        for (int k = 0; k < clickedButtons.size();k++) {
                                            buttonGrid[clickedButtons.get(k)[0]][clickedButtons.get(k)[1]].updateTexture(((clickedButtons.get(k)[0]+clickedButtons.get(k)[1])%2 == 0) ? buttonTextureOne:buttonTextureTwo);
                                        }
                                        clickedButtons.clear();
                                        clickedButtons.add(new int[]{finalI, finalJ});
                                        tempButton.updateTexture(new Texture(Gdx.files.internal("Images/tiles/Level1/Eye/Puzzles/TileSelected.png")));
                                    }
                                }
                            });
                            stage.addActor(tempButton);
                            buttonGrid[i][j] = tempButton;
                        }
                    }
                }

            }
            public char convertToCharacter(int a) {
                switch(a) {
                    case 1:
                        return 'a';
                    case 2:
                        return 'b';
                    case 3:
                        return 'c';
                    case 4:
                        return 'd';
                    case 5:
                        return 'e';
                    case 6:
                        return 'f';
                    case 7:
                        return 'g';
                    case 8:
                        return 'h';
                    case 9:
                        return 'i';
                    case 10:
                        return 'j';
                }
                return 'z';
            }
        @Override
        public void show() {

        }
        public boolean isAllowed(int a, int b) {
                int[][] badCoords = new int[][]{{5,0},{7,0},{8,0},{2,1},{6,1},{8,1},{0,2},{2,2},{3,2},{4,2},{0,3},{2,3},{4,3},{6,3},{8,3},{4,4},{5,4},{6,4},{7,4},{8,4},{1,5},{2,5},{3,5},{4,5},{8,5},{2,6},{6,6},{9,6},{0,7},{2,7},{4,7},{6,7},{7,7},{9,7},{2,8},{4,8},{8,8},{9,8},{0,9},{5,9},{6,9}};
            for(int i = 0; i < badCoords.length;i++) {
                    if (badCoords[i][0] == a && badCoords[i][1] == b) {
                        return false;
                    }
            }
                return true;
            }
        @Override
        public void render(float delta, boolean top) {
            if (top) {
                stage.act(delta);
            }
            stage.draw();
            if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)) {
                stack.remove(this);
            }

            if (checkIfWon()) {
                System.out.println("SIGMA");
            }
        }
        public boolean checkIfValidCoordinate(int[] coordinate) {
                int[][] validCoordinates = new int[][]{{1,2},{1,6},{6,8},{8,9}};
                for (int i = 0; i < 4;i++) {
                    if (coordinate[0] == validCoordinates[i][0] && coordinate[1] == validCoordinates[i][1]) {
                        return true;
                    }
                }
                return false;
        }

        public boolean checkIfWon() {
            if (clickedButtons.size() == 4) {
                for (int i = 0; i < clickedButtons.size(); i++) {
                    if (!checkIfValidCoordinate(clickedButtons.get(i))) {
                        return false;
                    }
                }
            }
            return true;
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
            return stage;
        }
    }
}
