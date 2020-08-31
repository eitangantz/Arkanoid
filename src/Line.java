import java.util.ArrayList;
import java.util.List;
/**
* Line class.
*/
public class Line {
private Point x, y;
/**
* constructor.
* @param start - start Point of the line.
* @param end - end Point of the line.
*/
public Line(Point start, Point end) {
this.x = start;
this.y = end;
}
/**
* constructor.
* @param x1  - x cordinates of start point.
* @param y1 - y cordinates of start point.
* @param x2 - x cordinates of end point.
* @param y2 - y cordinates of end point.
*/
public Line(double x1, double y1, double x2, double y2) {
this.x = new Point(x1, y1);
this.y = new Point(x2, y2);
}
/**
* length.
* @return Return the length of the line
*/
public double length() {
return Math.sqrt((this.x.getX() - this.y.getX()) * (this.x.getX() - this.y.getX())
+ (this.x.getY() - this.y.getY()) * (this.x.getY() - this.y.getY()));
}
/**
* middle Point.
* @return Returns the middle point of the line.
*/
public Point middle() {
Point mid = new Point(((this.x.getX() + this.y.getX()) / 2),
(this.x.getY() + this.y.getY()) / 2);
return mid;
}
/**
* start Point.
* @return Returns the start point of the line.
*/
public Point start() {
return this.x;
}
/**
* end point.
* @return Returns the end point of the line.
*/
public Point end() {
return this.y;
}
/**
* lines intersect.
* @param other - the line we compare to this line for finding intersect
* @return Returns true if the lines intersect, false otherwise.
*/
public boolean isIntersecting(Line other) {
return (intersectionWith(other) != null);
}
/**
 * intersection point.
 * @param other - the line we compare to this line for finding intersect
 * @return Returns the intersection point if the lines intersect,
 * and null otherwise.
 */
public Point intersectionWith(Line other) {
if ((other.x.getX() == other.y.getX()) && (this.x.getX() == this.y.getX())) {
return null;
}
if (other.x.getX() == other.y.getX()) {  // if other line is vertical
double m = (this.x.getY() - this.y.getY()) / (this.x.getX() - this.y.getX());
double y5 = m * (other.x.getX() - this.y.getX()) + this.y.getY();
Point intersection = new Point(other.x.getX(), y5);
if ((intersection.isBetween(this.x, this.y)) && (intersection.isBetween(other.x, other.y))) {
return intersection;
}
return null;
}
if (this.x.getX() == this.y.getX()) {  //if this line is vertical
double m = (other.x.getY() - other.y.getY()) / (other.x.getX() - other.y.getX());
double y5 = m * (this.x.getX() - other.y.getX()) + other.y.getY();
Point intersection = new Point(this.x.getX(), y5);
if ((intersection.isBetween(this.x, this.y)) && (intersection.isBetween(other.x, other.y))) {
return intersection;
}
return null;
}
double m1 = (other.x.getY() - other.y.getY()) / (other.x.getX() - other.y.getX());
double m2 = (this.x.getY() - this.y.getY()) / (this.x.getX() - this.y.getX());
double c1 = -m1 * other.x.getX() + other.x.getY();
double c2 = -m2 * this.x.getX() + this.x.getY();
if ((m1 == m2) && (c2 != c1)) {
return null;
}
if ((m1 == m2) && (c2 == c1)) {
return null;
}
if (m1 == 0) {   //if other is meozan
double x5 = (m2 * this.y.getX() + other.x.getY() - this.y.getY()) / m2;
Point intersection = new Point(x5, other.x.getY());
if ((intersection.isBetween(this.x, this.y)) && (intersection.isBetween(other.x, other.y)))
{
return intersection;
}
return null;
}
if (m2 == 0) {   //if other is meozan
double x5 = (m1 * other.y.getX() + this.x.getY() - other.y.getY()) / m1;
Point intersection = new Point(x5, this.x.getY());
if ((intersection.isBetween(this.x, this.y)) && (intersection.isBetween(other.x, other.y)))
{
return intersection;
}
return null;
}
double x1 = (c2 - c1) / (m1 - m2);
double y1 = m1 * (x1 - other.x.getX()) + other.x.getY();
Point intersection = new Point(x1, y1);
if ((intersection.isBetween(this.x, this.y)) && (intersection.isBetween(other.x, other.y)))
{
return intersection;
}
return null;
}
/**
 * equals lines.
 * @param other - the line we compare to this line for finding if equals
 * @return Returns return true is the lines are equal,
 *  false otherwise.
 */
public boolean equals(Line other) {
double m1 = (other.x.getY() - other.y.getY()) / (other.x.getX() - other.y.getX());
double m2 = (this.x.getY() - this.y.getY()) / (this.x.getX() - this.y.getX());
double c1 = -m1 * other.x.getX() + other.x.getY();
double c2 = -m2 * this.x.getX() + this.x.getY();
return ((m1 == m2) && (c2 == c1));
}
// If this line does not intersect with the rectangle, return null.
// Otherwise, return the closest intersection point to the
// start of the line.
/**
 * closestIntersectionToStartOfLine.
 * @param rect - the rect we compare to this line for finding if the closest intersection.
 * @return Returns the point of the closest intersection.
 */
public Point closestIntersectionToStartOfLine(Rectangle rect) {
List<Point> listofintersectionpoints = new ArrayList<Point>();
listofintersectionpoints.addAll(rect.intersectionPoints(this));
if (listofintersectionpoints.isEmpty()) {
return null;
}
int index = 0;
double min = this.x.distance(listofintersectionpoints.get(0));
for (int i = 0; i < listofintersectionpoints.size(); i++) {
if ((this.x.distance(listofintersectionpoints.get(i)) <= min)) {
min = this.x.distance(listofintersectionpoints.get(i));
index = i;
}
}
return listofintersectionpoints.get(index);
}
}