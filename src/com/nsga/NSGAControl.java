package com.nsga;

import java.awt.Color;
import javax.swing.JPanel;
import com.nsga.util.CheckBox;
import com.nsga.util.StartButton;

/**
 * 
 * @author Red Williams <red.devcs@gmail.com>
 * @since Jun 22, 2021
 */
public class NSGAControl extends JPanel {

    /**
     * NSGAControl Class Constructor
     */
    public NSGAControl(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        StartButton start = new StartButton("Start");
        CheckBox cb = new CheckBox("Print to Excel?");
        add(cb);
        add(start);
        setBackground(new Color(0, 0, 0, 12));

    }

}
