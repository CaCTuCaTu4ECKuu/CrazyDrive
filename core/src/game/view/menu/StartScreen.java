package game.view.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.view.MenuScreen;

/**
 * Created by Влад on 16.12.14.
 */
public class StartScreen extends MenuScreen {
    public StartScreen(Game game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        _batch.begin();
        _batch.draw(_button, (Gdx.graphics.getWidth() / 2) - (_button.getWidth() / 2), Gdx.graphics.getHeight() - 100);
        _batch.draw(_button, (Gdx.graphics.getWidth() / 2) - (_button.getWidth() / 2), Gdx.graphics.getHeight() - 150);
        _batch.draw(_button, (Gdx.graphics.getWidth() / 2) - (_button.getWidth() / 2), Gdx.graphics.getHeight() - 200);
        _batch.draw(_button, (Gdx.graphics.getWidth() / 2) - (_button.getWidth() / 2), Gdx.graphics.getHeight() - 250);
        _batch.end();

    }

    @Override
    public void show() {
        super.show();
    }
}
