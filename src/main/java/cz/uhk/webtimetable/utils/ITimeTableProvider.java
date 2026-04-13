package cz.uhk.webtimetable.utils;

import cz.uhk.webtimetable.model.LocationTimeTable;
/**
 * Interface of timetable privider classes // <- kontrakt
 * */
public interface ITimeTableProvider {
    /**
     * Returns newly read timetable of selected room/location
     * @param building building id
     * @param room room id
     * @return timetable of the room
     */
    LocationTimeTable readTimeTable(String building, String room); //<- signatura
}
