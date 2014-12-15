package game.model;

import game.model.ArmorType;

import java.awt.*;

/**
 * Created by Влад on 07.12.14.
 */
enum ArmorType {None, Light, Middle, Heavy, Unbreakable};
public class Unit {
    int _health;
    Point _position;

    int Angle;
    int Acceleration;
    Dimension Size;
    ArmorType Armor;
    boolean Destructable;
    boolean Passthrought;

    public Point getPosition() {return _position;}
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
        _position.x += x;
        _position.y += y;
        Angle += a;
        while (Angle >= 360)
            Angle -= 360;
        while (Angle < 0)
            Angle += 360;
    }
    public void MoveAt(int x, int y, int a) {
        _position = new Point(x,y);
        Angle = a;
    }
}