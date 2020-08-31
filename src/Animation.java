import biuoop.DrawSurface;
/**
 *
 */
public interface Animation {
/**
 *
 * @param d - drawface to draw on.
 */
void doOneFrame(DrawSurface d);
/**
 *
 * @return true or false
 */
boolean shouldStop();
}
