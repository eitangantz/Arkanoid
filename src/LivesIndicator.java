import biuoop.DrawSurface;
/**
 *
 * LivesIndicator.
 */
public class LivesIndicator implements Sprite {
private Counter lives;
/**
 * constructor.
 * @param lives - the counter.
 */
public LivesIndicator(Counter lives) {
this.lives = lives;
}
/**
 * @param d - the surface we draw on.
 */
public void drawOn(DrawSurface d) {
d.setColor(java.awt.Color.WHITE);
String letter = Integer.toString(this.lives.getValue());
String score = "lives: ";
letter = score.concat(letter);
d.drawText(50, 15, letter, 15);
}
/**
 * return.
 */
public void timePassed() {
return;
}
/**
 * @param g - add the sprite to a given game.
 */
public void addToGame(GameLevel g) {
g.addSprite(this);
}
}
