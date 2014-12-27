package game.model.objects;

import com.badlogic.gdx.graphics.Texture;
import game.model.GameObject;

import java.awt.*;

/**
 * Created by Влад on 26.12.14.
 */
public class Zombie extends GameObject {
    public boolean isAlive;

    public void Kill() {
        TextureName = "core/res/deadzombie.png";
        super.Size = new Dimension(70, 105);
        isAlive = false;
    }
    public Zombie(String id, Point position, boolean alive) {
        super(id, "core/res/zombie.png", position, new Dimension(70, 70));
        isAlive = alive;
    }
}
