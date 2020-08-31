import java.io.File;
import java.io.IOException;
import java.util.List;
import biuoop.DialogManager;
import biuoop.GUI;
/**
 *
 *
 */
public class GameTask implements Task<Void> {
private AnimationRunner ani;
private Counter score;
private Counter numberoflives;
private biuoop.KeyboardSensor keyboard;
private GUI gui;
private List<LevelInformation> levels;
/**
 *
 * @param ani - runner
 * @param score - score
 * @param numberoflives - lives
 * @param keyboard - keyboard
 * @param gui - gui
 * @param levels - levels
 */
public GameTask(AnimationRunner ani, Counter score, Counter numberoflives,
biuoop.KeyboardSensor keyboard, GUI gui, List<LevelInformation> levels) {
this.ani = ani;
this.score = score;
this.numberoflives = numberoflives;
this.keyboard = keyboard;
this.levels = levels;
this.gui = gui;
}
/**
 *@return null
 */
public Void run() {
this.numberoflives.setValue(0);
this.numberoflives.increase(7);
this.score.setValue(0);
File file = new File("highscores");
HighScoresTable scores = new HighScoresTable().loadFromFile(file);
int count = 0;
for (LevelInformation levelInfo : levels) {
count++;
GameLevel level = new GameLevel(levelInfo, this.keyboard, this.ani, this.score, this.numberoflives, this.gui);
level.initialize();
while ((level.getRemainedBlocks().getValue() > 0) && (this.numberoflives.getValue() > 0)) {
level.playOneTurn();
}
KeyPressStoppableAnimation end = new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY
, new EndScreen(this.keyboard, this.score, this.numberoflives));
if (this.numberoflives.getValue() == 0) {
if ((scores.loadFromFile(file).getRank(this.score.getValue()) <= scores.loadFromFile(file).getHighScores().size())
|| (scores.loadFromFile(file).size() > scores.loadFromFile(file).getHighScores().size())) {
DialogManager dialog = gui.getDialogManager();
String name = dialog.showQuestionDialog("Enter Name", "What is your name?", "");
ScoreInfo playerscore = new ScoreInfo(name, this.score.getValue());
scores.add(playerscore);
try {
scores.save(file);
} catch (IOException e) {
e.printStackTrace();
}
}
this.ani.run(end);
KeyPressStoppableAnimation scorepress = new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY
, new HighScoresAnimation(scores.loadFromFile(file)));
this.ani.run(scorepress);
break;
}
if (count == levels.size()) {
if ((scores.loadFromFile(file).getRank(this.score.getValue()) <= scores.loadFromFile(file).getHighScores().size())
|| (scores.loadFromFile(file).size() > scores.loadFromFile(file).getHighScores().size())) {
DialogManager dialog = gui.getDialogManager();
String name = dialog.showQuestionDialog("Enter Name", "What is your name?", "");
ScoreInfo playerscore = new ScoreInfo(name, this.score.getValue());
scores.add(playerscore);
try {
scores.save(file);
} catch (IOException e) {
e.printStackTrace();
}
}
this.ani.run(end);
KeyPressStoppableAnimation scorepress = new KeyPressStoppableAnimation(this.keyboard, this.keyboard.SPACE_KEY
, new HighScoresAnimation(scores.loadFromFile(file)));
this.ani.run(scorepress);
break;
}
}
return null;
}
}
