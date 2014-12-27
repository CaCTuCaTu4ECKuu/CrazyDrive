package game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import game.controller.Rules;
import javafx.geometry.Pos;

import javax.print.attribute.standard.Sides;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Влад on 17.12.14.
 */
public class Player {
    public final String ID;
    public Texture _carTexture;

    public float Points;                // Очки

    public Point2D.Float Position;      // Положение
    public Dimension Size;
    public float Fuel;                  // Топливо
    public float Speed;                 // Скорость движения вперед
    public float TurboSpeed;
    public float SideSpeed;             // Скорость движения по сторонам
    public MoveDirection Direction;
    public MoveDirection SideDirection;

    public Point ScreenPosition() {
        return new Point((int)Position.x, (int)Position.y);
    }
    public void keyDown(int keycode) {
        switch (keycode) {
            // Если уже нажата кнопка в противоположную сторону - нейтрализуем ее)
            case 19:
                // Вперед
                if (Direction == MoveDirection.Backward)
                    Direction = MoveDirection.None;
                else
                    Direction = MoveDirection.Forward;
                break;
            case 20:
                // Назад
                if (Direction == MoveDirection.Forward)
                    Direction = MoveDirection.None;
                else
                    Direction = MoveDirection.Backward;
                break;
            case 21:
                // Влево
                if (SideDirection == MoveDirection.Right)
                    SideDirection = MoveDirection.None;
                else
                    SideDirection = MoveDirection.Left;
                break;
            case 22:
                // Вправо
                if (SideDirection == MoveDirection.Left)
                    SideDirection = MoveDirection.None;
                else
                    SideDirection = MoveDirection.Right;
                break;
        }
    }
    public void keyUp(int keycode) {
        // Если было нажато 2 кнопки - возвращаем как было
        switch (keycode) {
            case 19:
                // Вперед
                if (Direction == MoveDirection.None)
                    Direction = MoveDirection.Backward;
                else
                    Direction = MoveDirection.None;
                break;
            case 20:
                // Назад
                if (Direction == MoveDirection.None)
                    Direction = MoveDirection.Forward;
                else
                    Direction = MoveDirection.None;
                break;
            case 21:
                // Влево
                if (SideDirection == MoveDirection.None)
                    SideDirection = MoveDirection.Right;
                else
                    SideDirection = MoveDirection.None;
                break;
            case 22:
                // Вправо
                if (SideDirection == MoveDirection.None)
                    SideDirection = MoveDirection.Left;
                else
                    SideDirection = MoveDirection.None;
                break;
        }
    }

    public void FillTank(float value) {
        if (Fuel + value > 1)
            Fuel = 1;
        else
            Fuel += value;
    }
    public void Boost(int time) {
        TurboSpeed = time;
    }
    public Player(String id, Point2D.Float startPosition) {
        ID = id;
        _carTexture = new Texture(Gdx.files.internal("core/res/car.png"));
        Fuel = 1;
        Size = new Dimension(60, 120);
        Position = startPosition;
        Speed = 0;
        SideSpeed = 0;
        Direction = MoveDirection.None;
        SideDirection = MoveDirection.None;


    }
}
