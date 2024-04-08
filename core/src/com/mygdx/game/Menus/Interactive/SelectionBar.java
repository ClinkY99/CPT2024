package com.mygdx.game.Menus.Interactive;

import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class SelectionBar extends Table {

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;


    public SelectionBar(FreeTypeFontGenerator generator, String... labels){
        this(generator,32,labels);
    }

    public SelectionBar(FreeTypeFontGenerator generator, float size, String... labels){


    }
}
