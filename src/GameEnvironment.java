import java.util.List;
import java.util.ArrayList;
/**
* GameEnvironment class.
*/
public class GameEnvironment {
private List<Collidable> collidables;
/**
* GameEnvironment constructor.
*/
public GameEnvironment() {
this.collidables = new ArrayList<Collidable>();
}
// add the given collidable to the environment.
/**
* addCollidable.
* @param c - collidable.
* add the collidable shape to the game environment.
*/
public void addCollidable(Collidable c) {
this.collidables.add(c);
}
/**
 *
 * @param c - the collidable to remove from the collidables.
 */
public void removeCollidable(Collidable c) {
this.collidables.remove(c);
}
/**
* getClosestCollision.
* @param trajectory - trajectory of the ball.
* @return CollisionInfo about the collision.
* Assume an object moving from line.start() to line.end().
* If this object will not collide with any of the collidables
* in this collection, return null. Else, return the information
* about the closest collision that is going to occur.
*/
public CollisionInfo getClosestCollision(Line trajectory) {
int index = 0;
double min = 100;
for (int i = 0; i < this.collidables.size(); i++) {
if ((trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle()) != null)
&& (trajectory.start().
distance(trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle())))
<= min) {
index = i;
min = trajectory.start().
distance(trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle()));
}
}
if (min == 100) {
return null;
}
Point collisionpoint =
new Point(trajectory.closestIntersectionToStartOfLine(collidables.get(index).getCollisionRectangle()));
CollisionInfo info = new CollisionInfo(collisionpoint, collidables.get(index));
return info;
}
}
