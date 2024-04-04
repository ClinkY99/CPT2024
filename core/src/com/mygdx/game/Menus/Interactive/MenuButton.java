package com.mygdx.game.Menus.Interactive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

import java.awt.*;

public class MenuButton extends Button {
    private Label label;
    private FreeTypeFontGenerator generator;

    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    public MenuButton(String Text){

        this(Text, -1);
    }
    public MenuButton(String Text, float scale){
        super();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Menu/tempus_sans_itc.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 55;
        if(scale != -1) parameter.size = 75;

        parameter.color = Color.BLACK;

        BitmapFont font = generator.generateFont(parameter);

        setStyle(new ButtonStyle());
        label = new Label(Text, new LabelStyle(font, null));
        label.setAlignment(Align.center);

        add(label).expand().fill();
        setSize(getPrefWidth(),getPrefHeight());
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }



}
