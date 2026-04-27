package cz.uhk.webtimetable.gui;

import cz.uhk.webtimetable.model.LocationTimeTable;
import cz.uhk.webtimetable.model.Room;
import cz.uhk.webtimetable.utils.ITimeTableProvider;
import cz.uhk.webtimetable.utils.StagRoomProvider;
import cz.uhk.webtimetable.utils.StagTimeTableProvider;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class TimeTableFrame extends JFrame {
    private ITimeTableProvider timetableProvider = new StagTimeTableProvider();
    private LocationTimeTable timetable;
    private StagRoomProvider stagRoomProvider = new StagRoomProvider();
    private List<Room> rooms = new ArrayList<>();

    private JTable tabTimetable;
    private TimeTableModel timeTableModel;
    private JComboBox comboboxBuilding;
    private JComboBox comboboxRoom;
    private JButton buttonRefresh;

    public TimeTableFrame() {
        super("Location TimeTable");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        timetable = timetableProvider.readTimeTable("J","J22");
        initGui();
        initActions();
        add(toolBar(), BorderLayout.NORTH);
        loadRooms();
    }
    private void initGui() {
        timeTableModel = new TimeTableModel();
        tabTimetable = new JTable(timeTableModel);
        buttonRefresh = new JButton("Refresh");
        comboboxBuilding = new JComboBox<>();
        comboboxRoom = new JComboBox<>();
        add(new JScrollPane(tabTimetable), BorderLayout.CENTER);
        pack();
    }
    private void loadRooms(){
        rooms = stagRoomProvider.readRooms();
        comboboxBuilding.removeAllItems();
        ArrayList<String> buildings = new ArrayList<>();

        for(Room r : rooms){
            String building = r.getZkrBudovy();

            if(building != null && !buildings.contains(building)){
                buildings.add(building);
            }
        }
        for(String building : buildings){
            comboboxBuilding.addItem(building);
        }

        repaint();
    }
    private JToolBar toolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.add(comboboxBuilding);
        toolBar.add(comboboxRoom);
        toolBar.add(refresh);
        toolBar.setVisible(true);
        return toolBar;
    }
    private Action refresh;
    private void initActions() {
        refresh = new AbstractAction("Refresh") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String building = comboboxBuilding.getSelectedItem().toString();
                String room = comboboxRoom.getSelectedItem().toString();
                timetable = timetableProvider.readTimeTable(building, room);
                timeTableModel.fireTableDataChanged();
            }
        };
        comboboxBuilding.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboboxBuilding.getSelectedItem() == null) {
                    return;
                }
                comboboxRoom.removeAllItems();
                String selectedBuilding = comboboxBuilding.getSelectedItem().toString();
                for (Room r : rooms) {
                    if (r.getZkrBudovy().equals(selectedBuilding)) {
                        comboboxRoom.addItem(r.getCisloMistnosti());
                    }
                }
            }
        });
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
