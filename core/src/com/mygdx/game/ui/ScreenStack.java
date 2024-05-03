package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Disposable;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;

public class ScreenStack implements Disposable{
    Stack<Screen> stack;

    public ScreenStack(Screen... screens) {
        stack = new Stack<>();
    }

    public void push(@NotNull final Screen screen) {
        screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stack.push(screen);
    }
    public void push(Screen... screens) {
        for (Screen screen : screens) {
            screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            stack.push(screen);
        }
    }
    public void render(float delta){

    }

    @Override
    public void dispose() {

    }
}
