import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
/**
 *
 *
 *
 */
public class HighScoresTable implements java.io.Serializable {
private int size;
private List<ScoreInfo> scores;
/**
 *
 * @param size - size
 */
public HighScoresTable(int size) {
this.size = size;
this.scores = new ArrayList<ScoreInfo>();
}
/**
 *
 * @param high - highscoretable
 */
public HighScoresTable(HighScoresTable high) {
this.scores = new ArrayList<ScoreInfo>();
this.scores.addAll(high.scores);
this.size = high.size;
}
/**
 *
 * default constructor.
 */
public HighScoresTable() {
this.scores = new ArrayList<ScoreInfo>();
}
/**
 *
 * @param score - score
 */
public void add(ScoreInfo score) {
if (this.scores.size() < this.size) {
this.scores.add(score);
} else if (this.scores.size() == this.size) {
if (this.getRank(score.getScore()) <= this.size) {
this.scores.add(score);
Collections.sort(this.scores, Collections.reverseOrder());
this.scores.remove(this.scores.size() - 1);
} else {
return;
}
}
}
/**
 *
 * @return size
 */
public int size() {
return this.size;
}
/**
 *
 * @return list
 */
public List<ScoreInfo> getHighScores() {
List<ScoreInfo> sortedscores = new ArrayList<ScoreInfo>();
sortedscores.addAll(this.scores);
Collections.sort(sortedscores, Collections.reverseOrder());
return sortedscores;
}
/**
 *
 * @param score - score
 * @return rank
 */
public int getRank(int score) {
List<ScoreInfo> sortedscores = new ArrayList<ScoreInfo>();
sortedscores.addAll(this.scores);
Collections.sort(sortedscores, Collections.reverseOrder());
for (int i = 0; i < sortedscores.size(); i++) {
if (score > sortedscores.get(i).getScore()) {
return i + 1;
}
}
return this.size + 1;
}
/**
 *
 *
 */
public void clear() {
this.scores = new ArrayList<ScoreInfo>();
}
/**
 *
 * @param filename - filename
 * @throws IOException - exc
 */
public void load(File filename) throws IOException {
try {
FileInputStream fileIn = new FileInputStream(filename);
ObjectInputStream in = new ObjectInputStream(fileIn);
this.clear();
HighScoresTable high = new HighScoresTable();
high = (HighScoresTable) in.readObject();
this.scores = high.scores;
this.size = high.size;
in.close();
fileIn.close();
} catch (IOException i) {
i.printStackTrace();
return;
} catch (ClassNotFoundException c) {
System.out.println("HighScoreTable class not found");
c.printStackTrace();
return;
}
}
/**
 *
 * @param filename - filename
 * @throws IOException - exc
 */
public void save(File filename) throws IOException {
try {
FileOutputStream fileOut = new FileOutputStream(filename);
ObjectOutputStream out = new ObjectOutputStream(fileOut);
out.writeObject(this);
out.close();
fileOut.close();
} catch (IOException i) {
i.printStackTrace();
}
}
/**
 *
 * @param filename - filename
 * @return highscortable
 */
public static HighScoresTable loadFromFile(File filename) {
try {
FileInputStream fileIn = new FileInputStream(filename);
ObjectInputStream in = new ObjectInputStream(fileIn);
HighScoresTable high = new HighScoresTable();
high = (HighScoresTable) in.readObject();
in.close();
fileIn.close();
return high;
} catch (IOException i) {
i.printStackTrace();
return new HighScoresTable();
} catch (ClassNotFoundException c) {
System.out.println("HighScoreTable class not found");
c.printStackTrace();
return new HighScoresTable();
}
}
/**
 *
 * @param size1 - size
 */
public void setSize(int size1) {
this.size = size1;
}
}