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

public class InvisibleMazePuzzle extends ImagePuzzleButton {
    int[][] badCoordinates;
    int[][] mazeStorage;
    int[] currentLocation;
    public boolean isWon;
    int[][] allowedCoordinates;
    public InvisibleMazePuzzleScreen mazeScreen;
    int[] goalLocation;
    public InvisibleMazePuzzle(Texture buttonTexture, Texture playerTexture, Texture arrowTexture, ScreenStack stack, int[][] badCoordinates,int[][]allowedCoordinates, int[] goalLocation) {
        super(buttonTexture,2);
        this.goalLocation = goalLocation;
        this.badCoordinates = badCoordinates;
        this.allowedCoordinates=allowedCoordinates;
        mazeStorage = new int[6][6];

        mazeScreen = new InvisibleMazePuzzleScreen(arrowTexture,playerTexture,stack);

        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("SUS");
                stack.push(mazeScreen);

            }
        });

    }



    public class InvisibleMazePuzzleScreen implements stackableScreen {
        Array<ImagePuzzleButton> arrows;
        int[] movement;
        int[] lastPosition;
        ImagePuzzleButton screenBlocker;
        Stage stage;
        ImagePuzzleButton player;
        ScreenStack stack;

        public InvisibleMazePuzzleScreen(Texture arrowTexture, Texture playerTexture,ScreenStack stack) {
            this.stack = stack;
            currentLocation = new int[]{0,0};
            movement = new int[]{0,0,0,0};
            stage = new Stage(new FitViewport(1920,1080));
            Texture screenBlockerTexture = new Texture(Gdx.files.internal("Images/screenBlocker.png"));
            arrows = new Array<>();
            player = new ImagePuzzleButton(playerTexture,2);
            player.setSize(200,200);
            screenBlocker = new ImagePuzzleButton(screenBlockerTexture,0);
            screenBlocker.setPosition(0,0);
            screenBlocker.setSize(1928,1080);
            stage.addActor(screenBlocker);
            stage.addActor(player);
            for (int i = 0; i < 4;i++) {
                ImagePuzzleButton arrow = new ImagePuzzleButton((i % 2 == 0) ? arrowTexture : new Texture(Gdx.files.internal("Levels/Level_1/updownarrow.png")),2);
                arrow.setOrigin(200,300);
                arrow.setSize(arrowTexture.getWidth(),arrowTexture.getHeight());
                arrow.setPosition(300,300);
                arrow.moveBy(2,2);

                int finalI = i;
                arrow.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        movement[finalI] = 1;

                    }
                });
                arrows.add(arrow);

                }
            for (int i = 0; i < arrows.size;i++) {
                arrows.get(i).setTransform(true);
                arrows.get(i).setOrigin(arrows.get(i).getWidth()/2, arrows.get(i).getHeight()/2);
                arrows.get(i).rotateBy(i*90);

                switch (i) {
                    case 0:
                        arrows.get(i).moveBy(50,0);
                        break;
                    case 1:
                        arrows.get(i).moveBy(0,-50);
                        break;
                    case 2:
                        arrows.get(i).moveBy(-50,0);
                        break;
                    case 3:
                        arrows.get(i).moveBy(0,50);
                        break;

                }
                stage.addActor(arrows.get(i));
            }

            }

        @Override
        public void show() {

        }

        @Override
        public void render(float delta, boolean top) {
            for (int i = 0; i < movement.length;i++) {
                if (movement[i] != 0) {
                    movement[i] = 0;
                    switch(i) {
                        case 0:
                            lastPosition = new int[]{currentLocation[0],currentLocation[1]};
                            currentLocation[0] += 1;
                            break;
                        case 1:
                            lastPosition = new int[]{currentLocation[0],currentLocation[1]};
                            currentLocation[1] -= 1;
                            break;
                        case 2:
                            lastPosition = new int[]{currentLocation[0],currentLocation[1]};
                            currentLocation[0] -= 1;
                            break;
                        case 3:
                            lastPosition = new int[]{currentLocation[0],currentLocation[1]};
                            currentLocation[1] += 1;
                            break;

                    }
                }
            }
            if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                stack.remove(this);

            }
            if (currentLocation[0] < 0) {
                currentLocation[0] += 1;

            }
            if (currentLocation[0] > mazeStorage.length) {
                currentLocation[0] -= 1;
            }
            if (currentLocation[1] < 0) {
                currentLocation[1] += 1;
            }
            if (currentLocation[1] > mazeStorage[0].length) {
                currentLocation[1] -= 1;
            }
            for (int i = 0; i < badCoordinates.length;i++) {
                if (badCoordinates[i][0] == currentLocation[0] && badCoordinates[i][1] == currentLocation[1] && !isAllowed()) {
                    currentLocation = new int[]{0,0};
                }
            }
            player.setPosition(400+currentLocation[0]*50,400+currentLocation[1]*50);

            if (currentLocation[0] == goalLocation[0] && currentLocation[1] == goalLocation[1]) {
                isWon = true;
            }
            if (top) {
                stage.act(delta);
            }
            stage.draw();
        }
        public boolean isAllowed() {
            for (int i = 0; i < allowedCoordinates.length; i++) {
                if (lastPosition[0] == allowedCoordinates[i][0] && lastPosition[1] == allowedCoordinates[i][1]) {
                    return true;
                }
            }
            return false;
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

        }

        @Override
        public Stage getStage() {
            return stage;
        }
    }
}
