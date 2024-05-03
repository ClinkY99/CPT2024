package com.mygdx.game.ui.widgets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.utils.Array;


public class Switcher extends Container<Actor> {

    private final Array<Actor> actorArray;

    private int focused;


    public Switcher(Actor... actors){
        super();
        actorArray = new Array<>(actors);
        focused = 0;
        setActor(actorArray.get(focused));
    }

    @Override
    public void act(float delta) {
        actorArray.get(focused).act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);


    }

    public void switchFocused(int newFocus){
        if(newFocus >=0 && newFocus < actorArray.size){
            focused = newFocus;
            setActor(actorArray.get(focused));
        }
    }
    public Actor getFocused(){return actorArray.get(focused);}

    public int getFocusedIndex(){return focused;}

    public void addActor(Actor actor){
        actorArray.add(actor);
    }

    public void updateFocused(Actor actor){
        actorArray.set(focused, actor);
        setActor(actorArray.get(focused));
    }

}
