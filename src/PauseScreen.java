import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 *
 *
 */
public class PauseScreen implements Animation {
private KeyboardSensor keyboard;
private boolean stop;
/**
 *
 * @param k - keyboard.
 */
public PauseScreen(KeyboardSensor k) {
this.keyboard = k;
this.stop = false;
}
/**
 * @param d - surface to draw on.
 */
public void doOneFrame(DrawSurface d) {
d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
}
/**
 * @return true for stopping the animation, false, otherwise.
 */
public boolean shouldStop() { return this.stop; }
}
