import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 *
 *
 *
 * @param <T> - task
 */
public class SubMenu<T> implements Menu<T> {
private boolean stop;
private List<String> keys;
private List<String> messages;
private List<T> tasks;
private T status;
private KeyboardSensor keyboard;
private String title;
/**
 *
 * @param title - title
 * @param keyboard - keyboard
 */
public SubMenu(String title, KeyboardSensor keyboard) {
this.keyboard = keyboard;
this.stop = false;
this.keys = new ArrayList<String>();
this.messages = new ArrayList<String>();
this.tasks = new ArrayList<T>();
this.title = title;
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
 * @return status(task)
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
for (int i = 0; i < keys.size(); i++) {
d.drawText(70, 100 + i * 25, '(' + this.keys.get(i) + ')', 20);
}
for (int i = 0; i < messages.size(); i++) {
d.drawText(95, 100 + i * 25, this.messages.get(i), 25);
}
for (int i = 0; i < this.keys.size(); i++) {
if (this.keyboard.isPressed(this.keys.get(i))) {
this.status = this.tasks.get(i);
this.stop = true;
}
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
 * @param message - message
 * @param subMenu - sub menu
 */
public void addSubMenu(String key, String message, Menu<T> subMenu) {
}
/**
 * @return null
 */
public String getKey() {
return null;
}
}
