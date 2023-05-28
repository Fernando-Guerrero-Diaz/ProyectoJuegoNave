package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;


public class PantallaGameOver implements Screen {

	private SpaceNavigation game;
	private OrthographicCamera camera;
	private Music fin;
	private Texture adios;


	public PantallaGameOver(SpaceNavigation game) {
		this.game = game;
        
		adios = new Texture(Gdx.files.internal("Adios.jpg"));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1200, 800);
		fin = Gdx.audio.newMusic(Gdx.files.internal("Final.mp3"));
		
		fin.setLooping(true);
		fin.setVolume(0.20f);
		fin.play();
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.getBatch().setProjectionMatrix(camera.combined);

		game.getBatch().begin();
        game.getBatch().draw(adios, 0, 0, 1200, 800);
		game.getFont().draw(game.getBatch(), "¡Tu aventura ha terminado!\n ¿Lo volveras intentar?", 400, 500,400,1,true);
		game.getFont().draw(game.getBatch(), "Pincha en cualquier lado para reiniciar ...", 400, 300);
	
		game.getBatch().end();

		if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
			Screen ss = new PantallaJuego(game,1,50,0,1,1,10,2,1);
			ss.resize(1200, 800);
			game.setScreen(ss);
			dispose();
		}
	}
 
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		fin.play();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		this.fin.dispose();		
	}
   
}