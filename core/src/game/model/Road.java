package game.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Влад on 15.12.14.
 */
public class Road {
    private List<RoadSector> _road;

    public Hashtable getResources() {
        // Переделать
        // Сообщить список всех ресурсов которые нужны для этой карты
        // Карты должны быть встроены и должна быть возможность подгружать отдельно
        // При этом ресурсы можно использовать уже имеющиеся и запаковывать в файл карты отдельно чтобы можно было
        // добавлять даже свои объекты в игру которых там раньше небыло
        return new Hashtable();
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
