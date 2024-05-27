/*
 * This class represents a draggable object in a puzzle game.
 * It extends the Actor class from LibGDX for rendering purposes.
 */

package com.mygdx.game.Game_Elements.Puzzle_Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class DragDropObject extends Actor {
    Sprite spriteThatGoesOverRectangle; // Sprite that indicates the object over the rectangle
    public Rectangle actualRectangle; // Rectangle representing the object's current position
    float changeInYPosition; // Change in Y position of the object
    public int data; // Custom data associated with the object
    boolean inPlace; // Flag indicating if the object is in its correct position
    public Texture image; // Texture for the object
    Rectangle bounds; // Bounds of the object

    // Constructor for DragDropObject class
    public DragDropObject(Texture img) {
        setWidth(img.getWidth()); // Set width of the object
        setHeight(img.getHeight()); // Set height of the object
        image = img; // Assign the texture
        // Add drag listener to the object
        addListener(new DragListener() {
            @Override
            public void drag(InputEvent event, float x, float y, int pointer) {
                if (!inPlace) { // If the object is not in place
                    moveBy(x - getWidth() / 2, y - getHeight() / 2); // Move the object
                }
            }
        });
        DragDropObject object = this;

        // Add input listener to bring the object to front on touch down
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                object.toFront(); // Bring the object to front
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        setPosition(500,500); // Set initial position of the object

        bounds = new Rectangle((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight()); // Initialize bounds
    }

    // Method to draw the object
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha); // Call superclass method to draw
        batch.draw(image, getX(), getY(), getWidth(), getHeight()); // Draw the object
        this.bounds.set(getX(), getY(), getWidth(), getHeight()); // Update bounds
    }
}
