package kr.co.tjeit.dabangcopy.datas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-08-24.
 */

public class Subway implements Serializable {
    // 고유속성
    private String stationName;
    private List<String> lines = new ArrayList<>(); // 몇 호선 역인지
    // Ex. 종로3가 : 1호선 3호선 5호선

    public Subway() {
    }

    public Subway(String stationName) {
        this.stationName = stationName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public boolean equals(Object obj) {
        return getStationName().equals(((Subway)obj).getStationName());
    }
}
