package game.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Влад on 07.12.14.
 */
public class RoadSector {
    private List<Unit> _units;
    private Hashtable _resources;

    public RoadSector() {
        _units = new ArrayList<Unit>();
    }
}
