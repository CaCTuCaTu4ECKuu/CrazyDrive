package game.controller;

import com.badlogic.gdx.Gdx;
import game.model.Unit;
import sun.launcher.resources.launcher;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 06.12.14.
 */
public class Calculating {

    public static boolean isInside(Rectangle zone, Point p) {
        return ((p.x > zone.x && p.x < zone.x + zone.width) && (p.y > zone.y && p.y < zone.y + zone.height));
    }
    public static Rectangle intersection(Rectangle a, int angleA, Rectangle b, int angleB) {
        return a.intersection(b);
    }
    public static int screenPosition(int visionHorizon, int position) {
        return Gdx.graphics.getHeight() + (visionHorizon - position);
    }
    public static List<Unit> getCrossingUnits(List<Unit> objects, Point position, Dimension size) {
        List<Unit> res = new ArrayList<Unit>();
        return res;
    }
}
