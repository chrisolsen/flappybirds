package com.c3w.flappybirds.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    private static final int FLUCTUATION = 130;
    private static final int TUBE_GAP = 100;
    private static final int LOWEST_OPENING = 120;

    private float posX;
    private Texture topTube, bottomTube;
    private Vector2 topPosition, bottomPosition;
    private Rectangle topBounds, bottomBounds;

    public Vector2 getTopPosition() {
        return topPosition;
    }

    public Vector2 getBottomPosition() {
        return bottomPosition;
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }


    public Tube(float x) {
        this.posX = x;

        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");

        topPosition = new Vector2();
        bottomPosition = new Vector2();

        topBounds = new Rectangle(0, 0, topTube.getWidth(), topTube.getHeight());
        bottomBounds = new Rectangle(0,0, bottomTube.getWidth(), bottomTube.getHeight());

        setPositionX(x);
        setRandomPositionY();
    }

    public void setPositionX(float x) {
        topPosition.x += x;
        bottomPosition.x += x;

        topBounds.x = topPosition.x;
        bottomBounds.x = bottomPosition.x;
    }

    public void setRandomPositionY() {
        Random r = new Random();
        topPosition.y = r.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING;
        bottomPosition.y = topPosition.y - TUBE_GAP - bottomTube.getHeight();

        topBounds.y = topPosition.y;
        bottomBounds.y = bottomPosition.y;
    }

    public int getWidth() {
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
