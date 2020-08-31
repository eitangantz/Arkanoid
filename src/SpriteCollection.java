import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
/**
* class SpriteCollection.
*/
public class SpriteCollection {
private List<Sprite> sprites;
/**
* SpriteCollection.
* call timePassed() on all sprites.
*/
public SpriteCollection() {
this.sprites = new ArrayList<Sprite>();
}
/**
* notifyAllTimePassed.
* @param s - sprite to add to sprites.
* call timePassed() on all sprites.
*/
public void addSprite(Sprite s) {
this.sprites.add(s);
}
/**
 * @param s - the sprite to remove from the sprites.
 */
public void removeSprite(Sprite s) {
this.sprites.remove(s);
}
/**
 * @return this sprites.
 */
public List<Sprite> getsprites() {
return this.sprites;
}
/**
* notifyAllTimePassed.
* call timePassed() on all sprites.
*/
public void notifyAllTimePassed() {
for (int i = 0; i < this.sprites.size(); i++) {
this.sprites.get(i).timePassed();
}
}
/**
* draAllwOn.
* @param d - the surface which the Sprites draw on.
* call drawOn(d) on all sprites.
*/
public void drawAllOn(DrawSurface d) {
for (int i = 0; i < this.sprites.size(); i++) {
this.sprites.get(i).drawOn(d);
}
}
}
