import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
/**
 *
 *
 *
 */
public class GeneralBlockCreator implements BlockCreator {
private String symbol;
private int height;
private int width;
private int hitpoints;
private Image imgfill;
private Color colorfill;
private List<Integer> kHitPointsIntegersCo;
private List<Color> colorsForkHitPoints;
private List<Integer> kHitPointsIntegersIm;
private List<Image> imagesForkHitPoints;
private Color stroke;
/**
 *
 *
 */
public GeneralBlockCreator() {
this.symbol = new String();
this.height = -2;
this.width = -2;
this.hitpoints = -2;
this.colorfill = null;
this.imagesForkHitPoints = new ArrayList<Image>();
this.kHitPointsIntegersIm = new ArrayList<Integer>();
this.colorsForkHitPoints = new ArrayList<Color>();
this.kHitPointsIntegersIm = new ArrayList<Integer>();
this.stroke = null;
this.imgfill = null;
}
/**
 *
 * @param s - string
 */
public void setSymbol(String s) {
this.symbol = s;
}
/**
 *
 * @param s - int
 */
public void setWidth(int s) {
this.width = s;
}
/**
 *
 * @param s - int
 */
public void setHeight(int s) {
this.height = s;
}
/**
 *
 * @param s - image
 */
public void setImgfill(Image s) {
this.imgfill = s;
}
/**
 *
 * @param s - int
 */
public void setHitpoints(int s) {
this.hitpoints = s;
}
/**
 *
 * @param s - color
 */
public void setFill(Color s) {
this.colorfill = s;
}
/**
 *
 * @param s - color
 */
public void setStroke(Color s) {
this.stroke = s;
}
/**
 *
 * @param l - list
 */
public void setIntegerskColors(List<Integer> l) {
this.kHitPointsIntegersCo = l;
}
/**
 *
 * @param l - list
 */
public void setIntegerskImages(List<Integer> l) {
this.kHitPointsIntegersIm = l;
}
/**
 *
 * @param l - list
 */
public void setColorsk(List<Color> l) {
this.colorsForkHitPoints = l;
}
/**
 *
 * @param l - list
 */
public void setImagesk(List<Image> l) {
this.imagesForkHitPoints = l;
}
/**
 *
 * @return symbol
 */
public String getSymbol() {
return this.symbol;
}
/**
 *
 * @return stroke
 */
public Color getStroke() {
return this.stroke;
}
/**
 *
 * @return height
 */
public int getHeight() {
return this.height;
}
/**
 * @param xpos - x
 * @param ypos - y
 * @return block
 */
public Block create(int xpos, int ypos) {
TreeMap<Integer, Color> map1 = new TreeMap<Integer, Color>();
TreeMap<Integer, Image> map2 = new TreeMap<Integer, Image>();
for (int i = 0; i < this.kHitPointsIntegersCo.size(); i++) {
map1.put(this.kHitPointsIntegersCo.get(i), this.colorsForkHitPoints.get(i));
}
for (int i = 0; i < this.kHitPointsIntegersIm.size(); i++) {
map2.put(this.kHitPointsIntegersIm.get(i), this.imagesForkHitPoints.get(i));
}
Point upperleft = new Point(xpos, ypos);
Rectangle rect = new Rectangle(upperleft, this.width, this.height);
Block block = new Block(rect, this.hitpoints, this.imgfill, this.colorfill, map1, map2, this.stroke);
return block;
}
}
