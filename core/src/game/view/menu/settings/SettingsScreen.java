package game.view.menu.settings;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import game.controller.Calculating;
import game.view.GameScreen;
import game.view.MenuScreen;
import game.view.menu.MultiplayerScreen;
import game.view.menu.StartScreen;

import java.awt.*;

/**
 * Created by Влад on 16.12.14.
 */
public class SettingsScreen extends MenuScreen {
    private Point _back;

    private navigation _navigation;

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        _batch.begin();

        if (Calculating.isInside(new Rectangle(_back, _btnDimension), _location))
            _batch.draw(_buttonActive, _back.x, Gdx.graphics.getHeight() - _back.y - _button.getHeight());
        else
            _batch.draw(_button, _back.x, Gdx.graphics.getHeight() - _back.y - _button.getHeight());

        _font.draw(_batch, "Back", _back.x, Gdx.graphics.getHeight() - _back.y - _button.getHeight());

        _batch.end();
    }

    public SettingsScreen(Game game) {
        super(game);
        _back = new Point((Gdx.graphics.getWidth() / 2) - (_button.getWidth() / 2), 250 - _button.getHeight());
        _navigation = new navigation();
    }
    private class navigation extends InputAdapter {
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (button == Input.Buttons.LEFT) {
                if (Calculating.isInside(new Rectangle(_back, _btnDimension), _location))
                    _game.setScreen(new StartScreen(_game));
            }
            return super.touchDown(screenX, screenY, pointer, button);
        }
    }
}
