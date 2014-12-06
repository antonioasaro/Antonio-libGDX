package com.antoniogdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.audio.Sound;

public class AntonioGdxGame extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture dropletImage;
	private Texture bucketImage;
	private Music rainMusic;
	private Rectangle bucket;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		batch = new SpriteBatch();
		dropletImage = new Texture("droplet.png");
		bucketImage = new Texture("bucket.png");
		
        bucket = new Rectangle();
	    bucket.x = 800 / 2 - 64 / 2;
	    bucket.y = 20;
	    bucket.width = 64;
	    bucket.height = 64;
		
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		rainMusic.setLooping(true);
		rainMusic.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.5f, 1.0f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
	    batch.setProjectionMatrix(camera.combined);		
		batch.begin();
		batch.draw(bucketImage, bucket.x, bucket.y);
		batch.draw(dropletImage, 400, 400);
		batch.end();
		
	    if(Gdx.input.isTouched()) {
	        Vector3 touchPos = new Vector3();
	        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	        camera.unproject(touchPos);
	        bucket.x = touchPos.x - 64 / 2;
	     }
	     if(bucket.x < 0) bucket.x = 0;
	     if(bucket.x > 800 - 64) bucket.x = 800 - 64;

	}
	
	   @Override
	   public void dispose() {
	      dropletImage.dispose();
	      bucketImage.dispose();
	      rainMusic.dispose();
	      batch.dispose();
	   }

	   @Override
	   public void resize(int width, int height) {
	   }

	   @Override
	   public void pause() {
	   }

	   @Override
	   public void resume() {
	   }
	
}
