package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ray3k.stripe.FreeTypeSkin;

public class ImagePuzzleButton extends ImageButton {
    //to make this work, add it to a table and then a stage
    public Texture ButtonTexture;
    Boolean isClicked;
    World world;
    public int data;
    PuzzleTable puzzleTable;
    public boolean isLoaded;
    public int scale;
    public boolean isEmpty;

    public ImagePuzzleButton(Texture texture, int scale)
    {
        super(new FreeTypeSkin(Gdx.files.internal("Menu/Skins/RedPixelSkin.json")), "default");
        ButtonTexture = texture;
        this.scale = scale;
        setSize(ButtonTexture.getWidth(), ButtonTexture.getHeight());
        isLoaded = true;
        this.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(ButtonTexture));
        this.getStyle().imageChecked = new TextureRegionDrawable(new TextureRegion(ButtonTexture));
        this.getStyle().imageDisabled = new TextureRegionDrawable(new TextureRegion(ButtonTexture));
        this.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(ButtonTexture));
        this.getStyle().imageOver = new TextureRegionDrawable(new TextureRegion(ButtonTexture));
        this.getStyle().imageCheckedDown = new TextureRegionDrawable(new TextureRegion(ButtonTexture));
        this.getStyle().imageCheckedOver = new TextureRegionDrawable(new TextureRegion(ButtonTexture));

    }
    public ImagePuzzleButton(FreeTypeSkin skin, String styleName){
        super(skin, styleName);
    }

    public void updateTexture(Texture tex) {
        this.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(tex));
        this.getStyle().imageChecked = new TextureRegionDrawable(new TextureRegion(tex));
        this.getStyle().imageDisabled = new TextureRegionDrawable(new TextureRegion(tex));
        this.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(tex));
        this.getStyle().imageOver = new TextureRegionDrawable(new TextureRegion(tex));
        this.getStyle().imageCheckedDown = new TextureRegionDrawable(new TextureRegion(tex));
        this.getStyle().imageCheckedOver = new TextureRegionDrawable(new TextureRegion(tex));
    }
    public Texture getTexture() {
        return ButtonTexture;
    }
}
