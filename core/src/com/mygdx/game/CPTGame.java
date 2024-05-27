package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.Game_Elements.SaveFile;
import com.mygdx.game.Levels.Screens.pauseMenu;
import com.mygdx.game.Levels.Test;
import com.mygdx.game.Menus.CreditsScreen;
import com.mygdx.game.Menus.SplashScreen;

public class CPTGame extends Game {
	public SpriteBatch batch;
	SplashScreen menuLevel;

	public pauseMenu pause;

	public FreeTypeFontGenerator DefaultFont;

	//this is the top most class that controls all the other classes

	@Override
	public void create ()
	{
		batch = new SpriteBatch();

		DefaultFont = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Menu/tempus_sans_itc.ttf"));
		menuLevel = new SplashScreen(this);
		setScreen(menuLevel);
	}

	@Override
	public void render () {
		super.render();
	}


	@Override
	public void dispose () {
		batch.dispose();
		DefaultFont.dispose();
		//img.dispose();
	}

}
