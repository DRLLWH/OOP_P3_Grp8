package engine.entity;

public class Entity {
    private float x, y;
    private float width, height;
    private boolean active = true;

    public Entity(float x, float y, float width, float height) {
        this.x = x; this.y = y;
        this.width = width; this.height = height;
    }

    public float getX() { return x; }
    public float getY() { return y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
