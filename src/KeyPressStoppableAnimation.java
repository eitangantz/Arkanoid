import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 *
 *
 */
public class KeyPressStoppableAnimation implements Animation {
private Animation ani;
private KeyboardSensor keyboard;
private String key;
private boolean isAlreadyPressed = true;
/**
 *
 * @param sensor - keyboard.
 * @param key - string for exit.
 * @param animation - animation.
 */
public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
this.ani = animation;
this.key = key;
this.keyboard = sensor;
}
/**
 * @param d - surface to draw on.
 */
public void doOneFrame(DrawSurface d) {
if (!this.keyboard.isPressed(this.key)) {
this.isAlreadyPressed = false;
}
if (!this.shouldStop()) {
this.ani.doOneFrame(d);
}
}
/**
 * @return true or false.
 */
public boolean shouldStop() {
if ((isAlreadyPressed) && (this.keyboard.isPressed(this.key))) {
return false;
}
return this.keyboard.isPressed(this.key);
}
}