package game.controller;

import game.model.Unit;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 06.12.14.
 */
public class Calculating {

    public boolean isCrossing(Rectangle a, int angleA, Rectangle b, int angleB) {
        Point a1 = new Point((int)(a.x + a.getWidth()), (int)(a.y - a.getHeight()));
        Point b1 = new Point((int)(b.x + b.getWidth()), (int)(b.y - b.getHeight()));
        return ( a.y < b1.y || a1.y > b.y || a1.x < b.x || a.x > b1.x );
    }
    public List<Unit> getCrossingUnits(List<Unit> objects, Point position, Dimension size) {
        List<Unit> res = new ArrayList<Unit>();
        for (int i = 0; i < objects.size(); i++) {
            if (isCrossing(objects.get(i).getObject(), 0, new Rectangle(position, size), 0))
                res.add(objects.get(i));
        }
        return res;
    }
}
