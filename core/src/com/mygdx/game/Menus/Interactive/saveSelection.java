package com.mygdx.game.Menus.Interactive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Game_Elements.SaveFile;
import com.ray3k.stripe.FreeTypeSkin;

public class saveSelection extends TextButton {
    private final Array<Label> labels = new Array<>();
    private final SaveFile saveFile;


    public saveSelection(SaveFile save) {
        this(save, -1);
    }
    public saveSelection(SaveFile save, float scale) {
        this(save, scale, new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Menu/tempus_sans_itc.ttf")));
    }

    public saveSelection(SaveFile file, float scale, FreeTypeFontGenerator generator) {
        super(file.getName(), new FreeTypeSkin(Gdx.files.internal("Menu/Skins/MenuInteractables.json")),"SelectionButton");

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int) (48*(scale!=-1?scale:1));
        BitmapFont font = generator.generateFont(parameter);
        labels.add(newLabel(file.getName(), new Label.LabelStyle(font, getStyle().fontColor)));
        setLabel(labels.get(0));



        labels.add(newLabel(file.getDateLP(), new Label.LabelStyle(font, getStyle().fontColor)));
        labels.add(newLabel(file.getLevel()+"", new Label.LabelStyle(font, getStyle().fontColor)));

        add(labels.get(1), labels.get(2));

        setWidth(1920/5f*3);
        setHeight(100);

        saveFile = file;
    }

    public SaveFile getSaveFile() {
        return saveFile;
    }

    public Array<Label> getLabels() {
        return labels;
    }
}
