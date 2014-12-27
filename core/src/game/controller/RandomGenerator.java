package game.controller;

import game.model.GameObject;
import game.model.objects.Coin;
import game.model.objects.Fuel;
import game.model.objects.Triangle;
import game.model.objects.Zombie;

import java.awt.*;
import java.util.Random;

/**
 * Created by Влад on 26.12.14.
 */
public class RandomGenerator implements Runnable{
    private GameProcessor _processor;
    private Random _rnd;
    private boolean _work;

    public RandomGenerator(GameProcessor processor) {
        _processor = processor;
        _rnd = new Random(0);
        _work = false;
        /*
        _processor.addObject(new Fuel("f1", new Point(280, 2000), 1f));
        _processor.addObject(new Zombie("z1", new Point(350, 5000), true));
        _processor.addObject(new Coin("c1", new Point(400, 10000), 100));
        _processor.addObject(new Coin("c2", new Point(150, 16000), 500));
        _processor.addObject(new Zombie("z2", new Point(460, 25000), true));
        */
    }

    private void generateOneOf() {
        GameObject newObject = null;
        _rnd = new Random();
        int xPos = _processor.getMap().rightSide(0) - _rnd.nextInt(_processor.getMap().rightSide(0) - _processor.getMap().leftSide(0));
        switch (_rnd.nextInt(4)) {
            case 0:
                newObject = new Fuel("f"+Integer.toString(_rnd.nextInt(65535)), new Point(xPos, (int)(_processor.getPlayer("P1").Position.y + 2000)), 1f);
                break;
            case 1:
                newObject = new Zombie("z"+Integer.toString(_rnd.nextInt(65535)), new Point(xPos, (int)(_processor.getPlayer("P1").Position.y + 2000)), true);
                break;
            case 2:
                newObject = new Coin("c"+Integer.toString(_rnd.nextInt(65535)), new Point(xPos, (int)(_processor.getPlayer("P1").Position.y + 2000)), 1000 - _rnd.nextInt(901));
                break;
            case 3:
                newObject = new Triangle("t"+Integer.toString(_rnd.nextInt(65535)), new Point(xPos, (int)(_processor.getPlayer("P1").Position.y + 2000)), 100 - _rnd.nextInt(90));
                break;
        }
        _processor.addObject(newObject);
    }
    public void Generate(boolean atLeastOne) {
        int r = _rnd.nextInt(100);
        GameObject newObject = null;
        if (r < 10) {
            // 10 % шанс
        } else if (r < 35) {
            // 15 % шанс
            generateOneOf();
        } else if (r < 50) {
            // 15 % шанс
        }
        if (newObject == null && atLeastOne)
            generateOneOf();
    }

    public void Stop() {
        _work = false;
    }
    public void Start() {
        _work = true;
    }
    private void generate() {
        if (_work) {
            Generate(false);
        }
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            generate();
            try { Thread.sleep(1000); }
            catch (Exception ex) { /* nothing to do here */ }
        }
    }
}
