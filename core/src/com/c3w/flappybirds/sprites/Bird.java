package com.c3w.flappybirds.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Bird extends Image {
    private static final int GRAVITY = -15;
    private static final int JUMP = 5;

    private Vector2 velocity;

    public Bird(int x, int y, int vel) {
        super(new Texture("bird.png"));
        this.setPosition(x, y);
        velocity = new Vector2(vel, 0);
    }

    public void update(float dt) {
        if (this.getY() > 0) {
            velocity.add(0, GRAVITY * dt);
        }
        this.setPosition(this.getX() + velocity.x * dt, this.getY() + velocity.y);

        if (this.getY() < 0) {
            this.setY(0);
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void jump() {
        velocity.y = JUMP;
    }

    public String toString() {
        return "Bird: " + getBounds().toString();
    }
}
