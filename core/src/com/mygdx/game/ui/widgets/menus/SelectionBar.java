package com.mygdx.game.ui.widgets.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

public class SelectionBar extends ButtonGroup {
    private final Table table;
    private int margins;

    private final Array<SelectionButton> buttons;



    public SelectionBar(int spacingX, int spacingY, String... labels){
        this(new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Menu/tempus_sans_itc.ttf")), spacingX, spacingY, labels);
    }

    public SelectionBar(FreeTypeFontGenerator generator, int spacingX, int spacingY, String... labels){
        this(generator,-1, spacingX, spacingY, labels);
    }

    public SelectionBar(FreeTypeFontGenerator generator, float size, int spacingX, int spacingY, String... labels){
        super();

        table = new Table();

        buttons = new Array<>();

        for(String Label: labels){
            SelectionButton button = new SelectionButton(Label, size, generator);
            button.center();
            button.debug();
            buttons.add(button);
            add(button);
            table.add(button).width(spacingX).height(spacingY).center();
            table.center();
        }

        setMinCheckCount(1);
        setMaxCheckCount(1);
        setUncheckLast(true);

        //table.debug();
    }

    public Table getTable() { return table; }

    public void add(String label) {
        super.add(new SelectionButton(label));
    }

    @Override
    public Array<SelectionButton> getButtons() {
        return buttons;
    }
}
