package com.antoniogdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.antoniogdx.game.AntonioGdxGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	    config.title = "Droplets";
	    config.width = 800;
	    config.height = 480;
		new LwjglApplication(new AntonioGdxGame(), config);
	}
}

