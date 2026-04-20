package cz.uhk.webtimetable.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalTime;

public class LocalTimeAdapter extends TypeAdapter<LocalTime> {

    @Override
    public void write(JsonWriter out, LocalTime value) throws IOException {

    }

    @Override
    public LocalTime read(JsonReader in) throws IOException {
        in.beginObject(); //vlezeme do zavorky v jsonu
        in.nextName();
        var timeString = in.nextString();
        in.endObject(); //vylezu ze závorky
        return LocalTime.parse(timeString); //TODO zkontrolovat
    }
}
