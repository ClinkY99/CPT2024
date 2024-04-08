package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.Game_Elements.World;
import com.mygdx.game.Menus.MainMenu;

public class CPTGame extends Game {
	public SpriteBatch batch;
	public FreeTypeFontGenerator DefaultFont;
	//	SpriteBatch batch;
//	Texture img;
//	private Stage stage;
//	private Texture bucketImage;
//	private Sound susSound;
//	World level;

	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		DefaultFont = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Menu/tempus_sans_itc.ttf"));

		setScreen(new MainMenu(this));

		// declares images
//		level = new World(img);
//		stage = new Stage(new ScreenViewport());
//		Gdx.input.setInputProcessor(stage);
		//Skin mySkin = new Skin(Gdx.files.internal("skin/vhs_ui.png"));
	}

	@Override
	public void render () {
		super.render();
		//ScreenUtils.clear(0, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
//		level.run(batch);
//		stage.act();
//		stage.draw();
//		batch.end();
	}


	@Override
	public void dispose () {
		batch.dispose();
		DefaultFont.dispose();
		//img.dispose();
	}
}
