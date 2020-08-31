/**
* Frame.
*/
public class Frame {
private Point a, b;
/**
* constructor.
* @param a - the smallest point in the frame (smallest x, smallest y)
* @param b - the biggest point in the frame (biggest x, biggest y)
*/
public Frame(Point a, Point b) {
this.a = a;
this.b = b;
}
/**
* constructor.
* @param x1 - x cordunate of the smallest point in the frame
* @param y1 - y cordunate of the smallest point in the frame
* @param x2 - x cordunate of the biggest point in the frame
* @param y2 - y cordunate of the biggest point in the frame
*/
public Frame(double x1, double y1, double x2, double y2) {
this.a = new Point(x1, y1);
this.b = new Point(x2, y2);
}
/**
* setFrame1.
* @param x1 - x cordunate of the smallest point in the frame
* @param y1 - y cordunate of the smallest point in the frame
* @param x2 - x cordunate of the biggest point in the frame
* @param y2 - y cordunate of the biggest point in the frame
*/
public void setFrame1(double x1, double y1, double x2, double y2) {
this.a = new Point(x1, y1);
this.b = new Point(x2, y2);
}
/**
* geta.
* @return smallest point of the frame
*/
public Point geta() {
return this.a;
}
/**
* getb.
* @return biggest point of the frame
*/
public Point getb() {
return this.b;
}
}
