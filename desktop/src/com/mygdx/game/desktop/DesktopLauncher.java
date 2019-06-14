package com.mygdx.game.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.mygdx.game.game.MinecubeGame;
import com.mygdx.game.util.Constants;

public class DesktopLauncher {

	private static boolean rebuildAtlas = false;

	public static void main (String[] arg) {
		if(rebuildAtlas) {
			TexturePacker.Settings settings = new TexturePacker.Settings();
			settings.maxWidth = 2048;
			settings.maxHeight = 2048;
			settings.duplicatePadding = false;
			TexturePacker.process(settings, "images", "atlases", "sprites.pack");
			TexturePacker.process(settings, "images/actors", "atlases", "sprites.pack");
			TexturePacker.process(settings, "images/ui", "atlases", "ui.pack");
		}
		Game minecube = new MinecubeGame();
		new LwjglApplication(minecube, "Minecube", Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	}
}
