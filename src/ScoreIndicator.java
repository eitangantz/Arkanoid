import biuoop.DrawSurface;
/**
 *
 * ScoreIndicator.
 */
public class ScoreIndicator implements Sprite {
private Counter score;
private java.awt.Color c;
/**
 * constructor.
 * @param score - counter for score.
 * @param c - color.
 */
public ScoreIndicator(Counter score, java.awt.Color c) {
this.score = score;
this.c = c;
}
/**
 * @param d - surface to draw on.
 */
public void drawOn(DrawSurface d) {
d.setColor(c);
d.fillRectangle(0, 0, 800, 15);
d.setColor(java.awt.Color.WHITE);
String letter = Integer.toString(this.score.getValue());
String scores = "Score: ";
letter = scores.concat(letter);
d.drawText(350, 15, letter, 15);
}
/**
 * return.
 */
public void timePassed() {
return;
}
/**
 *
 * @param g - given game to add this sprite to.
 */
public void addToGame(GameLevel g) {
g.addSprite(this);
}
}
