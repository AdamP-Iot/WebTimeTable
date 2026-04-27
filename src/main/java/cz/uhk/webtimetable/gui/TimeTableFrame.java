package cz.uhk.webtimetable.gui;

import cz.uhk.webtimetable.model.LocationTimeTable;
import cz.uhk.webtimetable.utils.ITimeTableProvider;
import cz.uhk.webtimetable.utils.StagTimeTableProvider;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TimeTableFrame extends JFrame {
    private ITimeTableProvider timetableProvider = new StagTimeTableProvider();
    private LocationTimeTable timetable;
    private JTable tabTimetable;
    private TimeTableModel timeTableModel;
    private JComboBox comboboxBuilding;
    private JComboBox comboboxRoom;
    private JButton buttonRefresh;

    public TimeTableFrame() {
        super("Location TimeTable");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        timetable = timetableProvider.readTimeTable("J","J22");
        initActions();
        initGui();
    }
    private void initGui() {
        timeTableModel = new TimeTableModel();
        tabTimetable = new JTable(timeTableModel);
        buttonRefresh = new JButton("Refresh data");
        comboboxBuilding = new JComboBox<>();
        comboboxRoom = new JComboBox<>();
        comboboxBuilding.addItem("Building");
        comboboxRoom.addItem("Room");
        add(new JScrollPane(tabTimetable), BorderLayout.CENTER);
        add(toolBar(), BorderLayout.NORTH);

        pack();
    }
    private JToolBar toolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.add(comboboxBuilding);
        toolBar.add(comboboxRoom);
        toolBar.add(buttonRefresh);
        toolBar.setVisible(true);
        return toolBar;
    }
   //private Action actionCreateSquare;
    private void initActions(){




        /*
        actionCreateSquare = new AbstractAction("Square") {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.add(new Square(new Point(0, 0), Color.BLACK, 50));
            }
        };*/
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
