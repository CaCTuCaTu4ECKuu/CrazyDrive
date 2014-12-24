package game.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Влад on 15.12.14.
 */
public class Road {
    public List<RoadSector> _road;

    public List<String> getResources() {
        List<String> res = new ArrayList<String>();
        List<String> t;
        for (int i = 0; i < _road.size(); i++) {
            t = _road.get(i).getResourcesList();
            for (String s : t) {
                if (!res.contains(s))
                    res.add(s);
            }
        }
        return res;
    }
    public RoadSector getSector(int sector) {
        if (sector > _road.size()) {
            return _road.get(_road.size() - 1);
        } else {
            return _road.get(sector);
        }
    }
    public void LoadMap(String path) {
        _road = new ArrayList<RoadSector>();
        // Открываем файл карты и считываем карту по секторам =)
    }

    public Road(String path) {
        LoadMap(path);
    }
}
