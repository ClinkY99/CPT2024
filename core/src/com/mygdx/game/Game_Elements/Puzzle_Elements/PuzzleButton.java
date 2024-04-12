package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.Game_Elements.World;

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

public class PuzzleButton extends TextButton {

    public PuzzleButton(String Text){
        this(Text, -1);
    }
    public PuzzleButton(String Text, float scale){
        this(Text, scale, new FreeTypeFontGenerator(Gdx.files.internal("Images/roboto/Roboto-light.ttf")));
    }

    public PuzzleButton(String Text, float scale, FreeTypeFontGenerator generator){
        super(Text, new FreeTypeSkin(Gdx.files.internal("skin/vhs-ui.json")));
        if(scale !=-1) {
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = (int) (30 * scale);

            BitmapFont font = generator.generateFont(parameter);

            setLabel(newLabel(Text, new LabelStyle(font, getStyle().fontColor)));

        }



    }

}
