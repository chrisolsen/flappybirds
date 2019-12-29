package com.c3w.flappybirds.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State {

    public MenuState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void handleInput() {
        // handle button click
    }

    @Override
    public void update(float dt) {
        // tell the gsm to load the next state...
    }

    @Override
    public void render(SpriteBatch batch) {
        // render the background

        // render the button
    }

    @Override
    public void dispose() {

    }
}
