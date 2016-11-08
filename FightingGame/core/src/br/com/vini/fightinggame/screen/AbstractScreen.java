package br.com.vini.fightinggame.screen;

import com.badlogic.gdx.Screen;

public abstract class AbstractScreen implements Screen{

	public abstract void update(float delta);
	public abstract void draw(float delta);
	
	private String id;
	private boolean done;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public boolean isDone() {
		return done;
	}
	
	public void setDone(boolean done) {
		this.done = done;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		update(delta);
		draw(delta);
	}
	
	@Override
	public void show() {
		// Não faz nada
	}

	@Override
	public void resize(int width, int height) {
		// Não faz nada
	}

	@Override
	public void pause() {
		// Não faz nada
	}

	@Override
	public void resume() {
		// Não faz nada
	}

	@Override
	public void hide() {
		// Não faz nada
	}

}
