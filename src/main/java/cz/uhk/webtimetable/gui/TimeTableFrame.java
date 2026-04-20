package cz.uhk.webtimetable.gui;

import cz.uhk.webtimetable.model.LocationTimeTable;
import cz.uhk.webtimetable.utils.ITimeTableProvider;
import cz.uhk.webtimetable.utils.MockTimeTableProvider;
import cz.uhk.webtimetable.utils.StagTimeTableProvider;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class TimeTableFrame extends JFrame {
    private ITimeTableProvider timetableProvider = new StagTimeTableProvider();
    private LocationTimeTable timetable;
    private JTable tabTimetable;
    private TimeTableModel timeTableModel;
    public TimeTableFrame() {
        super("Location TimeTable");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timetable = timetableProvider.readTimeTable("J","J22");

        initGui();
    }
    private void initGui() {
        timeTableModel = new TimeTableModel();
        tabTimetable = new JTable(timeTableModel);
        add(new JScrollPane(tabTimetable), BorderLayout.CENTER);
        pack();
    }
    class TimeTableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return timetable.getActivities().size();
        }
        private static final String[] colNames = {"ID","NAZEV","UCITEL","TYP","DEN","ZACATEK","KONEC"};
        @Override
        public String getColumnName(int column) {
            return colNames[column];
        }

        @Override
        public int getColumnCount() {
            return 7; //change later if more models

        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var a = timetable.getActivities().get(rowIndex);
            return switch (columnIndex) {
                case 0 -> a.getId();
                case 1 -> a.getName();
                case 2 -> a.getTeacher();
                case 3 -> a.getType();
                case 4 -> a.getDay();
                case 5 -> a.getStart();
                case 6 -> a.getEnd();
                default -> "?";
            };
        }
    }
}
