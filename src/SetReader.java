
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
/**
 *
 *
 *
 */
public class SetReader {
/**
 *
 * @param reader - reader
 * @return list of sets
 */
public List<SetFromFile> fromReader(java.io.Reader reader) {
String key = new String();
String massage = new String();
List<SetFromFile> sets = new ArrayList<SetFromFile>();
LineNumberReader br = null;
String line;
br = new LineNumberReader(reader);
try {
while ((line = br.readLine()) != null) {
if (br.getLineNumber() % 2 == 1) {
String[] s = line.split(":");
key = s[0];
massage = s[1];
} else {
LevelSpecificationReader spec = new LevelSpecificationReader();
List<LevelInformation> levelsfromfiles = new ArrayList<LevelInformation>();
String path = line;
Reader r = null;
InputStream ins = null;
//ins = new FileInputStream(path);
ins = ClassLoader.getSystemClassLoader().getResourceAsStream(path);
r = new InputStreamReader(ins, "UTF-8");
levelsfromfiles = spec.fromReader(r);
SetFromFile set = new SetFromFile(key, massage, levelsfromfiles);
sets.add(set);
}
}
} catch (IOException e) {
e.printStackTrace();
}
return sets;
}
}
