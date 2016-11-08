package br.com.vini.fightinggame.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.assets.loaders.ModelLoader.ModelParameters;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.UBJsonReader;

public class Player {
	public static final int IDLE = 0;
	public static final int ATTACK = 1;
	public static final int HIT = 2;
	private int vida = 10;
	private boolean alreadyHit = false;

	
	public int estado;
	public GameObject personagem[];
	private Texture skins[];
	private int currentSkin = 0;
	
	public Player(int mode){
		Model model;
		personagem = new GameObject[3];
		ModelLoader<ModelParameters> loader = new G3dModelLoader(new UBJsonReader());
		model = loader.loadModel(Gdx.files.internal("player/idle_fight_a.g3db"));
		personagem[IDLE] = new GameObject(model);
		model = loader.loadModel(Gdx.files.internal("player/kick_a.g3db"));
		personagem[ATTACK] = new GameObject(model);
		model = loader.loadModel(Gdx.files.internal("player/head_hit_a.g3db"));
		personagem[HIT] = new GameObject(model);
		
		estado = IDLE;
		
		if (mode == 1){
			for (GameObject g : personagem){
				g.transform.rotate(Vector3.Y,90);
				g.transform.translate(0,-1,-5);
			}
		}
		else{
			for (GameObject g : personagem){
				g.transform.rotate(Vector3.Y,-90);
				g.transform.translate(0,-1,-5);
				g.transform.rotate(Vector3.Y,10);
			}
		}
		
		// Carregando todas as possiveis texturas
		skins = new Texture[5];
		skins[0] = new Texture(Gdx.files.internal("player/texture2.jpg"));
		skins[1] = new Texture(Gdx.files.internal("player/texture_antman.jpg"));
		skins[2] = new Texture(Gdx.files.internal("player/texture_ironman.jpg"));
		skins[3] = new Texture(Gdx.files.internal("player/texture_kratos.jpg"));
		skins[4] = new Texture(Gdx.files.internal("player/texture_chapolin.jpg"));

	}
	
	
	public void update(float delta){
		personagem[estado].update(delta);
		if (estado == ATTACK || estado == HIT){
			if (personagem[estado].isFinished()){
				personagem[estado].resetAnimation();
				estado = IDLE;
				alreadyHit = false;
			}
		}
	}
	
	public void chutar(){
		estado = ATTACK;
		alreadyHit = false;
	}
	
	public void idle(){
		estado = IDLE;
		alreadyHit = false;
	}
	
	public void nextSkin(){
		currentSkin = (currentSkin + 1) % skins.length;
		for (GameObject g : personagem){
			TextureAttribute text = new TextureAttribute(TextureAttribute.createDiffuse(skins[currentSkin]));
			g.materials.get(0).set(text);;
		}
	}
	
	public void andarParaFrente(){
		for (GameObject g : personagem){
			g.transform.translate(0,0,0.5f);
		}
	}
	
	public void andarParaTras(){
		for (GameObject g : personagem){
			g.transform.translate(0,0,-0.25f);
		}
	}
	
	public void tomouPorradaForte(){
		vida -= 2;
		estado = HIT;
	}
	
	public void tomouPorradaFraco(){
		vida -= 1;
		estado = HIT;
	}
	
	public int getVida(){
		return vida;
	}
	
	public void setAlreadyHit(boolean alreadyHit){
		 this.alreadyHit = alreadyHit;
	}
	
	public boolean isAlreadyHit(){
		 return alreadyHit;
	}
	
	public void draw(float delta){
		
	}
	
}
