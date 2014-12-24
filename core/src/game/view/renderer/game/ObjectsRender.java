package game.view.renderer.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.model.Map;
import game.model.Player;

import java.util.Hashtable;

/**
 * Created by Влад on 17.12.14.
 */
public class ObjectsRender {
    public ObjectsRender() {

    }

    public void render(SpriteBatch batch, Map map, Player player) {
        batch.draw(player._car, player.ScreenPosition.x, player.ScreenPosition.y, 75, 150);
    }
}
