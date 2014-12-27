package game.view.renderer.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.model.Map;

import java.util.Hashtable;

/**
 * Created by Влад on 17.12.14.
 */
public class RoadRender {
    private int _screenPosition;
    private int _halfScreen;
    private int _extScreen;

    public RoadRender() {
        _halfScreen = Gdx.graphics.getHeight() / 2;
        _extScreen = Gdx.graphics.getHeight() + _halfScreen;
    }

    public void render(SpriteBatch batch, Map map, int position) {
        _screenPosition = position % Gdx.graphics.getHeight();

        batch.draw(map._bg, 0, _halfScreen - _screenPosition, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if (_screenPosition <= _halfScreen)
            batch.draw(map._bg, 0, -_halfScreen - _screenPosition, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        else
            batch.draw(map._bg, 0, _extScreen - _screenPosition, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
