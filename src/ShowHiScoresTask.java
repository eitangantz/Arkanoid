/**
 *
 *
 *
 */
public class ShowHiScoresTask implements Task<Void> {
private AnimationRunner runner;
private Animation highScoresAnimation;
/**
 *
 * @param runner - runner
 * @param highScoresAnimation - Animation
 */
public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
this.runner = runner;
this.highScoresAnimation = highScoresAnimation;
}
/**
 * @return null
 */
public Void run() {
this.runner.run(this.highScoresAnimation);
return null;
}
}
