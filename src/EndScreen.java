import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 *
 *
 */
public class EndScreen implements Animation {
private KeyboardSensor keyboard;
private boolean stop;
private Counter score;
private Counter lives;
/**
 *
 * @param k - keyboard.
 * @param score - the counter of the score.
 * @param lives - the counter of the lives.
 */
public EndScreen(KeyboardSensor k, Counter score, Counter lives) {
this.keyboard = k;
this.score = score;
this.lives = lives;
this.stop = false;
}
/**
 * @param d - surface to draw on.
 */
public void doOneFrame(DrawSurface d) {
Integer sc = new Integer(this.score.getValue());
if (this.lives.getValue() == 0) {
d.drawText(10, d.getHeight() / 2, "Game Over. Your score is ".concat(sc.toString()) , 32);
}
if (this.lives.getValue() > 0) {
d.drawText(10, d.getHeight() / 2, "You Win! Your score is ".concat(sc.toString()) , 32);
}
}
/**
 * @return true for stopping the animation false elsewise.
 */
public boolean shouldStop() { return this.stop; }
}
