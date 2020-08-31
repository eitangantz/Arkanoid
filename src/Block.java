import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import biuoop.DrawSurface;
/**
* Block class.
*/
public class Block implements Collidable, Sprite, HitNotifier {
private Rectangle rect;
private java.awt.Color c;
private int hitpoints;
private List<HitListener> hitListeners;
private Image imgforfill;
private List<Image> imagesfork = new ArrayList<Image>();
private List<Color> colorsfork = new ArrayList<Color>();
private List<Integer> integersforkcolor = new ArrayList<Integer>();
private List<Integer> integersforkimages  = new ArrayList<Integer>();
private Color stroke;
/**
* Block constructor.
* @param rect - the block rectangle.
* @param color - the color of the block.
*/
public Block(Rectangle rect, java.awt.Color color) {
this.rect = rect;
this.c = color;
this.hitpoints = 1;
this.hitListeners = new ArrayList<HitListener>();
}
/**
 *
 * @param rect - rect
 * @param hitpoints - hitpoints
 * @param color - color
 */
public Block(Rectangle rect, int hitpoints, java.awt.Color color) {
this.rect = rect;
this.hitpoints = hitpoints;
this.c = color;
}
/**
 *
 * @param rect - rect
 * @param hitpoints - hitpoints
 * @param img - image
 * @param cfill - color for filling the block
 * @param map1 - image for filling the block
 * @param map2 - color per hitpoint
 * @param stroke - color
 */
public Block(Rectangle rect, int hitpoints, Image img, Color cfill, TreeMap<Integer, Color> map1,
TreeMap<Integer, Image> map2, Color stroke) {
this.rect = rect;
this.hitpoints = hitpoints;
this.imgforfill = img;
this.c = cfill;
this.stroke = stroke;
this.hitListeners = new ArrayList<HitListener>();
for (Map.Entry<Integer, Color> entry : map1.entrySet()) {
Integer key = entry.getKey();
Color value = entry.getValue();
this.integersforkcolor.add(key);
this.colorsfork.add(value);
}
for (Map.Entry<Integer, Image> entry : map2.entrySet()) {
Integer key = entry.getKey();
Image value = entry.getValue();
this.integersforkimages.add(key);
this.imagesfork.add(value);
}
}
/**
* getCollisionRectangle.
* @return return the rect of the block.
*/
public Rectangle getCollisionRectangle() {
return this.rect;
}
/**
* hit.
* @param collisionPoint - the collision Point with this block and a ball.
* @param currentVelocity - the current velocity of the ball.
* @param trajectory - the trajectory line.
* @param hitter - the ball that hits this block.
* @return return new velocity for the ball.
* Notify the object that we collided with it at collisionPoint with
* a given velocity.
* The return is the new velocity expected after the hit (based on
* the force the object inflicted on us).
*/
public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity, Line trajectory) {
countHits();
this.notifyHit(hitter);
/*leftupcorner*/
if ((collisionPoint.isBetween(this.rect.getup().start(), this.rect.getup().end()))
&& (collisionPoint.isBetween(this.rect.getleft().start(), this.rect.getleft().end()))
&& (trajectory.start().getX() <= collisionPoint.getX()) && (trajectory.start().getY() <= collisionPoint.getY())) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setxspeed(-currentVelocity.getxspeed());
return currentVelocity;
}
if ((collisionPoint.isBetween(this.rect.getup().start(), this.rect.getup().end()))
&& (collisionPoint.isBetween(this.rect.getleft().start(), this.rect.getleft().end()))
&& (trajectory.start().getX() <= collisionPoint.getX()) && (trajectory.start().getY() >= collisionPoint.getY())) {
currentVelocity.setxspeed(-currentVelocity.getxspeed());
return currentVelocity;
}
if ((collisionPoint.isBetween(this.rect.getup().start(), this.rect.getup().end()))
&& (collisionPoint.isBetween(this.rect.getleft().start(), this.rect.getleft().end()))
&& (trajectory.start().getX() >= collisionPoint.getX()) && (trajectory.start().getY() <= collisionPoint.getY())) {
currentVelocity.setdy(-currentVelocity.getdy());
return currentVelocity;
}
/*rightupcorner*/
if ((collisionPoint.isBetween(this.rect.getup().start(), this.rect.getup().end()))
&& (collisionPoint.isBetween(this.rect.getright().start(), this.rect.getright().end()))
&& (trajectory.start().getX() <= collisionPoint.getX()) && (trajectory.start().getY() <= collisionPoint.getY())) {
currentVelocity.setdy(-currentVelocity.getdy());
return currentVelocity;
}
if ((collisionPoint.isBetween(this.rect.getup().start(), this.rect.getup().end()))
&& (collisionPoint.isBetween(this.rect.getright().start(), this.rect.getright().end()))
&& (trajectory.start().getX() >= collisionPoint.getX()) && (trajectory.start().getY() >= collisionPoint.getY())) {
currentVelocity.setxspeed(-currentVelocity.getxspeed());
return currentVelocity;
}
if ((collisionPoint.isBetween(this.rect.getup().start(), this.rect.getup().end()))
&& (collisionPoint.isBetween(this.rect.getright().start(), this.rect.getright().end()))
&& (trajectory.start().getX() >= collisionPoint.getX()) && (trajectory.start().getY() <= collisionPoint.getY())) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setxspeed(-currentVelocity.getxspeed());
return currentVelocity;
}
/*rightdowncorner*/
if ((collisionPoint.isBetween(this.rect.getdown().start(), this.rect.getdown().end()))
&& (collisionPoint.isBetween(this.rect.getright().start(), this.rect.getright().end()))
&& (trajectory.start().getX() >= collisionPoint.getX()) && (trajectory.start().getY() >= collisionPoint.getY())) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setxspeed(-currentVelocity.getxspeed());
return currentVelocity;
}
if ((collisionPoint.isBetween(this.rect.getdown().start(), this.rect.getdown().end()))
&& (collisionPoint.isBetween(this.rect.getright().start(), this.rect.getright().end()))
&& (trajectory.start().getX() >= collisionPoint.getX()) && (trajectory.start().getY() <= collisionPoint.getY())) {
currentVelocity.setxspeed(-currentVelocity.getxspeed());
return currentVelocity;
}
if ((collisionPoint.isBetween(this.rect.getdown().start(), this.rect.getdown().end()))
&& (collisionPoint.isBetween(this.rect.getright().start(), this.rect.getright().end()))
&& (trajectory.start().getX() <= collisionPoint.getX()) && (trajectory.start().getY() >= collisionPoint.getY())) {
currentVelocity.setdy(-currentVelocity.getdy());
return currentVelocity;
}
/*leftdowncorner*/
if ((collisionPoint.isBetween(this.rect.getdown().start(), this.rect.getdown().end()))
&& (collisionPoint.isBetween(this.rect.getleft().start(), this.rect.getleft().end()))
&& (trajectory.start().getX() <= collisionPoint.getX()) && (trajectory.start().getY() >= collisionPoint.getY())) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setxspeed(-currentVelocity.getxspeed());
return currentVelocity;
}
if ((collisionPoint.isBetween(this.rect.getdown().start(), this.rect.getdown().end()))
&& (collisionPoint.isBetween(this.rect.getleft().start(), this.rect.getleft().end()))
&& (trajectory.start().getX() <= collisionPoint.getX()) && (trajectory.start().getY() <= collisionPoint.getY())) {
currentVelocity.setxspeed(-currentVelocity.getxspeed());
return currentVelocity;
}
if ((collisionPoint.isBetween(this.rect.getdown().start(), this.rect.getdown().end()))
&& (collisionPoint.isBetween(this.rect.getleft().start(), this.rect.getleft().end()))
&& (trajectory.start().getX() >= collisionPoint.getX()) && (trajectory.start().getY() >= collisionPoint.getY())) {
currentVelocity.setdy(-currentVelocity.getdy());
return currentVelocity;
}
if (collisionPoint.isBetween(this.rect.getup().start(), this.rect.getup().end())) {
currentVelocity.setdy(-currentVelocity.getdy());
return currentVelocity;
}
if (collisionPoint.isBetween(this.rect.getdown().start(), this.rect.getdown().end())) {
currentVelocity.setdy(-currentVelocity.getdy());
return currentVelocity;
}
if (collisionPoint.isBetween(this.rect.getleft().start(), this.rect.getleft().end())) {
currentVelocity.setxspeed(-currentVelocity.getxspeed());
return currentVelocity;
}
if (collisionPoint.isBetween(this.rect.getright().start(), this.rect.getright().end())) {
currentVelocity.setxspeed(-currentVelocity.getxspeed());
return currentVelocity;
}
return currentVelocity;
}
/**
* drawOn.
* @param surface - the surface which the block draw on.
*/
public void drawOn(DrawSurface surface) {
int flag1 = 0;
int flag2 = 0;
if (!this.colorsfork.isEmpty()) {
for (int i = 0; i < this.integersforkcolor.size(); i++) {
if (this.hitpoints == this.integersforkcolor.get(i)) {
surface.setColor(this.colorsfork.get(i));
surface.fillRectangle((int) this.rect.getUpperLeft().getX(),
(int) this.rect.getUpperLeft().getY(), (int) this.rect.getWidth(), (int) this.rect.getHeight());
flag1++;
}
}
}
if (!this.imagesfork.isEmpty()) {
for (int i = 0; i < this.integersforkimages.size(); i++) {
if (this.hitpoints == this.integersforkimages.get(i)) {
surface.drawImage((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(), this.imagesfork.get(i));
flag2++;
}
}
}
if ((flag1 == 0) && (flag2 == 0) && (this.c != null)) {
surface.setColor(c);
surface.fillRectangle((int) this.rect.getUpperLeft().getX(),
(int) this.rect.getUpperLeft().getY(), (int) this.rect.getWidth(), (int) this.rect.getHeight());
}
if ((flag1 == 0) && (flag2 == 0) && (this.imgforfill != null)) {
surface.drawImage((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(), this.imgforfill);
}
if (this.stroke != null) {
surface.setColor(this.stroke);
surface.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
(int) this.rect.getWidth(), (int) this.rect.getHeight());
}
surface.setColor(java.awt.Color.WHITE);
double midblockx = this.rect.getUpperLeft().getX() + (this.rect.getWidth() / 2);
double midblocky = this.rect.getUpperLeft().getY() + (this.rect.getHeight() / 2);
String letter = Integer.toString(this.hitpoints);
if (this.hitpoints == 0) {
letter = "X";
}
if (this.hitpoints == -1) {
return;
}
}
/**
* timePassed.
* notify time passed.
*/
public void timePassed() {
return;
}
/**
* addToGame.
* @param g - the game which the block is add to.
*/
public void addToGame(GameLevel g) {
g.addCollidable(this);
g.addSprite(this);
}
/**
* removeFromGame.
* @param g - the game which the block is remove from.
*/
public void removeFromGame(GameLevel g) {
g.removeCollidable(this);
g.removeSprite(this);
}
/**
* addHitListener.
* Add hl as a listener to hit events.
* @param hl - the listener.
*/
public void addHitListener(HitListener hl) {
this.hitListeners.add(hl);
}
/**
* addHitListener.
* Remove hl from the list of listeners to hit events.
* @param hl - the listener.
*/
public void removeHitListener(HitListener hl) {
this.hitListeners.remove(hl);
}
/**
 * @param hitter - the ball that hits this block.
 * Make a copy of the hitListeners before iterating over them.
 * Notify all listeners about a hit event.
 */
private void notifyHit(Ball hitter) {
List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
for (HitListener hl : listeners) {
hl.hitEvent(this, hitter);
}
}
/**
 *
 * @return number of hitpoints for destroying this block.
 */
public int getHitPoints() {
return this.hitpoints;
}
/**
* countHits.
* counts hits with the block.
*/
public void countHits() {
if (this.hitpoints <= 0) {
return;
}
this.hitpoints = this.hitpoints - 1;
}
/**
* setHitCount.
* sets the value of hitpoints.
* @param count - starting number of hitpoints.
*/
public void setHitCount(int count) {
this.hitpoints = count;
}
/**
 *
 * @return width
 */
public int getWidth() {
return (int) this.rect.getWidth();
}
}
