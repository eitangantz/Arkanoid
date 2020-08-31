import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.imageio.ImageIO;
/**
 *
 *
 */
public class BlocksDefinitionReader {

/**
 *
 * @param reader - reader
 * @return factory
 */
public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
BufferedReader br = null;
String line;
Map<String, Integer> spacerWidths = new TreeMap<String, Integer>();
Map<String, BlockCreator> blockCreators = new TreeMap<String, BlockCreator>();
GeneralBlockCreator creator = new GeneralBlockCreator();
try {
br = new BufferedReader(reader);
line = br.readLine();
List<Integer> defkHitPointsIntegersCo = new ArrayList<Integer>();
List<Color> defcolorsForkHitPoints = new ArrayList<Color>();
List<Integer> defkHitPointsIntegersIm = new ArrayList<Integer>();
List<Image> defimagesForkHitPoints = new ArrayList<Image>();
int defheight = -2;
int defwidth = -2;
int defhitpoints = -2;
Color defcolorfill = null;
Color defstroke = null;
Image defimgfill = null;
while ((line = br.readLine()) != null) {
List<Integer> kHitPointsIntegersCo = new ArrayList<Integer>();
List<Color> colorsForkHitPoints = new ArrayList<Color>();
List<Integer> kHitPointsIntegersIm = new ArrayList<Integer>();
List<Image> imagesForkHitPoints = new ArrayList<Image>();
kHitPointsIntegersCo.addAll(defkHitPointsIntegersCo);
colorsForkHitPoints.addAll(defcolorsForkHitPoints);
kHitPointsIntegersIm.addAll(defkHitPointsIntegersIm);
imagesForkHitPoints.addAll(defimagesForkHitPoints);
creator.setColorsk(colorsForkHitPoints);
creator.setImagesk(imagesForkHitPoints);
creator.setIntegerskColors(kHitPointsIntegersCo);
creator.setIntegerskImages(kHitPointsIntegersIm);
shrink(creator, defheight, defwidth, defhitpoints, defcolorfill, defstroke, defimgfill);
if (line.startsWith("default")) {
ColorsParser colorParser = new ColorsParser();
String[] defult = line.split(" ");
for (int i = 0; i < defult.length; i++) {
shrink2(defult, i, defcolorfill, colorParser, defimgfill, defhitpoints);
if (defult[i].startsWith("hit_points")) {
String hitpoints = (defult[i].split(":"))[1];
defhitpoints = Integer.parseInt(hitpoints);
}
if (defult[i].startsWith("fill-")) {
String colorfillk = (defult[i].split(":"))[1];
String key = (defult[i].split(":"))[0];
key = key.substring(key.length() - 1);
if ((!colorfillk.contains("RGB")) && (!colorfillk.contains("image"))) {
colorfillk = colorfillk.replaceFirst("color", "");
colorfillk = colorfillk.substring(1, colorfillk.lastIndexOf(')'));
defkHitPointsIntegersCo.add(Integer.parseInt(key));
defcolorsForkHitPoints.add(colorParser.colorFromString(colorfillk));
}
if (colorfillk.contains("RGB")) {
String r = new String();
String g = new String();
String b = new String();
String[] rgb = null;
colorfillk = colorfillk.replaceFirst("color", "");
colorfillk = colorfillk.substring(5, colorfillk.lastIndexOf(")"));
colorfillk = colorfillk.substring(0, colorfillk.lastIndexOf(")"));
rgb = colorfillk.split(",");
r = rgb[0];
g = rgb[1];
b = rgb[2];
defkHitPointsIntegersCo.add(Integer.parseInt(key));
defcolorsForkHitPoints.add(new Color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b)));
}
if (colorfillk.contains("image")) {
colorfillk = colorfillk.replaceFirst("image", "");
colorfillk = colorfillk.substring(1, colorfillk.lastIndexOf(")"));
//String[] s = colorfillk.split("/");
//colorfillk = s[1];
//colorfillk = ("resources/").concat(colorfillk);
InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(colorfillk);
Image img = null;
try {
img = ImageIO.read(is);
defkHitPointsIntegersIm.add(Integer.parseInt(key));
defimagesForkHitPoints.add(img);
} catch (IOException e) {
e.printStackTrace();
}
}
}
if (defult[i].startsWith("stroke")) {
String stroke = (defult[i].split(":"))[1];
if (stroke.contains("RGB")) {
String r = new String();
String g = new String();
String b = new String();
String[] rgb = null;
stroke = stroke.replaceFirst("color", "");
stroke = stroke.substring(5, stroke.lastIndexOf(")"));
stroke = stroke.substring(0, stroke.lastIndexOf(")"));
rgb = stroke.split(",");
r = rgb[0];
g = rgb[1];
b = rgb[2];
defstroke = new Color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b));
} else {
stroke = stroke.replaceFirst("color", "");
stroke = stroke.substring(1, stroke.lastIndexOf(')'));
defstroke = colorParser.colorFromString(stroke);
}
}
if (defult[i].startsWith("width")) {
String width = (defult[i].split(":"))[1];
defwidth = Integer.parseInt(width);
}
if (defult[i].startsWith("height")) {
String height = (defult[i].split(":"))[1];
defheight = Integer.parseInt(height);
}
}
}
if (line.startsWith("bdef")) {
bdef(creator,  line,  colorsForkHitPoints, kHitPointsIntegersCo,  kHitPointsIntegersIm,  imagesForkHitPoints);
blockCreators.put(creator.getSymbol(), creator);
creator = new GeneralBlockCreator();
}
if (line.startsWith("sdef")) {
String[] sdef = line.split(":");
String width = sdef[sdef.length - 1];
String symb = sdef[sdef.length - 2].substring(0, 1);
spacerWidths.put(symb, Integer.parseInt(width));
}
}
br.close();
return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
} catch (IOException e) {
e.printStackTrace();
}
return null;
}
/**
 *
 * @param creator - creator
 * @param line - line
 * @param colorsForkHitPoints - colors
 * @param kHitPointsIntegersCo - integers
 * @param kHitPointsIntegersIm - integers
 * @param imagesForkHitPoints - images
 */
