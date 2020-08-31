import java.util.List;
import java.util.ArrayList;
/**
* Rectangle class.
*/
public class Rectangle {
private Point upperleft;
private double witdh;
private double height;
private Line up;
private Line down;
private Line left;
private Line right;
/**
* Rectangle constructor.
* Create a new rectangle with location and width/height.
* @param upperLeft - upper left point of the rectangle.
* @param width - width of the rectangle.
* @param height - height of the rectangle.
*/
public Rectangle(Point upperLeft, double width, double height) {
this.upperleft = upperLeft;
this.witdh = width;
this.height = height;
Point leftdown = new Point(this.upperleft.getX(), this.upperleft.getY() + this.height);
Point rightdown = new Point(this.upperleft.getX() + this.witdh, this.upperleft.getY() + this.height);
Point rightup = new Point(this.upperleft.getX() + this.witdh, this.upperleft.getY());
this.up = new Line(this.upperleft, rightup);
this.down = new Line(leftdown, rightdown);
this.left = new Line(this.upperleft, leftdown);
this.right = new Line(rightup, rightdown);
}
/**
* intersectionPoints.
* Return a (possibly empty) List of intersection points
* with the specified line.
* @param line - the line we check for intersections with this rectangle.
* @return list of intersections points.
*/
public java.util.List<Point> intersectionPoints(Line line) {
Point leftdown = new Point(this.upperleft.getX(), this.upperleft.getY() + this.height);
Point rightdown = new Point(this.upperleft.getX() + this.witdh, this.upperleft.getY() + this.height);
Point rightup = new Point(this.upperleft.getX() + this.witdh, this.upperleft.getY());
Line up1 = new Line(this.upperleft, rightup);
Line down1 = new Line(leftdown, rightdown);
Line right1 = new Line(rightup, rightdown);
Line left1 = new Line(this.upperleft, leftdown);
List<Point> listofintersections = new ArrayList<Point>();
if (up1.isIntersecting(line)) {
listofintersections.add(up1.intersectionWith(line));
}
if (down1.isIntersecting(line)) {
listofintersections.add(down1.intersectionWith(line));
}
if (right1.isIntersecting(line)) {
listofintersections.add(right1.intersectionWith(line));
}
if (left1.isIntersecting(line)) {
listofintersections.add(left1.intersectionWith(line));
}
return listofintersections;
}
/**
* getWidth.
* @return Return the width of the rectangle
*/
public double getWidth() {
return this.witdh;
}
/**
* getHeight.
* @return Return the height of the rectangle
*/
public double getHeight() {
return this.height;
}
/**
* getUpperLeft.
* @return Returns the upper-left point of the rectangle.
*/
public Point getUpperLeft() {
return this.upperleft;
}
/**
* getup.
* @return Returns the up line of the rectangle.
*/
public Line getup() {
return this.up;
}
/**
* getdown.
* @return Returns the down line of the rectangle.
*/
public Line getdown() {
return this.down;
}
/**
* getleft.
* @return Returns the left line of the rectangle.
*/
public Line getleft() {
return this.left;
}
/**
* getright.
* @return Returns the right line of the rectangle.
*/
public Line getright() {
return this.right;
}
/**
* setUPPerLeftPoint.
* @param x - x cordinate of the new upperleft.
* @param y - y cordinate of the new upperleft.
*/
public void setUPPerLeftPoint(double x, double y) {
this.upperleft = new Point(x, y);
}
/**
 *
 * @param witdh1 - witdh.
 */
public void setWitdh(int witdh1) {
this.witdh = witdh1;
}
}
