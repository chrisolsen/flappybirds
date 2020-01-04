package com.c3w.flappybirds.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int Gravity = -15;

    private Vector3 position;
    private Vector3 velocity;
    private Texture bird;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bird = new Texture("bird.png");
    }

    public void update(float dt) {
        velocity.add(0, Gravity*dt, 0);
        position.add(0, velocity.y, 0);

        if (position.y < 0) position.y = 0;
    }

    public Texture getBird() {
        return bird;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.position.x, this.position.y, this.bird.getWidth(), this.bird.getHeight());
    }

    public void jump() {
        velocity.y = 5;
    }

    public String toString() {
        return String.format("Bird: %s %dx%d", position.toString(), bird.getWidth(), bird.getHeight());
    }

    public void dispose() {
        bird.dispose();
    }
}