public static void bdef(GeneralBlockCreator creator, String line, List<Color> colorsForkHitPoints,
List<Integer> kHitPointsIntegersCo, List<Integer> kHitPointsIntegersIm, List<Image> imagesForkHitPoints) {
String[] bdef = line.split(" ");
ColorsParser colorParser = new ColorsParser();
for (int i = 0; i < bdef.length; i++) {
if (bdef[i].startsWith("symbol")) {
String symbol = (bdef[i].split(":"))[1];
creator.setSymbol(symbol);
}
if (bdef[i].startsWith("fill:")) {
String colorfill = (bdef[i].split(":"))[1];
if ((!colorfill.contains("RGB")) && (!colorfill.contains("image"))) {
colorfill = colorfill.replaceFirst("color", "");
colorfill = colorfill.substring(1, colorfill.lastIndexOf(')'));
creator.setFill(colorParser.colorFromString(colorfill));
}
if (colorfill.contains("RGB")) {
String r = new String();
String g = new String();
String b = new String();
String[] rgb = null;
colorfill = colorfill.replaceFirst("color", "");
colorfill = colorfill.substring(5, colorfill.lastIndexOf(")"));
colorfill = colorfill.substring(0, colorfill.lastIndexOf(")"));
rgb = colorfill.split(",");
r = rgb[0];
g = rgb[1];
b = rgb[2];
creator.setFill(new Color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b)));
}
if (colorfill.contains("image")) {
colorfill = colorfill.replaceFirst("image", "");
colorfill = colorfill.substring(1, colorfill.lastIndexOf(")"));
//String[] s = colorfill.split("/");
//colorfill = s[1];
//colorfill = ("resources/").concat(colorfill);
InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(colorfill);
Image img = null;
try {
img = ImageIO.read(is);
creator.setImgfill(img);
} catch (IOException e) {
e.printStackTrace();
}
}
}
if (bdef[i].startsWith("hit_points")) {
String hitpoints = (bdef[i].split(":"))[1];
creator.setHitpoints(Integer.parseInt(hitpoints));
}
if (bdef[i].startsWith("fill-")) {
String colorfillk = (bdef[i].split(":"))[1];
String key = (bdef[i].split(":"))[0];
key = key.substring(key.length() - 1);
if ((!colorfillk.contains("RGB")) && (!colorfillk.contains("image"))) {
colorfillk = colorfillk.replaceFirst("color", "");
colorfillk = colorfillk.substring(1, colorfillk.lastIndexOf(')'));
kHitPointsIntegersCo.add(Integer.parseInt(key));
colorsForkHitPoints.add(colorParser.colorFromString(colorfillk));
}
if (colorfillk.contains("RGB")) {
String r = new String();
String g = new String();
String b = new String();
String[] rgb = null;
colorfillk = colorfillk.replaceFirst("color", "");
colorfillk = colorfillk.substring(5, colorfillk.lastIndexOf(")"));
colorfillk = colorfillk.substring(0, colorfillk.lastIndexOf(")"));
rgb = colorfillk.split(",");
r = rgb[0];
g = rgb[1];
b = rgb[2];
kHitPointsIntegersCo.add(Integer.parseInt(key));
colorsForkHitPoints.add(new Color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b)));
}
if (colorfillk.contains("image")) {
colorfillk = colorfillk.replaceFirst("image", "");
colorfillk = colorfillk.substring(1, colorfillk.lastIndexOf(")"));
//String[] s =  colorfillk.split("/");
//colorfillk = s[1];
//colorfillk = ("resources/").concat(colorfillk);
InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(colorfillk);
Image img = null;
try {
img = ImageIO.read(is);
kHitPointsIntegersIm.add(Integer.parseInt(key));
imagesForkHitPoints.add(img);
} catch (IOException e) {
e.printStackTrace();
}
}
}
if (bdef[i].startsWith("stroke")) {
String stroke = (bdef[i].split(":"))[1];
if (stroke.contains("RGB")) {
String r = new String();
String g = new String();
String b = new String();
String[] rgb = null;
stroke = stroke.replaceFirst("color", "");
stroke = stroke.substring(5, stroke.lastIndexOf(")"));
stroke = stroke.substring(0, stroke.lastIndexOf(")"));
rgb = stroke.split(",");
r = rgb[0];
g = rgb[1];
b = rgb[2];
creator.setStroke(new Color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b)));
} else {
stroke = stroke.replaceFirst("color", "");
stroke = stroke.substring(1, stroke.lastIndexOf(')'));
creator.setStroke(colorParser.colorFromString(stroke));
}
}
if (bdef[i].startsWith("width")) {
String width = (bdef[i].split(":"))[1];
creator.setWidth(Integer.parseInt(width));
}
if (bdef[i].startsWith("height")) {
String height = (bdef[i].split(":"))[1];
creator.setHeight(Integer.parseInt(height));
}
}
creator.setColorsk(colorsForkHitPoints);
creator.setIntegerskColors(kHitPointsIntegersCo);
creator.setIntegerskImages(kHitPointsIntegersIm);
creator.setImagesk(imagesForkHitPoints);
}
/**
 *
 * @param creator - creator
 * @param defheight - height
 * @param defwidth - width
 * @param defhitpoints - hitpoints
 * @param defcolorfill - fill color
 * @param defstroke - stroke
 * @param defimgfill - image fill
 */
