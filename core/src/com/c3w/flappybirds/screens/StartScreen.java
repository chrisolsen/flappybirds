package com.c3w.flappybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.c3w.flappybirds.FlappyBirds;


public class StartScreen implements Screen {

    private Stage stage;
    private FlappyBirds game;

    public StartScreen(FlappyBirds g) {
        game = g;
        stage = new Stage(new ScreenViewport());

        // background
        Image bg = new Image(new Texture("bg.png"));
        bg.setFillParent(true);
        stage.addActor(bg);

        // button
        Image btnPlay = new Image(new Texture("playbtn.png")) ;
        btnPlay.setPosition(Gdx.graphics.getWidth()/2 - btnPlay.getWidth()/2, Gdx.graphics.getHeight()/2);
        btnPlay.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
            }
        });
        stage.addActor(btnPlay);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
