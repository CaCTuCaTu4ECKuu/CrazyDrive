package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 07.12.14.
 */
public class RoadSector {
    List<Unit> units;
    Image Background;
    int borders;            // Отступ слева и справа за которым нет дороги

    public RoadSector() {
        units = new ArrayList<Unit>();
    }
}
