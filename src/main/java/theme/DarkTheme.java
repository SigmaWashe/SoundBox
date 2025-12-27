package theme;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 
 * @author SigmaWashe
 */

public class DarkTheme {

    public static final Color DARK_BG = new Color(45, 40, 30);
    public static final Color CARD_BG = new Color(60, 55, 45);

    public static final Color TEXT_PRIMARY = new Color(240, 220, 190);
    public static final Color TEXT_SECONDARY = new Color(190, 160, 130);

    public static final Color YOUTUBE = new Color(215, 45, 45);
    public static final Color SPOTIFY = new Color(45, 160, 85);

    public static final Color HOVER = new Color(150, 125, 100);

    public void transparentButton(JButton button) {
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(new Color(0, 0, 0, 128), 2, true));
    }

    public void transparentTextField(JTextField field) {
        field.setOpaque(false);
        field.setBackground(new Color(0, 0, 0, 0));
        field.setBorder(new LineBorder(new Color(0, 0, 0, 128), 2, true));
    }
}
