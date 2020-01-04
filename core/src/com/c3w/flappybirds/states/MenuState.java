package com.c3w.flappybirds.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.c3w.flappybirds.Game;


public class MenuState extends State {

    Texture btnPlay;
    Texture background;

    public MenuState(GameStateManager gsm) {
        super(gsm);

        btnPlay = new Texture("playbtn.png");
        background = new Texture("bg.png");
    }

    @Override
    public void handleInput() {
        // start game on screen touch
        if (Gdx.input.justTouched()) {
            this.gsm.set(new PlayState(this.gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch b) {
        b.begin();
        b.draw(background, 0,0 , Game.WIDTH, Game.HEIGHT);
        b.draw(btnPlay, Game.WIDTH / 2 - btnPlay.getWidth() / 2, Game.HEIGHT / 2);
        b.end();
    }

    @Override
    public void dispose() {
        btnPlay.dispose();
        background.dispose();
    }
}
