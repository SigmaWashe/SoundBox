package theme;

import com.formdev.flatlaf.*;
import org.jdesktop.swingx.prompt.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;

/**
 *
 * @author SigmaWashe
 */
public class Latte {
    public static final Color LATTE_BG = new Color(238, 218, 185);
    public static final Color CARD_BG = new Color(250, 240, 220);

    public static final Color TEXT_PRIMARY = new Color(75, 50, 30);
    public static final Color TEXT_SECONDARY = new Color(140, 110, 85);

    public static final Color YOUTUBE = new Color(215, 45, 45);
    public static final Color SPOTIFY = new Color(45, 160, 85);

    public static final Color HOVER = new Color(220, 195, 160);

    public Latte(){

        try{
            UIManager.setLookAndFeel(new FlatLightLaf());
            FlatLaf.updateUI();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Font mainFont = new Font("JetBrains Mono", Font.PLAIN, 12);
        Font boldFont = new Font("JetBrains Mono", Font.BOLD, 12);

        // --- Global Component Settings ---
        UIManager.put("Component.arrowType", "triangle");
        UIManager.put("Component.borderColor", HOVER);
        UIManager.put("Component.focusedBorderColor", HOVER);

        // --- Labels & Panels ---
        UIManager.put("Label.font", mainFont);
        UIManager.put("Label.foreground", TEXT_PRIMARY);
        UIManager.put("Panel.background", LATTE_BG);
        UIManager.put("RootPane.background", LATTE_BG);

        // --- Text Fields ---
        UIManager.put("TextField.font", mainFont);
        UIManager.put("TextField.background", CARD_BG);
        UIManager.put("TextField.foreground", TEXT_PRIMARY);
        UIManager.put("TextField.caretForeground", TEXT_PRIMARY);
        UIManager.put("TextField.border", new LineBorder(HOVER, 1, true));

        // --- Buttons ---
        UIManager.put("Button.font", boldFont);
        UIManager.put("Button.background", LATTE_BG);
        UIManager.put("Button.foreground", TEXT_PRIMARY);
        UIManager.put("ToggleButton.background", LATTE_BG);
        UIManager.put("ToggleButton.foreground", TEXT_PRIMARY);

        // --- ComboBox Styling ---
        UIManager.put("ComboBox.font", mainFont);
        UIManager.put("ComboBox.background", LATTE_BG);
        UIManager.put("ComboBox.foreground", TEXT_PRIMARY);
        UIManager.put("ComboBox.buttonBackground", CARD_BG);
        UIManager.put("ComboBox.selectionBackground", HOVER);
        UIManager.put("ComboBox.selectionForeground", TEXT_PRIMARY);
        UIManager.put("ComboBox.popupBackground", CARD_BG);
        UIManager.put("ComboBox.popupForeground", TEXT_PRIMARY);
        UIManager.put("ComboBox.border", new LineBorder(HOVER, 1, true));
        UIManager.put("ComboBox.padding", new Insets(5, 8, 5, 8));

        // --- Lists & Tables ---
        UIManager.put("List.font", mainFont);
        UIManager.put("List.background", CARD_BG);
        UIManager.put("List.foreground", TEXT_PRIMARY);
        UIManager.put("List.selectionBackground", HOVER);
        UIManager.put("List.selectionForeground", TEXT_PRIMARY);

        UIManager.put("Table.background", CARD_BG);
        UIManager.put("Table.foreground", TEXT_PRIMARY);
        UIManager.put("Table.selectionBackground", HOVER);
        UIManager.put("Table.selectionForeground", TEXT_PRIMARY);
        UIManager.put("TableHeader.background", LATTE_BG);
        UIManager.put("TableHeader.foreground", TEXT_PRIMARY);

        // --- Scrolling & Containers ---
        UIManager.put("ScrollPane.background", CARD_BG);
        UIManager.put("Viewport.background", CARD_BG);
        UIManager.put("ScrollBar.track", CARD_BG);
        UIManager.put("ScrollBar.thumb", HOVER);

        UIManager.put("TabbedPane.background", CARD_BG);
        UIManager.put("TabbedPane.foreground", TEXT_PRIMARY);
        UIManager.put("ToolBar.background", LATTE_BG);
        UIManager.put("Tree.background", CARD_BG);

        // --- ToolTips ---
        UIManager.put("ToolTip.font", mainFont);
        UIManager.put("ToolTip.background", CARD_BG);
        UIManager.put("ToolTip.foreground", TEXT_PRIMARY);
        UIManager.put("ToolTip.border", BorderFactory.createLineBorder(HOVER));

        // --- File Chooser ---
        UIManager.put("FileChooser.background", LATTE_BG);

    }

    ///=================================================== MAIN PANEL ==================================================
    public void mainPanel(JPanel mainPanel){
        mainPanel.setBackground(LATTE_BG);
    }

    ///================================================== HEADER PANEL =================================================
    public void headerPanel(JPanel headerPanel, JPanel titlePanel, JLabel titleLbl, JLabel subTitleLbl){
        headerPanel.setBackground(CARD_BG);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        titlePanel.setBackground(CARD_BG);
        titleLbl.setForeground(TEXT_PRIMARY);
        subTitleLbl.setForeground(TEXT_SECONDARY);
    }

    ///================================================== TABBED PANE ==================================================
    public void tabbedPane(JTabbedPane tabbedPane){
        tabbedPane.setBackground(CARD_BG);
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        tabbedPane.setForeground(TEXT_PRIMARY);
    }

    ///================================================== YOUTUBE TAB ==================================================
    public void youtubeTab(JPanel youtubeTab){
        youtubeTab.setBackground(LATTE_BG);
    }
    public void youtubeInputCard(JPanel youtubeInputCard, JLabel yt_urlLbl, JTextField yt_urlFld, JLabel yt_OutputDirLbl,
                                 JPanel yt_DirPnl, JTextField yt_OutputDirFld, JButton yt_BrowseBtn, JPanel yt_OptionsPnl,
                                 JCheckBox yt_PlaylistMode, JComboBox yt_Format, JComboBox yt_Quality){

        youtubeInputCard.setBackground(CARD_BG);
        youtubeInputCard.setForeground(CARD_BG);
        youtubeInputCard.setBorder(BorderFactory.createCompoundBorder(new LineBorder(HOVER, 1, true),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)));
        yt_urlLbl.setForeground(TEXT_PRIMARY);
        styleTextField(yt_urlFld);
        yt_OutputDirLbl.setForeground(TEXT_PRIMARY);
        yt_DirPnl.setBackground(CARD_BG);
        styleTextField(yt_OutputDirFld);
        yt_BrowseBtn.setIcon(new ImageIcon(getClass().getResource("/latteTheme/browse.png")));
        styleButton(yt_BrowseBtn, true, CARD_BG);
        yt_OptionsPnl.setBackground(CARD_BG);
        yt_OptionsPnl.setForeground(CARD_BG);
        styleCheckBox(yt_PlaylistMode);
        yt_PlaylistMode.setBackground(CARD_BG);
        styleComboBox(yt_Format);
        styleComboBox(yt_Quality);

    }
    public void youtubeControlCard(JPanel youtubeControlCard, JButton yt_DownloadBtn, JButton yt_CancelBtn,
                                   JProgressBar yt_ProgressBar, JLabel yt_statusLbl){
        youtubeControlCard.setBackground(LATTE_BG);
        styleButton(yt_DownloadBtn, true, YOUTUBE);
        yt_DownloadBtn.setIcon(new ImageIcon(getClass().getResource("/latteTheme/download.png")));
        styleButton(yt_CancelBtn, true, TEXT_SECONDARY);
        yt_CancelBtn.setIcon(new ImageIcon(getClass().getResource("/latteTheme/downloadCancel.png")));
        styleProgressBar(yt_ProgressBar, YOUTUBE);
        yt_statusLbl.setForeground(TEXT_SECONDARY);
    }

