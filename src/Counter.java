/**
 *
 * Counter.
 */
public class Counter {
private int count;
/**
 * constructor.
 * sets count to zero.
 */
public Counter() {
this.count = 0;
}
/**
 * @param number - number to add to the count.
 */
void increase(int number) {
this.count += number;
}
/**
 * @param number - number to subtract from the count.
 */
void decrease(int number) {
this.count -= number;
}
/**
 *
 * @return this count.
 */
int getValue() {
return this.count;
}
/**
 *
 * @param val - value.
 */
void setValue(int val) {
this.count = val;
}
}
