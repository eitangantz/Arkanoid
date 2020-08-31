
import biuoop.DrawSurface;
/**
* class Ball.
*/
public class Ball implements Sprite {
private Point x;
private double r;
private java.awt.Color c;
private Velocity d;
private Frame frame;
private GameEnvironment environment;
/**
* constructor.
* @param center - center point of the ball.
* @param r - radius of the ball.
* @param color - color of the ball.
*/
public Ball(Point center, int r, java.awt.Color color) {
this.x = center;
this.r = r;
this.c = color;
this.d = new Velocity(0, 0);
this.frame = new Frame(0, 0, 1000, 1000);
this.environment = new GameEnvironment();
}
/**
* constructor.
* @param x1 - x cordinate of center point of the ball.
* @param y1 - y cordinate of radius of the ball.
* @param r1 - radius of the ball.
* @param color - color of the ball.
* @param environment - environment of the ball.
*/
public Ball(int x1, int y1, int r1, java.awt.Color color, GameEnvironment environment) {
this.x = new Point(x1, y1);
this.r = r1;
this.c = color;
this.d = new Velocity(0, 0);
this.frame = new Frame(0, 0, 1000, 1000);
this.environment = environment;
}
/**
* getX.
* @return x cordinate of center point in int
*/
public int getX() {
return (int) this.x.getX();
}
/**
* getY.
* @return y cordinate of center point in int
*/
public int getY() {
return (int) this.x.getY();
}
/**
* getSize.
* @return the radius in int
*/
public int getSize() {
return (int) this.r;
}
/**
* getColor.
* @return the color of the ball
*/
public java.awt.Color getColor() {
return this.c;
}
/**
* drawOn.
* draw the ball on the given DrawSurface
* @param surface - given DrawSurface
*/
public void drawOn(DrawSurface surface) {
surface.setColor(c);
surface.fillCircle((int) this.x.getX(), (int) this.x.getY(), (int) this.r);
surface.setColor(java.awt.Color.BLACK);
surface.drawCircle((int) this.x.getX(), (int) this.x.getY(), (int) this.r);
}
/**
* setVelocity.
* sets the ball's velocity values correspondence to the given velocity
* @param v - given Velocity
*/
public void setVelocity(Velocity v) {
this.d.setdy(v.getdy());
this.d.setdx(v.getdx());
this.d.setxspeed(v.getxspeed());
}
/**
* setVelocity.
* sets the ball's velocity values correspondence to the given values
* @param dx - given angle
* @param dy - given speed
*/
public void setVelocity(double dx, double dy) {
this.d.setdx(dx);
this.d.setdy(dy);
this.d.setxspeed(dy);
}
/**
* getVelocity.
* @return the velocity of the ball
*/
public Velocity getVelocity() {
return this.d;
}
/**
* moveOneStep.
* move the ball one step - may get out of the limits
* this method refer dx and dy as deltax and deltay
* instead of angle and speed
* the method calls to another method applyToPoint
* which returns point with position (x+dx,y+dy)
* if the ball position is out of bound than velocity values changes
* to the opposite.
* @param surface - given DrawSurface. we need it for the bounderis of the ball
*/
public void moveOneStep(DrawSurface surface) {
if ((this.x.getY() - this.r < 0) || (this.x.getY() + this.r > surface.getHeight())) {
this.d.setdy(-(this.d.getdy()));
}
if ((this.x.getX() - this.r < 0) || (this.x.getX() + this.r > surface.getWidth())) {
this.d.setdx(-(this.d.getdx()));
}
this.x = this.getVelocity().applyToPoint(this.x);
}
/**
* moveOneStepAngle.
* calls correctmove.
* @param surface - given DrawSurface. we need it for the bounderis of the ball
*/
public void moveOneStepAngle(DrawSurface surface) {
this.frame.setFrame1(0, 0, surface.getWidth(), surface.getHeight()); ///
moveOneStepWithcollidables();
}
/**
* correctMove.
* move the ball one step
* this method refer dx and dy as angle and speed
* instead of deltax and deltay
* the method calls to another method applyToPointAngle
* which return a new point
* with position (x+this.xspeed * Math.cos((this.dx - 90) * (Math.PI / 180),
* y+this.dy * Math.sin((this.dx - 90) * (Math.PI / 180))
* if the ball position is out of bound than velocity values changes
* to the opposite.
*/
public void correctMove() {
int flag = 0;
double maxX, maxY, minX, minY;
maxX = this.frame.getb().getX() - this.r;
minX = this.frame.geta().getX() + this.r;
maxY = this.frame.getb().getY() - this.r;
minY = this.frame.geta().getY() + this.r;
double nextX = this.getVelocity().applyToPointFromAngle(this.x).getX();
double nextY = this.getVelocity().applyToPointFromAngle(this.x).getY();
if ((nextY  >= maxY) && (nextX  <= minX)) { //doun left
this.x = new Point(minX, maxY);
this.d.setdy(-(this.d.getdy()));
this.d.setxspeed(-(this.d.getxspeed()));
flag = 1;
}
if ((nextY  >= maxY) && (nextX  >= maxX)) { //down right
this.x = new Point(maxX, maxY);
this.d.setdy(-(this.d.getdy()));
this.d.setxspeed(-(this.d.getxspeed()));
flag = 1;
}
if ((nextY <= minY) && (nextX <= minX)) { //up left
this.x = new Point(minX, minY);
this.d.setdy(-(this.d.getdy()));
this.d.setxspeed(-(this.d.getxspeed()));
flag = 1;
}
if ((nextY <= minY) && (nextX >= maxX)) { //up right
this.x = new Point(maxX, minY);
this.d.setdy(-(this.d.getdy()));
this.d.setxspeed(-(this.d.getxspeed()));
flag = 1;
}
if ((nextY <= minY) && (flag != 1)) {
this.x = new Point(this.getVelocity().applyToPointFromAngle(this.x).getX(), minY);
this.d.setdy(-(this.d.getdy()));
flag = 2;
}
if ((nextY >= maxY) && (flag != 1)) {
this.x = new Point(this.getVelocity().applyToPointFromAngle(this.x).getX(), maxY);
this.d.setdy(-(this.d.getdy()));
flag = 2;
}
if ((nextX <= minX) && (flag != 1)) {
this.x = new Point(minX, this.getVelocity().applyToPointFromAngle(this.x).getY());
this.d.setxspeed(-(this.d.getxspeed()));
flag = 2;
}
if ((nextX >= maxX) && (flag != 1)) {
this.x = new Point(maxX, this.getVelocity().applyToPointFromAngle(this.x).getY());
this.d.setxspeed(-(this.d.getxspeed()));
flag = 2;
}
if ((flag != 1) && (flag != 2)) {
this.x = this.getVelocity().applyToPointFromAngle(this.x);
}
}
/**
* MoveOneStepWithcollidables.
* move the ball one step
* this method refer dx and dy as angle and speed
* instead of deltax and deltay
* the method calls to another method applyToPointAngle
* which return a new point
* with position (x+this.xspeed * Math.cos((this.dx - 90) * (Math.PI / 180),
* y+this.dy * Math.sin((this.dx - 90) * (Math.PI / 180))
* if the ball position is out of bound than velocity values changes
* to the opposite.
*/
public void moveOneStepWithcollidables() {
double maxX, maxY, minX, minY;
maxX = this.frame.getb().getX() - this.r;
minX = this.frame.geta().getX() + this.r;
maxY = this.frame.getb().getY() - this.r;
minY = this.frame.geta().getY() + this.r;
double nextX = this.getVelocity().applyToPointFromAngle(this.x).getX();
double nextY = this.getVelocity().applyToPointFromAngle(this.x).getY();
Point nextPoint = this.getVelocity().applyToPointFromAngle(this.x);
Line trajectory = new Line(this.x, nextPoint);
if ((nextY  >= maxY) && (nextX  <= minX)) { //doun left
this.x = new Point(minX, maxY);
this.d.setdy(-(this.d.getdy()));
this.d.setxspeed(-(this.d.getxspeed()));
return;
}
if ((nextY  >= maxY) && (nextX  >= maxX)) { //down right
this.x = new Point(maxX, maxY);
this.d.setdy(-(this.d.getdy()));
this.d.setxspeed(-(this.d.getxspeed()));
return;
}
if ((nextY <= minY) && (nextX <= minX)) { //up left
this.x = new Point(minX, minY);
this.d.setdy(-(this.d.getdy()));
this.d.setxspeed(-(this.d.getxspeed()));
return;
}
if ((nextY <= minY) && (nextX >= maxX)) { //up right
this.x = new Point(maxX, minY);
this.d.setdy(-(this.d.getdy()));
this.d.setxspeed(-(this.d.getxspeed()));
return;
}
if ((nextY <= minY)) {
this.x = new Point(this.getVelocity().applyToPointFromAngle(this.x).getX(), minY);
this.d.setdy(-(this.d.getdy()));
return;
}
if ((nextY >= maxY)) {
this.x = new Point(this.getVelocity().applyToPointFromAngle(this.x).getX(), maxY);
this.d.setdy(-(this.d.getdy()));
return;
}
if ((nextX <= minX)) {
this.x = new Point(minX, this.getVelocity().applyToPointFromAngle(this.x).getY());
this.d.setxspeed(-(this.d.getxspeed()));
return;
}
if ((nextX >= maxX)) {
this.x = new Point(maxX, this.getVelocity().applyToPointFromAngle(this.x).getY());
this.d.setxspeed(-(this.d.getxspeed()));
return;
}
if (this.environment.getClosestCollision(trajectory) != null) {
CollisionInfo info = this.environment.getClosestCollision(trajectory);
Point collsionround = new Point(Math.round(info.collisionPoint().getX()), Math.round(info.collisionPoint().getY()));
if ((this.x.getY() <= collsionround.getY())
&& (this.getVelocity().applyToPointFromAngle(this.x).getY() >= collsionround.getY())
&& (collsionround.isBetween(info.collisionObject().getCollisionRectangle().getup().start(),
info.collisionObject().getCollisionRectangle().getup().end()))) {
this.x = new Point(collsionround.getX(), collsionround.getY() - 0.51);
this.d = info.collisionObject().hit(this, collsionround, this.d, trajectory);
return;
}
if ((this.x.getY() >= collsionround.getY())
&& (this.getVelocity().applyToPointFromAngle(this.x).getY() <= collsionround.getY())
&& (collsionround.isBetween(info.collisionObject().getCollisionRectangle().getdown().start(),
info.collisionObject().getCollisionRectangle().getdown().end()))) {
this.x = new Point(collsionround.getX(), collsionround.getY() + 0.51);
this.d = info.collisionObject().hit(this, collsionround, this.d, trajectory);
return;
}
if ((this.x.getX() <= collsionround.getX())
&& (this.getVelocity().applyToPointFromAngle(this.x).getX() >= collsionround.getX())
&& (collsionround.isBetween(info.collisionObject().getCollisionRectangle().getleft().start(),
info.collisionObject().getCollisionRectangle().getleft().end()))) {
this.x = new Point(collsionround.getX() - 0.51, collsionround.getY());
this.d = info.collisionObject().hit(this, collsionround, this.d, trajectory);
return;
}
if ((this.x.getX() >= collsionround.getX())
&& (this.getVelocity().applyToPointFromAngle(this.x).getX() <= collsionround.getX())
&& (collsionround.isBetween(info.collisionObject().getCollisionRectangle().getright().start(),
info.collisionObject().getCollisionRectangle().getright().end()))) {
this.x = new Point(collsionround.getX() + 0.51, collsionround.getY());
this.d = info.collisionObject().hit(this, collsionround, this.d, trajectory);
return;
}
if ((this.x.getY() <= collsionround.getY())
&& (this.getVelocity().applyToPointFromAngle(this.x).getY() >= collsionround.getY())) {
this.x = new Point(collsionround.getX(), collsionround.getY() - 0.51);
this.d = info.collisionObject().hit(this, collsionround, this.d, trajectory);
return;
}
if ((this.x.getY() >= collsionround.getY())
&& (this.getVelocity().applyToPointFromAngle(this.x).getY() <= collsionround.getY())) {
this.x = new Point(collsionround.getX(), collsionround.getY() + 0.51);
this.d = info.collisionObject().hit(this, collsionround, this.d, trajectory);
return;
}
if ((this.x.getX() <= collsionround.getX())
&& (this.getVelocity().applyToPointFromAngle(this.x).getX() >= collsionround.getX())) {
this.x = new Point(collsionround.getX() - 0.51, collsionround.getY());
this.d = info.collisionObject().hit(this, collsionround, this.d, trajectory);
return;
}
if ((this.x.getX() >= collsionround.getX())
&& (this.getVelocity().applyToPointFromAngle(this.x).getX() <= collsionround.getX())) {
this.x = new Point(collsionround.getX() + 0.51, collsionround.getY());
this.d = info.collisionObject().hit(this, collsionround, this.d, trajectory);
return;
}

this.d = info.collisionObject().hit(this, info.collisionPoint(), this.d, trajectory);
return;
}
this.x = this.getVelocity().applyToPointFromAngle(this.x);
return;
}
/**
* setFrame.
* @param x1 - x cordinate of point a of frame
* @param y1 - y cordinate of point a of frame
* @param x2 - x cordinate of point b of frame
* @param y2 - y cordinate of point b of frame
*/
public void setFrame(double x1, double y1, double x2, double y2) {
this.frame.setFrame1(x1, y1, x2, y2);
}
/**
* setEnvironment.
* @param shape - add to the environment of the ball.
*/
public void setEnvironment(Collidable shape) {
this.environment.addCollidable(shape);
}
/**
* removeEnvironment.
* @param shape - remove from the environment of the ball.
*/
public void removeEnvironment(Collidable shape) {
this.environment.removeCollidable(shape);
}
/**
* removeFromGame.
* @param g - the game this ball in it.
* this method remove this ball from the environment of the game.
*/
public void removeFromGame(GameLevel g) {
g.removeSprite(this);
}
/**
* getEnvironment.
* @return the environment of the ball.
*/
public GameEnvironment getEnvironment() {
return this.environment;
}
/**
* timePassed.
*/
public void timePassed() {
moveOneStepWithcollidables();
}
/**
* addToGame.
* @param g - add the ball to given game.
*/
public void addToGame(GameLevel g) {
g.addSprite(this);
}
}
