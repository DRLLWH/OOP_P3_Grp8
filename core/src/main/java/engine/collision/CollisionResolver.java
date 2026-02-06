package engine.collision;


import java.util.ArrayList;
import java.util.List;
import engine.entity.Entity;

public class CollisionResolver {

    private String responseType = "NONE"; // e.g. NONE, CLAMP, DESTROY, BOUNCE
    private final List<CollisionPair> resolved = new ArrayList<>();

    public void resolve(CollisionPair pair) {
        // Keep abstract: default does nothing
        // In your simulation layer you can set responseType = "DESTROY" etc.
        resolved.add(pair);

        if ("DESTROY".equals(responseType)) {
            // example abstract behavior: deactivate both
            pair.a.setActive(false);
            pair.b.setActive(false);
        }
    }

    public void resolveOutOfBounds(Entity e, float xMin, float xMax, float yMin, float yMax) {
        if ("CLAMP".equals(responseType)) {
            float clampedX = Math.max(xMin, Math.min(e.getX(), xMax - e.getWidth()));
            float clampedY = Math.max(yMin, Math.min(e.getY(), yMax - e.getHeight()));
            e.setX(clampedX);
            e.setY(clampedY);
        } else if ("DESTROY".equals(responseType)) {
            e.setActive(false);
        }
        // else NONE = do nothing
    }

    public void clearResolved() {
        resolved.clear();
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }
}

