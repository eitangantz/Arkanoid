
import java.io.File;
import java.io.IOException;
import java.util.List;
import biuoop.GUI;
/**
 *
 *
 */
public class GameFlow {
private AnimationRunner ani;
private Counter score;
private Counter numberoflives;
private biuoop.KeyboardSensor keyboard;
private GUI gui;
/**
 *
 *
 */
public GameFlow() {
this.gui = new GUI("Arkanoid", 800, 600);
this.ani = new AnimationRunner(this.gui);
this.keyboard = this.gui.getKeyboardSensor();;
this.score = new Counter();
this.numberoflives = new Counter();
}
/**
 *
 * @param sets - list of sets to run.
 */
public void runLevels(List<SetFromFile> sets) {
HighScoresTable scores = new HighScoresTable(4);
File file = new File("highscores");
if (!file.exists()) {
try {
scores.save(file);
} catch (IOException e) {
e.printStackTrace();
}
}
while (true) {
Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arknoid", this.keyboard);
SubMenu<Task<Void>> submenu = new SubMenu<Task<Void>>("level sets", this.keyboard);
for (int i = 0; i < sets.size(); i++) {
submenu.addSelection(sets.get(i).getKey(), sets.get(i).getMassage(), new GameTask(this.ani,  this.score,
this.numberoflives,  this.keyboard,  this.gui,  sets.get(i).getLevels()));
}
menu.addSubMenu("s", "Start Game", submenu);
menu.addSelection("h", "high score",
new ShowHiScoresTask(this.ani, new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY
, new HighScoresAnimation(scores.loadFromFile(file)))));
menu.addSelection("q", "quit", new QuitTask());
this.ani.run(menu);
if (menu.getKey().equals("s")) {
this.ani.run(submenu);
Task<Void> task = submenu.getStatus();
task.run();
} else {
Task<Void> task = menu.getStatus();
task.run();
}
}
}
}
