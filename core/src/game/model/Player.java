package game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import javafx.geometry.Pos;

import java.awt.*;

/**
 * Created by Влад on 17.12.14.
 */
public class Player {
    public int Position;
    public Point ScreenPosition;
    public Texture _car;

    public void Move(int x, int y) {
        Position += y;
        ScreenPosition.x += x;
    }
    public Player(Point screenPosition) {
        ScreenPosition = screenPosition;
        _car = new Texture(Gdx.files.internal("core/res/car.png"));
    }
}
