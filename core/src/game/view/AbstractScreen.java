package game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

/**
 * Created by Влад on 16.12.14.
 */
public class AbstractScreen implements Screen {
    protected SpriteBatch _batch;
    protected Game _game;
    protected Point _location;

    public AbstractScreen(Game game) {
        _game = game;
    }

    @Override
    public void show() {
        _batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        _location = new Point(Gdx.input.getX(), Gdx.input.getY());
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
