import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
/**
 *
 *
 *
 */
public class LevelSpecificationReader {
private int flag3 = 0;
private List<Block> blocks = new ArrayList<Block>();
private BackgroundFromImage backg = new BackgroundFromImage();
private BlocksDefinitionReader breader = new BlocksDefinitionReader();
private BlocksFromSymbolsFactory factory = new BlocksFromSymbolsFactory();
private List<String> symbols = new ArrayList<String>();
private List<String> velo = new ArrayList<String>();
private List<Velocity> velocities = new ArrayList<Velocity>();
private String levelname  = new String();
private String ballvelos = new String();
private String background = new String();
private String paddlespeed = new String();
private String paddlewidth = new String();
private String blockdefinitions = new String();
private String blocksstarsx = new String();
private String blocksstarsy = new String();
private String rowheight = new String();
private String numblocks = new String();
private ColorsParser colorParser = new ColorsParser();
private List<LevelInformation> levels = new ArrayList<LevelInformation>();
private BufferedReader br = null;
private String line;
private int flag = 1;
/**
 *
 * @param reader - reader
 * @return list
 */
public List<LevelInformation> fromReader(java.io.Reader reader) {
try {
br = new BufferedReader(reader);
line = br.readLine();
while (line != null) {
initmembers();
if (flag == 0) {
line = br.readLine();
} else {
flag = 0;
}
while (!("END_LEVEL").equals(line) && (line != null)) {
line = br.readLine();
if (line.contains("level_name:")) {
String[] levelnamearr = line.split(":");
levelname = levelnamearr[1];
}
if (line.contains("ball_velocities:")) {
String[] ballvelosarr = line.split(":");
ballvelos = ballvelosarr[1];
String[] ballvelosarr1 = ballvelos.split(" ");
for (int i = 0; i < ballvelosarr1.length; i++) {
String[] ballvelosarr2 = ballvelosarr1[i].split(",");
velo.add(ballvelosarr2[0]);
velo.add(ballvelosarr2[1]);
}
for (int i = 0; i < velo.size(); i += 2) {
Velocity velocity = new Velocity(Integer.parseInt(velo.get(i)),
Integer.parseInt(velo.get(i + 1)) / 100, Integer.parseInt(velo.get(i + 1)) / 100);
velocities.add(velocity);
}
}
if (line.contains("background:")) {
String[] backgroundsarr = line.split(":");
background = backgroundsarr[1];
if ((!background.contains("RGB")) && (!background.contains("image"))) {
background = background.replaceFirst("color", "");
background = background.substring(1, background.lastIndexOf(')'));
backg = new BackgroundFromImage(colorParser.colorFromString(background));
}
if (background.contains("RGB")) {
String r = new String();
String g = new String();
String b = new String();
String[] rgb = null;
background = background.replaceFirst("color", "");
background = background.substring(5, background.lastIndexOf(")"));
background = background.substring(0, background.lastIndexOf(")"));
rgb = background.split(",");
backg = new BackgroundFromImage(new Color(Integer.parseInt(rgb[0]),
Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2])));
}
if (background.contains("image")) {
background = background.replaceFirst("image", "");
background = background.substring(1, background.lastIndexOf(')'));
URL treeURL = getClass().getResource(background);
Image img = null;
try {
img = ImageIO.read(treeURL);
backg = new BackgroundFromImage(img);
} catch (IOException e) {
e.printStackTrace();
}
}
}
if (line.contains("paddle_speed:")) {
String[] paddlespeedarr = line.split(":");
paddlespeed = paddlespeedarr[1];
}
if (line.contains("paddle_width:")) {
String[] paddlewidtharr = line.split(":");
paddlewidth = paddlewidtharr[1];
}
if (line.contains("block_definitions:")) {
String[] blockdefinitionsarr = line.split(":");
blockdefinitions = blockdefinitionsarr[1];
}
if (line.contains("blocks_start_x:")) {
String[] blocksstarsxarr = line.split(":");
blocksstarsx = blocksstarsxarr[1];
}
if (line.contains("blocks_start_y:")) {
String[] blocksstarsyarr = line.split(":");
blocksstarsy = blocksstarsyarr[1];
}
if (line.contains("row_height:")) {
String[] rowheightarr = line.split(":");
rowheight = rowheightarr[1];
}
if (line.contains("num_blocks:")) {
String[] numblocksarr = line.split(":");
numblocks = numblocksarr[1];
}
if (("START_BLOCKS").equals(line)) {
while (!("END_BLOCKS").equals(line)) {
line = br.readLine();
symbols.add(line);
}
symbols.remove(symbols.size() - 1);
}
if (!blockdefinitions.isEmpty()) {
InputStream ins = null;
Reader r = null;
try {
ins = ClassLoader.getSystemClassLoader().getResourceAsStream(blockdefinitions);
r = new InputStreamReader(ins, "UTF-8"); // leave charset out for default
factory = breader.fromReader(r);
ins.close();
r.close();
} catch (FileNotFoundException e1) {
e1.printStackTrace();
} catch (UnsupportedEncodingException e) {
e.printStackTrace();
}
blockdefinitions = new String();
}
flag3 = 1;
}
if (flag3 == 1) {
int startx = Integer.parseInt(blocksstarsx);
int starty = Integer.parseInt(blocksstarsy);
for (String l : symbols) {
if ((!("/n").equals(l)) && (!l.isEmpty())) {
String[]chars = l.split("");
for (int i = 0; i < chars.length; i++) {
if (factory.isSpaceSymbol(chars[i])) {
startx += factory.getSpaceWidth(chars[i]);
}
if (factory.isBlockSymbol(chars[i])) {
Block b = factory.getBlock(chars[i], startx, starty);
blocks.add(b);
startx += b.getWidth();
}
}
starty += Integer.parseInt(rowheight);
}
startx = Integer.parseInt(blocksstarsx);
}
LevelFromFile levelinfo = new LevelFromFile((Integer.parseInt(paddlespeed)) / 50, Integer.parseInt(paddlewidth),
Integer.parseInt(numblocks), backg, levelname, velocities, blocks);
levels.add(levelinfo);
}
}
br.close();
} catch (IOException e) {
e.printStackTrace();
}
return levels;
}
/**
 *
 *
 */
public void initmembers() {
this.flag3 = 0;
this.blocks = new ArrayList<Block>();
this.backg = new BackgroundFromImage();
this.breader = new BlocksDefinitionReader();
this.factory = new BlocksFromSymbolsFactory();
this.symbols = new ArrayList<String>();
this.velo = new ArrayList<String>();
this.velocities = new ArrayList<Velocity>();
this.levelname  = new String();
this.ballvelos = new String();
this.background = new String();
this.paddlespeed = new String();
this.paddlewidth = new String();
this.blockdefinitions = new String();
this.blocksstarsx = new String();
this.blocksstarsy = new String();
this.rowheight = new String();
this.numblocks = new String();
this.colorParser = new ColorsParser();
}
}