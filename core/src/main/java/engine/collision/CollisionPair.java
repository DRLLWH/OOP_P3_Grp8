package engine.collision;

import engine.entity.Entity;

public class CollisionPair {
    public final Entity a;
    public final Entity b;

    public CollisionPair(Entity a, Entity b) {
        this.a = a;
        this.b = b;
    }
}
