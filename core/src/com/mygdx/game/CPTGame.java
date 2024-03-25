package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Game_Elements.Object;
import com.mygdx.game.Game_Elements.Player;
import com.mygdx.game.Game_Elements.World;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CPTGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
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
		bucketImage = new Texture(Gdx.files.internal("bucketPicture.jpeg"));
		susSound = Gdx.audio.newSound(Gdx.files.internal("Music/among-us-start-sound.mp3"));

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		level.run(batch);
		batch.end();
	}


	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