    ///================================================= SPOTIFY TAB =================================================
    public void spotifyTab(JPanel spotifyTab){
        spotifyTab.setBackground(LATTE_BG);
    }
    public void spotifyInputCard(JPanel spotifyInputCard, JLabel spotify_urlLbl, JTextField spotify_urlFld, JLabel spotify_OutputDirLbl,
                                 JPanel spotify_DirPnl, JTextField spotify_OutputDirFld, JButton spotify_BrowseBtn, JPanel spotify_OptionsPnl,
                                 JCheckBox spotify_PlaylistMode, JComboBox spotify_Format, JComboBox spotify_Quality){

        spotifyInputCard.setBackground(CARD_BG);
        spotifyInputCard.setForeground(CARD_BG);
        spotifyInputCard.setBorder(BorderFactory.createCompoundBorder(new LineBorder(HOVER, 1, true),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)));
        spotify_urlLbl.setForeground(TEXT_PRIMARY);
        styleTextField(spotify_urlFld);
        spotify_OutputDirLbl.setForeground(TEXT_PRIMARY);
        spotify_DirPnl.setBackground(CARD_BG);
        styleTextField(spotify_OutputDirFld);
        spotify_BrowseBtn.setIcon(new ImageIcon(getClass().getResource("/latteTheme/browse.png")));
        styleButton(spotify_BrowseBtn, true, CARD_BG);
        spotify_OptionsPnl.setBackground(CARD_BG);
        spotify_OptionsPnl.setForeground(CARD_BG);
        styleCheckBox(spotify_PlaylistMode);
        styleComboBox(spotify_Format);
        styleComboBox(spotify_Quality);
    }
    public void spotifyControlCard(JPanel spotifyControlCard, JButton spotify_DownloadBtn, JButton spotify_CancelBtn,
                                   JProgressBar spotify_ProgressBar, JLabel spotify_statusLbl){
        spotifyControlCard.setBackground(LATTE_BG);
        styleButton(spotify_DownloadBtn, true, SPOTIFY);
        spotify_DownloadBtn.setIcon(new ImageIcon(getClass().getResource("/latteTheme/download.png")));
        styleButton(spotify_CancelBtn, true, TEXT_SECONDARY);
        spotify_CancelBtn.setIcon(new ImageIcon(getClass().getResource("/latteTheme/downloadCancel.png")));
        styleProgressBar(spotify_ProgressBar, SPOTIFY);
        spotify_statusLbl.setForeground(TEXT_SECONDARY);
    }

    /// ================================================= SETTINGS TAB =================================================
    public void settingsTab(JPanel settingsTab){
        settingsTab.setBackground(LATTE_BG);
    }
    public void settingsScrollPane(JScrollPane settingsScrollPane){
        settingsScrollPane.getViewport().setBackground(LATTE_BG);
        settingsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
    }
    public void settingsMainPanel(JPanel settingsMainPanel){
        settingsMainPanel.setBackground(CARD_BG);
    }
    public void appearanceCard(JPanel appearanceCard, JLabel themeLbl, JPanel appearanceContent, JPanel themePanel,
                               JRadioButton lightTheme, JRadioButton darkTheme, JRadioButton latteTheme, JRadioButton espressoTheme){
        appearanceCard.setBackground(CARD_BG);
        appearanceCard.setBorder(BorderFactory.createCompoundBorder(new LineBorder(HOVER, 1, true),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)));
        themeLbl.setForeground(TEXT_PRIMARY);
        appearanceContent.setBackground(CARD_BG);
        themePanel.setBackground(CARD_BG);
        styleRadioButton(lightTheme);
        styleRadioButton(darkTheme);
        styleRadioButton(latteTheme);
        styleRadioButton(espressoTheme);
    }
    public void downloadSettCard(JPanel downloadSettCard, JLabel downloadLbl, JPanel downloadContent,
                                 JCheckBox embedThumbnails, JCheckBox embedMetadata, JCheckBox deleteSourceFiles){
        downloadSettCard.setBackground(CARD_BG);
        downloadSettCard.setBorder(BorderFactory.createCompoundBorder(new LineBorder(HOVER, 1, true),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)));
        downloadLbl.setForeground(TEXT_PRIMARY);
        downloadContent.setBackground(CARD_BG);
        embedThumbnails.setForeground(TEXT_SECONDARY);
        embedMetadata.setForeground(TEXT_SECONDARY);
        deleteSourceFiles.setForeground(TEXT_SECONDARY);
    }
    public void statisticsCard(JPanel statisticsCard, JLabel statisticLbl, JPanel statisticsContent,
                               JLabel totalLbl, JLabel youtube_TotalLbl, JLabel spotify_TotalLbl){
        statisticsCard.setBackground(CARD_BG);
        statisticsCard.setBorder(BorderFactory.createCompoundBorder(new LineBorder(HOVER, 1, true),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)));
        statisticLbl.setForeground(TEXT_PRIMARY);
        statisticsContent.setBackground(CARD_BG);
        totalLbl.setForeground(TEXT_SECONDARY);
        youtube_TotalLbl.setForeground(TEXT_SECONDARY);
        spotify_TotalLbl.setForeground(TEXT_SECONDARY);
    }
    public void actionsCard(JPanel actionsCard, JLabel actionsLbl, JPanel actionsContent, JPanel actionsButtonsPanel,
                            JButton saveSettingsBtn, JButton resetSettingsBtn, JButton openSettingsBtn){
        actionsCard.setBackground(CARD_BG);
        actionsCard.setBorder(BorderFactory.createCompoundBorder(new LineBorder(HOVER, 1, true),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)));
        actionsLbl.setForeground(TEXT_PRIMARY);
        actionsContent.setBackground(CARD_BG);
        actionsButtonsPanel.setBackground(CARD_BG);
        styleButton(saveSettingsBtn, true, CARD_BG);
        styleButton(resetSettingsBtn, true, CARD_BG);
        styleButton(openSettingsBtn, true, CARD_BG);
    }

    /// ================================================== LOG PANEL ==================================================
    public void logPanel(JPanel logPanel, JLabel logLbl, JScrollPane scrollPane, JTextArea logArea){
        logPanel.setBackground(CARD_BG);
        logPanel.setBorder(BorderFactory.createCompoundBorder(new LineBorder(HOVER, 1, true),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)));
        logLbl.setForeground(TEXT_PRIMARY);
        scrollPane.setBorder(new LineBorder(HOVER, 1, true));
        logArea.setBackground(LATTE_BG);
        logArea.setForeground(TEXT_SECONDARY);
        logArea.setCaretColor(TEXT_SECONDARY);
    }

    ///==================================================== STYLERS ====================================================
    private void styleTextField(JTextField field) {
        field.setFont(new Font("JetBrains Mono", Font.PLAIN, 14));
        PromptSupport.setForeground(TEXT_SECONDARY, field);
        field.setBackground(LATTE_BG);
        field.setForeground(TEXT_SECONDARY);
        field.setCaretColor(TEXT_SECONDARY);
        field.setBorder(BorderFactory.createCompoundBorder(new LineBorder(HOVER, 1, true),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void styleCheckBox(JCheckBox checkBox) {
        checkBox.setFont(new Font("JetBrains Mono", Font.PLAIN, 14));
        checkBox.setForeground(TEXT_SECONDARY);
        checkBox.setBackground(CARD_BG);
        checkBox.setFocusPainted(false);
        checkBox.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void styleComboBox(JComboBox list){
        list.setFont(new Font("JetBrains Mono", Font.PLAIN, 14));
        list.setBackground(CARD_BG);
        list.setForeground(TEXT_SECONDARY);
        list.setBorder(new LineBorder(HOVER, 1, true));
        list.setAlignmentX(Component.LEFT_ALIGNMENT);
        list.setBorder(new LineBorder(HOVER, 1, true));
    }

    private JButton styleButton(JButton button, boolean isPrimary, Color primaryColor) {
        button.setFont(new Font("JetBrains Mono", Font.BOLD, 15));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        button.setOpaque(true);
        button.setContentAreaFilled(true);

        if (isPrimary) {
            button.setBackground(primaryColor);
            button.setForeground(TEXT_PRIMARY);
        } else {
            button.setBackground(HOVER);
            button.setForeground(TEXT_PRIMARY);
        }

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (button.isEnabled()) {
                    button.setBackground(LATTE_BG);
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (button.isEnabled()) {
                    button.setBackground(isPrimary ? primaryColor : HOVER);
                }
            }
        });

        return button;
    }

    private void styleRadioButton(JRadioButton radioBtn){
        radioBtn.setFont(new Font("JetBrains Mono", Font.PLAIN, 14));
        radioBtn.setForeground(TEXT_SECONDARY);
        radioBtn.setBackground(CARD_BG);
        radioBtn.setFocusPainted(false);
    }

    private void styleProgressBar(JProgressBar progressBar, Color foreground){
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
        progressBar.setForeground(foreground);
        progressBar.setBackground(CARD_BG);
        progressBar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        progressBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

}