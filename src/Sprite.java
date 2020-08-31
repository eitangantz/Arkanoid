import biuoop.DrawSurface;
/**
* Sprite.
*/
public interface Sprite {
/**
* drawOn.
* @param d - the drawsurface.
* draw the sprite to the screen.
*/
void drawOn(DrawSurface d);
/**
* drawOn.
* notify the sprite that time has passed.
*/
void timePassed();
/**
 *
 * @param g - given game level.
 */
void addToGame(GameLevel g);
}
