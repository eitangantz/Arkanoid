import biuoop.DrawSurface;
import biuoop.GUI;
/**
 *
 */
public class AnimationRunner {
private GUI gui;
private int framesPerSecond;
private biuoop.Sleeper sleeper;
/**
 *
 * @param gui - gui.
 */
public AnimationRunner(GUI gui) {
this.gui = gui;
this.framesPerSecond = 60;
this.sleeper = new biuoop.Sleeper();
}
/**
 *
 * @param animation - the animation to run by the animation runner.
 */
public void run(Animation animation) {
int millisecondsPerFrame = 1000 / this.framesPerSecond;
while (!animation.shouldStop()) {
long startTime = System.currentTimeMillis(); // timing
DrawSurface d = gui.getDrawSurface();
animation.doOneFrame(d);
gui.show(d);
long usedTime = System.currentTimeMillis() - startTime;
long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
if (milliSecondLeftToSleep > 0) {
this.sleeper.sleepFor(milliSecondLeftToSleep);
}
}
}
}
