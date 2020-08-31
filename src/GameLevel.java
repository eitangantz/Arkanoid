import biuoop.DrawSurface;
import biuoop.GUI;
/**
* Game class.
*/
public class GameLevel implements Animation {
private SpriteCollection sprites;
private GameEnvironment environment;
private GUI gui;
private Counter remainedblocks;
private Counter remainingBalls;
private Counter score;
private Counter numberoflives;
private AnimationRunner runner;
private boolean running;
private biuoop.KeyboardSensor keyboard;
private LevelInformation levelInformation;
/**
* Game constructor.
* @param info - the info of the level.
*/
public GameLevel(LevelInformation info) {
this.environment = new GameEnvironment();
this.sprites = new SpriteCollection();
this.remainedblocks = new Counter();
this.remainingBalls = new Counter();
this.score = new Counter();
this.numberoflives = new Counter();
this.gui = new GUI("Arkanoid", 800, 600);
this.runner = new AnimationRunner(this.gui);
this.keyboard = this.gui.getKeyboardSensor();
this.levelInformation = info;
}
/**
 *
 * @param info - information of the level.
 * @param ks - keyboard.
 * @param ar - Animation runner.
 * @param score - counter of the score.
 * @param lives - counter of the lives.
 * @param gui - gui.
 */
public GameLevel(LevelInformation info, biuoop.KeyboardSensor ks,
AnimationRunner ar, Counter score, Counter lives, GUI gui) {
this.environment = new GameEnvironment();
this.sprites = new SpriteCollection();
this.remainedblocks = new Counter();
this.remainingBalls = new Counter();
this.score = score;
this.numberoflives = lives;
this.gui = gui;
this.runner = ar;
this.keyboard = ks;
this.levelInformation = info;
}
/**
* addCollidable.
* @param c - the collidable shape we want to add to the game.
*/
public void addCollidable(Collidable c) {
this.environment.addCollidable(c);
}
/**
* addSprite.
* @param s - the Sprite shape we want to add to the game.
*/
public void addSprite(Sprite s) {
this.sprites.addSprite(s);
}
/**
 * @param c - collidable to remove from this game.
 */
public void removeCollidable(Collidable c) {
this.environment.removeCollidable(c);
}
/**
 * @param s - sprite to remove from the game.
 */
public void removeSprite(Sprite s) {
this.sprites.removeSprite(s);
}
/**
 * @return the game sprites collection.
 */
public SpriteCollection getsprites() {
return this.sprites;
}
/**
* initialize.
* Initialize a new game: create the Blocks and Ball (and Paddle)
* and add them to the game.
*/
public void initialize() {
BallRemover blr = new BallRemover(this, this.remainingBalls);
Point p3 = new Point(25, 580);
Rectangle r3 = new Rectangle(p3, 760, 20);
Block b3 = new Block(r3, java.awt.Color.red);
b3.setHitCount(-1);
b3.addHitListener(blr);
b3.addToGame(this);
Point p1 = new Point(0, 15);
Point p2 = new Point(0, 25);
Point p4 = new Point(775, 25);
Rectangle r1 = new Rectangle(p1, 800, 10);
Rectangle r2 = new Rectangle(p2, 25, 580);
Rectangle r4 = new Rectangle(p4, 25, 760);
Block b5 = new Block(r1, java.awt.Color.red);
Block b2 = new Block(r2, java.awt.Color.red);
Block b4 = new Block(r4, java.awt.Color.red);
b5.setHitCount(-1);
b2.setHitCount(-1);
b4.setHitCount(-1);
b5.addToGame(this);
b2.addToGame(this);
b4.addToGame(this);
this.levelInformation.getBackground().addToGame(this);
BlockRemover br = new BlockRemover(this, this.remainedblocks);
ScoreIndicator si = new ScoreIndicator(this.score, java.awt.Color.gray);
si.addToGame(this);
LivesIndicator li = new LivesIndicator(this.numberoflives);
li.addToGame(this);
ScoreTrackingListener stl = new ScoreTrackingListener(this.score);
NameLevel name = new NameLevel(this.levelInformation.levelName());
name.addToGame(this);
for (int i = 0; i < this.levelInformation.blocks().size(); i++) {
Block b = this.levelInformation.blocks().get(i);
b.addHitListener(br);
b.addHitListener(stl);
b.addToGame(this);
this.remainedblocks.increase(1);
}
}
/**
* playOneTurn.
* Run one turn of the game -- start the animation loop.
*/
public void playOneTurn() {
Paddle paddle = new Paddle(this.keyboard, this.levelInformation.upperLeft());
paddle.setSpeed(this.levelInformation.paddleSpeed());
paddle.setWitdh(this.levelInformation.paddleWidth());
paddle.addToGame(this);
for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
Ball b = new Ball((int) this.levelInformation.centerpointsforballs().get(i).getX(),
(int) this.levelInformation.centerpointsforballs().get(i).getY(), 5,
this.levelInformation.colorsforballs().get(i), this.environment);
b.addToGame(this);
this.remainingBalls.increase(1);
b.setVelocity(this.levelInformation.initialBallVelocities().get(i));
}
this.runner.run(new CountdownAnimation(2, 3, this.sprites));
this.running = true;
this.runner.run(this);
paddle.removeFromGame(this);
}
/**
 * run.
 * calls palyoneturn.
 * if there is no lives anymore than return,
 * else run again.
 */
public void run() {
playOneTurn();
this.numberoflives.decrease(1);
if ((this.numberoflives.getValue() == 0) || (this.remainedblocks.getValue() == 0)) {
gui.close();
return;
} else { run(); }
}
/**
 * @param d - surface to draw on.
 */
public void doOneFrame(DrawSurface d) {
if (this.remainedblocks.getValue() == 0) {
this.score.increase(100);
this.running = false;
}
if (this.remainingBalls.getValue() == 0) {
this.numberoflives.decrease(1);
this.running = false;
}
if (this.numberoflives.getValue() == 0) {
this.sprites.drawAllOn(d);
return;
}
if (this.keyboard.isPressed("p")) {
this.runner.run(new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY, new PauseScreen(this.keyboard)));
}
this.sprites.drawAllOn(d);
this.sprites.notifyAllTimePassed();
}
/**
 * @return true for stop run the animatiom, false for keepinig it running for another frame.
 */
public boolean shouldStop() {
return !this.running;
}
/**
 *
 * @return counter of the blocks.
 */
public Counter getRemainedBlocks() {
return this.remainedblocks;
}
/**
 *
 * @return counter of the balls.
 */
public Counter getRemainedBalls() {
return this.remainingBalls;
}
}
