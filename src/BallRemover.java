/**
 *
 * BallRemover.
 */
public class BallRemover implements HitListener {
private GameLevel gameLevel;
private Counter remainingBalls;
/**
 * @param gameLevel - reference to the game.
 * @param remainingBalls - counter of the remaining balls.
 */
public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
this.gameLevel = gameLevel;
this.remainingBalls = remainingBalls;
}
/**
 * @param beingHit - the block being hit.
 * @param hitter - the ball that hits the block.
 */
public void hitEvent(Block beingHit, Ball hitter) {
hitter.removeFromGame(this.gameLevel);
remainingBalls.decrease(1);
return;
}
}
