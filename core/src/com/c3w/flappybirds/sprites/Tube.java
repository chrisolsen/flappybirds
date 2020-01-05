package com.c3w.flappybirds.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.Random;

public class Tube extends Image {
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;

    private Texture topTube, bottomTube;
    private Rectangle topBounds, bottomBounds;

    public Rectangle getTopBounds() {
        return topBounds;
    }

    public Rectangle getBottomBounds() {
        return bottomBounds;
    }

    public Tube(float x) {
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");

        topBounds = new Rectangle(0, 0, topTube.getWidth(), topTube.getHeight());
        bottomBounds = new Rectangle(0,0, bottomTube.getWidth(), bottomTube.getHeight());

        setPositionX(x);
        setRandomPositionY();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(topTube, this.topBounds.x, this.topBounds.y);
        batch.draw(bottomTube, this.bottomBounds.x, this.bottomBounds.y);
    }

    public void setPositionX(float x) {
        topBounds.x = x;
        bottomBounds.x = x;
    }

    public void setRandomPositionY() {
        Random r = new Random();
        topBounds.y = r.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING;
        bottomBounds.y = topBounds.y - TUBE_GAP - bottomTube.getHeight();
    }

    public float getWidth() {
        return topTube.getWidth();
    }

    public boolean overlaps(Rectangle r) {
        return topBounds.overlaps(r) || bottomBounds.overlaps(r);
    }

    @Override
    public String toString() {
        return String.format("Tube: top:%s bottom:%s", topBounds.toString(), bottomBounds.toString());
    }

    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}
