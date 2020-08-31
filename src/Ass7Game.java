
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 */
public class Ass7Game {
/**
* @param args - args
 * @throws IOException - exc
*/
public static void main(String[] args) throws IOException {
GameFlow gameflow = new GameFlow();
if (args.length == 0) {
List<SetFromFile> sets = new ArrayList<SetFromFile>();
SetReader setreader = new SetReader();
InputStream ins = null; // raw byte-stream
Reader r = null; // cooked reader
try {
ins = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
//ins = new FileInputStream("resources/level_sets.txt");
r = new InputStreamReader(ins, "UTF-8"); // leave charset out for default
sets = setreader.fromReader(r);
ins.close();
r.close();
} catch (FileNotFoundException e1) {
e1.printStackTrace();
} catch (UnsupportedEncodingException e) {
e.printStackTrace();
}
gameflow.runLevels(sets);
}
if (args.length == 1) {
List<SetFromFile> sets = new ArrayList<SetFromFile>();
SetReader setreader = new SetReader();
InputStream ins = null; // raw byte-stream
Reader r = null; // cooked reader
try {
ins = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
//ins = new FileInputStream(args[0]);
r = new InputStreamReader(ins, "UTF-8"); // leave charset out for default
sets = setreader.fromReader(r);
ins.close();
r.close();
} catch (FileNotFoundException e1) {
e1.printStackTrace();
} catch (UnsupportedEncodingException e) {
e.printStackTrace();
}
gameflow.runLevels(sets);
}
}
}