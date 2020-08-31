/**
 *
 *
 *
 * @param <T> - task
 */
public interface Menu<T> extends Animation {
/**
 *
 * @param key - key
 * @param message - message
 * @param returnVal - task
 */
void addSelection(String key, String message, T returnVal);
/**
 *
 * @return task
 */
T getStatus();
/**
 *
 * @param key - key
 * @param message - message
 * @param subMenu - submenu
 */
void addSubMenu(String key, String message, Menu<T> subMenu);
/**
 *
 * @return key
 */
String getKey();
}
