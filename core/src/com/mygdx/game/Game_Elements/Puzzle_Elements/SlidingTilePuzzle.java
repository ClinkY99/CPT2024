package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SlidingTilePuzzle {
    Texture tileTexture;
    ImagePuzzleButton[][] tileList;
    int[][] goalLocations;
    public int x;
    public int y;
    public PuzzleTable puzzleHolder;
    Texture specTexture;
    Texture normTexture;

    Texture emptTexture;

    public SlidingTilePuzzle(int rows, int columns, int[][] goals, Texture specialTexture, Texture normalTexture, Stage stage,int x,int y) {
        //Update the button's texture to hide them
        specTexture = specialTexture;
        normTexture = normalTexture;

        puzzleHolder = new PuzzleTable(x,y,stage);
        tileTexture = normalTexture;
        goalLocations = goals;
        tileList = new ImagePuzzleButton[rows][columns];

        for(int i = 0; i < tileList.length;i++) {
            for(int j = 0; j < tileList[i].length;j++) {
                if (tileList[i][j] == null) {
                    ImagePuzzleButton button = new ImagePuzzleButton(normalTexture,1);
                    button.data = 2;
                    int finalI = i;
                    int finalJ = j;
                    button.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            if (isAdjacentEmpty(new int[]{finalI,finalJ})[0] != -1){
                                int[] emptyCoordinates = isAdjacentEmpty(new int[]{finalI,finalJ});
                                int tempData =  tileList[emptyCoordinates[0]][emptyCoordinates[1]].data;
                                tileList[emptyCoordinates[0]][emptyCoordinates[1]].data = button.data;
                                tileList[finalI][finalJ].data = tempData;


                            }
                        }
                    });

                    tileList[i][j] = button;
                }
            }


        }
        for (int i = 0; i < goals.length;i++) {
            tileList[2][i].data = 3;
        }
        tileList[0][0].data = 1;
        emptTexture = new Texture(Gdx.files.internal("Images/idle_0.png"));
        tileList[0][0].updateTexture(emptTexture);


        for(int i = 0; i < tileList.length;i++) {
            for(int j = 0; j < tileList[i].length;j++) {
                puzzleHolder.add(tileList[i][j]);
            }
            puzzleHolder.row();
        }
    }

    public void render() {
        if (checkIfGoalsAreFull()) {
            System.out.println("GOOD JOB");
        }
        for(int i = 0; i < tileList.length;i++) {
            for (int j = 0; j < tileList[i].length; j++) {
                switch(tileList[i][j].data) {
                    case 1:
                        tileList[i][j].updateTexture(emptTexture);
                        break;
                    case 2:
                        tileList[i][j].updateTexture(normTexture);
                        break;
                    case 3:
                        tileList[i][j].updateTexture(specTexture);
                        break;
                }
            }
        }
    }
    //return coordinates of empty space
    public int[] isAdjacentEmpty(int[] coordinate){
        for (int i = 0; i < 4; i++) {
            try {
                switch(i) {
                    case 0:
                        if (tileList[coordinate[0]+1][coordinate[1]].data == 1) {
                            return new int[]{coordinate[0]+1,coordinate[1]};
                        }
                    case 1:
                        if (tileList[coordinate[0]][coordinate[1]-1].data == 1) {

                            return new int[]{coordinate[0],coordinate[1]-1};
                        }
                    case 2:
                        if (tileList[coordinate[0]-1][coordinate[1]].data == 1) {


                            return new int[]{coordinate[0]-1,coordinate[1]};
                        }
                    case 3:
                        if (tileList[coordinate[0]][coordinate[1]+1].data == 1) {

                            return new int[]{coordinate[0],coordinate[1]+1};
                        }

                }
            }
            catch (Exception E) {

            }
        }
        return new int[]{-1,-1};
    }
    public boolean checkIfGoalsAreFull() {
        for (int i = 0; i < goalLocations.length;i++) {
            if (tileList[goalLocations[i][0]][goalLocations[i][1]].data != 3) {
                return false;
            }
        }
        return true;
    }

}
