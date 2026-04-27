package cz.uhk.webtimetable.model;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
//list of json
public class RoomData {
    @SerializedName("mistnostInfo")
    private List<Room> roomList = new ArrayList<>();

    public List<Room> getRoomList() {
        return roomList;
    }
    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public RoomData(List<Room> roomList) {
        this.roomList = roomList;
    }
    public RoomData() {}

}
