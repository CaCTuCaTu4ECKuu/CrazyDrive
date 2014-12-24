package game.view.menu;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.controller.AbstractInputAdapter;
import game.controller.Calculating;
import game.model.Map;
import game.view.GameScreen;
import game.view.MenuScreen;
import game.view.menu.settings.SettingsScreen;

import java.awt.*;

/**
 * Created by Влад on 16.12.14.
 */
public class StartScreen extends MenuScreen {
    private Point _startSingle;
    private Point _multiplayer;
    private Point _settings;
    private Point _exit;

    private navigation _navigation;

    public StartScreen(Game game) {
        super(game);

        _startSingle = new Point((Gdx.graphics.getWidth() / 2) - (_button.getWidth() / 2), 100 - _button.getHeight());
        _multiplayer = new Point((Gdx.graphics.getWidth() / 2) - (_button.getWidth() / 2), 150 - _button.getHeight());
        _settings = new Point((Gdx.graphics.getWidth() / 2) - (_button.getWidth() / 2), 200 - _button.getHeight());
        _exit = new Point((Gdx.graphics.getWidth() / 2) - (_button.getWidth() / 2), 250 - _button.getHeight());
        _navigation = new navigation(this);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        _batch.begin();

        if (Calculating.isInside(new Rectangle(_startSingle, _btnDimension), _location))
            _batch.draw(_buttonActive, _startSingle.x, Gdx.graphics.getHeight() - _startSingle.y - _button.getHeight());
        else
            _batch.draw(_button, _startSingle.x, Gdx.graphics.getHeight() - _startSingle.y - _button.getHeight());
        if (Calculating.isInside(new Rectangle(_multiplayer, _btnDimension), _location))
            _batch.draw(_buttonActive, _multiplayer.x, Gdx.graphics.getHeight() - _multiplayer.y - _button.getHeight());
        else
            _batch.draw(_button, _multiplayer.x, Gdx.graphics.getHeight() - _multiplayer.y - _button.getHeight());
        if (Calculating.isInside(new Rectangle(_settings, _btnDimension), _location))
            _batch.draw(_buttonActive, _settings.x, Gdx.graphics.getHeight() - _settings.y - _button.getHeight());
        else
            _batch.draw(_button, _settings.x, Gdx.graphics.getHeight() - _settings.y - _button.getHeight());
        if (Calculating.isInside(new Rectangle(_exit, _btnDimension), _location))
            _batch.draw(_buttonActive, _exit.x, Gdx.graphics.getHeight() - _exit.y - _button.getHeight());
        else
            _batch.draw(_button, _exit.x, Gdx.graphics.getHeight() - _exit.y - _button.getHeight());

        _font.draw(_batch, "Start", _startSingle.x, Gdx.graphics.getHeight() - _startSingle.y - _button.getHeight());
        _font.draw(_batch, "Multiplayer", _multiplayer.x, Gdx.graphics.getHeight() - _multiplayer.y - _button.getHeight());
        _font.draw(_batch, "Settings", _settings.x, Gdx.graphics.getHeight() - _settings.y - _button.getHeight());
        _font.draw(_batch, "Quit", _exit.x, Gdx.graphics.getHeight() - _exit.y - _button.getHeight());

        _batch.end();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(_navigation);
    }

    private class navigation extends AbstractInputAdapter {
        public navigation(Screen parentScreen) {
            super(parentScreen);
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            if (button == Input.Buttons.LEFT) {
                Screen newScreen = null;

                // Добавить еще меню в одиночную игру чтобы тоже можно было выбирать карту
                if (Calculating.isInside(new Rectangle(_startSingle, _btnDimension), _location))
                    newScreen = new GameScreen(_game, "singleplayer.cdm");
                else if (Calculating.isInside(new Rectangle(_multiplayer, _btnDimension), _location))
                    newScreen = new MultiplayerScreen(_game);
                else if (Calculating.isInside(new Rectangle(_settings, _btnDimension), _location)) {
                    newScreen = new SettingsScreen(_game);
                }
                else if (Calculating.isInside(new Rectangle(_exit, _btnDimension), _location))
                    System.exit(0);

                if (newScreen != null)
                    _game.setScreen(newScreen);
            }
            return super.touchDown(screenX, screenY, pointer, button);
        }
    }
}
