package game.controller;

import com.badlogic.gdx.Gdx;
import game.model.GameObject;
import game.model.Map;
import game.model.MoveDirection;
import game.model.Player;
import game.model.objects.Coin;
import game.model.objects.Fuel;
import game.model.objects.Triangle;
import game.model.objects.Zombie;
import game.view.renderer.game.ObjectsRender;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

/**
 * Created by Влад on 26.12.14.
 */
public class GameProcessor implements Runnable{
    private boolean _paused;
    private int _accuracy;
    private Rules _rules;
    private Map _gameMap;
    private Hashtable _objects;             // Все объекты в игре
    private Hashtable _players;             // Все игроки в игре

    private List<String> _cleanList;

    private float _visionHorizon;

    private Object _syncUpdates = new Object();

    public void addPlayer(Player player) {
        synchronized (_syncUpdates) {
            if (!_players.containsKey(player.ID))
                _players.put(player.ID, player);
            Gdx.app.log("Player added", player.ID);
        }
    }
    public void addObject(GameObject obj) {
        synchronized (_syncUpdates) {
            if (!_objects.containsKey(obj.ID))
                _objects.put(obj.ID, obj);
            Gdx.app.log("Object added", obj.ID);
        }
    }

    public Map getMap() {
        return _gameMap;
    }
    public Player getPlayer(String id) {
        synchronized (_syncUpdates) {
            if (_players.containsKey(id))
                return (Player)_players.get(id);
            else
                return null;
        }
    }
    public GameObject getObject(String id) {
        synchronized (_syncUpdates) {
            if (_objects.containsKey(id))
                return (GameObject)_objects.get(id);
            else
                return null;
        }
    }
    public Hashtable getObjects() {
        synchronized (_syncUpdates) {
            return _objects;
        }
    }

    public GameProcessor(int accuracy) {
        super();
        _visionHorizon = 0;
        _paused = true;
        _accuracy = accuracy;
        _rules = new Rules(15, 10, 0.04f, _accuracy);
        _gameMap = new Map();
        _objects = new Hashtable();
        _players = new Hashtable();
        _cleanList = new ArrayList<String>();
    }

    private float tresholdSpeed(float speed) {
        if (speed > _rules.MaximumSpeed)
            return _rules.MaximumSpeed;
        else if (speed < 0)
            return 0;
        return speed;
    }
    private float tresholdSideSpeed(float speed, float sideSpeed) {
        if ((sideSpeed >= _rules.SideStabilizationSpeed || sideSpeed <= -_rules.SideStabilizationSpeed) && (speed > 0 || speed < 0)) {
            if (sideSpeed > _rules.MaximumSideSpeed)
                return _rules.MaximumSideSpeed;
            else if (sideSpeed < -_rules.MaximumSideSpeed)
                return -_rules.MaximumSideSpeed;
            return sideSpeed;
        } else
            return 0;
    }
    private float newSpeed(float speed, float fuel, MoveDirection direction) {
        float newSpeed = speed;
        if (fuel > 0) {
            switch (direction) {
                case Backward:
                    if (newSpeed > _rules.BackwardAccelerationSpeed)
                        newSpeed -= _rules.SlowDownSpeed;
                    break;
                case Forward:
                    if (newSpeed < _rules.ForwardAccelerationSpeed)
                        newSpeed += _rules.SpeedUpSpeed;
                    break;
                case None:
                    if (newSpeed < _rules.NormalSpeed)
                        newSpeed += _rules.StabilizationSpeed;
                    else
                        newSpeed -= _rules.StabilizationSpeed;
                    break;
            }
        } else {
            if (direction == MoveDirection.Backward)
                newSpeed -= _rules.SlowDownSpeed;
            else
                newSpeed -= _rules.StabilizationSpeed;
        }
        return tresholdSpeed(newSpeed);
    }
    private float newSideSpeed(float speed, float sideSpeed, MoveDirection direction) {
        float newSpeed = sideSpeed;
        switch (direction) {
            case Left:
                if (newSpeed > -_rules.MaximumSideSpeed) {
                    newSpeed -= _rules.SideAccelerationSpeed;
                }
                break;
            case Right:
                if (newSpeed < _rules.MaximumSideSpeed) {
                    newSpeed  += _rules.SideAccelerationSpeed;
                }
                break;
            case None:
                if (newSpeed > 0)
                    newSpeed -= _rules.SideStabilizationSpeed;
                else if (sideSpeed < 0)
                    newSpeed += _rules.SideStabilizationSpeed;
                break;
        }
        return tresholdSideSpeed(speed, newSpeed);
    }
    private Point2D.Float newPosition(Point2D.Float oldPosition, float speed, float sideSpeed) {
        Point2D.Float newPosition = new Point2D.Float((float)(oldPosition.x + sideSpeed), (float)(oldPosition.y + speed));
        if (newPosition.x < _gameMap.leftSide((int) newPosition.x))
            newPosition.x = _gameMap.leftSide((int) newPosition.x);
        else if (newPosition.x > _gameMap.rightSide((int)newPosition.x))
            newPosition.x = _gameMap.rightSide((int)newPosition.x);
        return newPosition;
    }
    private float recalculateSpeed(float xPosition, float sideSpeed, float speed) {
        // Должно зависеть от всех скоростей
        if ((xPosition == _gameMap.leftSide((int)xPosition) && sideSpeed < 0) || (xPosition == _gameMap.rightSide((int)xPosition) && sideSpeed > 0))
            return speed * 0.95f;
        else
            return speed;
    }
    private float recalculateSideSpeed(float xPosition, float sideSpeed, float speed) {
        // Должно зависеть и от всех скоростей
        if ((xPosition == _gameMap.leftSide((int)xPosition) && sideSpeed < 0) || (xPosition == _gameMap.rightSide((int)xPosition) && sideSpeed > 0))
            return sideSpeed * 0.75f;
        else
            return sideSpeed;
    }
    private float newFuel (float fuel) {
        if (fuel > 0)
            return  fuel - _rules.FuelPerSecond;
        else
            return 0;
    }
    private float newPoints(float oldPoints, float speed) {
        return oldPoints + speed / 10;
    }
    private void checkVisionHorizon(float visionHorizon) {
        if (_visionHorizon < visionHorizon)
            _visionHorizon = visionHorizon;
    }

