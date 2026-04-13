package cz.uhk.webtimetable.gui;

import cz.uhk.webtimetable.model.LocationTimeTable;

import javax.swing.*;
import java.awt.*;

public class TimeTableFrame extends JFrame {
    private LocationTimeTable timetable;
    private JTable tabTimetable;

    public TimeTableFrame() {
        super("Location TimeTable");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGui();
    }
    private void initGui() {
        tabTimetable = new JTable(10,7);
        add(tabTimetable, BorderLayout.CENTER);
        pack();
    }
}
