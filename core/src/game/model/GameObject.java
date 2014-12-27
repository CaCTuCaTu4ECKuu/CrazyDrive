package game.model;

import com.badlogic.gdx.Game;
import javafx.geometry.Pos;

import java.awt.*;

/**
 * Created by Влад on 26.12.14.
 */
public class GameObject {
    public final String ID;
    public String TextureName;
    public Point Position;
    public Dimension Size;

    public Rectangle getArea() {
        return new Rectangle(Position, Size);
    }

    public void Move(int x, int y) {
        Position.x += x;
        Position.y += y;
    }

    public GameObject(String id, String texture, Point position, Dimension size) {
        ID = id;
        TextureName = texture;
        Position = position;
        Size = size;
    }
}
