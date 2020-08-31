import biuoop.DrawSurface;
/**
* Collidable interface.
*/
public interface Collidable {
/**
* getCollisionRectangle.
* @return Return rectangle of the collidable shape.
*/
Rectangle getCollisionRectangle();
/**
* hit.
* @param collisionPoint - the point of the collision.
* @param currentVelocity - the current velocity of thr ball.
* @param trajectory - line which start point is the current location of the ball
* @param hitter - ball of the collision.
* and end point is the location after one move.
* @return Return the new velocity of the ball.
*/
Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity, Line trajectory);
/**
* drawOn.
* @param surface - the surface which we draw on.
*/
void drawOn(DrawSurface surface);
}
