package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class CPTGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private Texture bucketImage;
	private Sound susSound;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("among us.png");
		// declares images
		bucketImage = new Texture(Gdx.files.internal("bucketPicture.jpeg"));
		susSound = Gdx.audio.newSound(Gdx.files.internal("among-us-start-sound.mp3"));
		susSound.play();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
