/**
 *
 * PrintingHitListener.
 */
public class PrintingHitListener implements HitListener {
/**
* This method is called whenever the beingHit object is hit.
* @param beingHit - object being hit.
* @param hitter - the ball that hit the object.
*/
public void hitEvent(Block beingHit, Ball hitter) {
System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
}
}
