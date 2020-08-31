/**
 *
 *
 *
 */
public class ScoreInfo implements Comparable<ScoreInfo>, java.io.Serializable {
private String scoreName;
private int scorePoints;
/**
 *
 * @param name - name
 * @param score - score
 */
public ScoreInfo(String name, int score) {
this.scoreName = name;
this.scorePoints = score;
}
/**
 *
 * @return name
 */
public String getName() {
return this.scoreName;
}
/**
 *
 * @return score
 */
public int getScore() {
return this.scorePoints;
}
/**
 * @param o - socrinfo
 * @return int
 */
public int compareTo(ScoreInfo o) {
if (this.scorePoints == o.scorePoints) {
return 0;
} else if (this.scorePoints >  o.scorePoints) {
return 1;
} else {
return -1;
}
}
}
