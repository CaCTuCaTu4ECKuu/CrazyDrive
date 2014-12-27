package game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import game.controller.GameProcessor;
import game.controller.RandomGenerator;
import game.model.*;
import game.model.objects.Coin;
import game.model.objects.Fuel;
import game.model.objects.Zombie;
import game.view.renderer.game.InfoRender;
import game.view.renderer.game.ObjectsRender;
import game.view.renderer.game.RoadRender;

import java.awt.*;
import java.awt.geom.Point2D;
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

    private GameProcessor _processor;
    private RandomGenerator _randomer;
    private Thread thrProcessor;
    private Thread thrRandom;
    private Player _player;

    private Control _control;

    @Override
    public void render(float delta) {
        super.render(delta);
        // Сначала нужно отрендерть дорогу, склеив 2 куска карты в один по вертикали
        // Потом отрисовать все обьекты
        // Отрисовать всех игроков (одного для начала)
        _batch.begin();
        // Отрисовываем фон-дорогу
        _roadRender.render(_batch, _processor.getMap(), _player.ScreenPosition().y);
        // Отрисовываем объекты на фоне
        _objectRender.render(_batch, _processor.getObjects(), _player);
        // Отрисовываем информацию игрока вверху экрана
        _infoRender.render(_batch, _player);
        _batch.end();
    }

    @Override
    public void show() {
        super.show();
        _processor.Start();
        _randomer.Start();
    }

    public GameScreen(Game game, String mapName) {
        super(game);
        _control = new Control();
        Gdx.input.setInputProcessor(_control);

        _roadRender = new RoadRender();
        _objectRender = new ObjectsRender();
        _infoRender = new InfoRender();

        _processor = new GameProcessor(50);
        _randomer = new RandomGenerator(_processor);
        _player = new Player("P1", new Point2D.Float((float)(Gdx.graphics.getWidth() / 2), 0f));
        _processor.addPlayer(_player);

        thrProcessor = new Thread(_processor);
        thrRandom = new Thread(_randomer);
        thrProcessor.start();
        thrRandom.start();
    }
    private class Control extends InputAdapter {
        @Override
        public boolean keyDown(int keycode) {
            // Gdx.app.log("pressed", Integer.toString(keycode));
            _player.keyDown(keycode);
            return super.keyDown(keycode);
        }

        @Override
        public boolean keyUp(int keycode) {
            // Gdx.app.log("unpressed", Integer.toString(keycode));
            _player.keyUp(keycode);
            return super.keyUp(keycode);
        }
    }
}
