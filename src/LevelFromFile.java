import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 *
 *
 *
 */
public class LevelFromFile implements LevelInformation {
private int numberofballs;
private int paddlespeed;
private int paddlewitdh;
private Point paddleupperleft;
private int numberofblockstoremove;
private BackgroundFromImage background;
private String levelname;
private List<Velocity> velocities;
private List<Block> blocks;
/**
 *
 *
 * @param paddlespeed - paddlespeed
 * @param paddlewidth - paddlewidth
 * @param numberofblocks - numberofblocks
 * @param background - background
 * @param levelname - levelname
 * @param velocities - velocities
 * @param blocks - blocks
 */
public LevelFromFile(int paddlespeed,
int paddlewidth, int numberofblocks, BackgroundFromImage background
, String levelname, List<Velocity> velocities, List<Block> blocks) {
this.numberofballs = velocities.size();
this.background = background;
this.blocks = blocks;
this.levelname = levelname;
this.numberofblockstoremove = numberofblocks;
this.paddlespeed = paddlespeed;
this.paddlewitdh = paddlewidth;
this.paddleupperleft =  new Point(400 - paddlewidth / 2, 570);
this.velocities = velocities;
}
/**
 * @return list
 */
public List<Block> blocks() {
return this.blocks;
}
/**
 * @return list
 */
public List<Point> centerpointsforballs() {
List<Point> centerpoints = new ArrayList<Point>();
for (int i = 0; i < this.numberofballs; i++) {
Point p = new Point(400, 550);
centerpoints.add(p);
}
return centerpoints;
}
/**
 * @return list
 */
public List<Color> colorsforballs() {
List<java.awt.Color> colors = new ArrayList<java.awt.Color>();
for (int i = 0; i < this.numberofballs; i++) {
colors.add(java.awt.Color.white);
}
return colors;
}
/**
 * @return background
 */
public Sprite getBackground() {
return this.background;
}
/**
 * @return list
 */
public List<Velocity> initialBallVelocities() {
return this.velocities;
}
/**
 * @return name
 */
public String levelName() {
return this.levelname;
}
/**
 * @return numbr of balls
 */
public int numberOfBalls() {
return this.numberofballs;
}
/**
 * @return number of blocks
 */
public int numberOfBlocksToRemove() {
return this.numberofblockstoremove;
}
/**
 * @return speed
 */
public int paddleSpeed() {
return this.paddlespeed;
}
/**
 * @return width
 */
public int paddleWidth() {
return this.paddlewitdh;
}
/**
 * @return upperleft
 */
public Point upperLeft() {
return this.paddleupperleft;
}
}