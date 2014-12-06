package com.antoniogdx.game;

import javax.swing.text.html.HTMLDocument.Iterator;
import java.util.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class AntonioGdxGame extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture dropletImage;
	private Texture bucketImage;
	private Music rainMusic;
	private Sound dropSound;
	private Rectangle bucket;
	private Array<Rectangle> raindrops;
	private long lastDropTime;
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
		
		batch = new SpriteBatch();
		dropletImage = new Texture("droplet.png");
		bucketImage = new Texture("bucket.png");

	    dropSound = Gdx.audio.newSound(Gdx.files.internal("drip.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		rainMusic.setLooping(true);
		rainMusic.play();
		
        bucket = new Rectangle();
	    bucket.x = 800 / 2 - 64 / 2;
	    bucket.y = 20;
	    bucket.width = 64;
	    bucket.height = 64;
	    
	    raindrops = new Array<Rectangle>();
	    spawnRaindrop();
	}

	private void spawnRaindrop() {
	    Rectangle raindrop = new Rectangle();
	    raindrop.x = MathUtils.random(0, 800-64);
	    raindrop.y = 480;
	    raindrop.width = 64;
	    raindrop.height = 64;
	    raindrops.add(raindrop);
	    lastDropTime = TimeUtils.nanoTime();
	}
	
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0.3f, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
	    batch.setProjectionMatrix(camera.combined);		
		batch.begin();
		batch.draw(bucketImage, bucket.x, bucket.y);
	    for(Rectangle raindrop: raindrops) {
	        batch.draw(dropletImage, raindrop.x, raindrop.y);
	    }
		batch.end();
		
	    if(Gdx.input.isTouched()) {
	        Vector3 touchPos = new Vector3();
	        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
	        camera.unproject(touchPos);
	        bucket.x = touchPos.x - 64 / 2;
	    }
	    if(bucket.x < 0) bucket.x = 0;
	    if(bucket.x > 800 - 64) bucket.x = 800 - 64;

        if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();
 	    java.util.Iterator<Rectangle> iter = raindrops.iterator();
	    while(iter.hasNext()) {
	        Rectangle raindrop = iter.next();
	        raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
	        if(raindrop.y + 64 < 0) iter.remove();
	        if(raindrop.overlaps(bucket)) {
	           dropSound.play();
	           iter.remove();
	        }
	    }
	}
	
	@Override
	public void dispose() {
	    dropletImage.dispose();
	    bucketImage.dispose();
	    dropSound.dispose();
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
