/*
 * This class represents a draggable goal element in our game.
 * It extends the Actor class from LibGDX for rendering purposes.
 */

package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class DragDropGoal extends Actor {
    Texture img; // Texture for the goal element
    public int data; // Custom data associated with the goal

    public boolean touchingCorrectTile; // Flag indicating if the goal is touching the correct tile
    public Rectangle bounds; // Bounds of the goal element

    // Constructor for DragDropGoal class
    public DragDropGoal(Texture image) {
        img = image;
        bounds = new Rectangle(getX(), getY(), getWidth(), getHeight()); // Initialize bounds
        setWidth(image.getWidth()); // Set width of the goal element
        setHeight(image.getHeight()); // Set height of the goal element
    }

    // Method to draw the goal element
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(img, getX(), getY(), getWidth(), getHeight()); // Draw the goal element
        this.bounds.set(getX(), getY(), getWidth(), getHeight()); // Update bounds
    }
}

