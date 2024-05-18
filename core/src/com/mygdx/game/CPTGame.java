package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.Levels.Screens.pauseMenu;
import com.mygdx.game.Menus.SplashScreen;

public class CPTGame extends Game {
	public SpriteBatch batch;
	SplashScreen menuLevel;

	public pauseMenu pause;

	public FreeTypeFontGenerator DefaultFont;

	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		pause = new pauseMenu();


		DefaultFont = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Menu/tempus_sans_itc.ttf"));
		menuLevel = new SplashScreen(this);
		setScreen(menuLevel);
		//setScreen(new characterSelection(this, new SaveFile("Test")));
		// declares images
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
