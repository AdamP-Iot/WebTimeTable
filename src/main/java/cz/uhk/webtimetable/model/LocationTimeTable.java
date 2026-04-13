package cz.uhk.webtimetable.model;

import java.util.ArrayList;
import java.util.List;

public class LocationTimeTable {
    private String building, room;
    private List<Activity> activities = new ArrayList<>();

    public LocationTimeTable() {
    }

    public LocationTimeTable(String building, String room) {
        this.building = building;
        this.room = room;
    }

    public LocationTimeTable(String building, String room, List<Activity> activities) {
        this.building = building;
        this.room = room;
        this.activities = activities;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
