package UI;

import config.Settings;
import theme.*;

import com.formdev.flatlaf.*;
import org.jdesktop.swingx.prompt.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 *
 * @author SigmaWashe
 */
public class MainMenu extends javax.swing.JFrame {

    private static final Color LIGHT_BG = new Color(238, 218, 185);
    private static final Color CARD_BG = new Color(250, 240, 220);

    private static final Color TEXT_PRIMARY = new Color(75, 50, 30);
    private static final Color TEXT_SECONDARY = new Color(140, 110, 85);

    private static final Color YOUTUBE = new Color(215, 45, 45);
    private static final Color SPOTIFY = new Color(45, 160, 85);

    private static final Color HOVER = new Color(220, 195, 160);

    private Settings settings = new Settings();
    private Dark darkTheme;
    private Espresso espressoTheme;
    private Latte latteTheme;
    private Light lightTheme;


    /** 
     * Creates new form MainMenu
     */
    public MainMenu() {
        super("SOUND BOX");
        initComponents();
        setLocationRelativeTo(null);
        
        PromptSupport.setPrompt("Enter YouTube URL", yt_urlFld);
        PromptSupport.setForeground(TEXT_SECONDARY, yt_urlFld);
        
        PromptSupport.setPrompt("Enter Spotify URL", spotify_urlFld);
        PromptSupport.setForeground(TEXT_SECONDARY, spotify_urlFld);

        applyDefaultTheme();
        
    }

    private void applyDefaultTheme(){
        if(settings.getTheme().equalsIgnoreCase("light")){
            getContentPane().setBackground(UIManager.getColor("Frame.background"));
            lightThemeBtn.setSelected(true);
            applyLightTheme();
        } else if(settings.getTheme().equalsIgnoreCase("dark")){
            getContentPane().setBackground(UIManager.getColor("Frame.background"));
            darkThemeBtn.setSelected(true);
            applyDarkTheme();
        }else if(settings.getTheme().equalsIgnoreCase("latte")){
            latteThemeBtn.setSelected(true);
            applyLatteTheme();
        }else if(settings.getTheme().equalsIgnoreCase("espresso")){
            latteThemeBtn.setSelected(true);
            applyEspressoTheme();
        }
    }

