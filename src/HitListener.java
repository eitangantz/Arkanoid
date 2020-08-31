/**
 *
 * HitListener.
 */
public interface HitListener {
/**
 * This method is called whenever the beingHit object is hit.
 * @param beingHit - object being hit.
 * @param hitter - the ball that hit the object.
 */
void hitEvent(Block beingHit, Ball hitter);
}
