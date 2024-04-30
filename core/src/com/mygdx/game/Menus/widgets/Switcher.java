package com.mygdx.game.Menus.widgets;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class Switcher extends Actor {

    private final Array<Actor> actorArray;

    private int focused;


    public Switcher(Actor... actors){
        actorArray = new Array<>(actors);
        focused = 0;

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        actorArray.get(focused).act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        actorArray.get(focused).draw(batch, parentAlpha);
        actorArray.get(focused);
    }

    public void switchFocused(int newFocus){
        System.out.println("test");
        if(newFocus >=0 && newFocus < actorArray.size){
            focused = newFocus;
        }
        System.out.println("focused = " + focused);
    }
    public Actor getFocused(){return actorArray.get(focused);}

    public int getFocusedIndex(){return focused;}

    public void addActor(Actor actor){
        actorArray.add(actor);
    }

    public void updateFocused(Actor actor){
        actorArray.set(focused, actor);
    }

}
