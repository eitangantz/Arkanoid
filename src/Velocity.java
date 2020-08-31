/**
* Velocity.
*/
public class Velocity {
private double dx, dy;
private double xspeed;
/**
* constructor.
* @param dx - the diffrence between current x value to
* new x value.
* @param dy - the diffrence between current y value to
* new y value.
*/
public Velocity(double dx, double dy) {
this.dx = dx;
this.dy = dy;
}
/**
* constructor.
* copy an instance of velocity to this velocity.
* @param v - velocity
*/
public Velocity(Velocity  v) {
this.dx = v.dx;
this.dy = v.dy;
}
/**
* constructor.
* * this costructor refer diffrently
* to dx and dy, instead of the deltas between x and y correspondingly
* dx become the angle and dy is the speed of x and y
* @param angle - direction
* @param speedy - the speed of y value
* @param speedx - the speed of x value
*/
public Velocity(double angle, double speedy, double speedx) {
this.dx = angle;
this.dy = speedy;
this.xspeed = speedx;
}
/**
* constructor.
* this costructor refer diffrently
* to dx and dy, instead of the deltas between x and y correspondingly
* dx become the angle and dy is the speed of x and y
* @param angle - direction
* @param speed - the speed of y and x value
* @return this velocity
*/
public static Velocity fromAngleAndSpeed(double angle, double speed) {
double angle1 = angle;
double speedy = speed;
double speedx = speed;
return new Velocity(angle1, speedy, speedx);
}
/**
* applyToPoint.
* @param p - point to add deltas to.
* @return Take a point with position (x,y) and return a new point
* with position (x+dx, y+dy)
*/
public Point applyToPoint(Point p) {
Point vel = new Point(p.getX() + this.dx, p.getY() + this.dy);
return vel;
}
/**
* applyToPointFromAngle.
* @param p - point to add deltas to.
* @return Take a point with position (x,y) and return a new point
* with position (x+this.xspeed * Math.cos((this.dx - 90) * (Math.PI / 180),
* y+this.dy * Math.sin((this.dx - 90) * (Math.PI / 180))
*/
public Point applyToPointFromAngle(Point p) {
Point vel = new Point(p.getX() + this.xspeed * Math.cos((this.dx - 90) * (Math.PI / 180)),
p.getY() + this.dy * Math.sin((this.dx - 90) * (Math.PI / 180)));
Point roundvel = new Point(Math.round(vel.getX()), Math.round(vel.getY()));
return roundvel;
}
/**
* setdy.
* @param dy1 - delta or speed of y.
*/
public void setdy(double dy1) {
this.dy = dy1;
}
/**
* setdx.
* @param dx1 - delta or angle.
*/
public void setdx(double dx1) {
this.dx = dx1;
}
/**
* setdx.
* @param speed - speed of x.
*/
public void setxspeed(double speed) {
this.xspeed = speed;
}
/**
* getxspeed.
* @return speed of x.
*/
public double getxspeed() {
return this.xspeed;
}
/**
* getdy.
* @return speed of y.
*/
public double getdy() {
return this.dy;
}
/**
* getdx.
* @return speed of x.
*/
public double getdx() {
return this.dx;
}
}