package br.com.vini.fightinggame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import br.com.vini.fightinggame.screen.AbstractScreen;
import br.com.vini.fightinggame.screen.GameScreen;
import br.com.vini.fightinggame.screen.WelcomeScreen;

public class FightingGame extends Game {

	AbstractScreen telaAtual;
	
	@Override
	public void create() {
		telaAtual = new WelcomeScreen();
		telaAtual.setId("WELCOME");
	}
	
	@Override
	public void render() {
		float delta = Gdx.graphics.getDeltaTime();
		telaAtual.render(delta);
		
		if (telaAtual.isDone()){ //terminei meu trabalho?
			
			if (telaAtual.getId().equals("WELCOME")){
				telaAtual= new GameScreen();
				telaAtual.setId("GAME");
			}
			else{
				telaAtual = new WelcomeScreen();
				telaAtual.setId("WELCOME");
			}
		}
		
	}
	
	
	
}
