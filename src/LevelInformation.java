import java.util.List;
/**
 *
 *
 */
public interface LevelInformation {
/**
 *
 * @return number of balls.
 */
int numberOfBalls();
/**
 *
 * @return list of velocities for balls initialization.
 */
List<Velocity> initialBallVelocities();
/**
 *
 * @return paddle speed for initialization.
 */
int paddleSpeed();
/**
 *
 * @return paddle width for initialization.
 */
int paddleWidth();
/**
 *
 * @return level name.
 */
String levelName();
/**
 *
 * @return background.
 */
Sprite getBackground();
/**
 *
 * @return list of blocks for initialization.
 */
List<Block> blocks();
/**
 *
 * @return number of blocks.
 */
int numberOfBlocksToRemove();
/**
 *
 * @return list of center points for balls initialization.
 */
List<Point> centerpointsforballs();
/**
 *
 * @return list of colors for balls initialization.
 */
List<java.awt.Color> colorsforballs();
/**
 *
 * @return upper-lrft point of the paddle for initialization.
 */
Point upperLeft();
}
