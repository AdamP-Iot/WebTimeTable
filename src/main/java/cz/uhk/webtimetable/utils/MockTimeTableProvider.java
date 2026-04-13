package cz.uhk.webtimetable.utils;

import cz.uhk.webtimetable.model.Activity;
import cz.uhk.webtimetable.model.LocationTimeTable;

import java.time.LocalTime;

public class MockTimeTableProvider implements ITimeTableProvider {

    @Override
    public LocationTimeTable readTimeTable(String building, String room) {
        var tt = new LocationTimeTable("J","J22");
        tt.getActivities().add(new Activity(
                "PRO1","Programovani I","Kozel","Pondělí","Cvičení",LocalTime.of(11,35), LocalTime.of(13,5)
        ));
        tt.getActivities().add(new Activity(
                "PRO1","Programovani I","Kozel","Úterý","Přednáška",LocalTime.of(9,5), LocalTime.of(10,35)
        ));
        return tt;
    }
}
