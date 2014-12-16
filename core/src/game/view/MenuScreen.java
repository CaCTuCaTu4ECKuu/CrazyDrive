package game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by Влад on 16.12.14.
 */
public class MenuScreen extends AbstractScreen {
    protected Texture _menuBg;
    protected Texture _button;
    protected BitmapFont _font;

    public MenuScreen(Game game) {
        super(game);
        _menuBg = new Texture(Gdx.files.internal("core/res/mainMenuBg.png"));
        _button = new Texture(Gdx.files.internal("core/res/menuButton.png"));
        _font = new BitmapFont();
        _font.setColor(Color.BLUE);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        _batch.begin();
        _batch.draw(_menuBg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        _batch.end();
    }

    @Override
    public void show() {
        super.show();
    }
}
