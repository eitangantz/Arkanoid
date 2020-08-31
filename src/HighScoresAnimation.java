import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
/**
 *
 *
 *
 */
public class HighScoresAnimation implements Animation {
private boolean stop;
private HighScoresTable highScores;
/**
 *
 * @param scores - highscoretable
 */
public HighScoresAnimation(HighScoresTable scores) {
this.highScores = scores;
this.stop = false;
}
/**
 * @param d - drawsurface
 */
public void doOneFrame(DrawSurface d) {
d.setColor(java.awt.Color.yellow);
d.drawText(50, 100, "High Scores", 50);
d.setColor(java.awt.Color.green);
d.drawText(100, 140, "Player Name", 32);
d.drawText(500, 140, "Score", 32);
d.drawLine(100, 145, 700, 145);
List<ScoreInfo> scores = new ArrayList<ScoreInfo>();
try {
this.highScores.load(new File("highscores"));
} catch (IOException e) {
e.printStackTrace();
}
scores = this.highScores.getHighScores();
d.setColor(java.awt.Color.orange);
for (int i = 0; i < scores.size(); i++) {
d.drawText(100, 180 + 35 * i, scores.get(i).getName(), 32);
Integer itos = new Integer(scores.get(i).getScore());
d.drawText(500, 180 + 35 * i, itos.toString(), 32);
}
d.setColor(java.awt.Color.blue);
d.drawText(230, 500, "Press space to continue", 32);
}
/**
 * @return true or false
 */
public boolean shouldStop() {
return this.stop;
}
}
