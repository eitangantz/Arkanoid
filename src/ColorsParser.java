import java.awt.Color;
/**
 *
 *
 *
 */
public class ColorsParser {
private Color color;
/**
 *
 * @param s - string
 * @return color
 */
public java.awt.Color colorFromString(String s) {
if (s.toLowerCase().equals("black")) {
color = Color.BLACK;
}
if (s.toLowerCase().equals("blue")) {
color = Color.BLUE;
}
if (s.toLowerCase().equals("cyan")) {
color = Color.CYAN;
}
if (s.toLowerCase().equals("darkgray")) {
color = Color.DARK_GRAY;
}
if (s.toLowerCase().equals("gray")) {
color = Color.GRAY;
}
if (s.toLowerCase().equals("green")) {
color = Color.GREEN;
}
if (s.toLowerCase().equals("yellow")) {
color = Color.YELLOW;
}
if (s.toLowerCase().equals("lightgray")) {
color = Color.LIGHT_GRAY;
}
if (s.toLowerCase().equals("magneta")) {
color = Color.MAGENTA;
}
if (s.toLowerCase().equals("orange")) {
color = Color.ORANGE;
}
if (s.toLowerCase().equals("pink")) {
color = Color.PINK;
}
if (s.toLowerCase().equals("red")) {
color = Color.RED;
}
if (s.toLowerCase().equals("white")) {
color = Color.WHITE;
}
return color;
}
}


