package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;


public class PantallaMenu implements Screen {

	private SpaceNavigation game;
	private OrthographicCamera camera;
	private Texture hola;
	private Music inicio;


	public PantallaMenu(SpaceNavigation game) {
		this.game = game;
        
		hola = new Texture(Gdx.files.internal("PantallaInicio.jpg"));
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1200, 800);
		inicio = Gdx.audio.newMusic(Gdx.files.internal("Pirata.mp3"));

		inicio.setLooping(true);
		inicio.setVolume(0.15f);
		inicio.play();
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		game.getBatch().setProjectionMatrix(camera.combined);

		game.getBatch().begin();
        game.getBatch().draw(hola, 0, 0, 1200, 800);
        game.getFont().setColor(0,0,0,1);
		game.getFont().draw(game.getBatch(), "Bienvenido a Pirates Survivors !", 500, 600);
        game.getFont().setColor(1,1,1,1);
		game.getFont().draw(game.getBatch(), "Pincha en cualquier lado o presiona cualquier tecla para comenzar ...", 250, 250);
	
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
		inicio.play();
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
		this.inicio.dispose();		
	}
   
}