package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import game.view.menu.StartScreen;

/**
 * Created by Влад on 16.12.14.
 */
public class CrazyDrive extends Game {
    @Override
    public void create() {
        setScreen(new StartScreen(this));
    }
}
