import biuoop.DrawSurface;
/**
 *
 *
 */
public class NameLevel implements Sprite {
private String name;
/**
 *
 * @param name - level name.
 */
public NameLevel(String name) {
this.name = name;
}
/**
 * @param d - surface to draw on.
 */
public void drawOn(DrawSurface d) {
d.setColor(java.awt.Color.white);
d.drawText(550, 15, this.name, 15);
}
/**
 *
 */
public void timePassed() {
return;
}
/**
 * @param g - given gamelevel for adding to this sprite.
 */
public void addToGame(GameLevel g) {
g.addSprite(this);
}
}
