package game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import game.model.Map;
import game.model.Player;
import game.model.Road;
import game.model.RoadSector;
import game.view.renderer.game.InfoRender;
import game.view.renderer.game.ObjectsRender;
import game.view.renderer.game.RoadRender;

import java.awt.*;
import java.io.File;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Влад on 16.12.14.
 */
// Непосредственно сама игра
public class GameScreen extends AbstractScreen {
    private RoadRender _roadRender;
    private ObjectsRender _objectRender;
    private InfoRender _infoRender;

    private Map _map;
    private Player _player;
    private Hashtable _resources;
    private int _acceleration;
    private int _sideAcceleration;

    private Control _control;

    @Override
    public void render(float delta) {
        super.render(delta);
        _player.Move(_sideAcceleration, _acceleration);
        // Сначала нужно отрендерть дорогу, склеив 2 куска карты в один по вертикали
        // Потом отрисовать все обьекты
        // Отрисовать всех игроков (одного для начала)
        _batch.begin();
        // Отрисовываем фон-дорогу
        _roadRender.render(_batch, _map, _player.Position);
        // Отрисовываем объекты на фоне
        _objectRender.render(_batch, _map, _player);
        // Отрисовываем информацию игрока вверху экрана
        _infoRender.render(_batch, _player);
        _batch.end();
    }

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(_control);
    }

    public GameScreen(Game game, String mapName) {
        super(game);
        _control = new Control();

        _roadRender = new RoadRender();
        _objectRender = new ObjectsRender();
        _infoRender = new InfoRender();

        _map = new Map();
        _player = new Player(new Point(Gdx.graphics.getWidth() / 2,0));
    }
    private class Control extends InputAdapter {
        public Control() {
            super();
        }

        @Override
        public boolean keyDown(int keycode) {
            // Gdx.app.log("pressed", Integer.toString(keycode));
            switch (keycode) {
                case 19:
                    _acceleration += 5;
                    break;
                case 20:
                    _acceleration -= 5;
                    break;
                case 21:
                    _sideAcceleration -= 5;
                    break;
                case 22:
                    _sideAcceleration += 5;
                    break;
            }
            return super.keyDown(keycode);
        }

        @Override
        public boolean keyUp(int keycode) {
            // Gdx.app.log("unpressed", Integer.toString(keycode));
            switch (keycode) {
                case 19:
                    _acceleration -= 5;
                    break;
                case 20:
                    _acceleration += 5;
                    break;
                case 21:
                    _sideAcceleration += 5;
                    break;
                case 22:
                    _sideAcceleration -= 5;
                    break;
            }
            return super.keyUp(keycode);
        }
    }
}
