package game.controller;

import com.badlogic.gdx.Gdx;

import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * Created by Влад on 25.12.14.
 */
public class Rules {
    private static final float _maxCarSpeed = 0.75f;            // Скорость при удержании кнопки вперед
    private static final float _forwardAcceleration = 0.1f;     // Изменение скорости при удержании кнопки вперед
    private static final float _normalSpeed = 0.5f;             // Скорость покоя (кнопки не нажимаються)
    private static final float _normalAcceleration = 0.05f;     // Изменение скорости до состояния покоя (движеие вперед)
    private static final float _minCarSpeed = 0.3f;             // Скорость при удержании кнопки назад
    private static final float _backwardAcceleration = 0.13f;   // Изменение скорости при нажатии кнопки назад
    private static final float _turboBoostSpeed = 0.3f;         // Изменение скорости при турбо ускорении
    private static final float _sideStabilizationSpeed = 0.5f;  // Изменения скорости до состояния покоя (движение в стороны)

    public final int MaximumSpeed;                              // Максимально допустимая скорость машины
    public final int MaximumSideSpeed;                          // Максимальная скорость движения по бокам

    /* Эти значения пересчитываються исходя их максимально допустимой скорости */
    public final float ForwardAccelerationSpeed;                // Ограничение скорости про удержании ускорения
    public final float NormalSpeed;                             // Скорость покоя
    public final float BackwardAccelerationSpeed;               // Ограничение скорости при удержании замедления

    /* Эти значения пересчитываються исходя из максимально допустимой скорости и точности (изменений в секунду) */
    public final float FuelPerSecond;                           // Расход топлива
    public final float SpeedUpSpeed;                            // Изменение скорости при ускорении
    public final float StabilizationSpeed;                      // Изменение скорости при возврате к состоянию покоя
    public final float SlowDownSpeed;                           // Изменение скорости при замедлении
    public final float TurboBoostSpeed;                         // Изменение скорости при турбо ускорении

    /* Эти значения пересчитываються исходя из точности (изменений в секунду) */
    public final float SideAccelerationSpeed;                   // Изменения движения в сторону
    public final float SideStabilizationSpeed;                  // Скорость стабилизации движения в сторону


    public Rules(int maximumSpeed, int maximumSideSpeed, float fuelPerSecond, int Accuracy) {
        MaximumSpeed = maximumSpeed;
        MaximumSideSpeed = maximumSideSpeed;

        FuelPerSecond = fuelPerSecond / Accuracy;

        ForwardAccelerationSpeed = maximumSpeed * _maxCarSpeed;
        NormalSpeed = maximumSpeed * _normalSpeed;
        BackwardAccelerationSpeed = maximumSpeed * _minCarSpeed;

        SpeedUpSpeed = (maximumSpeed * _forwardAcceleration) / Accuracy;
        StabilizationSpeed = (maximumSpeed * _normalAcceleration) / Accuracy;
        SlowDownSpeed = (maximumSpeed * _backwardAcceleration) / Accuracy;
        TurboBoostSpeed = (maximumSpeed * _turboBoostSpeed) / Accuracy;
        SideAccelerationSpeed = (float)maximumSideSpeed / Accuracy;
        SideStabilizationSpeed = SideAccelerationSpeed * _sideStabilizationSpeed;
        Gdx.app.log("Speeds", Float.toString(MaximumSideSpeed)+" "+Float.toString(SideAccelerationSpeed)+" "+Float.toString(SideStabilizationSpeed));
    }
}
