package game.model.objects;

import game.model.GameObject;

import java.awt.*;

/**
 * Created by Влад on 26.12.14.
 */
public class Triangle extends GameObject {
    public final float Value;
    public boolean broken;

    public void Destroy() {
        super.TextureName = "core/res/brokentriangle.png";
        broken = true;
    }
    public Triangle(String id, Point position, int value) {
        super(id, "core/res/triangle.png", position, new Dimension(60, 60));
        broken = false;
        Value = value;
    }
}
