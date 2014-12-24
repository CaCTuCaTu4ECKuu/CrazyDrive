package game.model;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Влад on 07.12.14.
 */
public class RoadSector {
    public static final int sectorWidth = 960;
    public static final int sectorWidthMid = sectorWidth / 2;
    public static final int sectorHeight = 960;
    public static final int sectorHeightMid = sectorHeight / 2;

    public List<Unit> _units;
    public String _bgTexture;

    public List<String> getResourcesList() {
        List<String> res = new ArrayList<String>();
        res.add(_bgTexture);
        for (int i = 0; i < _units.size(); i++) {
            if (!res.contains(_units.get(i)._texture)) {
                res.add(_units.get(i)._texture);
            }
        }
        return res;
    }
    public RoadSector(String bgTexture) {
        _units = new ArrayList<Unit>();
        _bgTexture = bgTexture;
    }
}
