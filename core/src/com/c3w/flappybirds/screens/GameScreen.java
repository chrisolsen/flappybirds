package com.c3w.flappybirds.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.c3w.flappybirds.FlappyBirds;
import com.c3w.flappybirds.sprites.Bird;
import com.c3w.flappybirds.sprites.Tube;


public class GameScreen implements Screen {

    private static final int TUBE_OFFSET = 150;
    private static final int X_VEL = 40;

    private Bird bird;
    private Array<Tube> tubes;
    private Image background;

    private FlappyBirds game;
    private Stage stage;

    private float lastTubeOffset;

    public GameScreen(FlappyBirds g) {
        game = g;
        stage = new Stage(new ScreenViewport());
        OrthographicCamera cam = (OrthographicCamera) stage.getCamera();
        cam.setToOrtho(false, g.WIDTH / 2, g.HEIGHT / 2);

        background = new Image(new Texture("bg.png"));
        background.setFillParent(true);
        stage.addActor(background);

        bird = new Bird(50, 300, X_VEL);
        bird.setPosition(50, 300);
        stage.addActor(bird);

        tubes = new Array<>();
        for (int i = 0; i < 3; i++) {
            Tube t = new Tube(TUBE_OFFSET * (i+1));
            tubes.add(t);
            stage.addActor(t);
        }

        lastTubeOffset = tubes.get(tubes.size - 1).getTopBounds().getX();
    }

    public void update(float dt) {
        Camera cam = stage.getCamera();
        cam.translate(X_VEL * dt, 0f, 0f);

        background.setX(background.getX() + X_VEL * dt);

        if (Gdx.input.justTouched()) {
           bird.jump();
        }
        bird.update(dt);

        // wrap the next pipe
        float xBounds = cam.position.x - cam.viewportWidth / 2;
        for (Tube t: tubes) {
            if (Math.abs(xBounds) > (t.getTopBounds().x + t.getWidth())) {
                lastTubeOffset += TUBE_OFFSET;
                t.setPositionX(lastTubeOffset);
                t.setRandomPositionY();
            }
        }
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
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
        for (Tube tube: tubes) {
            tube.dispose();
        }
        stage.dispose();
    }
}
