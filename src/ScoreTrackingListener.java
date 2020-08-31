/**
 *
 * ScoreTrackingListener.
 */
public class ScoreTrackingListener implements HitListener {
private Counter currentScore;
/**
 * constructor.
 * @param scoreCounter - counter for the score.
 */
public ScoreTrackingListener(Counter scoreCounter) {
this.currentScore = scoreCounter;
}
/**
 * This method is called whenever the beingHit object is hit.
 * @param beingHit - object being hit.
 * @param hitter - the ball that hit the object.
 */
public void hitEvent(Block beingHit, Ball hitter) {
if (beingHit.getHitPoints() == 0) {
this.currentScore.increase(10);
beingHit.removeHitListener(this);
return;
}
this.currentScore.increase(5);
}
}
