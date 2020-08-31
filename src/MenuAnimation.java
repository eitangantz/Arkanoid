import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 *
 *
 *
 * @param <T>
 */
public class MenuAnimation<T> implements Menu<T> {
private boolean stop;
private String title;
private List<String> keys;
private List<String> messages;
private List<T> tasks;
private T status;
private KeyboardSensor keyboard;
private Menu<T> sub;
private String subkey;
private String submessage;
private String returnkey;
/**
 *
 * @param menutitle - menutitle
 * @param keyboard - keyboard
 */
public MenuAnimation(String menutitle, KeyboardSensor keyboard) {
this.title = menutitle;
this.keyboard = keyboard;
this.stop = false;
this.keys = new ArrayList<String>();
this.messages = new ArrayList<String>();
this.tasks = new ArrayList<T>();
}
/**
 * @param key - key
 * @param message - message
 * @param returnVal - task
 */
public void addSelection(String key, String message, T returnVal) {
this.keys.add(key);
this.messages.add(message);
this.tasks.add(returnVal);
}
/**
 * @return task
 */
public T getStatus() {
return this.status;
}
/**
 * @param d - drawsurface
 */
public void doOneFrame(DrawSurface d) {
d.setColor(java.awt.Color.yellow);
d.drawText(70, 70, this.title, 40);
d.setColor(java.awt.Color.green);
d.drawText(70, 100, '(' + this.subkey + ')', 20);
d.drawText(95, 100, this.submessage, 25);
for (int i = 0; i < keys.size(); i++) {
d.drawText(70, 125 + i * 25, '(' + this.keys.get(i) + ')', 20);
}
for (int i = 0; i < messages.size(); i++) {
d.drawText(95, 125 + i * 25, this.messages.get(i), 25);
}
for (int i = 0; i < this.keys.size(); i++) {
if (this.keyboard.isPressed(this.keys.get(i))) {
this.status = this.tasks.get(i);
returnkey = this.keys.get(i);
this.stop = true;
}
}
if (this.keyboard.isPressed(this.subkey)) {
returnkey = subkey;
this.stop = true;
}
}
/**
 * @return true or false
 */
public boolean shouldStop() {
return this.stop;
}
/**
 * @param key - key
 * @param message - mesage
 * @param subMenu - sub
 */
public void addSubMenu(String key, String message, Menu<T> subMenu) {
this.subkey = key;
this.submessage = message;
this.sub = subMenu;
}
/**
 * @return key
 */
public String getKey() {
return this.returnkey;
}
}
