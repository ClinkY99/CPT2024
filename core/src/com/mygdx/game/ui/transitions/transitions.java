package com.mygdx.game.ui.transitions;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.io.IOException;

/**
 * Contains all transition classes as well as the function to be called on load
 */
public interface transitions {

    /**
     * Function to be called on completion of fade out
     * @return loads screen class.
     */
    Screen load() throws IOException;

    /**
     * default time transition class, returns time remaining out of 1
     */
    class timeTransition{
        float duration;
        float timeElapsed;

        timeTransition(float duration){
            this.duration = duration;
            timeElapsed = 0;
        }

        float get(){
            return timeElapsed/duration;
        }

        void update(float delta){
            if(timeElapsed < duration) {
                timeElapsed += delta;
            }

            if(timeElapsed >= duration){
                timeElapsed = duration;
            }
        }

        boolean isFinished(){
            return timeElapsed == duration;
        }

    }

    /**
     * base affect for the transition.
     */
    class transitionEffect{
        float duration;
        timeTransition transition;

        transitionEffect(float time){
            this.duration = time;
            transition = new timeTransition(duration);
        }

        public float getAlpha() {return transition.get();}

        public float getTime() {return duration;}

        void update(float delta){
            transition.update(delta);
        }

        public void render(float delta){
            update(delta);
        }

        public boolean isFinished(){return transition.isFinished();}
    }

    /**
     * fade in function for stage
     */
    class fadeInTransitionStage extends transitionEffect{

        Stage current;

        public fadeInTransitionStage(float time, Stage current) {
            super(time);
            this.current = current;
        }

        @Override
        public void render(float delta) {
            super.render(delta);
            current.getRoot().getColor().a = getAlpha();
        }
    }

    /**
     * fade out function for stage
     */
    class fadeOutTransitionStage extends transitionEffect{

        Stage current;

        public fadeOutTransitionStage(float time, Stage current) {
            super(time);
            this.current = current;
        }

        @Override
        public void render(float delta) {
            super.render(delta);
            current.getRoot().getColor().a = 1.0f-getAlpha();
        }
    }

    /**
     * fade in function for screen
     */
    class fadeInTransitionScreen extends transitionEffect{

        Screen current;
        Image image;

        public fadeInTransitionScreen(float time, Screen current, Image image) {
            super(time);
            this.current = current;

            this.image = image;
        }

        @Override
        public void render(float delta) {
            super.render(delta);
            current.render(delta);

            image.getColor().a = 1.0f-getAlpha();

        }
    }

    /**
     * fade out function for screen
     */
    class fadeOutTransitionScreen extends transitionEffect{

        Screen current;
        Image image;

        public fadeOutTransitionScreen(float time, Screen current, Image image) {
            super(time);
            this.current = current;

            this.image = image;
        }

        @Override
        public void render(float delta) {
            super.render(delta);
            current.render(delta);

            image.getColor().a = getAlpha();
        }


    }


}
