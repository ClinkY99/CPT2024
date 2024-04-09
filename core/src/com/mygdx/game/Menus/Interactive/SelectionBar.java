package com.mygdx.game.Menus.Interactive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class SelectionBar extends ButtonGroup {
    private final Table table;
    private int margins;



    public SelectionBar(String... labels){
        this(new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Menu/tempus_sans_itc.ttf")), labels);
    }

    public SelectionBar(FreeTypeFontGenerator generator, String... labels){
        this(generator,-1,labels);
    }

    public SelectionBar(FreeTypeFontGenerator generator, float size, String... labels){
        super();

        table = new Table();

        for(String Label: labels){
            SelectionButton button = new SelectionButton(Label, size!=-1?size:-1, generator);
            add(button);
            table.add(button).width(1080/3).height(100);
        }

        Array<SelectionButton> buttons = getButtons();

        buttons.get(0).setChecked(true);

        setMinCheckCount(1);
        setMaxCheckCount(1);
        setUncheckLast(true);

        //table.debug();
    }

    public Table getTable() { return table; }
}
