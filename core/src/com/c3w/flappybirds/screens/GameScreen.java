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

import static com.c3w.flappybirds.screens.GameScreen.GameState.*;

public class GameScreen implements Screen {

    private static final int TUBE_OFFSET = 150;
    private static final int X_VEL = 40;

    enum GameState {
        PLAYING,
        OVER
    }

    private Bird bird;
    private Array<Tube> tubes;
    private Image background;
    private Image[] grounds;

    private FlappyBirds game;
    private Stage stage;

    private GameState state;
    private float lastTubeOffset;

    public GameScreen(FlappyBirds g) {
        game = g;
        init();
    }

    private void init() {
        state = PLAYING;
        stage = new Stage(new ScreenViewport());
        OrthographicCamera cam = (OrthographicCamera) stage.getCamera();
        cam.setToOrtho(false, game.WIDTH / 2, game.HEIGHT / 2);

        // background
        background = new Image(new Texture("bg.png"));
        stage.addActor(background);

        // bird
        bird = new Bird(50, 300, X_VEL);
        bird.setPosition(50, 300);
        stage.addActor(bird);

        // tubes
        tubes = new Array<>();
        for (int i = 0; i < 3; i++) {
            Tube t = new Tube(TUBE_OFFSET * (i+1));
            tubes.add(t);
            stage.addActor(t);
        }

        // set looping ground
        Texture groundTexture = new Texture("ground.png");

        grounds = new Image[2];
        grounds[0] = new Image(groundTexture);
        grounds[0].setY(-grounds[0].getHeight() + 30);
        stage.addActor(grounds[0]);

        grounds[1] = new Image(groundTexture);
        grounds[1].setPosition(grounds[1].getWidth(), -grounds[1].getHeight() + 30);
        stage.addActor(grounds[1]);

        lastTubeOffset = tubes.get(tubes.size - 1).getTopBounds().getX();
    }

    public void update(float dt) {
        // act based on game state
        if (Gdx.input.justTouched()) {
            switch (state) {
                case PLAYING:
                    bird.jump();
                    break;
                case OVER:
                    // TODO: show restart screen
                    return;
                default:
                    return;
            }
        }

        // bird collision with ground
        if (bird.getY() < 30) {
            state = OVER;
            return;
        }

        // tube collisions
        for (Tube t: tubes) {
            if (t.overlaps(bird.getBounds(true))) {
                state = OVER;
                return;
            }
        }

        // update entity positions
        Camera cam = stage.getCamera();
        cam.translate(X_VEL * dt, 0f, 0f);
        bird.update(dt);
        background.setX(background.getX() + X_VEL * dt);

        // wrapping ground
        for (Image g: grounds) {
            boolean isGroundOffCamera = g.getX() + g.getWidth() < cam.position.x - cam.viewportWidth/2;
            if (isGroundOffCamera) {
                g.setX(cam.position.x + cam.viewportWidth / 2);
            }
        }

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
