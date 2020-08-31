/**
 * BlockRemover.
 */
public class BlockRemover implements HitListener {
private GameLevel gameLevel;
private Counter remainingBlocks;
/**
 *
 * @param gameLevel - reference to the game.
 * @param remainingBlocks - counter of the remaining balls.
 */
public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
this.gameLevel = gameLevel;
this.remainingBlocks = remainingBlocks;
}
/**
 * @param beingHit - the block being hit.
 * @param hitter - the ball that hits the block.
 * if the being hit block is out of hitpoints than we remove it from the game
 * else we decrease hitpoints by 1.
 */
public void hitEvent(Block beingHit, Ball hitter) {
if (beingHit.getHitPoints() == 0) {
beingHit.removeFromGame(this.gameLevel);
beingHit.removeHitListener(this);
remainingBlocks.decrease(1);
}
return;
}
/**
 *
 * @return the counter of the remaining blocks.
 */
public Counter getcounter() {
return this.remainingBlocks;
}
}
