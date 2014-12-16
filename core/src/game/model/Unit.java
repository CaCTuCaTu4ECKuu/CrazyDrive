package game.model;

import game.model.ArmorType;

import java.awt.*;

/**
 * Created by Влад on 07.12.14.
 */
enum ArmorType {None, Light, Middle, Heavy, Unbreakable};
public class Unit {
    private int _health;
    private Rectangle _object;
    private int Angle;

    public int Acceleration;
    public ArmorType Armor;
    public boolean Destructable;
    public boolean Passthrought;

    public Point getPosition() {
        return _object.getLocation();
    }
    public Rectangle getObject() {
        return _object;
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
}