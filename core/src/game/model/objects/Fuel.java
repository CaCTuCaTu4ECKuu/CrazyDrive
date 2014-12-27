package game.model.objects;

import game.model.GameObject;

import java.awt.*;

/**
 * Created by Влад on 26.12.14.
 */
public class Fuel extends GameObject {
    public final float Value;

    public Fuel(String id, Point position, float value) {
        super(id, "core/res/fuel.png", position, new Dimension(48,64));
        Value = value;
    }
}
