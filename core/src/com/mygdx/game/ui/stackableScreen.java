package com.mygdx.game.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

public interface stackableScreen extends Disposable {
    void show();
    void render(float delta,boolean top);
    void resize(int width, int height);
    void pause();
    void resume();
    void hide();
    void dispose();
    void setStage(Stage stage);
    Stage getStage();
}
