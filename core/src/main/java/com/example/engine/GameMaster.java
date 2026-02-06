package com.example.engine;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class GameMaster extends ApplicationAdapter {

    private CollisionManager collisionManager;
    private List<Entity> entities;

    @Override
    public void create() {
        entities = new ArrayList<>();

        CollisionDetector detector = new CollisionDetector();
        CollisionResolver resolver = new CollisionResolver();
        collisionManager = new CollisionManager(detector, resolver);

        collisionManager.setBounds(0, 800, 0, 600);

        // Add test entities
        entities.add(new Entity(100, 100, 50, 50));
        entities.add(new Entity(120, 120, 50, 50));
    }

    @Override
    public void render() {
        collisionManager.checkCollisions(entities);
    }
}
