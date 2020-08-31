/**
* Point class.
*/
public class Point {
private double x, y;
/**
* constructor.
* @param x - x cordinate.
* @param y - y cordinate.
*/
public Point(double x, double y) {
this.x = x;
this.y = y;
}
/**
* constructor.
* @param other - initilize this point with another point.
*/
public Point(Point other) {
this.x = other.x;
this.y = other.y;
}
/**
* distance.
* @param other - point that we want to calculate the
*  distance from it to this point.
* @return Return the distance between the two points
*/
public double distance(Point other) {
return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
}
/**
* equals.
* @param other - point that we want to compare
*   to this point.
* @return return true if the points are equal, false otherwise
*/
public boolean equals(Point other) {
return (this.x == other.x) && (this.y == other.y);
}
/**
* getX.
* @return Return the x vaule of this point
*/
public double getX() {
return this.x;
}
/**
* getY.
* @return Return the y vaule of this point
*/
public double getY() {
return this.y;
}
/**
* isBetween.
* given two points(a and b), we want to find out
* if the x value of this point is located between the x's values
* of the two points and
* if the y value of this point is located between the y's values of the two points
* @param a - first point
* @param b - second point
* @return return true if this point x and y values are between x's and y's values of a and b
* ,false otherwise
*/
public boolean isBetween(Point a, Point b) {
if ((Math.round(a.x) >= Math.round(b.x)) && (Math.round(a.y) >= Math.round(b.y))) {
if (((Math.round(this.x) <= Math.round(a.x)) && (Math.round(this.x) >= Math.round(b.x)))
&& ((Math.round(this.y) <= Math.round(a.y)) && (this.y >= Math.round(b.y)))) {
return true;
}
}
if ((Math.round(a.x) <= Math.round(b.x)) && (Math.round(a.y) <= Math.round(b.y))) {
if (((Math.round(this.x) <= Math.round(b.x)) && (Math.round(this.x) >= Math.round(a.x)))
&& ((Math.round(this.y) <= Math.round(b.y)) && (Math.round(this.y) >= Math.round(a.y)))) {
return true;
}
}
if ((Math.round(a.x) >= Math.round(b.x)) && (Math.round(a.y) <= Math.round(b.y))) {
if (((Math.round(this.x) <= Math.round(a.x)) && (Math.round(this.x) >= Math.round(b.x)))
&& ((Math.round(this.y) <= Math.round(b.y)) && (Math.round(this.y) >= Math.round(a.y)))) {
return true;
}
}
if ((Math.round(a.x) <= Math.round(b.x)) && (Math.round(a.y) >= Math.round(b.y))) {
if (((Math.round(this.x) <= Math.round(b.x)) && (Math.round(this.x) >= Math.round(a.x)))
&& ((Math.round(this.y) <= Math.round(a.y)) && (Math.round(this.y) >= Math.round(b.y)))) {
return true;
}
}
return false;
}
}
