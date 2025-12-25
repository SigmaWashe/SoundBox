package theme;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LightTheme {
    public static final Color BG_LIGHT = new Color(245, 245, 245);
    public static final Color CARD_BG = new Color(255, 255, 255);

    public static final Color TEXT_PRIMARY = new Color(30, 30, 30);
    public static final Color TEXT_SECONDARY = new Color(90, 90, 90);
    public static final Color TEXT_DISABLED = new Color(150, 150, 150);

    public static final Color YT_ACCENT = new Color(251, 0, 50);
    public static final Color YT_HOVER = new Color(230, 230, 230);

    public static final Color SPOTIFY_ACCENT = new Color(30, 215, 96);
    public static final Color SPOTIFY_HOVER = new Color(225, 225, 225);

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

    public void addPlaceholder(JTextField field, String placeholderText) {
        field.setText(placeholderText);
        field.setForeground(TEXT_DISABLED);

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholderText)) {
                    field.setText("");
                    field.setForeground(BG_LIGHT);
                }
            }

            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(TEXT_DISABLED);
                    field.setText(placeholderText);
                }
            }
        });
    }

    // =================================================== UI COMPONENTS ===================================================
    public void headerPanel(JPanel header, JLabel titleLbl, JLabel subTitleLbl, JPanel titlePnl){
        header.setBackground(CARD_BG);
        titleLbl.setForeground(TEXT_PRIMARY);
        subTitleLbl.setForeground(TEXT_SECONDARY);
        titlePnl.setBackground(CARD_BG);
    }

    public void youtubeTab(JPanel tab){ tab.setBackground(CARD_BG); }
    public void spotifyTab(JPanel tab){ tab.setBackground(CARD_BG); }

    public void youtubeInputCard(JPanel card, JLabel urlLabel, JTextField urlField, String placeHolder, JCheckBox playlistCB,
                                 JLabel outputDirLbl, JTextField outputDir, JButton browse, JPanel outputDirPnl){
        card.setBackground(CARD_BG);
        urlLabel.setForeground(TEXT_PRIMARY);

        transparentTextField(urlField);
        urlField.setForeground(TEXT_PRIMARY);
        urlField.setBackground(CARD_BG);
        urlField.setCaretColor(TEXT_PRIMARY);
        addPlaceholder(urlField, placeHolder);

        playlistCB.setForeground(TEXT_SECONDARY);
        playlistCB.setBackground(CARD_BG);

        outputDirLbl.setForeground(TEXT_PRIMARY);
        outputDir.setForeground(TEXT_PRIMARY);
        outputDir.setBackground(CARD_BG);
        transparentTextField(outputDir);

        transparentButton(browse);
        browse.setIcon(new ImageIcon(getClass().getResource("/darkThemeIcon/folder.png")));
        browse.setForeground(TEXT_PRIMARY);
        browse.setToolTipText("Browse");

        outputDirPnl.setBackground(CARD_BG);
    }

    public void spotifyInputCard(JPanel card, JLabel urlLabel, JTextField urlField, String placeHolder,JCheckBox playlistCB,
                                 JLabel outputDirLbl, JTextField outputDir, JButton browse, JPanel outputDirPnl){

        card.setBackground(CARD_BG);
        urlLabel.setForeground(TEXT_PRIMARY);

        transparentTextField(urlField);
        urlField.setForeground(TEXT_PRIMARY);
        urlField.setBackground(CARD_BG);
        urlField.setCaretColor(TEXT_PRIMARY);
        addPlaceholder(urlField, placeHolder);

        playlistCB.setForeground(TEXT_SECONDARY);
        playlistCB.setBackground(CARD_BG);

        outputDirLbl.setForeground(TEXT_PRIMARY);
        outputDir.setForeground(TEXT_PRIMARY);
        outputDir.setBackground(CARD_BG);
        transparentTextField(outputDir);

        transparentButton(browse);
        browse.setIcon(new ImageIcon(getClass().getResource("/darkThemeIcon/folder.png")));
        browse.setForeground(TEXT_PRIMARY);
        browse.setToolTipText("Browse");

        outputDirPnl.setBackground(CARD_BG);
    }

    public void youTubeControlPanel(JPanel mainPnl, JButton downloadBtn, JButton cancelBtn, JProgressBar progressBar,
                                    JLabel statusLbl){

        mainPnl.setBackground(CARD_BG);

        transparentButton(downloadBtn);
        downloadBtn.setIcon(new ImageIcon(getClass().getResource("/darkThemeIcon/download.png")));
        downloadBtn.setToolTipText("Download");

        transparentButton(cancelBtn);
        cancelBtn.setIcon(new ImageIcon(getClass().getResource("/darkThemeIcon/downloadCancel.png")));
        cancelBtn.setToolTipText("Cancel Download");

        progressBar.setForeground(YT_ACCENT);
        progressBar.setBackground(CARD_BG);

        statusLbl.setForeground(TEXT_SECONDARY);
    }

    public void spotifyControlPanel(JPanel mainPnl, JButton downloadBtn, JButton cancelBtn, JProgressBar progressBar,
                                    JLabel statusLbl){

        mainPnl.setBackground(CARD_BG);

        transparentButton(downloadBtn);
        downloadBtn.setIcon(new ImageIcon(getClass().getResource("/darkThemeIcon/download.png")));
        downloadBtn.setToolTipText("Download");

        transparentButton(cancelBtn);
        cancelBtn.setIcon(new ImageIcon(getClass().getResource("/darkThemeIcon/downloadCancel.png")));
        cancelBtn.setToolTipText("Cancel Download");

        progressBar.setForeground(SPOTIFY_ACCENT);
        progressBar.setBackground(CARD_BG);

        statusLbl.setForeground(TEXT_SECONDARY);
    }

    public void settingsCard(String title, JPanel panel){

    }


    public void logPanel(JPanel panel, JLabel logTitle, JTextArea logArea, JScrollPane scrollPane){

        panel.setBackground(CARD_BG);
        logTitle.setForeground(TEXT_PRIMARY);

        logArea.setBackground(BG_LIGHT);
        logArea.setForeground(TEXT_PRIMARY);
        logArea.setCaretColor(TEXT_PRIMARY);

        scrollPane.getViewport().setBackground(BG_LIGHT);

    }

    public void styleTextField(JTextField field){
        field.setBackground(BG_LIGHT);
        field.setForeground(TEXT_PRIMARY);
        field.setCaretColor(TEXT_PRIMARY);
    }

}
