package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Game_Elements.Object;
import com.mygdx.game.Game_Elements.Player;

public class CPTGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private Texture bucketImage;
	private Sound susSound;
	Player player;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("idle_0.png");
		player = new Player(img);

		// declares images
		bucketImage = new Texture(Gdx.files.internal("bucketPicture.jpeg"));
		susSound = Gdx.audio.newSound(Gdx.files.internal("among-us-start-sound.mp3"));
		susSound.play();

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		player.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