    private void applyLightTheme(){
        try {
            resetTheme();
            lightTheme = new Light();
            lightTheme.mainPanel(MainPanel);
            lightTheme.headerPanel(HeaderPnl, TitlePanel, titleLbl, subtitleLbl);
            lightTheme.tabbedPane(TabbedPane);
            lightTheme.youtubeTab(YouTubePnl);
            lightTheme.youtubeInputCard(YouTubeInputCard, yt_urlLbl, yt_urlFld, yt_OutputDirLbl, yt_DirPnl, yt_OutputDirFld,
                    yt_BrowseBtn, yt_OptionsPnl, yt_PlaylistCheckBox, yt_AudioFormatComBox, yt_AudioQualityComBox);
            lightTheme.youtubeControlCard(YouTubeControlCard, yt_DownloadBtn, yt_CancelDownloadBtn, yt_ProgressBar, yt_StatusLbl);
            lightTheme.spotifyTab(SpotifyPnl);
            lightTheme.spotifyInputCard(SpotifyInputCard, spotify_urlLbl, spotify_urlFld, spotify_OutputDirLbl, spotify_DirPnl,
                    spotify_OutputDirFld, spotify_BrowseBtn, spotify_OptionsPnl, spotify_PlaylistCheckBox, spotify_AudioFormatComBox, spotify_AudioQualityComBox);
            lightTheme.spotifyControlCard(SpotifyControlCard, spotify_DownloadBtn, spotify_CancelDownloadBtn, spotify_ProgressBar, spotify_StatusLbl);
            lightTheme.settingsTab(SettingsPnl);
            lightTheme.settingsScrollPane(SettingsScrollPane);
            lightTheme.settingsMainPanel(SettingsMainPnl);
            lightTheme.appearanceCard(appearanceCard, themeLbl, appearanceContent, themePnl, lightThemeBtn, darkThemeBtn, latteThemeBtn, espressoThemeBtn);
            lightTheme.downloadSettCard(downloadSettingsCard, downloadLbl, downloadContent, embedThumbnails, embedMetadata, deleteSourceFiles);
            lightTheme.statisticsCard(statisticsCard, statisticsLbl, statsContent, totalLbl, youtube_TotalLbl, spotify_TotalLbl);
            lightTheme.actionsCard(actionsCard, actionsLbl, actionsContent, actionButtonsPnl, saveSettingsBtn, resetSettingsBtn, openSettingsFolderBtn);
            lightTheme.logPanel(LogPnl, logLbl, jScrollPane1, logArea);

            SwingUtilities.updateComponentTreeUI(this);
            revalidate();
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void applyDarkTheme(){
        try {
            resetTheme();
            darkTheme = new Dark();
            darkTheme.mainPanel(MainPanel);
            darkTheme.headerPanel(HeaderPnl, TitlePanel, titleLbl, subtitleLbl);
            darkTheme.tabbedPane(TabbedPane);
            darkTheme.youtubeTab(YouTubePnl);
            darkTheme.youtubeInputCard(YouTubeInputCard, yt_urlLbl, yt_urlFld, yt_OutputDirLbl, yt_DirPnl, yt_OutputDirFld,
                    yt_BrowseBtn, yt_OptionsPnl, yt_PlaylistCheckBox, yt_AudioFormatComBox, yt_AudioQualityComBox);
            darkTheme.youtubeControlCard(YouTubeControlCard, yt_DownloadBtn, yt_CancelDownloadBtn, yt_ProgressBar, yt_StatusLbl);
            darkTheme.spotifyTab(SpotifyPnl);
            darkTheme.spotifyInputCard(SpotifyInputCard, spotify_urlLbl, spotify_urlFld, spotify_OutputDirLbl, spotify_DirPnl,
                    spotify_OutputDirFld, spotify_BrowseBtn, spotify_OptionsPnl, spotify_PlaylistCheckBox, spotify_AudioFormatComBox, spotify_AudioQualityComBox);
            darkTheme.spotifyControlCard(SpotifyControlCard, spotify_DownloadBtn, spotify_CancelDownloadBtn, spotify_ProgressBar, spotify_StatusLbl);
            darkTheme.settingsTab(SettingsPnl);
            darkTheme.settingsScrollPane(SettingsScrollPane);
            darkTheme.settingsMainPanel(SettingsMainPnl);
            darkTheme.appearanceCard(appearanceCard, themeLbl, appearanceContent, themePnl, lightThemeBtn, darkThemeBtn, latteThemeBtn, espressoThemeBtn);
            darkTheme.downloadSettCard(downloadSettingsCard, downloadLbl, downloadContent, embedThumbnails, embedMetadata, deleteSourceFiles);
            darkTheme.statisticsCard(statisticsCard, statisticsLbl, statsContent, totalLbl, youtube_TotalLbl, spotify_TotalLbl);
            darkTheme.actionsCard(actionsCard, actionsLbl, actionsContent, actionButtonsPnl, saveSettingsBtn, resetSettingsBtn, openSettingsFolderBtn);
            darkTheme.logPanel(LogPnl, logLbl, jScrollPane1, logArea);

            SwingUtilities.updateComponentTreeUI(this);
            revalidate();
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void applyLatteTheme(){
        try {
            resetTheme();
            latteTheme = new Latte();
            latteTheme.mainPanel(MainPanel);
            latteTheme.headerPanel(HeaderPnl, TitlePanel, titleLbl, subtitleLbl);
            latteTheme.tabbedPane(TabbedPane);
            latteTheme.youtubeTab(YouTubePnl);
            latteTheme.youtubeInputCard(YouTubeInputCard, yt_urlLbl, yt_urlFld, yt_OutputDirLbl, yt_DirPnl, yt_OutputDirFld,
                    yt_BrowseBtn, yt_OptionsPnl, yt_PlaylistCheckBox, yt_AudioFormatComBox, yt_AudioQualityComBox);
            latteTheme.youtubeControlCard(YouTubeControlCard, yt_DownloadBtn, yt_CancelDownloadBtn, yt_ProgressBar, yt_StatusLbl);
            latteTheme.spotifyTab(SpotifyPnl);
            latteTheme.spotifyInputCard(SpotifyInputCard, spotify_urlLbl, spotify_urlFld, spotify_OutputDirLbl, spotify_DirPnl,
                    spotify_OutputDirFld, spotify_BrowseBtn, spotify_OptionsPnl, spotify_PlaylistCheckBox, spotify_AudioFormatComBox, spotify_AudioQualityComBox);
            latteTheme.spotifyControlCard(SpotifyControlCard, spotify_DownloadBtn, spotify_CancelDownloadBtn, spotify_ProgressBar, spotify_StatusLbl);
            latteTheme.settingsTab(SettingsPnl);
            latteTheme.settingsScrollPane(SettingsScrollPane);
            latteTheme.settingsMainPanel(SettingsMainPnl);
            latteTheme.appearanceCard(appearanceCard, themeLbl, appearanceContent, themePnl, lightThemeBtn, darkThemeBtn, latteThemeBtn, espressoThemeBtn);
            latteTheme.downloadSettCard(downloadSettingsCard, downloadLbl, downloadContent, embedThumbnails, embedMetadata, deleteSourceFiles);
            latteTheme.statisticsCard(statisticsCard, statisticsLbl, statsContent, totalLbl, youtube_TotalLbl, spotify_TotalLbl);
            latteTheme.actionsCard(actionsCard, actionsLbl, actionsContent, actionButtonsPnl, saveSettingsBtn, resetSettingsBtn, openSettingsFolderBtn);
            latteTheme.logPanel(LogPnl, logLbl, jScrollPane1, logArea);

            SwingUtilities.updateComponentTreeUI(this);
            revalidate();
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void applyEspressoTheme(){
        try {
            resetTheme();
            espressoTheme = new Espresso();
            espressoTheme.mainPanel(MainPanel);
            espressoTheme.headerPanel(HeaderPnl, TitlePanel, titleLbl, subtitleLbl);
            espressoTheme.tabbedPane(TabbedPane);
            espressoTheme.youtubeTab(YouTubePnl);
            espressoTheme.youtubeInputCard(YouTubeInputCard, yt_urlLbl, yt_urlFld, yt_OutputDirLbl, yt_DirPnl, yt_OutputDirFld,
                    yt_BrowseBtn, yt_OptionsPnl, yt_PlaylistCheckBox, yt_AudioFormatComBox, yt_AudioQualityComBox);
            espressoTheme.youtubeControlCard(YouTubeControlCard, yt_DownloadBtn, yt_CancelDownloadBtn, yt_ProgressBar, yt_StatusLbl);
            espressoTheme.spotifyTab(SpotifyPnl);
            espressoTheme.spotifyInputCard(SpotifyInputCard, spotify_urlLbl, spotify_urlFld, spotify_OutputDirLbl, spotify_DirPnl,
                    spotify_OutputDirFld, spotify_BrowseBtn, spotify_OptionsPnl, spotify_PlaylistCheckBox, spotify_AudioFormatComBox, spotify_AudioQualityComBox);
            espressoTheme.spotifyControlCard(SpotifyControlCard, spotify_DownloadBtn, spotify_CancelDownloadBtn, spotify_ProgressBar, spotify_StatusLbl);
            espressoTheme.settingsTab(SettingsPnl);
            espressoTheme.settingsScrollPane(SettingsScrollPane);
            espressoTheme.settingsMainPanel(SettingsMainPnl);
            espressoTheme.appearanceCard(appearanceCard, themeLbl, appearanceContent, themePnl, lightThemeBtn, darkThemeBtn, latteThemeBtn, espressoThemeBtn);
            espressoTheme.downloadSettCard(downloadSettingsCard, downloadLbl, downloadContent, embedThumbnails, embedMetadata, deleteSourceFiles);
            espressoTheme.statisticsCard(statisticsCard, statisticsLbl, statsContent, totalLbl, youtube_TotalLbl, spotify_TotalLbl);
            espressoTheme.actionsCard(actionsCard, actionsLbl, actionsContent, actionButtonsPnl, saveSettingsBtn, resetSettingsBtn, openSettingsFolderBtn);
            espressoTheme.logPanel(LogPnl, logLbl, jScrollPane1, logArea);

            SwingUtilities.updateComponentTreeUI(this);
            revalidate();
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    private void browseOutputDirectory(JTextField field) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(field.getText()));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            field.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }
    
    private void resetTheme() {
        // --- Global Component Settings ---
        UIManager.put("Component.arrowType", null);
        UIManager.put("Component.borderColor", null);
        UIManager.put("Component.focusedBorderColor", null);

        // --- Labels & Panels ---
        UIManager.put("Label.font", null);
        UIManager.put("Label.foreground", null);
        UIManager.put("Panel.background", null);
        UIManager.put("RootPane.background", null);

        // --- Text Fields ---
        UIManager.put("TextField.font", null);
        UIManager.put("TextField.background", null);
        UIManager.put("TextField.foreground", null);
        UIManager.put("TextField.caretForeground", null);
        UIManager.put("TextField.border", null);

        // --- Buttons ---
        UIManager.put("Button.font", null);
        UIManager.put("Button.background", null);
        UIManager.put("Button.foreground", null);
        UIManager.put("ToggleButton.background", null);
        UIManager.put("ToggleButton.foreground", null);

        // --- ComboBox Styling ---
        UIManager.put("ComboBox.font", null);
        UIManager.put("ComboBox.background", null);
        UIManager.put("ComboBox.foreground", null);
        UIManager.put("ComboBox.buttonBackground", null);
        UIManager.put("ComboBox.selectionBackground", null);
        UIManager.put("ComboBox.selectionForeground", null);
        UIManager.put("ComboBox.popupBackground", null);
        UIManager.put("ComboBox.popupForeground", null);
        UIManager.put("ComboBox.border", null);
        UIManager.put("ComboBox.padding", null);

        // --- Lists & Tables ---
        UIManager.put("List.font", null);
        UIManager.put("List.background", null);
        UIManager.put("List.foreground", null);
        UIManager.put("List.selectionBackground", null);
        UIManager.put("List.selectionForeground", null);

        UIManager.put("Table.background", null);
        UIManager.put("Table.foreground", null);
        UIManager.put("Table.selectionBackground", null);
        UIManager.put("Table.selectionForeground", null);
        UIManager.put("TableHeader.background", null);
        UIManager.put("TableHeader.foreground", null);

        // --- Scrolling & Containers ---
        UIManager.put("ScrollPane.background", null);
        UIManager.put("Viewport.background", null);
        UIManager.put("ScrollBar.track", null);
        UIManager.put("ScrollBar.thumb", null);

        UIManager.put("TabbedPane.background", null);
        UIManager.put("TabbedPane.foreground", null);
        UIManager.put("ToolBar.background", null);
        UIManager.put("Tree.background", null);

        // --- ToolTips ---
        UIManager.put("ToolTip.font", null);
        UIManager.put("ToolTip.background", null);
        UIManager.put("ToolTip.foreground", null);
        UIManager.put("ToolTip.border", null);

        // --- File Chooser ---
        UIManager.put("FileChooser.background", null);

        // --- FlatLaf @ properties (if using Light theme's @ properties) ---
        UIManager.put("@background", null);
        UIManager.put("@foreground", null);
        UIManager.put("@accentColor", null);
        UIManager.put("@componentBackground", null);
        UIManager.put("@componentForeground", null);
        UIManager.put("@selectionBackground", null);
        UIManager.put("@selectionForeground", null);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        themeGroup = new javax.swing.ButtonGroup();
        MainPanel = new javax.swing.JPanel();
        HeaderPnl = new javax.swing.JPanel();
        TitlePanel = new javax.swing.JPanel();
        titleLbl = new javax.swing.JLabel();
        subtitleLbl = new javax.swing.JLabel();
        TabbedPane = new javax.swing.JTabbedPane();
        YouTubePnl = new javax.swing.JPanel();
        YouTubeInputCard = new javax.swing.JPanel();
        yt_urlLbl = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        yt_urlFld = new javax.swing.JTextField();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 25), new java.awt.Dimension(0, 25), new java.awt.Dimension(32767, 25));
        yt_OutputDirLbl = new javax.swing.JLabel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        yt_DirPnl = new javax.swing.JPanel();
        yt_OutputDirFld = new javax.swing.JTextField();
        yt_BrowseBtn = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        yt_OptionsPnl = new javax.swing.JPanel();
        yt_PlaylistCheckBox = new javax.swing.JCheckBox();
        yt_AudioFormatComBox = new javax.swing.JComboBox<>();
        yt_AudioQualityComBox = new javax.swing.JComboBox<>();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(32767, 15));
        YouTubeControlCard = new javax.swing.JPanel();
        yt_DownloadBtn = new javax.swing.JButton();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 12), new java.awt.Dimension(0, 12), new java.awt.Dimension(32767, 12));
        yt_CancelDownloadBtn = new javax.swing.JButton();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        yt_ProgressBar = new javax.swing.JProgressBar();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        yt_StatusLbl = new javax.swing.JLabel();
        SpotifyPnl = new javax.swing.JPanel();
        SpotifyInputCard = new javax.swing.JPanel();
        spotify_urlLbl = new javax.swing.JLabel();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        spotify_urlFld = new javax.swing.JTextField();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 25), new java.awt.Dimension(0, 25), new java.awt.Dimension(32767, 25));
        spotify_OutputDirLbl = new javax.swing.JLabel();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        spotify_DirPnl = new javax.swing.JPanel();
        spotify_OutputDirFld = new javax.swing.JTextField();
        spotify_BrowseBtn = new javax.swing.JButton();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        spotify_OptionsPnl = new javax.swing.JPanel();
        spotify_PlaylistCheckBox = new javax.swing.JCheckBox();
        spotify_AudioFormatComBox = new javax.swing.JComboBox<>();
        spotify_AudioQualityComBox = new javax.swing.JComboBox<>();
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(32767, 15));
        SpotifyControlCard = new javax.swing.JPanel();
        spotify_DownloadBtn = new javax.swing.JButton();
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 12), new java.awt.Dimension(0, 12), new java.awt.Dimension(32767, 12));
        spotify_CancelDownloadBtn = new javax.swing.JButton();
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        spotify_ProgressBar = new javax.swing.JProgressBar();
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        spotify_StatusLbl = new javax.swing.JLabel();
        SettingsPnl = new javax.swing.JPanel();
        SettingsScrollPane = new javax.swing.JScrollPane();
        SettingsMainPnl = new javax.swing.JPanel();
        appearanceCard = new javax.swing.JPanel();
        themeLbl = new javax.swing.JLabel();
        appearanceContent = new javax.swing.JPanel();
        themePnl = new javax.swing.JPanel();
        lightThemeBtn = new javax.swing.JRadioButton();
        darkThemeBtn = new javax.swing.JRadioButton();
        latteThemeBtn = new javax.swing.JRadioButton();
        espressoThemeBtn = new javax.swing.JRadioButton();
        filler18 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        downloadSettingsCard = new javax.swing.JPanel();
        downloadLbl = new javax.swing.JLabel();
        downloadContent = new javax.swing.JPanel();
        embedThumbnails = new javax.swing.JCheckBox();
        filler17 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(32767, 15));
        embedMetadata = new javax.swing.JCheckBox();
        filler23 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 15), new java.awt.Dimension(0, 15), new java.awt.Dimension(32767, 15));
        deleteSourceFiles = new javax.swing.JCheckBox();
        filler21 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        statisticsCard = new javax.swing.JPanel();
        statisticsLbl = new javax.swing.JLabel();
        statsContent = new javax.swing.JPanel();
        totalLbl = new javax.swing.JLabel();
        filler19 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        youtube_TotalLbl = new javax.swing.JLabel();
        filler20 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 10), new java.awt.Dimension(0, 10), new java.awt.Dimension(32767, 10));
        spotify_TotalLbl = new javax.swing.JLabel();
        filler22 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        actionsCard = new javax.swing.JPanel();
        actionsLbl = new javax.swing.JLabel();
        actionsContent = new javax.swing.JPanel();
        actionButtonsPnl = new javax.swing.JPanel();
        saveSettingsBtn = new javax.swing.JButton();
        resetSettingsBtn = new javax.swing.JButton();
        openSettingsFolderBtn = new javax.swing.JButton();
        LogPnl = new javax.swing.JPanel();
        logLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(CARD_BG);

        MainPanel.setBackground(LIGHT_BG);
        MainPanel.setPreferredSize(new java.awt.Dimension(700, 900));
        MainPanel.setLayout(new java.awt.BorderLayout());

        HeaderPnl.setBackground(CARD_BG);
        HeaderPnl.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 30, 20, 30));
        HeaderPnl.setMinimumSize(new java.awt.Dimension(0, 0));
        HeaderPnl.setLayout(new java.awt.BorderLayout());

        TitlePanel.setBackground(CARD_BG);
        TitlePanel.setMinimumSize(new java.awt.Dimension(0, 0));
        TitlePanel.setName(""); // NOI18N
        TitlePanel.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        titleLbl.setFont(new java.awt.Font("JetBrains Mono", 1, 26)); // NOI18N
        titleLbl.setForeground(TEXT_PRIMARY);
        titleLbl.setText("SOUND BOX");
        TitlePanel.add(titleLbl);

        subtitleLbl.setFont(new java.awt.Font("JetBrains Mono", 0, 13)); // NOI18N
        subtitleLbl.setForeground(TEXT_SECONDARY);
        subtitleLbl.setText("Download high quality audio files from YouTube and Spotify");
        TitlePanel.add(subtitleLbl);

        HeaderPnl.add(TitlePanel, java.awt.BorderLayout.WEST);

        MainPanel.add(HeaderPnl, java.awt.BorderLayout.NORTH);

        TabbedPane.setBackground(CARD_BG);
        TabbedPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 50, 10, 50));
        TabbedPane.setForeground(TEXT_PRIMARY);
        TabbedPane.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TabbedPane.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N

        YouTubePnl.setBackground(LIGHT_BG);
        YouTubePnl.setLayout(new java.awt.BorderLayout(0, 20));

        YouTubeInputCard.setBackground(CARD_BG        );
        YouTubeInputCard.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(HOVER, 1, true), javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25)));
        YouTubeInputCard.setForeground(CARD_BG);
        YouTubeInputCard.setLayout(new javax.swing.BoxLayout(YouTubeInputCard, javax.swing.BoxLayout.Y_AXIS));

        yt_urlLbl.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 14)); // NOI18N
        yt_urlLbl.setText("YouTube URL");
        YouTubeInputCard.add(yt_urlLbl);
        YouTubeInputCard.add(filler1);

        yt_urlFld.setBackground(LIGHT_BG);
        yt_urlFld.setColumns(40);
        yt_urlFld.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        yt_urlFld.setForeground(TEXT_SECONDARY);
        yt_urlFld.setAlignmentX(0.0F);
        yt_urlFld.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(HOVER, 1, true), javax.swing.BorderFactory.createEmptyBorder(12, 15, 12, 15)));
        yt_urlFld.setCaretColor(TEXT_PRIMARY);
        YouTubeInputCard.add(yt_urlFld);
        YouTubeInputCard.add(filler2);

        yt_OutputDirLbl.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        yt_OutputDirLbl.setForeground(TEXT_PRIMARY);
        yt_OutputDirLbl.setText("Output Folder");
        yt_OutputDirLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        YouTubeInputCard.add(yt_OutputDirLbl);
        YouTubeInputCard.add(filler4);

        yt_DirPnl.setBackground(CARD_BG);
        yt_DirPnl.setAlignmentX(0.0F);
        yt_DirPnl.setMaximumSize(new java.awt.Dimension(2147483647, 45));
        yt_DirPnl.setPreferredSize(new java.awt.Dimension(655, 45));
        yt_DirPnl.setLayout(new java.awt.BorderLayout(10, 0));

        yt_OutputDirFld.setBackground(CARD_BG);
        yt_OutputDirFld.setColumns(40);
        yt_OutputDirFld.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        yt_OutputDirFld.setForeground(TEXT_SECONDARY);
        yt_OutputDirFld.setAlignmentX(0.0F);
        yt_OutputDirFld.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(HOVER, 1, true), javax.swing.BorderFactory.createEmptyBorder(12, 15, 12, 15)));
        yt_OutputDirFld.setCaretColor(TEXT_PRIMARY);
        yt_DirPnl.add(yt_OutputDirFld, java.awt.BorderLayout.CENTER);

        yt_BrowseBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/latteTheme/browse.png"))); // NOI18N
        yt_BrowseBtn.setToolTipText("Browse");
        yt_BrowseBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 25, 12, 25));
        yt_BrowseBtn.setPreferredSize(new java.awt.Dimension(45, 45));
        yt_BrowseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yt_BrowseBtnActionPerformed(evt);
            }
        });
        yt_DirPnl.add(yt_BrowseBtn, java.awt.BorderLayout.EAST);
        yt_DirPnl.add(filler3, java.awt.BorderLayout.PAGE_START);

        YouTubeInputCard.add(yt_DirPnl);

        yt_OptionsPnl.setBackground(CARD_BG);
        yt_OptionsPnl.setForeground(CARD_BG);
        yt_OptionsPnl.setAlignmentX(0.0F);
        yt_OptionsPnl.setLayout(new java.awt.GridBagLayout());

        yt_PlaylistCheckBox.setBackground(CARD_BG);
        yt_PlaylistCheckBox.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        yt_PlaylistCheckBox.setForeground(TEXT_PRIMARY);
        yt_PlaylistCheckBox.setText("Download entire playlist");
        yt_PlaylistCheckBox.setFocusPainted(false);
        yt_PlaylistCheckBox.setPreferredSize(new java.awt.Dimension(217, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        yt_OptionsPnl.add(yt_PlaylistCheckBox, gridBagConstraints);

        yt_AudioFormatComBox.setBackground(CARD_BG);
        yt_AudioFormatComBox.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        yt_AudioFormatComBox.setForeground(TEXT_PRIMARY);
        yt_AudioFormatComBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MP3 Audio", "M4A Audio", "WAV Audio" }));
        yt_AudioFormatComBox.setToolTipText("Audio Format");
        yt_AudioFormatComBox.setAlignmentX(0.0F);
        yt_AudioFormatComBox.setBorder(new javax.swing.border.LineBorder(HOVER, 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        yt_OptionsPnl.add(yt_AudioFormatComBox, gridBagConstraints);

        yt_AudioQualityComBox.setBackground(CARD_BG);
        yt_AudioQualityComBox.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        yt_AudioQualityComBox.setForeground(TEXT_PRIMARY);
        yt_AudioQualityComBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ultra  (320 kbps)", "High   (256 kbps)", "Medium (192 kbps)", "Low    (128 kbps)" }));
        yt_AudioQualityComBox.setAlignmentX(0.0F);
        yt_AudioQualityComBox.setBorder(new javax.swing.border.LineBorder(HOVER, 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        yt_OptionsPnl.add(yt_AudioQualityComBox, gridBagConstraints);
        yt_OptionsPnl.add(filler6, new java.awt.GridBagConstraints());

        YouTubeInputCard.add(yt_OptionsPnl);

        YouTubePnl.add(YouTubeInputCard, java.awt.BorderLayout.NORTH);

        YouTubeControlCard.setBackground(LIGHT_BG);
        YouTubeControlCard.setLayout(new javax.swing.BoxLayout(YouTubeControlCard, javax.swing.BoxLayout.Y_AXIS));

        yt_DownloadBtn.setBackground(YOUTUBE);
        yt_DownloadBtn.setFont(new java.awt.Font("JetBrains Mono", 1, 15)); // NOI18N
        yt_DownloadBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/latteTheme/download.png"))); // NOI18N
        yt_DownloadBtn.setText("DOWNLOAD");
        yt_DownloadBtn.setToolTipText("Download");
        yt_DownloadBtn.setAlignmentX(0.5F);
        yt_DownloadBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 25, 12, 25));
        yt_DownloadBtn.setBorderPainted(false);
        yt_DownloadBtn.setFocusPainted(false);
        yt_DownloadBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        yt_DownloadBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        yt_DownloadBtn.setMinimumSize(new java.awt.Dimension(180, 50));
        yt_DownloadBtn.setOpaque(true);
        yt_DownloadBtn.setPreferredSize(new java.awt.Dimension(180, 50));
        YouTubeControlCard.add(yt_DownloadBtn);
        YouTubeControlCard.add(filler5);

        yt_CancelDownloadBtn.setFont(new java.awt.Font("JetBrains Mono", 1, 15)); // NOI18N
        yt_CancelDownloadBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/latteTheme/downloadCancel.png"))); // NOI18N
        yt_CancelDownloadBtn.setText("CANCEL  ");
        yt_CancelDownloadBtn.setToolTipText("Cancel Download");
        yt_CancelDownloadBtn.setAlignmentX(0.5F);
        yt_CancelDownloadBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 25, 12, 25));
        yt_CancelDownloadBtn.setBorderPainted(false);
        yt_CancelDownloadBtn.setFocusPainted(false);
        yt_CancelDownloadBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        yt_CancelDownloadBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        yt_CancelDownloadBtn.setMinimumSize(new java.awt.Dimension(180, 50));
        yt_CancelDownloadBtn.setOpaque(true);
        yt_CancelDownloadBtn.setPreferredSize(new java.awt.Dimension(180, 50));
        YouTubeControlCard.add(yt_CancelDownloadBtn);
        YouTubeControlCard.add(filler7);

        yt_ProgressBar.setBackground(CARD_BG);
        yt_ProgressBar.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        yt_ProgressBar.setForeground(YOUTUBE);
        yt_ProgressBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        yt_ProgressBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));
        yt_ProgressBar.setString("Ready");
        yt_ProgressBar.setStringPainted(true);
        YouTubeControlCard.add(yt_ProgressBar);
        YouTubeControlCard.add(filler8);

        yt_StatusLbl.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        yt_StatusLbl.setForeground(TEXT_SECONDARY);
        yt_StatusLbl.setText("Ready to download");
        yt_StatusLbl.setAlignmentX(0.5F);
        YouTubeControlCard.add(yt_StatusLbl);

        YouTubePnl.add(YouTubeControlCard, java.awt.BorderLayout.CENTER);

        TabbedPane.addTab("YouTube", new javax.swing.ImageIcon(getClass().getResource("/yt_music.png")), YouTubePnl, "YouTube"); // NOI18N

        SpotifyPnl.setBackground(LIGHT_BG);
        SpotifyPnl.setLayout(new java.awt.BorderLayout(0, 20));

        SpotifyInputCard.setBackground(CARD_BG        );
        SpotifyInputCard.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(HOVER, 1, true), javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25)));
        SpotifyInputCard.setLayout(new javax.swing.BoxLayout(SpotifyInputCard, javax.swing.BoxLayout.Y_AXIS));

        spotify_urlLbl.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 14)); // NOI18N
        spotify_urlLbl.setText("Spotify URL");
        SpotifyInputCard.add(spotify_urlLbl);
        SpotifyInputCard.add(filler9);

        spotify_urlFld.setBackground(LIGHT_BG);
        spotify_urlFld.setColumns(40);
        spotify_urlFld.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        spotify_urlFld.setForeground(TEXT_PRIMARY);
        spotify_urlFld.setAlignmentX(0.0F);
        spotify_urlFld.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(HOVER, 1, true), javax.swing.BorderFactory.createEmptyBorder(12, 15, 12, 15)));
        SpotifyInputCard.add(spotify_urlFld);
        SpotifyInputCard.add(filler10);

        spotify_OutputDirLbl.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        spotify_OutputDirLbl.setForeground(TEXT_PRIMARY);
        spotify_OutputDirLbl.setText("Output Folder");
        spotify_OutputDirLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        SpotifyInputCard.add(spotify_OutputDirLbl);
        SpotifyInputCard.add(filler11);

        spotify_DirPnl.setBackground(CARD_BG);
        spotify_DirPnl.setAlignmentX(0.0F);
        spotify_DirPnl.setMaximumSize(new java.awt.Dimension(2147483647, 45));
        spotify_DirPnl.setPreferredSize(new java.awt.Dimension(655, 45));
        spotify_DirPnl.setLayout(new java.awt.BorderLayout(10, 0));

        spotify_OutputDirFld.setBackground(CARD_BG);
        spotify_OutputDirFld.setColumns(40);
        spotify_OutputDirFld.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        spotify_OutputDirFld.setForeground(TEXT_SECONDARY);
        spotify_OutputDirFld.setAlignmentX(0.0F);
        spotify_OutputDirFld.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(HOVER, 1, true), javax.swing.BorderFactory.createEmptyBorder(12, 15, 12, 15)));
        spotify_DirPnl.add(spotify_OutputDirFld, java.awt.BorderLayout.CENTER);

        spotify_BrowseBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/latteTheme/browse.png"))); // NOI18N
        spotify_BrowseBtn.setToolTipText("Browse");
        spotify_BrowseBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 25, 12, 25));
        spotify_BrowseBtn.setPreferredSize(new java.awt.Dimension(45, 45));
        spotify_BrowseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spotify_BrowseBtnActionPerformed(evt);
            }
        });
        spotify_DirPnl.add(spotify_BrowseBtn, java.awt.BorderLayout.EAST);
        spotify_DirPnl.add(filler12, java.awt.BorderLayout.PAGE_START);

        SpotifyInputCard.add(spotify_DirPnl);

        spotify_OptionsPnl.setBackground(CARD_BG);
        spotify_OptionsPnl.setAlignmentX(0.0F);
        spotify_OptionsPnl.setLayout(new java.awt.GridBagLayout());

        spotify_PlaylistCheckBox.setBackground(CARD_BG);
        spotify_PlaylistCheckBox.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        spotify_PlaylistCheckBox.setForeground(TEXT_PRIMARY);
        spotify_PlaylistCheckBox.setText("Download entire playlist");
        spotify_PlaylistCheckBox.setFocusPainted(false);
        spotify_PlaylistCheckBox.setPreferredSize(new java.awt.Dimension(217, 30));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        spotify_OptionsPnl.add(spotify_PlaylistCheckBox, gridBagConstraints);

        spotify_AudioFormatComBox.setBackground(CARD_BG);
        spotify_AudioFormatComBox.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        spotify_AudioFormatComBox.setForeground(TEXT_PRIMARY);
        spotify_AudioFormatComBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MP3 Audio", "M4A Audio", "WAV Audio" }));
        spotify_AudioFormatComBox.setToolTipText("Audio Format");
        spotify_AudioFormatComBox.setAlignmentX(0.0F);
        spotify_AudioFormatComBox.setBorder(new javax.swing.border.LineBorder(HOVER, 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        spotify_OptionsPnl.add(spotify_AudioFormatComBox, gridBagConstraints);

        spotify_AudioQualityComBox.setBackground(CARD_BG);
        spotify_AudioQualityComBox.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        spotify_AudioQualityComBox.setForeground(TEXT_PRIMARY);
        spotify_AudioQualityComBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ultra  (320 kbps)", "High   (256 kbps)", "Medium (192 kbps)", "Low    (128 kbps)" }));
        spotify_AudioQualityComBox.setAlignmentX(0.0F);
        spotify_AudioQualityComBox.setBorder(new javax.swing.border.LineBorder(HOVER, 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        spotify_OptionsPnl.add(spotify_AudioQualityComBox, gridBagConstraints);
        spotify_OptionsPnl.add(filler13, new java.awt.GridBagConstraints());

        SpotifyInputCard.add(spotify_OptionsPnl);

        SpotifyPnl.add(SpotifyInputCard, java.awt.BorderLayout.NORTH);

        SpotifyControlCard.setBackground(LIGHT_BG);
        SpotifyControlCard.setLayout(new javax.swing.BoxLayout(SpotifyControlCard, javax.swing.BoxLayout.Y_AXIS));

        spotify_DownloadBtn.setBackground(SPOTIFY);
        spotify_DownloadBtn.setFont(new java.awt.Font("JetBrains Mono", 1, 15)); // NOI18N
        spotify_DownloadBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/latteTheme/download.png"))); // NOI18N
        spotify_DownloadBtn.setText("DOWNLOAD");
        spotify_DownloadBtn.setToolTipText("Download");
        spotify_DownloadBtn.setAlignmentX(0.5F);
        spotify_DownloadBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 25, 12, 25));
        spotify_DownloadBtn.setBorderPainted(false);
        spotify_DownloadBtn.setFocusPainted(false);
        spotify_DownloadBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        spotify_DownloadBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        spotify_DownloadBtn.setMinimumSize(new java.awt.Dimension(180, 50));
        spotify_DownloadBtn.setOpaque(true);
        spotify_DownloadBtn.setPreferredSize(new java.awt.Dimension(180, 50));
        SpotifyControlCard.add(spotify_DownloadBtn);
        SpotifyControlCard.add(filler14);

        spotify_CancelDownloadBtn.setFont(new java.awt.Font("JetBrains Mono", 1, 15)); // NOI18N
        spotify_CancelDownloadBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/latteTheme/downloadCancel.png"))); // NOI18N
        spotify_CancelDownloadBtn.setText("CANCEL  ");
        spotify_CancelDownloadBtn.setToolTipText("Cancel Download");
        spotify_CancelDownloadBtn.setAlignmentX(0.5F);
        spotify_CancelDownloadBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 25, 12, 25));
        spotify_CancelDownloadBtn.setBorderPainted(false);
        spotify_CancelDownloadBtn.setFocusPainted(false);
        spotify_CancelDownloadBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        spotify_CancelDownloadBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        spotify_CancelDownloadBtn.setMinimumSize(new java.awt.Dimension(180, 50));
        spotify_CancelDownloadBtn.setOpaque(true);
        spotify_CancelDownloadBtn.setPreferredSize(new java.awt.Dimension(180, 50));
        SpotifyControlCard.add(spotify_CancelDownloadBtn);
        SpotifyControlCard.add(filler15);

        spotify_ProgressBar.setBackground(CARD_BG);
        spotify_ProgressBar.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        spotify_ProgressBar.setForeground(SPOTIFY);
        spotify_ProgressBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        spotify_ProgressBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));
        spotify_ProgressBar.setString("Ready");
        spotify_ProgressBar.setStringPainted(true);
        SpotifyControlCard.add(spotify_ProgressBar);
        SpotifyControlCard.add(filler16);

        spotify_StatusLbl.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        spotify_StatusLbl.setForeground(TEXT_SECONDARY);
        spotify_StatusLbl.setText("Ready to download");
        spotify_StatusLbl.setAlignmentX(0.5F);
        SpotifyControlCard.add(spotify_StatusLbl);

        SpotifyPnl.add(SpotifyControlCard, java.awt.BorderLayout.CENTER);

        TabbedPane.addTab("Spotify", new javax.swing.ImageIcon(getClass().getResource("/spotify.png")), SpotifyPnl, "Spotify"); // NOI18N

        SettingsPnl.setBackground(LIGHT_BG);
        SettingsPnl.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 20, 0));
        SettingsPnl.setLayout(new java.awt.BorderLayout(0, 20));

        SettingsScrollPane.setBorder(null);
        SettingsScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        SettingsScrollPane.getViewport().setBackground(LIGHT_BG);

        SettingsMainPnl.setBackground(CARD_BG);
        SettingsMainPnl.setLayout(new javax.swing.BoxLayout(SettingsMainPnl, javax.swing.BoxLayout.Y_AXIS));

        appearanceCard.setBackground(CARD_BG);
        appearanceCard.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(HOVER, 1, true), javax.swing.BorderFactory.createEmptyBorder(20, 25, 20, 25)));
        appearanceCard.setLayout(new java.awt.BorderLayout());

        themeLbl.setFont(new java.awt.Font("JetBrains Mono", 1, 16)); // NOI18N
        themeLbl.setForeground(TEXT_PRIMARY);
        themeLbl.setText("THEME");
        themeLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 15, 0));
        appearanceCard.add(themeLbl, java.awt.BorderLayout.NORTH);

        appearanceContent.setBackground(CARD_BG);
        appearanceContent.setLayout(new javax.swing.BoxLayout(appearanceContent, javax.swing.BoxLayout.Y_AXIS));

        themePnl.setBackground(CARD_BG);
        themePnl.setAlignmentX(0.0F);
        themePnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 0));

        themeGroup.add(lightThemeBtn);
        lightThemeBtn.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        lightThemeBtn.setForeground(TEXT_SECONDARY);
        lightThemeBtn.setText("Light");
        lightThemeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lightThemeBtnActionPerformed(evt);
            }
        });
        themePnl.add(lightThemeBtn);

        themeGroup.add(darkThemeBtn);
        darkThemeBtn.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        darkThemeBtn.setForeground(TEXT_SECONDARY);
        darkThemeBtn.setText("Dark");
        darkThemeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                darkThemeBtnActionPerformed(evt);
            }
        });
        themePnl.add(darkThemeBtn);

        themeGroup.add(latteThemeBtn);
        latteThemeBtn.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        latteThemeBtn.setForeground(TEXT_SECONDARY);
        latteThemeBtn.setSelected(true);
        latteThemeBtn.setText("Latte");
        latteThemeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                latteThemeBtnActionPerformed(evt);
            }
        });
        themePnl.add(latteThemeBtn);

        themeGroup.add(espressoThemeBtn);
        espressoThemeBtn.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        espressoThemeBtn.setForeground(TEXT_SECONDARY);
        espressoThemeBtn.setText("Espresso");
        espressoThemeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                espressoThemeBtnActionPerformed(evt);
            }
        });
        themePnl.add(espressoThemeBtn);

        appearanceContent.add(themePnl);

        appearanceCard.add(appearanceContent, java.awt.BorderLayout.CENTER);

        SettingsMainPnl.add(appearanceCard);
        SettingsMainPnl.add(filler18);

        downloadSettingsCard.setBackground(CARD_BG);
        downloadSettingsCard.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(HOVER, 1, true), javax.swing.BorderFactory.createEmptyBorder(20, 25, 20, 25)));
        downloadSettingsCard.setLayout(new java.awt.BorderLayout());

        downloadLbl.setFont(new java.awt.Font("JetBrains Mono", 1, 16)); // NOI18N
        downloadLbl.setForeground(TEXT_PRIMARY);
        downloadLbl.setText("DOWNLOAD SETTINGS");
        downloadLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 15, 0));
        downloadSettingsCard.add(downloadLbl, java.awt.BorderLayout.NORTH);

        downloadContent.setBackground(CARD_BG);
        downloadContent.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        downloadContent.setLayout(new javax.swing.BoxLayout(downloadContent, javax.swing.BoxLayout.Y_AXIS));

        embedThumbnails.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        embedThumbnails.setForeground(TEXT_SECONDARY);
        embedThumbnails.setText("Embed thumbnails in audio files");
        downloadContent.add(embedThumbnails);
        downloadContent.add(filler17);

        embedMetadata.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        embedMetadata.setForeground(TEXT_SECONDARY);
        embedMetadata.setText("Embed metadata (title, artist, album)");
        embedMetadata.setFocusPainted(false);
        downloadContent.add(embedMetadata);
        downloadContent.add(filler23);

        deleteSourceFiles.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        deleteSourceFiles.setForeground(TEXT_SECONDARY);
        deleteSourceFiles.setText("Delete Source Files");
        downloadContent.add(deleteSourceFiles);

        downloadSettingsCard.add(downloadContent, java.awt.BorderLayout.CENTER);

        SettingsMainPnl.add(downloadSettingsCard);
        SettingsMainPnl.add(filler21);

        statisticsCard.setBackground(CARD_BG);
        statisticsCard.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(HOVER, 1, true), javax.swing.BorderFactory.createEmptyBorder(20, 25, 20, 25)));
        statisticsCard.setLayout(new java.awt.BorderLayout());

        statisticsLbl.setFont(new java.awt.Font("JetBrains Mono", 1, 16)); // NOI18N
        statisticsLbl.setForeground(TEXT_PRIMARY);
        statisticsLbl.setText("STATISTICS");
        statisticsLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 15, 0));
        statisticsCard.add(statisticsLbl, java.awt.BorderLayout.NORTH);

        statsContent.setBackground(CARD_BG);
        statsContent.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        statsContent.setLayout(new javax.swing.BoxLayout(statsContent, javax.swing.BoxLayout.Y_AXIS));

        totalLbl.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        totalLbl.setForeground(TEXT_SECONDARY);
        totalLbl.setText("Total Downloads:   ");
        statsContent.add(totalLbl);
        statsContent.add(filler19);

        youtube_TotalLbl.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        youtube_TotalLbl.setForeground(TEXT_SECONDARY);
        youtube_TotalLbl.setText("Youtube Downloads: ");
        statsContent.add(youtube_TotalLbl);
        statsContent.add(filler20);

        spotify_TotalLbl.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        spotify_TotalLbl.setForeground(TEXT_SECONDARY);
        spotify_TotalLbl.setText("Spotify Downloads: ");
        statsContent.add(spotify_TotalLbl);

        statisticsCard.add(statsContent, java.awt.BorderLayout.CENTER);

        SettingsMainPnl.add(statisticsCard);
        SettingsMainPnl.add(filler22);

        actionsCard.setBackground(CARD_BG);
        actionsCard.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(HOVER, 1, true), javax.swing.BorderFactory.createEmptyBorder(20, 25, 20, 25)));
        actionsCard.setLayout(new java.awt.BorderLayout());

        actionsLbl.setFont(new java.awt.Font("JetBrains Mono", 1, 16)); // NOI18N
        actionsLbl.setForeground(TEXT_PRIMARY);
        actionsLbl.setText("SETTINGS");
        actionsLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 15, 0));
        actionsCard.add(actionsLbl, java.awt.BorderLayout.NORTH);

        actionsContent.setBackground(CARD_BG);
        actionsContent.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        actionsContent.setLayout(new javax.swing.BoxLayout(actionsContent, javax.swing.BoxLayout.Y_AXIS));

        actionButtonsPnl.setBackground(CARD_BG);
        actionButtonsPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));

        saveSettingsBtn.setBackground(CARD_BG);
        saveSettingsBtn.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        saveSettingsBtn.setForeground(TEXT_PRIMARY);
        saveSettingsBtn.setText("SAVE");
        actionButtonsPnl.add(saveSettingsBtn);

        resetSettingsBtn.setBackground(CARD_BG);
        resetSettingsBtn.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        resetSettingsBtn.setForeground(TEXT_PRIMARY);
        resetSettingsBtn.setText("RESTORE TO DEFAULT");
        actionButtonsPnl.add(resetSettingsBtn);

        openSettingsFolderBtn.setBackground(CARD_BG);
        openSettingsFolderBtn.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        openSettingsFolderBtn.setForeground(TEXT_PRIMARY);
        openSettingsFolderBtn.setText("OPEN FOLDER");
        actionButtonsPnl.add(openSettingsFolderBtn);

        actionsContent.add(actionButtonsPnl);

        actionsCard.add(actionsContent, java.awt.BorderLayout.CENTER);

        SettingsMainPnl.add(actionsCard);

        SettingsScrollPane.setViewportView(SettingsMainPnl);

        SettingsPnl.add(SettingsScrollPane, java.awt.BorderLayout.CENTER);

        TabbedPane.addTab("Settings", new javax.swing.ImageIcon(getClass().getResource("/settings.png")), SettingsPnl); // NOI18N

        MainPanel.add(TabbedPane, java.awt.BorderLayout.CENTER);

        LogPnl.setBackground(CARD_BG);
        LogPnl.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 30, 30, 30));
        LogPnl.setPreferredSize(new java.awt.Dimension(807, 250));
        LogPnl.setLayout(new java.awt.BorderLayout());

        logLbl.setFont(new java.awt.Font("JetBrains Mono", 1, 15)); // NOI18N
        logLbl.setForeground(TEXT_PRIMARY);
        logLbl.setText("Download Log");
        logLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        LogPnl.add(logLbl, java.awt.BorderLayout.NORTH);

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(HOVER, 1, true));

        logArea.setEditable(false);
        logArea.setBackground(LIGHT_BG);
        logArea.setColumns(20);
        logArea.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        logArea.setForeground(TEXT_PRIMARY);
        logArea.setRows(5);
        logArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        logArea.setCaretColor(TEXT_PRIMARY);
        jScrollPane1.setViewportView(logArea);

        LogPnl.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        MainPanel.add(LogPnl, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(MainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void yt_BrowseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yt_BrowseBtnActionPerformed
        // TODO add your handling code here:
        browseOutputDirectory(yt_urlFld);
    }//GEN-LAST:event_yt_BrowseBtnActionPerformed

    private void spotify_BrowseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spotify_BrowseBtnActionPerformed
        // TODO add your handling code here:
        browseOutputDirectory(spotify_urlFld);
    }//GEN-LAST:event_spotify_BrowseBtnActionPerformed

    private void lightThemeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lightThemeBtnActionPerformed
        // TODO add your handling code here:
        resetTheme();
        applyLightTheme();
    }//GEN-LAST:event_lightThemeBtnActionPerformed

    private void darkThemeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_darkThemeBtnActionPerformed
        // TODO add your handling code here:
        resetTheme();
        applyDarkTheme();
    }//GEN-LAST:event_darkThemeBtnActionPerformed

    private void latteThemeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_latteThemeBtnActionPerformed
        // TODO add your handling code here:
        resetTheme();
        applyLatteTheme();
    }//GEN-LAST:event_latteThemeBtnActionPerformed

    private void espressoThemeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_espressoThemeBtnActionPerformed
        // TODO add your handling code here:
        resetTheme();
        applyEspressoTheme();
    }//GEN-LAST:event_espressoThemeBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {   
        
        FlatLightLaf.setup();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel HeaderPnl;
    private javax.swing.JPanel LogPnl;
    private javax.swing.JPanel MainPanel;
    private javax.swing.JPanel SettingsMainPnl;
    private javax.swing.JPanel SettingsPnl;
    private javax.swing.JScrollPane SettingsScrollPane;
    private javax.swing.JPanel SpotifyControlCard;
    private javax.swing.JPanel SpotifyInputCard;
    private javax.swing.JPanel SpotifyPnl;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JPanel TitlePanel;
    private javax.swing.JPanel YouTubeControlCard;
    private javax.swing.JPanel YouTubeInputCard;
    private javax.swing.JPanel YouTubePnl;
    private javax.swing.JPanel actionButtonsPnl;
    private javax.swing.JPanel actionsCard;
    private javax.swing.JPanel actionsContent;
    private javax.swing.JLabel actionsLbl;
    private javax.swing.JPanel appearanceCard;
    private javax.swing.JPanel appearanceContent;
    private javax.swing.JRadioButton darkThemeBtn;
    private javax.swing.JCheckBox deleteSourceFiles;
    private javax.swing.JPanel downloadContent;
    private javax.swing.JLabel downloadLbl;
    private javax.swing.JPanel downloadSettingsCard;
    private javax.swing.JCheckBox embedMetadata;
    private javax.swing.JCheckBox embedThumbnails;
    private javax.swing.JRadioButton espressoThemeBtn;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler14;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler16;
    private javax.swing.Box.Filler filler17;
    private javax.swing.Box.Filler filler18;
    private javax.swing.Box.Filler filler19;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler20;
    private javax.swing.Box.Filler filler21;
    private javax.swing.Box.Filler filler22;
    private javax.swing.Box.Filler filler23;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton latteThemeBtn;
    private javax.swing.JRadioButton lightThemeBtn;
    private javax.swing.JTextArea logArea;
    private javax.swing.JLabel logLbl;
    private javax.swing.JButton openSettingsFolderBtn;
    private javax.swing.JButton resetSettingsBtn;
    private javax.swing.JButton saveSettingsBtn;
    private javax.swing.JComboBox<String> spotify_AudioFormatComBox;
    private javax.swing.JComboBox<String> spotify_AudioQualityComBox;
    private javax.swing.JButton spotify_BrowseBtn;
    private javax.swing.JButton spotify_CancelDownloadBtn;
    private javax.swing.JPanel spotify_DirPnl;
    private javax.swing.JButton spotify_DownloadBtn;
    private javax.swing.JPanel spotify_OptionsPnl;
    private javax.swing.JTextField spotify_OutputDirFld;
    private javax.swing.JLabel spotify_OutputDirLbl;
    private javax.swing.JCheckBox spotify_PlaylistCheckBox;
    private javax.swing.JProgressBar spotify_ProgressBar;
    private javax.swing.JLabel spotify_StatusLbl;
    private javax.swing.JLabel spotify_TotalLbl;
    private javax.swing.JTextField spotify_urlFld;
    private javax.swing.JLabel spotify_urlLbl;
    private javax.swing.JPanel statisticsCard;
    private javax.swing.JLabel statisticsLbl;
    private javax.swing.JPanel statsContent;
    private javax.swing.JLabel subtitleLbl;
    private javax.swing.ButtonGroup themeGroup;
    private javax.swing.JLabel themeLbl;
    private javax.swing.JPanel themePnl;
    private javax.swing.JLabel titleLbl;
    private javax.swing.JLabel totalLbl;
    private javax.swing.JLabel youtube_TotalLbl;
    private javax.swing.JComboBox<String> yt_AudioFormatComBox;
    private javax.swing.JComboBox<String> yt_AudioQualityComBox;
    private javax.swing.JButton yt_BrowseBtn;
    private javax.swing.JButton yt_CancelDownloadBtn;
    private javax.swing.JPanel yt_DirPnl;
    private javax.swing.JButton yt_DownloadBtn;
    private javax.swing.JPanel yt_OptionsPnl;
    private javax.swing.JTextField yt_OutputDirFld;
    private javax.swing.JLabel yt_OutputDirLbl;
    private javax.swing.JCheckBox yt_PlaylistCheckBox;
    private javax.swing.JProgressBar yt_ProgressBar;
    private javax.swing.JLabel yt_StatusLbl;
    private javax.swing.JTextField yt_urlFld;
    private javax.swing.JLabel yt_urlLbl;
    // End of variables declaration//GEN-END:variables
}
