/**
* CollisionInfo class.
*/
public class CollisionInfo {
private Point collisionpoint;
private Collidable shape;
/**
* CollisionInfo constructor.
* @param collision - collision point.
* @param shape - collidable shape.
*/
public CollisionInfo(Point collision, Collidable shape) {
this.collisionpoint = collision;
this.shape = shape;
}
/**
* CollisionInfo constructor.
* @return this collision point.
*/
public Point collisionPoint() {
return this.collisionpoint;
}
/**
* CollisionInfo constructor.
* @return this shape.
*/
public Collidable collisionObject() {
return this.shape;
}
}
