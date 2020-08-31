import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
* Paddle class.
*/
public class Paddle implements Sprite, Collidable {
private biuoop.KeyboardSensor keyboard;
private Rectangle rect;
private java.awt.Color c;
private int speed;
private Point upperleft;
/**
* Paddle constructor.
* @param keyboard - the keyboard for moving the paddle.
* @param upperleft - upper-left point of the paddle.
*/
public Paddle(KeyboardSensor keyboard, Point upperleft) {
this.upperleft = new Point(upperleft);
this.rect = new Rectangle(this.upperleft, 100, 10);
this.c = java.awt.Color.yellow;
this.keyboard  = keyboard;
this.speed = 10;
}
/**
* moveLeft.
* takes the upper left point of the rectangle of the paddle and reduce its x cordinate
* by the speed of the paddle, if the upper left is smaller than 25 then the x cordinate of upperleft initialize to 25
* for staying at the game surface.
*/
public void moveLeft() {
if (this.rect.getUpperLeft().getX() - this.speed >= 25) {
Point newUpper = new Point(this.rect.getUpperLeft().getX() - this.speed, this.rect.getUpperLeft().getY());
this.rect = new Rectangle(newUpper, this.rect.getWidth(), this.rect.getHeight());
} else { Point newUpper = new Point(25, this.rect.getUpperLeft().getY());
this.rect = new Rectangle(newUpper, this.rect.getWidth(), this.rect.getHeight());
}
}
/**
* moveRight.
* takes the upper left point of the rectangle of the paddle and increase its x cordinate
* by the speed of the paddle, if the upper left is bigger than 775then the x cordinate of upperleft initialize to 775
* for staying at the game surface.
*/
public void moveRight() {
if (this.rect.getup().end().getX() + this.speed <= 775) {
Point newUpper = new Point(this.rect.getUpperLeft().getX() + this.speed, this.rect.getUpperLeft().getY());
this.rect = new Rectangle(newUpper, this.rect.getWidth(), this.rect.getHeight());
} else {
Point newUpper = new Point(775 - this.rect.getWidth(), this.rect.getUpperLeft().getY());
this.rect = new Rectangle(newUpper, this.rect.getWidth(), this.rect.getHeight());
}
}
/**
* timePassed.
* notify that time passed and control the movment by the keyboard.
*/
public void timePassed() {
if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
moveLeft();
}
if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
moveRight();
}
}
/**
* drawOn.
* fill the paddle.
* @param d - the surface we draw on.
*/
public void drawOn(DrawSurface d) {
d.setColor(c);
d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
(int) this.rect.getWidth(), (int) this.rect.getHeight());
d.setColor(java.awt.Color.BLACK);
d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
(int) this.rect.getWidth(), (int) this.rect.getHeight());
}
/**
* getCollisionRectangle.
* @return return the rectangle of the paddle.
*/
public Rectangle getCollisionRectangle() {
return this.rect;
}
/**
* getCollisionRectangle.
* @param collisionPoint - the point where the collision occurs
* @param currentVelocity - the Velocity of the ball before collsion.
* @param trajectory - the line that intersects with the paddle.
* @param hitter - the ball that hit the paddle.
* @return return the new velocity for the ball.
*/
public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity, Line trajectory) {
Point endOfSection1 = new Point(this.rect.getUpperLeft().getX()
+ (this.rect.getWidth() / 5), this.rect.getUpperLeft().getY());
Point endOfSection2 = new Point(this.rect.getUpperLeft().getX()
+ ((this.rect.getWidth() * 2) / 5), this.rect.getUpperLeft().getY());
Point endOfSection3 = new Point(this.rect.getUpperLeft().getX()
+ ((this.rect.getWidth() * 3) / 5), this.rect.getUpperLeft().getY());
Point endOfSection4 = new Point(this.rect.getUpperLeft().getX()
+ ((this.rect.getWidth() * 4) / 5), this.rect.getUpperLeft().getY());
Point endOfSection5 = new Point(this.rect.getUpperLeft().getX()
+ ((this.rect.getWidth() * 5) / 5), this.rect.getUpperLeft().getY());
if (collisionPoint.isBetween(this.rect.getUpperLeft(), endOfSection1)
&& (trajectory.start().getX() >= collisionPoint.getX())
&& (currentVelocity.getxspeed() > 0)) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setdx(300);
return currentVelocity;
}
if (collisionPoint.isBetween(this.rect.getUpperLeft(), endOfSection1)
&& (trajectory.start().getX() >= collisionPoint.getX())
&& (currentVelocity.getxspeed() < 0)) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setxspeed(-currentVelocity.getxspeed());
currentVelocity.setdx(300);
return currentVelocity;
}
if (collisionPoint.isBetween(this.rect.getUpperLeft(), endOfSection1)
&& (trajectory.start().getX() <= collisionPoint.getX())
&& (currentVelocity.getxspeed() < 0)) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setxspeed(-currentVelocity.getxspeed());
currentVelocity.setdx(300);
return currentVelocity;
}
if (collisionPoint.isBetween(this.rect.getUpperLeft(), endOfSection1)
&& (trajectory.start().getX() <= collisionPoint.getX())
&& (currentVelocity.getxspeed() > 0)) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setdx(300);
return currentVelocity;
}
if (collisionPoint.isBetween(endOfSection1, endOfSection2)
&& (trajectory.start().getX() >= collisionPoint.getX())
&& (currentVelocity.getxspeed() > 0)) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setdx(330);
return currentVelocity;
}
if (collisionPoint.isBetween(endOfSection1, endOfSection2)
&& (trajectory.start().getX() >= collisionPoint.getX())
&& (currentVelocity.getxspeed() < 0)) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setxspeed(-currentVelocity.getxspeed());
currentVelocity.setdx(330);
return currentVelocity;
}
if (collisionPoint.isBetween(endOfSection1, endOfSection2)
&& (trajectory.start().getX() <= collisionPoint.getX())
&& (currentVelocity.getxspeed() < 0)) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setxspeed(-currentVelocity.getxspeed());
currentVelocity.setdx(330);
return currentVelocity;
}
if (collisionPoint.isBetween(endOfSection1, endOfSection2)
&& (trajectory.start().getX() <= collisionPoint.getX())
&& (currentVelocity.getxspeed() > 0)) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setdx(330);
return currentVelocity;
}
if (collisionPoint.isBetween(endOfSection2, endOfSection3)) {
currentVelocity.setdy(-currentVelocity.getdy());
return currentVelocity;
}
if (collisionPoint.isBetween(endOfSection3, endOfSection4)
&& (trajectory.start().getX() >= collisionPoint.getX())
&& (currentVelocity.getxspeed() < 0)) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setxspeed(-currentVelocity.getxspeed());
currentVelocity.setdx(30);
return currentVelocity;
}
if (collisionPoint.isBetween(endOfSection3, endOfSection4)
&& (trajectory.start().getX() >= collisionPoint.getX())
&& (currentVelocity.getxspeed() > 0)) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setdx(30);
return currentVelocity;
}
if (collisionPoint.isBetween(endOfSection3, endOfSection4)
&& (trajectory.start().getX() <= collisionPoint.getX())
&& (currentVelocity.getxspeed() > 0)) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setdx(30);
return currentVelocity;
}
if (collisionPoint.isBetween(endOfSection3, endOfSection4)
&& (trajectory.start().getX() <= collisionPoint.getX())
&& (currentVelocity.getxspeed() < 0)) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setxspeed(-currentVelocity.getxspeed());
currentVelocity.setdx(30);
return currentVelocity;
}
if (collisionPoint.isBetween(endOfSection4, endOfSection5)
&& (trajectory.start().getX() >= collisionPoint.getX())
&& (currentVelocity.getxspeed() < 0)) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setxspeed(-currentVelocity.getxspeed());
currentVelocity.setdx(60);
return currentVelocity;
}
if (collisionPoint.isBetween(endOfSection4, endOfSection5)
&& (trajectory.start().getX() >= collisionPoint.getX())
&& (currentVelocity.getxspeed() > 0)) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setdx(60);
return currentVelocity;
}
if (collisionPoint.isBetween(endOfSection4, endOfSection5)
&& (trajectory.start().getX() <= collisionPoint.getX())
&& (currentVelocity.getxspeed() > 0)) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setdx(60);
return currentVelocity;
}
if (collisionPoint.isBetween(endOfSection4, endOfSection5)
&& (trajectory.start().getX() <= collisionPoint.getX())
&& (currentVelocity.getxspeed() < 0)) {
currentVelocity.setdy(-currentVelocity.getdy());
currentVelocity.setxspeed(-currentVelocity.getxspeed());
currentVelocity.setdx(60);
return currentVelocity;
}
if (collisionPoint.isBetween(endOfSection5, this.rect.getdown().end())) {
currentVelocity.setxspeed(-currentVelocity.getxspeed());
}
if (collisionPoint.isBetween(this.rect.getUpperLeft(), this.rect.getdown().start())) {
currentVelocity.setxspeed(-currentVelocity.getxspeed());
}
return currentVelocity;
}
/**
* addToGame.
* @param g - given game.
* add the paddle to given game.
*/
public void addToGame(GameLevel g) {
g.addCollidable(this);
g.addSprite(this);
}
/**
 * remove the paddle from given game.
 * @param g - given game.
 */
public void removeFromGame(GameLevel g) {
g.removeSprite(this);
g.removeCollidable(this);
}
/**
 *
 * @param speed1 - set the speed of the paddle.
 */
public void setSpeed(int speed1) {
this.speed = speed1;
}
/**
 *
 * @param witdh - set the witdh of the paddle.
 */
public void setWitdh(int witdh) {
this.rect.setWitdh(witdh);
}
/**
 *
 * @param upperleft1 - set the upperleft point of the paddle.
 */
public void setUpperLeft(Point upperleft1) {
this.upperleft = new Point(upperleft1);
}
}
