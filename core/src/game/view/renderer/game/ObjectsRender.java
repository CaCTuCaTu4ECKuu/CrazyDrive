package game.view.renderer.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.controller.Calculating;
import game.model.GameObject;
import game.model.Map;
import game.model.Player;
import game.model.Unit;

import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Влад on 17.12.14.
 */
public class ObjectsRender {
    private int _lastObject;
    private boolean _checked;
    private Hashtable _textures;

    private float chance;
    private Point playerPosition;

    public ObjectsRender() {
        _textures = new Hashtable();
        _lastObject = 0;
        _checked = true;    // Когда будут карты, а не рандом
    }

    private void prepareNewObjects() {
        if (!_checked) {
            for (int i = _lastObject; i < 0; i++) {

            }
            _checked = true;
        }
    }

    public void randomNewObjects() {

    }

    public void render(SpriteBatch batch, Hashtable objects, Player player) {
        // randomNewObjects();
        GameObject gO;
        Texture t;
        int screenPos;
        for (Object o : objects.keySet()) {
            gO = (GameObject)objects.get(o);
            if (!_textures.containsKey(gO.TextureName))
                _textures.put(gO.TextureName, new Texture(Gdx.files.internal(gO.TextureName)));
            t = (Texture)_textures.get(gO.TextureName);
            batch.draw(t,  gO.Position.x, Gdx.graphics.getHeight() - Calculating.screenPosition((int)player.Position.y, gO.Position.y), (int)gO.Size.getWidth(), (int)gO.Size.getHeight());
        }
        batch.draw(player._carTexture, player.ScreenPosition().x, 5, (int)player.Size.getWidth(), (int)player.Size.getHeight());
    }
}
