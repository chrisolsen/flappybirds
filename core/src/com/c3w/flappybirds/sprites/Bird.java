package com.c3w.flappybirds.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Bird extends Image {
    private static final int GRAVITY = -15;
    private static final int JUMP = 5;
    private static final float COLLISION_CUSHION = 10f;

    private Vector2 velocity;

    public Bird(int x, int y, int vel) {
        super(new Texture("bird.png"));
        this.setPosition(x, y);
        velocity = new Vector2(vel, 0);
    }

    public void update(float dt) {
        if (this.getY() > 0) {
            velocity.add(0, GRAVITY * dt);
            this.setPosition(this.getX() + velocity.x * dt, this.getY() + velocity.y);
        } else {
            this.setY(0);
        }
    }

    public void reset() {
        velocity = new Vector2(velocity.x, 0);
    }

    public Rectangle getBounds(boolean useCushion) {
        float cushion = useCushion ? COLLISION_CUSHION : 0;
        return new Rectangle(
                this.getX() + cushion/2,
                this.getY() + cushion/2,
                this.getWidth() - cushion,
                this.getHeight() - cushion);
    }

    public boolean overlaps(Rectangle r, boolean useCushion) {
        return getBounds(useCushion).overlaps(r);
    }

    public boolean overlaps(Image img, boolean useCushion) {
        Rectangle r = new Rectangle(img.getX(), img.getY(), img.getWidth(), img.getHeight());
        return getBounds(useCushion).overlaps(r);
    }

    public void jump() {
        velocity.y = JUMP;
    }

    public String toString() {
        return "Bird: " + getBounds(false).toString();
    }
}
