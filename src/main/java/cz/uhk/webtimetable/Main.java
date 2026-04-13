package cz.uhk.webtimetable;

import cz.uhk.webtimetable.gui.TimeTableFrame;

import javax.swing.*;

public class Main {
    static void main(String[] args) {
        SwingUtilities.invokeLater(()-> new TimeTableFrame().setVisible(true));
    }
}
