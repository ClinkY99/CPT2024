package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.ui.ScreenStack;
import com.mygdx.game.ui.stackableScreen;
import com.ray3k.stripe.FreeTypeSkin;
import sun.font.ScriptRun;

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

    public BookShelf() {
        super(new FreeTypeSkin(Gdx.files.internal("Levels/PuzzleElements.json")), "Bookshelf");



    }

    public BookShelf(int type, String imgPath, ScreenStack stack){
        super(new FreeTypeSkin(Gdx.files.internal("Levels/PuzzleElements.json")), "BookshelfInteractable");

        stack.push(new bookFocused());
    }

    public Table generateShelf(){
        Table table = new Table();
        TextureAtlas tex = new  TextureAtlas(Gdx.files.internal("Levels/PuzzleElements.json"));
        return table;
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

    class bookFocused implements stackableScreen {

        Stage stage;

        @Override
        public void show() {

        }

        @Override
        public void render(float delta, boolean top) {

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
