package game.view.menu.settings;

import com.badlogic.gdx.*;
import game.controller.AbstractInputAdapter;
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
        Gdx.input.setInputProcessor(_navigation);
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
        _navigation = new navigation(this);
    }
    private class navigation extends AbstractInputAdapter {
        public navigation(Screen parentScreen) {
            super(parentScreen);
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (button == Input.Buttons.LEFT) {
                Screen newScreen = null;

                if (Calculating.isInside(new Rectangle(_back, _btnDimension), _location)) {
                    newScreen = new StartScreen(_game);
                }

                if (newScreen != null)
                        _game.setScreen(newScreen);
            }
            return super.touchDown(screenX, screenY, pointer, button);
        }
    }
}
