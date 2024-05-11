package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class BookShelf extends ImagePuzzleButton {
    public PuzzleTable textHolder;
    public boolean isTextLoaded;
    Texture text;
    Texture shelf;

    public BookShelf(Texture shelfTexture, int scale,Texture textTexture) {
        super(shelfTexture, scale);
        text = textTexture;
        shelf = shelfTexture;
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isTextLoaded = true;
            }
        });

    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
     if (Gdx.input.isButtonPressed(Input.Keys.ESCAPE)) {
         isTextLoaded = false;
     }
     if (isTextLoaded) {
          updateTexture(text);
     } else {
         updateTexture(shelf);
     }
    }
}
