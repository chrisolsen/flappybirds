package com.c3w.flappybirds;

import com.badlogic.gdx.Game;
import com.c3w.flappybirds.screens.StartScreen;

public class FlappyBirds extends Game {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "FlappyBird";

	@Override
	public void create () {
		this.setScreen(new StartScreen(this));
	}
}
