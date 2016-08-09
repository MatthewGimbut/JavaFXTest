package sample;

import javafx.scene.image.Image;

/**
 * Created by matthew_gimbut on 8/8/2016.
 */
public class AnimatedImage {
    public Image[] frames;
    public double duration;

    public AnimatedImage() { }

    public Image getFrame(double time) {
        int index = (int)((time % (frames.length * duration)) / duration);
        return this.frames[index];
    }
}
