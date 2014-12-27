package game.view.renderer.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.model.Player;

/**
 * Created by Влад on 17.12.14.
 */
public class InfoRender {
    private BitmapFont _font;

    public InfoRender() {
        _font = new BitmapFont();
        _font.setColor(Color.GREEN);
    }

    public void render(SpriteBatch batch, Player player) {
        _font.draw(batch, "Speed: " + Float.toString(player.Speed), 10, Gdx.graphics.getHeight() - 15);
        _font.draw(batch, "Side: " + Float.toString(player.SideSpeed), 10, Gdx.graphics.getHeight() - 35);
        _font.draw(batch, "Fuel: " + Float.toString(player.Fuel), 10, Gdx.graphics.getHeight() - 55);
        _font.draw(batch, "Position: " + Float.toString(player.ScreenPosition().y), 10, Gdx.graphics.getHeight() - 75);
        _font.draw(batch, "Points: " + Float.toString(player.Points), 10, Gdx.graphics.getHeight() - 95);
    }
}
