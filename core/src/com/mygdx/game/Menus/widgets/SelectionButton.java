package com.mygdx.game.Menus.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.ray3k.stripe.FreeTypeSkin;

public class SelectionButton extends TextButton {
    public SelectionButton(String Text){
        this(Text, -1);
    }
    public SelectionButton(String Text, float scale){
        this(Text, scale, new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Menu/tempus_sans_itc.ttf")));
    }

    public SelectionButton(String Text, float scale, FreeTypeFontGenerator generator){
        super(Text, new FreeTypeSkin(Gdx.files.internal("Menu/Skins/MenuInteractables.json")), "SelectionButton");
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = (int) (48*(scale!=-1?scale:1));

        BitmapFont font = generator.generateFont(parameter);

        setLabel(newLabel(Text, new Label.LabelStyle(font, getStyle().fontColor)));

        getLabel().setAlignment(Align.center);
    }




}
