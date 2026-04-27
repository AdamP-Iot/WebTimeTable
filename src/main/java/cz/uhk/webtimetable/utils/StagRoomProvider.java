package cz.uhk.webtimetable.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cz.uhk.webtimetable.model.Room;
import cz.uhk.webtimetable.model.RoomData;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class StagRoomProvider {

    private static final String STAG_URL = "https://stag-demo.uhk.cz/ws/services/rest2/mistnost/getMistnostiInfo?zkrBudovy=%s&pracoviste=%s&typ=U&outputFormat=JSON&cisloMistnosti=%s";
    private final Gson gson;

    public StagRoomProvider() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    //same as TimeTableProvider but for rooms
    public List<Room> readRooms() {
        try {
            var url = new URL(STAG_URL);
            var reader = new InputStreamReader(url.openStream());
            RoomData roomData = gson.fromJson(reader, RoomData.class);
            return roomData.getRoomList();

        } catch (MalformedURLException exception) {
            System.out.println("Wrong room URL!");
            throw new RuntimeException(exception);

        } catch (IOException exception) {
            System.out.println("IO Error during room reading");
            throw new RuntimeException(exception);
        }
    }
}