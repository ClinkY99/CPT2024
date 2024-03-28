package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Game_Elements.Object;
import com.mygdx.game.Game_Elements.Player;
import com.mygdx.game.Game_Elements.World;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CPTGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private Stage stage;
	private Texture bucketImage;
	private Sound susSound;
	World level;

	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		img = new Texture("idle_0.png");
		// declares images
		level = new World(img);
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		//Skin mySkin = new Skin(Gdx.files.internal("skin/vhs_ui.png"));
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		level.run(batch);
		stage.act();
		stage.draw();
		batch.end();
	}


	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
