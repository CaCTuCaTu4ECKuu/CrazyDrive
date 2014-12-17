package game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;

/**
 * Created by Влад on 16.12.14.
 */
public class MenuScreen extends AbstractScreen {
    protected Texture _menuBg;
    protected Texture _button;
    protected Texture _buttonActive;
    protected BitmapFont _font;
    protected BitmapFont _fontActive;

    protected Dimension _btnDimension;

    public MenuScreen(Game game) {
        super(game);
        _menuBg = new Texture(Gdx.files.internal("core/res/mainMenuBg.png"));
        _button = new Texture(Gdx.files.internal("core/res/menuButton.png"));
        _buttonActive = new Texture(Gdx.files.internal("core/res/menuButtonActive.png"));
        _font = new BitmapFont();
        _font.setColor(Color.BLUE);
        _fontActive = _font;
        _btnDimension = new Dimension(_button.getWidth(), _button.getHeight());
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
