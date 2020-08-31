import java.util.List;
/**
 *
 *
 *
 */
public class SetFromFile {
private String key;
private String massage;
private List<LevelInformation> levels;
/**
 *
 * @param k - k
 * @param m - m
 * @param lvls - lvls
 */
public SetFromFile(String k, String m, List<LevelInformation> lvls) {
this.key = k;
this.massage = m;
this.levels = lvls;
}
/**
 *
 * @return key
 */
public String getKey() {
return this.key;
}
/**
 *
 * @return message
 */
public String getMassage() {
return this.massage;
}
/**
 *
 * @return list
 */
public List<LevelInformation> getLevels() {
return this.levels;
}
}