    private void processPlayers() {
        Player tPl;
        for (Object key : _players.keySet()) {
            tPl = (Player)_players.get(key);

            tPl.Speed = newSpeed(tPl.Speed, tPl.Fuel, tPl.Direction);
            tPl.SideSpeed = newSideSpeed(tPl.Speed, tPl.SideSpeed, tPl.SideDirection);
            tPl.Position = newPosition(tPl.Position, tPl.Speed, tPl.SideSpeed);
            tPl.Fuel = newFuel(tPl.Fuel);
            tPl.Points = newPoints(tPl.Points, tPl.Speed);
            tPl.Speed = recalculateSpeed(tPl.Position.x, tPl.SideSpeed, tPl.Speed);
            tPl.SideSpeed = recalculateSideSpeed(tPl.Position.x, tPl.SideSpeed, tPl.Speed);

            checkVisionHorizon(tPl.Position.y);
        }
    }

    private boolean behind(GameObject o, float point) {
        if (o.Position.y + o.Size.getHeight() < point)
            return true;
        else
            return false;
    }
    private void interract(Player player, GameObject object) {
        if (object instanceof Fuel) {
            Gdx.app.log("Fuel taken", "yup");
            Fuel f = (Fuel)object;
            player.FillTank(f.Value);
            _cleanList.add(f.ID);
        } else if (object instanceof Zombie) {
            Zombie z = (Zombie)object;
            if (z.isAlive) {
                Gdx.app.log("Zombie killed", "yup");
                player.Boost(1);
                player.FillTank(0.1f);
                z.Kill();
            }
        } else if (object instanceof Coin) {
            Coin c = (Coin)object;
            Gdx.app.log("Coin picked", "yup");
            player.Points += c.Value;
            _cleanList.add(c.ID);
        } else if (object instanceof Triangle) {
            Triangle t = (Triangle)object;
            if (!t.broken) {
                player.Speed = player.Speed * 0.1f;
                t.Destroy();
            }
        }
    }

    private void processObjects() {
        GameObject gO;
        Player tPl;
        for (Object key : _objects.keySet()) {
            gO = (GameObject)_objects.get(key);
            // Проверяем все объекты на столкновение
            if (!behind(gO, _visionHorizon)) {
                // С каждым игроком
                for (Object pKey : _players.keySet()) {
                    tPl = (Player)_players.get(pKey);

                    if (new Rectangle(tPl.ScreenPosition(), tPl.Size).intersects(gO.getArea()))
                        interract(tPl, gO);
                }
            } else
                _cleanList.add(gO.ID);
        }
    }

    private void cleanUp() {
        for (String id : _cleanList) {
            if (_objects.containsKey(id))
                _objects.remove(id);
            else if (_players.contains(id))
                _players.remove(id);
            Gdx.app.log("Object removed", id);
        }
        _cleanList.clear();
    }

    private void processAll() {
        if (!_paused) {
            synchronized (_syncUpdates) {
                processPlayers();
                processObjects();
                cleanUp();
            }
        }
    }

    public void Pause() {
        _paused = true;
    }
    public void Start() {
        _paused = false;
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            processAll();
            try { Thread.sleep(1000 / _accuracy);}
            catch (Exception ex) {/* nothing to do here */}
        }
    }
}
