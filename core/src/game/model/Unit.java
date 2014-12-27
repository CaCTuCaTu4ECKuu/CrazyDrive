package game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import game.model.ArmorType;

import java.awt.*;

/**
 * Created by Влад on 07.12.14.
 */
public class Unit {
    public int MaxHealth;
    private int _health;
    private Rectangle _object;

    public int Angle;
    public int Speed;
    public int Acceleration;
    public int Weight;
    public ArmorType Armor;
    public boolean Destructable;
    public boolean Passthrought;
    public String _texture;

    public Unit(int maxHealth, Rectangle object, int angle, int speed, ArmorType armor, boolean destructable, boolean passthrought, String texture) {
        MaxHealth = maxHealth;
        _health = MaxHealth;
        _object = object;
        Angle = angle;
        Speed = speed;
        Armor = armor;
        Destructable = destructable;
        Passthrought = passthrought;
        _texture = texture;
    }

    public Point getPosition() {
        return _object.getLocation();
    }
    public Rectangle getObject() {
        return _object;
    }
    /// Вершины с учетом угла, на который повернут объект
    public float[] getVertices() {
        float[] res = new float[4];
        return res;
    }
    public Dimension getSize() {
        return _object.getSize();
    }
    public int getHealth() {return _health;}
    public void Damage(int damage) {
        switch (Armor) {
            case Light:
                damage = Math.round((float)(damage * 0.8));
                break;
            case Middle:
                damage = Math.round((float)(damage * 0.5));
                break;
            case Heavy:
                damage = Math.round((float)(damage * 0.2));
                break;
            case Unbreakable:
                damage = 0;
        }
        _health -= damage;
    }
    public void Move(int x, int y, int a) {
        _object.x += x;
        _object.y += y;
        Angle += a;
        while (Angle >= 360)
            Angle -= 360;
        while (Angle < 0)
            Angle += 360;
    }
    public void MoveAt(int x, int y, int a) {
        _object.setLocation(x, y);
        Angle = a;
    }
    public void setTexture(String texture) {
        _texture = texture;
    }
    public Texture getTexture() {
        return new Texture(Gdx.files.internal(_texture)); }
}