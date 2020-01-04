package com.c3w.flappybirds.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.c3w.flappybirds.Game;
import com.c3w.flappybirds.sprites.Bird;
import com.c3w.flappybirds.sprites.Tube;


public class PlayState extends State {

    public static final int TUBE_OFFSET = 170;
    public static final int X_VEL = -40;

    Bird bird;
    Array<Tube> tubes;
    Texture background;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        tubes = new Array<Tube>();
        bird = new Bird(50, 300);
        for (int i = 0; i < 2; i++) {
            Tube t = new Tube(0 + TUBE_OFFSET * (i+1));
            tubes.add(t);
        }
        background = new Texture("bg.png");
        cam.setToOrtho(false, Game.WIDTH/2, Game.HEIGHT/2);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        cam.position.add(X_VEL * dt, 0, 0);

        // wrap the next pipe
        float xBounds = cam.position.x;// - cam.viewportWidth / 2;
        for (Tube t: tubes) {
            if (Math.abs(xBounds) > (t.getTopPosition().x + t.getWidth())) {
                int x = (int)cam.viewportWidth + t.getWidth() + (int)cam.viewportWidth - TUBE_OFFSET;
                t.setPositionX(x);
                t.setRandomPositionY();
            }
        }
    }

    @Override
    public void render(SpriteBatch b) {
        b.setProjectionMatrix(cam.combined);
        b.begin();
        {
            float camOffset = cam.position.x;// - cam.viewportWidth/2;
            b.draw(background, 0, 0);
            b.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
            for (Tube tube: tubes) {
                b.draw(tube.getTopTube(), tube.getTopPosition().x + camOffset , tube.getTopPosition().y);
                b.draw(tube.getBottomTube(), tube.getBottomPosition().x + camOffset , tube.getBottomPosition().y);
                System.out.println(tube.toString());
                System.out.println(bird.toString());
                if (tube.overlaps(bird.getBounds())) {
                    System.out.println("Collision");
                }
            }
        }
        b.end();
    }

    @Override
    public void dispose() {
        bird.dispose();
        for (Tube tube: tubes) {
            tube.dispose();
        }
        background.dispose();
    }
}
