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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.ray3k.stripe.FreeTypeSkin;

import java.awt.*;

public class MenuButton extends TextButton {

    public MenuButton(String Text){
        this(Text, -1);
    }
    public MenuButton(String Text, float scale){
        this(Text, scale, new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Menu/tempus_sans_itc.ttf")));
    }

    public MenuButton(String Text, float scale, FreeTypeFontGenerator generator){
        super(Text, new FreeTypeSkin(Gdx.files.internal("Menu/Skins/Button.json")), "MenuButton");
        if(scale !=-1) {
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

            parameter.size = (int) (55 * scale);

            BitmapFont font = generator.generateFont(parameter);

            setLabel(newLabel(Text, new LabelStyle(font, getStyle().fontColor)));

        }



    }

}