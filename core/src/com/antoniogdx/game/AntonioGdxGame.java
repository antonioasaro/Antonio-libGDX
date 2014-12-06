package com.antoniogdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Sound;

public class AntonioGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture dropletImage;
	Texture bucketImage;
	Music rainMusic;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		dropletImage = new Texture("droplet.png");
		bucketImage = new Texture("bucket.png");
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		
		rainMusic.setLooping(true);
		rainMusic.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 1, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(dropletImage, 200, 200);
		batch.draw(bucketImage, 0, 0);
		batch.end();
	}
}
