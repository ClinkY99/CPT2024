package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.ui.ScreenStack;
import com.mygdx.game.ui.stackableScreen;
import com.ray3k.stripe.FreeTypeSkin;
import sun.font.ScriptRun;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.ui.ScreenStack;
import com.mygdx.game.ui.stackableScreen;

import java.awt.*;

public class BookShelf extends ImagePuzzleButton {
    TextureRegion region;
    Library library;
    ScreenStack stack;

    public BookShelf(Texture shelfTexture, int scale, Texture textTexture, ScreenStack stack) {
        super(shelfTexture, scale);

        library = new Library(textTexture);
        region = new TextureRegion(shelfTexture);
        setBounds(region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight());
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("SUS");
                stack.push(library);

            }
        });

        this.stack = stack;
    }

    public BookShelf() {
        super(new FreeTypeSkin(Gdx.files.internal("Levels/PuzzleElements.json")), "Bookshelf");



    }

    public BookShelf(int type, String imgPath, ScreenStack stack){
        super(new FreeTypeSkin(Gdx.files.internal("Levels/PuzzleElements.json")), "BookshelfInteractable");

        //stack.push(new Library());
    }

    public Table generateShelf(){
        Table table = new Table();
        TextureAtlas tex = new  TextureAtlas(Gdx.files.internal("Levels/PuzzleElements.json"));
        return table;
    }


    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    }
    class Library implements stackableScreen {
        //when you click a bookshelf, it makes this screen and puts it on stack, pass it the screenstack
        Stage stage;
        ImagePuzzleButton screenBlocker;
        ImagePuzzleButton text;
        public Library(Texture textTexture) {

            stage = new Stage(new FitViewport(1920,1080));
            Texture screenBlockerTexture = new Texture(Gdx.files.internal("Images/screenBlocker.png"));

            screenBlocker = new ImagePuzzleButton(screenBlockerTexture,0);
            screenBlocker.setPosition(0,0);
            screenBlocker.setSize(1928,1080);

            text = new ImagePuzzleButton(textTexture,2);
            text.setPosition(400,500);
            text.setSize(300,300);


            stage.addActor(screenBlocker);
            stage.addActor(text);
       }

        @Override
        public void show() {

        }
        @Override
        public void render(float delta, boolean top) {
            stage.act(delta);
            stage.draw();

            if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
                stack.remove(this);

            }
        }

        @Override
        public void resize(int width, int height) {
            stage.getViewport().update(width, height, true);
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
            stage.dispose();
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
