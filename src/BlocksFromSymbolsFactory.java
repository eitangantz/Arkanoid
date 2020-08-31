import java.util.Map;
import java.util.TreeMap;
/**
 *
 *
 */
public class BlocksFromSymbolsFactory {
private Map<String, Integer> spacerWidths;
private Map<String, BlockCreator> blockCreators;
/**
 *
 * @param spacerWidths - spacerWidths
 * @param blockCreators - blockCreators
 */
public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
this.spacerWidths = spacerWidths;
this.blockCreators = blockCreators;
}
/**
 *
 * @param b - factory
 */
public BlocksFromSymbolsFactory(BlocksFromSymbolsFactory b) {
this.spacerWidths = b.spacerWidths;
this.blockCreators = b.blockCreators;
}
/**
 * default costructor.
 *
 */
public BlocksFromSymbolsFactory() {
this.spacerWidths = new TreeMap<String, Integer>();
this.blockCreators = new TreeMap<String, BlockCreator>();
}
/**
 *
 * @param s - string
 * @return true or false
 */
public boolean isSpaceSymbol(String s) {
if (s.length() == 1) {
char c = s.charAt(0);
if ((!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z'))) {
return true;
}
return false;
}
return false;
}
/**
 *
 * @param s - string
 * @return true or false
 */
public boolean isBlockSymbol(String s) {
if (s.length() == 1) {
char c = s.charAt(0);
if ((!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z'))) {
return false;
}
return true;
}
return false;
}
/**
 *
 * @param s - string
 * @param xpos - x
 * @param ypos - y
 * @return block
 */
public Block getBlock(String s, int xpos, int ypos) {
if (this.isBlockSymbol(s)) {
return this.blockCreators.get(s).create(xpos, ypos);
}
return null;
}
/**
 *
 * @param s - symbol
 * @return width
 */
public int getSpaceWidth(String s) {
return this.spacerWidths.get(s);
}
}