public static void shrink(GeneralBlockCreator creator, int defheight, int defwidth, int defhitpoints,
Color defcolorfill, Color defstroke, Image defimgfill) {
if (defheight != 2) {
creator.setHeight(defheight);
}
if (defwidth != -2) {
creator.setWidth(defwidth);
}
if (defhitpoints != -2) {
creator.setHitpoints(defhitpoints);
}
if (defcolorfill != null) {
creator.setFill(defcolorfill);
}
if (defstroke != null) {
creator.setStroke(defstroke);
}
if (defimgfill != null) {
creator.setImgfill(defimgfill);
}
}
/**
 *
 * @param defult - string
 * @param i - index
 * @param defcolorfill - color
 * @param colorParser - color praser
 * @param defimgfill - image
 * @param defhitpoints - hitpoints
 */
public static void shrink2(String[] defult, int i, Color defcolorfill, ColorsParser colorParser,
Image defimgfill, int defhitpoints) {
if (defult[i].startsWith("fill:")) {
String colorfill = (defult[i].split(":"))[1];
if ((!colorfill.contains("RGB")) && (!colorfill.contains("image"))) {
colorfill = colorfill.replaceFirst("color", "");
colorfill = colorfill.substring(1, colorfill.lastIndexOf(')'));
defcolorfill = colorParser.colorFromString(colorfill);
}
if (colorfill.contains("RGB")) {
String r = new String();
String g = new String();
String b = new String();
String[] rgb = null;
colorfill = colorfill.replaceFirst("color", "");
colorfill = colorfill.substring(5, colorfill.lastIndexOf(")"));
colorfill = colorfill.substring(0, colorfill.lastIndexOf(")"));
rgb = colorfill.split(",");
r = rgb[0];
g = rgb[1];
b = rgb[2];
defcolorfill = new Color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b));
}
if (colorfill.contains("image")) {
colorfill = colorfill.replaceFirst("image", "");
colorfill = colorfill.substring(1, colorfill.lastIndexOf(")"));
//String[] s = colorfill.split("/");
//colorfill = s[1];
//colorfill = ("resources/").concat(colorfill);
InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(colorfill);
Image img = null;
try {
img = ImageIO.read(is);
defimgfill = img;
} catch (IOException e) {
e.printStackTrace();
}
}
}
}
}
