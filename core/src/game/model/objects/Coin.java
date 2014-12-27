package game.model.objects;

import game.model.GameObject;

import java.awt.*;

/**
 * Created by Влад on 26.12.14.
 */
public class Coin extends GameObject {
    public final int Value;
    public Coin(String id, Point position, int value) {
        super(id, "core/res/coin.png", position, new Dimension(48, 64));
        Value = value;
        if (value > 500)
            super.TextureName = "core/res/euro.png";
    }
}
