package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.Game_Elements.World;
import com.mygdx.game.Levels.TestLevel;
import com.mygdx.game.Menus.MainMenu;
import jdk.incubator.vector.VectorOperators;

public class CPTGame extends Game {
	public SpriteBatch batch;
	MainMenu menuLevel;

	public FreeTypeFontGenerator DefaultFont;
	//	SpriteBatch batch;
	Texture img;
//	private Stage stage;

	@Override
	public void create ()
	{
		img = new Texture(Gdx.files.internal("Images/whiteRectangle.png"));
		batch = new SpriteBatch();
		DefaultFont = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Menu/tempus_sans_itc.ttf"));
		menuLevel = new MainMenu(this);;
		setScreen(menuLevel);
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
