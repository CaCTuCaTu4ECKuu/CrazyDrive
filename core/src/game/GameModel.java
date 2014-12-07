package game;

/**
 * Created by Влад on 06.12.14.
 */
enum ArmorType {None, Light, Middle, Heavy, Unbreakable};
public class ают
        GameModel {
    public int MaxSpeed = 100;                      // Максимальная скорость, с которой может двигаться машина - можно влиять на скорость игры
    public final float turboSpeed = 0.8f;           // Скорость машины при удержании кнопки ускорения
    public final float normalSpeed = 0.5f;          // Скорость машины без вмешательств
    public final float slowSpeed = 0.3f;            // Скорость машины при удержании кнопки торможения

    public GameModel(int maxSpeed) {
        MaxSpeed = maxSpeed;
    }
}
