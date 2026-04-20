package cz.uhk.webtimetable.utils;

import com.google.gson.GsonBuilder;
import cz.uhk.webtimetable.model.LocationTimeTable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;

import com.google.gson.Gson;

public class StagTimeTableProvider implements ITimeTableProvider {
    private static final String STAG_URL = "https://stag-demo.uhk.cz/ws/services/rest2/rozvrhy/getRozvrhByMistnost?semestr=LS&budova=%s&mistnost=%s&outputFormat=JSON"; //konstanta takže staci a final, %s pro změnu, LS
    private final Gson gson;

    public StagTimeTableProvider() {
        gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalTime.class,new LocalTimeAdapter()).create();

    }

    @Override
    public LocationTimeTable readTimeTable(String building, String room) {
        try {
            var url = new URL(STAG_URL.formatted(building,room)); //misto %s se dá správný kod budovy a mistnosti
            var reader = new InputStreamReader(url.openStream());
            return gson.fromJson(reader, LocationTimeTable.class);
        } catch (MalformedURLException exception) {
            System.out.println("Wrong URL !");
            throw new RuntimeException(exception);
        } catch (IOException e) {
            System.out.println("IO Error during timetable reading");
            throw new RuntimeException(e);
        }
    }
}
