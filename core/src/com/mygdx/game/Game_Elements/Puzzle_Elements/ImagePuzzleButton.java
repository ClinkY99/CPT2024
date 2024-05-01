package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Levels.TestLevel;
import com.ray3k.stripe.FreeTypeSkin;

public class ImagePuzzleButton extends ImageButton {
    //to make this work, add it to a table and then a stage
    Texture ButtonTexture;
    Boolean isClicked;
    World world;
    PuzzleTable puzzleTable;
    public boolean isLoaded;
    public ImagePuzzleButton(Texture texture, int scale, TestLevel level)
    {
        super(new FreeTypeSkin(Gdx.files.internal("Menu/Skins/RedPixelSkin.json")), "default");
        ButtonTexture = texture;
        this.scaleBy(scale);
        this.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(ButtonTexture));
        this.getStyle().imageChecked = new TextureRegionDrawable(new TextureRegion(ButtonTexture));
        this.getStyle().imageDisabled = new TextureRegionDrawable(new TextureRegion(ButtonTexture));
        this.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(ButtonTexture));
        this.getStyle().imageOver = new TextureRegionDrawable(new TextureRegion(ButtonTexture));
        this.getStyle().imageCheckedDown = new TextureRegionDrawable(new TextureRegion(ButtonTexture));
        this.getStyle().imageCheckedOver = new TextureRegionDrawable(new TextureRegion(ButtonTexture));

        this.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if (!isLoaded) {
                    remove();
                }
            }
        });

    }
}
