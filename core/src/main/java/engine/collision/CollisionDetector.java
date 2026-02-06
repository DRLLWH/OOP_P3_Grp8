package engine.collision;

import java.util.ArrayList;
import java.util.List;
import engine.entity.Entity;

public class CollisionDetector {

    private final List<CollisionPair> activeCollisions = new ArrayList<>();
    private float detectionThreshold = 0f; // optional buffer (can stay 0)

    public boolean detectCollision(Entity a, Entity b) {
        // Axis-Aligned Bounding Box (AABB)
        float ax1 = a.getX(), ay1 = a.getY();
        float ax2 = a.getX() + a.getWidth(), ay2 = a.getY() + a.getHeight();

        float bx1 = b.getX(), by1 = b.getY();
        float bx2 = b.getX() + b.getWidth(), by2 = b.getY() + b.getHeight();

        return ax1 < bx2 - detectionThreshold &&
               ax2 > bx1 + detectionThreshold &&
               ay1 < by2 - detectionThreshold &&
               ay2 > by1 + detectionThreshold;
    }

    public List<CollisionPair> checkAllCollisions(List<Entity> entities) {
        activeCollisions.clear();

        for (int i = 0; i < entities.size(); i++) {
            Entity a = entities.get(i);
            if (!a.isActive()) continue;

            for (int j = i + 1; j < entities.size(); j++) {
                Entity b = entities.get(j);
                if (!b.isActive()) continue;

                if (detectCollision(a, b)) {
                    activeCollisions.add(new CollisionPair(a, b));
                }
            }
        }
        return new ArrayList<>(activeCollisions);
    }

    public boolean isOutOfBounds(Entity e, float xMin, float xMax, float yMin, float yMax) {
        return e.getX() < xMin ||
               e.getX() + e.getWidth() > xMax ||
               e.getY() < yMin ||
               e.getY() + e.getHeight() > yMax;
    }

    public void clampToBounds(Entity e, float xMin, float xMax, float yMin, float yMax) {
        float clampedX = Math.max(xMin, Math.min(e.getX(), xMax - e.getWidth()));
        float clampedY = Math.max(yMin, Math.min(e.getY(), yMax - e.getHeight()));
        e.setX(clampedX);
        e.setY(clampedY);
    }

    public void clear() {
        activeCollisions.clear();
    }

    // optional setters
    public void setDetectionThreshold(float t) { this.detectionThreshold = t; }
}
