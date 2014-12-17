package game.view;

import com.badlogic.gdx.Game;

/**
 * Created by Влад on 16.12.14.
 */

// Меню паузы расширяет AbstractScreen потому что фон здесь не рисуеться, а немного затеняеться сама игра
// Нужно чтобы игра продолжалась и движение продолжало отображаться на экране
// Если вообще это нужно

public class PauseScreen extends AbstractScreen {
    public PauseScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
