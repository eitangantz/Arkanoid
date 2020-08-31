import biuoop.DrawSurface;
/**
 *
 *
 */
public class CountdownAnimation implements Animation {
private int countFrom;
private SpriteCollection spriteCollection;
private boolean stop;
private biuoop.Sleeper sleeper;
private long sleep;
private int counter;
/**
 *
 * @param numOfSeconds - time in seconds for countdown animation to run.
 * @param countFrom - counting from this number to 1.
 * @param gameScreen - all the sprites.
 */
public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
this.countFrom = countFrom;
this.spriteCollection = gameScreen;
this.stop = false;
this.sleeper = new biuoop.Sleeper();
this.sleep = (long) ((1000 * numOfSeconds) / countFrom);
this.counter = countFrom;
}
/**
 * @param d - surface to draw on.
 */
public void doOneFrame(DrawSurface d) {
if (this.countFrom != this.counter) {
this.sleeper.sleepFor(this.sleep);
}
this.spriteCollection.drawAllOn(d);
Integer count = new Integer(this.counter);
d.setColor(java.awt.Color.red);
d.drawText(d.getWidth() / 2, d.getHeight() / 2, count.toString(), 32);
if (this.counter == 0) {
this.stop = true;
return;
}
this.counter -= 1;
}
/**
 * @return true for stop the animation or false to continue.
 */
public boolean shouldStop() {
return this.stop;
}
}
