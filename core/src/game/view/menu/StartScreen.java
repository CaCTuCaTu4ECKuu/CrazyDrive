package game.view.menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.controller.Calculating;
import game.view.MenuScreen;

import java.awt.*;

/**
 * Created by Влад on 16.12.14.
 */
public class StartScreen extends MenuScreen {
    private Point _startSingle;
    private Point _multiplayer;
    private Point _settings;
    private Point _exit;
    private Dimension _btnDimension;
    private Point _location;

    public StartScreen(Game game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        _location = new Point(Gdx.input.getX(), Gdx.input.getY());
        super.render(delta);
        _batch.begin();
        if (Calculating.isInside(new Rectangle(_startSingle, _btnDimension), _location))
            _batch.draw(_button, _startSingle.x, Gdx.graphics.getHeight() - _startSingle.y - _button.getHeight());
        else
            _batch.draw(_buttonActive, _startSingle.x, Gdx.graphics.getHeight() - _startSingle.y - _button.getHeight());
        if (Calculating.isInside(new Rectangle(_multiplayer, _btnDimension), _location))
            _batch.draw(_button, _multiplayer.x, Gdx.graphics.getHeight() - _multiplayer.y - _button.getHeight());
        else
            _batch.draw(_buttonActive, _multiplayer.x, Gdx.graphics.getHeight() - _multiplayer.y - _button.getHeight());
        if (Calculating.isInside(new Rectangle(_settings, _btnDimension), _location))
            _batch.draw(_button, _settings.x, Gdx.graphics.getHeight() - _settings.y - _button.getHeight());
        else
            _batch.draw(_buttonActive, _settings.x, Gdx.graphics.getHeight() - _settings.y - _button.getHeight());
        if (Calculating.isInside(new Rectangle(_exit, _btnDimension), _location))
            _batch.draw(_button, _exit.x, Gdx.graphics.getHeight() - _exit.y - _button.getHeight());
        else
            _batch.draw(_buttonActive, _exit.x, Gdx.graphics.getHeight() - _exit.y - _button.getHeight());
        _batch.end();
    }

    @Override
    public void show() {
        super.show();
        _btnDimension = new Dimension(_button.getWidth(), _button.getHeight());
        _startSingle = new Point((Gdx.graphics.getWidth() / 2) - (_button.getWidth() / 2), 100 - _button.getHeight());
        _multiplayer = new Point((Gdx.graphics.getWidth() / 2) - (_button.getWidth() / 2), 150 - _button.getHeight());
        _settings = new Point((Gdx.graphics.getWidth() / 2) - (_button.getWidth() / 2), 200 - _button.getHeight());
        _exit = new Point((Gdx.graphics.getWidth() / 2) - (_button.getWidth() / 2), 250 - _button.getHeight());
    }
}
