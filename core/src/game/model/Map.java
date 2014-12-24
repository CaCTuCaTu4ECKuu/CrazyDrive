package game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 17.12.14.
 */
public class Map {
    public Texture _bg;
    public Texture _car;

    public Map() {
        _bg = new Texture(Gdx.files.internal("core/res/road.png"));
        _car = new Texture(Gdx.files.internal("core/res/car.png"));
    }
}
