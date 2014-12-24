package game.controller;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;

/**
 * Created by Влад on 17.12.14.
 */
public class AbstractInputAdapter extends InputAdapter {
    protected Screen _parentScreen;

    public AbstractInputAdapter(Screen parentScreen) {
        super();
        _parentScreen = parentScreen;
    }
}
