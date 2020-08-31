import java.awt.Color;
import java.awt.Image;
import biuoop.DrawSurface;
/**
 *
 *
 */
public class BackgroundFromImage implements Sprite {
private Image img;
private Color c;
/**
 *
 * @param img - image
 */
public BackgroundFromImage(Image img) {
this.img = img;
}
/**
 *
 * @param c - color
 */
public BackgroundFromImage(Color c) {
this.c = c;
}
/**
 * default constructor.
 *
 */
public BackgroundFromImage() {
}
/**
 * @param g - game
 */
public void addToGame(GameLevel g) {
g.addSprite(this);
}
/**
 * @param d - drawsurface.
 */
public void drawOn(DrawSurface d) {
if (this.img != null) {
d.drawImage(0, 0, this.img);
} else {
d.setColor(java.awt.Color.gray);
d.fillRectangle(0, 0, 800, 600);
d.setColor(this.c);
d.fillRectangle(25, 25, 750, 600);
}
}
/**
 * return.
 */
public void timePassed() {
return;
}
}
