package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
import java.util.function.Function;

public class PuzzleButton extends TextButton {
    //to use this class, you can either pass just text and a scale, or also pass a puzzletable and have it
    //added automatically. After that, just add a clickListener. If you dont pass a puzzletable directly,
    //add it later
    public PuzzleButton(String Text, PuzzleTable table){
        this(Text, -1, table);
    }
    public PuzzleButton(String Text, float scale, PuzzleTable table){
        this(Text, scale, new FreeTypeFontGenerator(Gdx.files.internal("Images/roboto/Roboto-light.ttf")), table);
    }

    public PuzzleButton(String Text, float scale, FreeTypeFontGenerator generator,PuzzleTable table){
        super(Text, new FreeTypeSkin(Gdx.files.internal("skin/vhs-ui.json")));
        if(scale !=-1) {
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = (int) (30 * scale);
            BitmapFont font = generator.generateFont(parameter);
            setLabel(newLabel(Text, new LabelStyle(font, getStyle().fontColor)));
            table.add(this);


        }



    }



    }



