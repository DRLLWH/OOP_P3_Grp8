package engine.collision;

import java.util.List;
import engine.entity.Entity;

public class CollisionManager {

    private float xMin, xMax, yMin, yMax;

    private final CollisionDetector detector;
    private final CollisionResolver resolver;

    public CollisionManager(CollisionDetector detector, CollisionResolver resolver) {
        this.detector = detector;
        this.resolver = resolver;
    }

    public void setBounds(float xMin, float xMax, float yMin, float yMax) {
        this.xMin = xMin; this.xMax = xMax;
        this.yMin = yMin; this.yMax = yMax;
    }

    public void checkCollisions(List<Entity> entities) {
        // 1) Entity-entity collisions
        List<CollisionPair> pairs = detector.checkAllCollisions(entities);
        for (CollisionPair p : pairs) {
            resolver.resolve(p);
        }

        // 2) Barrier / off-screen handling
        for (Entity e : entities) {
            if (!e.isActive()) continue;

            if (detector.isOutOfBounds(e, xMin, xMax, yMin, yMax)) {
                // choose: clamp via detector or let resolver decide
                // If your design says resolver decides outcome:
                resolver.resolveOutOfBounds(e, xMin, xMax, yMin, yMax);
            }
        }
    }

    public void clearCollisions() {
        detector.clear();
        resolver.clearResolved();
    }
}

