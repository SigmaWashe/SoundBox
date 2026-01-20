package UI;

import com.formdev.flatlaf.themes.*;
import config.Settings;
import core.*;
import theme.*;

import com.formdev.flatlaf.*;
import org.jdesktop.swingx.prompt.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;

/**
 *
 * @author SigmaWashe
 */
public class MainMenu extends javax.swing.JFrame {

    private Settings settings = new Settings();
    private Dark darkTheme;
    private Light lightTheme;

    private final AtomicBoolean isCancelled = new AtomicBoolean(false);
    private ExecutorService executor;
    private String downloadType;

    /** 
     * Creates new form MainMenu
     */
    public MainMenu() {
        super("SOUND BOX");
        initComponents();
        setLocationRelativeTo(null);

        PromptSupport.setPrompt("Enter YouTube URL", yt_urlFld);
        PromptSupport.setPrompt("Enter Spotify URL", spotify_urlFld);

        yt_OutputDirFld.setText(settings.getYouTubeOutputDir());
        spotify_OutputDirFld.setText(settings.getSpotifyOutputDir());

        applyDefaultTheme();
        checkDownloadTools();
        loadSettings();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                settings.setWindowSize(getWidth(), getHeight());
                saveSettings();
            }
        });
    }

    public void loadSettings(){
        yt_OutputDirFld.setText(settings.getYouTubeOutputDir().trim());
        yt_PlaylistCheckBox.setEnabled(settings.getYouTubePlaylistMode());
        String youtubeAudioFormat = settings.getYouTubeAudioFormat();
        switch (youtubeAudioFormat) {
            case "mp3":
                yt_AudioFormatComBox.setSelectedIndex(0);
                break;

            case "m4a":
                yt_AudioFormatComBox.setSelectedIndex(1);
                break;

            case "wav":
                yt_AudioFormatComBox.setSelectedIndex(2);
                break;
        }

        spotify_OutputDirFld.setText(settings.getSpotifyOutputDir().trim());
        spotify_PlaylistCheckBox.setEnabled(settings.getSpotifyPlaylistMode());
        String spotifyAudioFormat = settings.getSpotifyAudioFormat();
        switch (spotifyAudioFormat) {
            case "mp3":
                spotify_AudioFormatComBox.setSelectedIndex(0);
                break;

            case "m4a":
                spotify_AudioFormatComBox.setSelectedIndex(1);
                break;

            case "wav":
                spotify_AudioFormatComBox.setSelectedIndex(2);
                break;
        }

        if(settings.getTheme().equalsIgnoreCase("light")){
            lightThemeBtn.setSelected(true);
            applyLightTheme();
        } else if(settings.getTheme().equalsIgnoreCase("dark")){
            darkThemeBtn.setSelected(true);
            applyDarkTheme();
        }

        setSize(settings.getWindowWidth(), settings.getWindowHeight());
        TabbedPane.setSelectedIndex(settings.getLastTab());
        updateStatistics();
    }

    public void updateStatistics(){
        totalLbl.setText("Total Downloads:   " + String.valueOf(settings.getTotalDownloads()));
        youtube_TotalLbl.setText("YouTube Downloads: " + String.valueOf(settings.getYouTubeDownloads()));
        spotify_TotalLbl.setText("Spotify Downloads: " + String.valueOf(settings.getSpotifyDownloads()));
    }

    public void saveSettings(){
        settings.setYouTubeOutputDir(yt_OutputDirFld.getText().trim());
        settings.setYouTubePlaylistMode(yt_PlaylistCheckBox.isSelected());
        String youtube_AudioFormat = (String) yt_AudioFormatComBox.getSelectedItem();
        settings.setYouTubeAudioFormat(youtube_AudioFormat);

        settings.setSpotifyOutputDir(spotify_OutputDirFld.getText().trim());
        settings.setSpotifyPlaylistMode(spotify_PlaylistCheckBox.isSelected());
        String spotify_AudioFormat = (String) spotify_AudioFormatComBox.getSelectedItem();
        settings.setSpotifyAudioFormat(spotify_AudioFormat);

        String selectedTheme = settings.getTheme();
        if (darkThemeBtn.isSelected()) {
            settings.setTheme("dark");
        } else if (lightThemeBtn.isSelected()) {
            settings.setTheme("light");
        }

        settings.setWindowSize(getWidth(), getHeight());
        settings.setLastTab(TabbedPane.getSelectedIndex());

        settings.saveSettings();
        log("✅ Settings saved successfully.");
    }

    public String getCurrentDownloadType() {
        return this.downloadType;
    }

    private void applyDefaultTheme(){
        if(settings.getTheme().equalsIgnoreCase("light")){
            lightThemeBtn.setSelected(true);
            applyLightTheme();
        } else if(settings.getTheme().equalsIgnoreCase("dark")){
            darkThemeBtn.setSelected(true);
            applyDarkTheme();
        }
    }

    private void applyLightTheme(){
        try {
            FlatMacLightLaf.setup();
            lightTheme = new Light();
            lightTheme.mainPanel(MainPanel);
            lightTheme.headerPanel(HeaderPnl, TitlePanel, titleLbl, subtitleLbl);
            lightTheme.tabbedPane(TabbedPane);
            lightTheme.youtubeTab(YouTubePnl);
            lightTheme.youtubeInputCard(YouTubeInputCard, yt_urlLbl, yt_urlFld, yt_OutputDirLbl, yt_DirPnl, yt_OutputDirFld,
                    yt_BrowseBtn, yt_OptionsPnl, yt_PlaylistCheckBox, yt_AudioFormatComBox);
            lightTheme.youtubeControlCard(YouTubeControlCard, yt_DownloadBtn, yt_CancelDownloadBtn, yt_ProgressBar, yt_StatusLbl);
            lightTheme.spotifyTab(SpotifyPnl);
            lightTheme.spotifyInputCard(SpotifyInputCard, spotify_urlLbl, spotify_urlFld, spotify_OutputDirLbl, spotify_DirPnl,
                    spotify_OutputDirFld, spotify_BrowseBtn, spotify_OptionsPnl, spotify_PlaylistCheckBox, spotify_AudioFormatComBox);
            lightTheme.spotifyControlCard(SpotifyControlCard, spotify_DownloadBtn, spotify_CancelDownloadBtn, spotify_ProgressBar, spotify_StatusLbl);
            lightTheme.settingsTab(SettingsPnl);
            lightTheme.settingsScrollPane(SettingsScrollPane);
            lightTheme.settingsMainPanel(SettingsMainPnl);
            lightTheme.appearanceCard(appearanceCard, themeLbl, appearanceContent, themePnl, lightThemeBtn, darkThemeBtn);
            //lightTheme.downloadSettCard(downloadSettingsCard, downloadLbl, downloadContent, embedThumbnails, embedMetadata, deleteSourceFiles);
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
            FlatMacDarkLaf.setup();
            darkTheme = new Dark();
            darkTheme.mainPanel(MainPanel);
            darkTheme.headerPanel(HeaderPnl, TitlePanel, titleLbl, subtitleLbl);
            darkTheme.tabbedPane(TabbedPane);
            darkTheme.youtubeTab(YouTubePnl);
            darkTheme.youtubeInputCard(YouTubeInputCard, yt_urlLbl, yt_urlFld, yt_OutputDirLbl, yt_DirPnl, yt_OutputDirFld,
                    yt_BrowseBtn, yt_OptionsPnl, yt_PlaylistCheckBox, jLabel1, yt_AudioFormatComBox);
            darkTheme.youtubeControlCard(YouTubeControlCard, yt_DownloadBtn, yt_CancelDownloadBtn, yt_ProgressBar, yt_StatusLbl);
            darkTheme.spotifyTab(SpotifyPnl);
            darkTheme.spotifyInputCard(SpotifyInputCard, spotify_urlLbl, spotify_urlFld, spotify_OutputDirLbl, spotify_DirPnl,
                    spotify_OutputDirFld, spotify_BrowseBtn, spotify_OptionsPnl, spotify_PlaylistCheckBox, jLabel2, spotify_AudioFormatComBox);
            darkTheme.spotifyControlCard(SpotifyControlCard, spotify_DownloadBtn, spotify_CancelDownloadBtn, spotify_ProgressBar, spotify_StatusLbl);
            darkTheme.settingsTab(SettingsPnl);
            darkTheme.settingsScrollPane(SettingsScrollPane);
            darkTheme.settingsMainPanel(SettingsMainPnl);
            darkTheme.appearanceCard(appearanceCard, themeLbl, appearanceContent, themePnl, lightThemeBtn, darkThemeBtn);
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
    
    private void browseOutputDirectory(JTextField field) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(field.getText()));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            field.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void detectPlaylistUrl(JTextField urlField, JCheckBox playlistCheckBox) {
        String url = urlField.getText().trim();
        if (YouTubeDownloader.isPlaylistUrl(url)) {
            playlistCheckBox.setSelected(true);
            log("ℹ️ Playlist URL detected - playlist mode enabled automatically");
        }
    }

    private void prepareDownloadUI(JButton downloadBtn, JButton cancelBtn, JProgressBar progressBar, JLabel statusLabel) {
        logArea.setText("");
        downloadBtn.setEnabled(false);
        cancelBtn.setEnabled(true);
        isCancelled.set(false);
        progressBar.setValue(0);
        progressBar.setString("Starting...");
        statusLabel.setText("Initializing...");
    }

    // ------------------- Core And Config -------------------
    private void startYoutubeDownload(boolean isPlaylist) {
        String youtubeUrl = yt_urlFld.getText().trim();
        String outputDir = yt_OutputDirFld.getText().trim();
        String audioFormat = (String) yt_AudioFormatComBox.getSelectedItem();
        this.downloadType = "youtube";

        if (!validateAndPrepareDownload(youtubeUrl, outputDir)) {
            return;
        }

        if (outputDir.isEmpty()){
            outputDir = System.getProperty("user.home") + File.separator + "Youtube Downloads";
            try {
                Files.createDirectories(Path.of(outputDir));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try{
            saveSettings();
            isCancelled.set(false);
            final int totalPhases = isPlaylist ? 2 : 1;

            prepareDownloadUI(yt_DownloadBtn, yt_CancelDownloadBtn, yt_ProgressBar, yt_StatusLbl);

            updateStatus("Downloading audio --- Phase 1/" + totalPhases + " ---", yt_StatusLbl);
            log(isPlaylist ? "📋 Playlist mode enabled" : "🎵 Single video mode");
            log("Starting download from: " + youtubeUrl);
            log("Output directory: " + outputDir);
            log("Audio format: " + audioFormat);;

            yt_ProgressBar.setIndeterminate(false);
            yt_ProgressBar.setValue(0);
            yt_ProgressBar.setString("0% (Download) | 0% (Overall)");

            yt_DownloadBtn.setEnabled(false);
            yt_CancelDownloadBtn.setEnabled(true);

            String finalOutputDir = outputDir;
            executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> {
                try {
                    Consumer<Integer> downloadProgress = progress -> {
                        if (isCancelled.get()) return;
                        int overallProgress = isPlaylist ? (int) (progress * 0.5) : progress;
                        SwingUtilities.invokeLater(() -> {
                            yt_ProgressBar.setValue(overallProgress);
                            yt_ProgressBar.setString(progress + "% (Download) | " + overallProgress + "% (Overall)");
                        });
                    };

                    YouTubeDownloader.downloadPlaylist(youtubeUrl, finalOutputDir, message -> log(message),
                            downloadProgress, isPlaylist, audioFormat);

                    if (isCancelled.get()){
                        updateStatus("Cancelled", yt_StatusLbl);
                        finishProcess(false, yt_DownloadBtn, yt_CancelDownloadBtn, yt_ProgressBar);
                        return;
                    }
                    YouTubeDownloader.cleanupThumbnails(finalOutputDir, message -> log(message));

                    if (isPlaylist) {
                        processYoutubePlaylist(youtubeUrl, finalOutputDir);
                    }

                    settings.incrementDownloadStats(true);
                    updateStatus("Complete!", yt_StatusLbl);
                    updateStatistics();
                    finishProcess(true, yt_DownloadBtn, yt_CancelDownloadBtn, yt_ProgressBar);

                } catch (Exception e) {
                    log("❌ Error: " + e.getMessage());
                    e.printStackTrace();
                    updateStatus("Error occurred", yt_StatusLbl);
                    finishProcess(false, yt_DownloadBtn, yt_CancelDownloadBtn, yt_ProgressBar);
                }
            });

        } catch (Exception e) {
            log("❌ Error: " + e.getMessage());
            e.printStackTrace();
            updateStatus("Error occurred", yt_StatusLbl);
            finishProcess(false, yt_DownloadBtn, yt_CancelDownloadBtn, yt_ProgressBar);
        }
    }

    private void processYoutubePlaylist(String url, String outputDir) throws Exception {
        updateStatus("Organizing into playlist folder (Phase 2/2)...", yt_StatusLbl);
        SwingUtilities.invokeLater(() -> { yt_ProgressBar.setValue(90);
                                           yt_ProgressBar.setString("90% (Organizing) | 90% (Overall)");});

        String playlistTitle = YouTubeDownloader.getPlaylistTitle(url);
        File outputFolder = new File(outputDir);
        File playlistFolder = new File(outputDir, playlistTitle);

        if (!playlistFolder.exists() && playlistFolder.mkdirs()) {
            log("📁 Created playlist folder: " + playlistTitle);
        }

        if (playlistFolder.exists()) {
            File[] audioFiles = outputFolder.listFiles((dir, name) ->
                    name.endsWith(".mp3") || name.endsWith(".m4a") || name.endsWith(".wav"));

            if (audioFiles != null && audioFiles.length > 0) {
                int movedCount = 0;
                for (File audioFile : audioFiles) {
                    try {
                        if (audioFile.exists() && audioFile.isFile()) {
                            Path source = audioFile.toPath();
                            Path destination = playlistFolder.toPath().resolve(audioFile.getName());
                            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
                            movedCount++;
                        }
                    } catch (Exception e) {
                        log("⚠️ Error moving file " + audioFile.getName() + ": " + e.getMessage());
                    }
                }
                if (movedCount > 0) {
                    log("✅ Moved " + movedCount + " file(s) into folder: " + playlistTitle);
                }
                YouTubeDownloader.cleanupThumbnails(playlistFolder.getAbsolutePath(), message -> log(message));
            }
        }

        SwingUtilities.invokeLater(() -> {
            yt_ProgressBar.setValue(100);
            yt_ProgressBar.setString("100% (Complete!)");
        });
        log("✅ YouTube download completed successfully!");
    }

    private void startSpotifyDownload(boolean isPlaylist) {
        String spotifyUrl = spotify_urlFld.getText().trim();
        String outputDir = spotify_OutputDirFld.getText().trim();
        String audioFormat = (String) spotify_AudioFormatComBox.getSelectedItem();
        this.downloadType = "spotify";

        if (!validateAndPrepareDownload(spotifyUrl, outputDir)) {
            return;
        }
        if (outputDir.isEmpty()){
            outputDir = System.getProperty("user.home") + File.separator + "Spotify Downloads";
            try {
                Files.createDirectories(Path.of(outputDir));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try{
            saveSettings();
            isCancelled.set(false);
            final int totalPhases = isPlaylist ? 2 : 1;

            prepareDownloadUI(spotify_DownloadBtn, spotify_CancelDownloadBtn, spotify_ProgressBar, spotify_StatusLbl);

            updateStatus("Downloading audio --- Phase 1/" + totalPhases + " ---", spotify_StatusLbl);
            log(isPlaylist ? "📋 Spotify playlist/album mode" : "🎵 Single track mode");
            log("Starting download from: " + spotifyUrl);
            log("Output directory: " + outputDir);
            log("Audio format: " + audioFormat);

            spotify_ProgressBar.setIndeterminate(false);
            spotify_ProgressBar.setValue(0);
            spotify_ProgressBar.setString("0% (Download) | 0% (Overall)");

            spotify_DownloadBtn.setEnabled(false);
            spotify_CancelDownloadBtn.setEnabled(true);

            String finalOutputDir = outputDir;
            executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> {
                try {
                    Consumer<Integer> downloadProgress = progress -> {
                        if (isCancelled.get()) return;
                        int overallProgress = isPlaylist ? (int) (progress * 0.5) : progress;
                        SwingUtilities.invokeLater(() -> {
                            spotify_ProgressBar.setValue(overallProgress);
                            spotify_ProgressBar.setString(progress + "% (Download) | " + overallProgress + "% (Overall)");
                        });
                    };

                    SpotifyDownloader.downloadPlaylist(spotifyUrl, finalOutputDir, message -> log(message),
                            downloadProgress, isPlaylist, audioFormat);

                    if (isCancelled.get()){
                        updateStatus("Cancelled", spotify_StatusLbl);
                        finishProcess(false, spotify_DownloadBtn, spotify_CancelDownloadBtn, spotify_ProgressBar);
                        return;
                    }
                    SpotifyDownloader.cleanupThumbnails(finalOutputDir, message -> log(message));

                    if (isPlaylist) {
                        processSpotifyPlaylist(spotifyUrl, finalOutputDir);
                    }

                    settings.incrementDownloadStats(false);
                    updateStatus("Complete!", spotify_StatusLbl);
                    updateStatistics();
                    finishProcess(true, spotify_DownloadBtn, spotify_CancelDownloadBtn, spotify_ProgressBar);

                } catch (Exception e) {
                    log("❌ Error: " + e.getMessage());
                    e.printStackTrace();
                    updateStatus("Error occurred", spotify_StatusLbl);
                    finishProcess(false, spotify_DownloadBtn, spotify_CancelDownloadBtn, spotify_ProgressBar);
                }
            });

        } catch (Exception e) {
            log("❌ Error: " + e.getMessage());
            e.printStackTrace();
            updateStatus("Error occurred", spotify_StatusLbl);
            finishProcess(false, spotify_DownloadBtn, spotify_CancelDownloadBtn, spotify_ProgressBar);
        }
    }

    private void processSpotifyPlaylist(String url, String outputDir) throws Exception {
        updateStatus("Organizing into playlist folder (Phase 2/2)...", spotify_StatusLbl);
        SwingUtilities.invokeLater(() -> {
            spotify_ProgressBar.setValue(90);
            spotify_ProgressBar.setString("90% (Organizing) | 90% (Overall)");
        });

        String playlistTitle = SpotifyDownloader.getSpotifyPlaylistTitle(url);
        File outputFolder = new File(outputDir);
        File playlistFolder = new File(outputDir, playlistTitle);

        if (!playlistFolder.exists() && playlistFolder.mkdirs()) {
            log("📁 Created playlist folder: " + playlistTitle);
        }

        if (playlistFolder.exists()) {
            File[] audioFiles = outputFolder.listFiles((dir, name) ->
                    name.endsWith(".mp3") || name.endsWith(".m4a") || name.endsWith(".wav"));

            if (audioFiles != null && audioFiles.length > 0) {
                int movedCount = 0;
                for (File audioFile : audioFiles) {
                    try {
                        if (audioFile.exists() && audioFile.isFile()) {
                            Path source = audioFile.toPath();
                            Path destination = playlistFolder.toPath().resolve(audioFile.getName());
                            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
                            movedCount++;
                        }
                    } catch (Exception e) {
                        log("⚠️ Error moving file " + audioFile.getName() + ": " + e.getMessage());
                    }
                }
                if (movedCount > 0) {
                    log("✅ Moved " + movedCount + " file(s) into folder: " + playlistTitle);
                }
                SpotifyDownloader.cleanupThumbnails(playlistFolder.getAbsolutePath(), message -> log(message));
            }
        }

        SwingUtilities.invokeLater(() -> {
            spotify_ProgressBar.setValue(100);
            spotify_ProgressBar.setString("100% (Complete!)");
        });
        log("✅ Spotify download completed successfully!");
    }

    public void updateStatus(String status, JLabel statusLbl){
        SwingUtilities.invokeLater(() -> statusLbl.setText(status));
    }

    private void finishProcess(boolean success, JButton downloadButton, JButton cancelButton, JProgressBar progressBar) {
        SwingUtilities.invokeLater(() -> {
            downloadButton.setEnabled(true);
            cancelButton.setEnabled(false);
            progressBar.setValue(success ? 100 : 0);

            this.downloadType = null;
            if (success) {
                progressBar.setString("Complete!");
                updateStatistics();
                JOptionPane.showMessageDialog(this, "Download and processing finished!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                progressBar.setString("Failed or Cancelled");
            }
        });
    }

    private boolean validateAndPrepareDownload(String url, String outputDir) {
        if (url.isEmpty() || !url.startsWith("http")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid URL starting with http:// or https://", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        File outputFolder = new File(outputDir);
        if (!outputFolder.exists() && !outputFolder.mkdirs()) {
            JOptionPane.showMessageDialog(this, "Cannot create output directory: " + outputDir,
                    "Directory Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!outputFolder.canWrite()) {
            JOptionPane.showMessageDialog(this,
                    "Output directory is not writable: " + outputDir,
                    "Permission Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void cancelDownload() {
        isCancelled.set(true);
        log("⚠️ Cancellation requested...");

        if(getCurrentDownloadType() != null){
            switch (getCurrentDownloadType()) {
                case "youtube":
                    yt_CancelDownloadBtn.setEnabled(false);
                    break;

                case "spotify":
                    spotify_CancelDownloadBtn.setEnabled(false);
                    break;
            }
        }

    }

    private void checkDownloadTools() {
        if (!YouTubeDownloader.isYtDlpAvailable() || !SpotifyDownloader.isSpotdlAvailable()) {
            JOptionPane.showMessageDialog(this,
                    "⚠️ yt-dlp is not installed or not in PATH.\n" +
                            "Install from: https://github.com/yt-dlp/yt-dlp\n\n" +
                            "Installation:\n" +
                            "- Windows: winget install yt-dlp\n" +
                            "- macOS: brew install yt-dlp\n" +
                            "- Linux: sudo apt install yt-dlp\n\n" +
                            "Note: yt-dlp is required for both YouTube and Spotify downloads.",
                       "yt-dlp Not Found", JOptionPane.ERROR_MESSAGE);
            yt_DownloadBtn.setEnabled(false);
            spotify_DownloadBtn.setEnabled(false);
        } else {
            String version = YouTubeDownloader.getYtDlpVersion();
            log("yt-dlp found (version: " + version + ")");
            log("Spotify downloads enabled via Spotify API + YouTube Music");
            log("To set up ffmpeg follow this tutorial: https://www.youtube.com/watch?v=EyIIvctDhYc&t=65s");
        }
    }

    // --- Log Panel ---
    private void log(String message){
        SwingUtilities.invokeLater(() -> {
            try {
                String formattedMsg = message;

                // Check if the message contains a URL
                if (formattedMsg.contains("https://") || formattedMsg.contains("http://")) {
                    // Regex to find the URL and wrap it in <a> tags
                    formattedMsg = formattedMsg.replaceAll("(https?://\\S+)",
                            "<a href=\"$1\" style=\"color: #58a6ff; text-decoration: underline;\">$1</a>");
                }

                // Convert current content to HTML and append new line
                javax.swing.text.html.HTMLDocument doc = (javax.swing.text.html.HTMLDocument) logArea.getDocument();
                javax.swing.text.html.HTMLEditorKit kit = (javax.swing.text.html.HTMLEditorKit) logArea.getEditorKit();

                // Use JetBrains Mono font to match your existing style
                String formattedMessage = "<div style='font-family: JetBrains Mono; font-size: 8px;'>" + formattedMsg + "</div>";

                kit.insertHTML(doc, doc.getLength(), formattedMessage, 0, 0, null);
                logArea.setCaretPosition(doc.getLength());
            } catch (Exception e) {
                // Fallback if HTML insertion fails
                System.err.println("Logging error: " + e.getMessage());
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        jLabel1 = new javax.swing.JLabel();
        yt_AudioFormatComBox = new javax.swing.JComboBox<>();
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
        filler17 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        jLabel2 = new javax.swing.JLabel();
        spotify_AudioFormatComBox = new javax.swing.JComboBox<>();
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
        filler18 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
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
        logArea = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MainPanel.setPreferredSize(new java.awt.Dimension(700, 900));
        MainPanel.setLayout(new java.awt.BorderLayout());

        HeaderPnl.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 30, 20, 30));
        HeaderPnl.setMinimumSize(new java.awt.Dimension(0, 0));
        HeaderPnl.setLayout(new java.awt.BorderLayout());

        TitlePanel.setMinimumSize(new java.awt.Dimension(0, 0));
        TitlePanel.setName(""); // NOI18N
        TitlePanel.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        titleLbl.setFont(new java.awt.Font("JetBrains Mono", 1, 26)); // NOI18N
        titleLbl.setText("SOUND BOX");
        TitlePanel.add(titleLbl);

        subtitleLbl.setFont(new java.awt.Font("JetBrains Mono", 0, 13)); // NOI18N
        subtitleLbl.setText("Download high quality audio files from YouTube and Spotify");
        TitlePanel.add(subtitleLbl);

        HeaderPnl.add(TitlePanel, java.awt.BorderLayout.WEST);

        MainPanel.add(HeaderPnl, java.awt.BorderLayout.NORTH);

        TabbedPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 50, 10, 50));
        TabbedPane.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TabbedPane.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N

        YouTubePnl.setLayout(new java.awt.BorderLayout(0, 20));

        YouTubeInputCard.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25)));
        YouTubeInputCard.setLayout(new javax.swing.BoxLayout(YouTubeInputCard, javax.swing.BoxLayout.Y_AXIS));

        yt_urlLbl.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 14)); // NOI18N
        yt_urlLbl.setText("YouTube URL");
        YouTubeInputCard.add(yt_urlLbl);
        YouTubeInputCard.add(filler1);

        yt_urlFld.setColumns(50);
        yt_urlFld.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        yt_urlFld.setAlignmentX(0.0F);
        yt_urlFld.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        yt_urlFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yt_urlFldActionPerformed(evt);
            }
        });
        YouTubeInputCard.add(yt_urlFld);
        YouTubeInputCard.add(filler2);

        yt_OutputDirLbl.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        yt_OutputDirLbl.setText("Output Folder");
        yt_OutputDirLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        YouTubeInputCard.add(yt_OutputDirLbl);
        YouTubeInputCard.add(filler4);

        yt_DirPnl.setAlignmentX(0.0F);
        yt_DirPnl.setMaximumSize(new java.awt.Dimension(2147483647, 45));
        yt_DirPnl.setPreferredSize(new java.awt.Dimension(655, 45));
        yt_DirPnl.setLayout(new java.awt.BorderLayout(10, 0));

        yt_OutputDirFld.setColumns(40);
        yt_OutputDirFld.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        yt_OutputDirFld.setAlignmentX(0.0F);
        yt_OutputDirFld.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), javax.swing.BorderFactory.createEmptyBorder(12, 15, 12, 15)));
        yt_DirPnl.add(yt_OutputDirFld, java.awt.BorderLayout.CENTER);

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

        yt_OptionsPnl.setAlignmentX(0.0F);
        yt_OptionsPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));

        yt_PlaylistCheckBox.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        yt_PlaylistCheckBox.setText("Download entire playlist");
        yt_PlaylistCheckBox.setFocusPainted(false);
        yt_PlaylistCheckBox.setPreferredSize(new java.awt.Dimension(217, 30));
        yt_OptionsPnl.add(yt_PlaylistCheckBox);
        yt_OptionsPnl.add(filler6);

        jLabel1.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        jLabel1.setText("Audio Format: ");
        yt_OptionsPnl.add(jLabel1);

        yt_AudioFormatComBox.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        yt_AudioFormatComBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MP3", "M4A", "WAV" }));
        yt_AudioFormatComBox.setToolTipText("Audio Format");
        yt_AudioFormatComBox.setAlignmentX(0.0F);
        yt_AudioFormatComBox.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        yt_OptionsPnl.add(yt_AudioFormatComBox);

        YouTubeInputCard.add(yt_OptionsPnl);

        YouTubePnl.add(YouTubeInputCard, java.awt.BorderLayout.NORTH);

        YouTubeControlCard.setLayout(new javax.swing.BoxLayout(YouTubeControlCard, javax.swing.BoxLayout.Y_AXIS));

        yt_DownloadBtn.setFont(new java.awt.Font("JetBrains Mono", 1, 15)); // NOI18N
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
        yt_DownloadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yt_DownloadBtnActionPerformed(evt);
            }
        });
        YouTubeControlCard.add(yt_DownloadBtn);
        YouTubeControlCard.add(filler5);

        yt_CancelDownloadBtn.setFont(new java.awt.Font("JetBrains Mono", 1, 15)); // NOI18N
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
        yt_CancelDownloadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yt_CancelDownloadBtnActionPerformed(evt);
            }
        });
        YouTubeControlCard.add(yt_CancelDownloadBtn);
        YouTubeControlCard.add(filler7);

        yt_ProgressBar.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        yt_ProgressBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        yt_ProgressBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));
        yt_ProgressBar.setString("Ready");
        yt_ProgressBar.setStringPainted(true);
        YouTubeControlCard.add(yt_ProgressBar);
        YouTubeControlCard.add(filler8);

        yt_StatusLbl.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        yt_StatusLbl.setText("Ready to download");
        yt_StatusLbl.setAlignmentX(0.5F);
        YouTubeControlCard.add(yt_StatusLbl);

        YouTubePnl.add(YouTubeControlCard, java.awt.BorderLayout.CENTER);

        TabbedPane.addTab("YouTube", new javax.swing.ImageIcon(getClass().getResource("/yt_music.png")), YouTubePnl, "YouTube"); // NOI18N

        SpotifyPnl.setLayout(new java.awt.BorderLayout(0, 20));

        SpotifyInputCard.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), javax.swing.BorderFactory.createEmptyBorder(25, 25, 25, 25)));
        SpotifyInputCard.setLayout(new javax.swing.BoxLayout(SpotifyInputCard, javax.swing.BoxLayout.Y_AXIS));

        spotify_urlLbl.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 14)); // NOI18N
        spotify_urlLbl.setText("Spotify URL");
        SpotifyInputCard.add(spotify_urlLbl);
        SpotifyInputCard.add(filler9);

        spotify_urlFld.setColumns(50);
        spotify_urlFld.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        spotify_urlFld.setAlignmentX(0.0F);
        spotify_urlFld.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        spotify_urlFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spotify_urlFldActionPerformed(evt);
            }
        });
        SpotifyInputCard.add(spotify_urlFld);
        SpotifyInputCard.add(filler10);

        spotify_OutputDirLbl.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        spotify_OutputDirLbl.setText("Output Folder");
        spotify_OutputDirLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        SpotifyInputCard.add(spotify_OutputDirLbl);
        SpotifyInputCard.add(filler11);

        spotify_DirPnl.setAlignmentX(0.0F);
        spotify_DirPnl.setMaximumSize(new java.awt.Dimension(2147483647, 45));
        spotify_DirPnl.setPreferredSize(new java.awt.Dimension(655, 45));
        spotify_DirPnl.setLayout(new java.awt.BorderLayout(10, 0));

        spotify_OutputDirFld.setColumns(40);
        spotify_OutputDirFld.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        spotify_OutputDirFld.setAlignmentX(0.0F);
        spotify_OutputDirFld.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), javax.swing.BorderFactory.createEmptyBorder(12, 15, 12, 15)));
        spotify_DirPnl.add(spotify_OutputDirFld, java.awt.BorderLayout.CENTER);

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

        spotify_OptionsPnl.setAlignmentX(0.0F);
        spotify_OptionsPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));

        spotify_PlaylistCheckBox.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        spotify_PlaylistCheckBox.setText("Download entire playlist");
        spotify_PlaylistCheckBox.setFocusPainted(false);
        spotify_PlaylistCheckBox.setPreferredSize(new java.awt.Dimension(217, 30));
        spotify_OptionsPnl.add(spotify_PlaylistCheckBox);
        spotify_OptionsPnl.add(filler17);

        jLabel2.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        jLabel2.setText("Audio Format: ");
        spotify_OptionsPnl.add(jLabel2);

        spotify_AudioFormatComBox.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        spotify_AudioFormatComBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MP3", "M4A", "WAV" }));
        spotify_AudioFormatComBox.setToolTipText("Audio Format");
        spotify_AudioFormatComBox.setAlignmentX(0.0F);
        spotify_AudioFormatComBox.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        spotify_OptionsPnl.add(spotify_AudioFormatComBox);

        SpotifyInputCard.add(spotify_OptionsPnl);

        SpotifyPnl.add(SpotifyInputCard, java.awt.BorderLayout.NORTH);

        SpotifyControlCard.setLayout(new javax.swing.BoxLayout(SpotifyControlCard, javax.swing.BoxLayout.Y_AXIS));

        spotify_DownloadBtn.setFont(new java.awt.Font("JetBrains Mono", 1, 15)); // NOI18N
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
        spotify_DownloadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spotify_DownloadBtnActionPerformed(evt);
            }
        });
        SpotifyControlCard.add(spotify_DownloadBtn);
        SpotifyControlCard.add(filler14);

        spotify_CancelDownloadBtn.setFont(new java.awt.Font("JetBrains Mono", 1, 15)); // NOI18N
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
        spotify_CancelDownloadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spotify_CancelDownloadBtnActionPerformed(evt);
            }
        });
        SpotifyControlCard.add(spotify_CancelDownloadBtn);
        SpotifyControlCard.add(filler15);

        spotify_ProgressBar.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        spotify_ProgressBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        spotify_ProgressBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));
        spotify_ProgressBar.setString("Ready");
        spotify_ProgressBar.setStringPainted(true);
        SpotifyControlCard.add(spotify_ProgressBar);
        SpotifyControlCard.add(filler16);

        spotify_StatusLbl.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        spotify_StatusLbl.setText("Ready to download");
        spotify_StatusLbl.setAlignmentX(0.5F);
        SpotifyControlCard.add(spotify_StatusLbl);

        SpotifyPnl.add(SpotifyControlCard, java.awt.BorderLayout.CENTER);

        TabbedPane.addTab("Spotify", new javax.swing.ImageIcon(getClass().getResource("/spotify.png")), SpotifyPnl, "Spotify"); // NOI18N

        SettingsPnl.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 20, 0));
        SettingsPnl.setLayout(new java.awt.BorderLayout(0, 20));

        SettingsScrollPane.setBorder(null);

        SettingsMainPnl.setLayout(new javax.swing.BoxLayout(SettingsMainPnl, javax.swing.BoxLayout.Y_AXIS));

        appearanceCard.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), javax.swing.BorderFactory.createEmptyBorder(20, 25, 20, 25)));
        appearanceCard.setLayout(new java.awt.BorderLayout());

        themeLbl.setFont(new java.awt.Font("JetBrains Mono", 1, 16)); // NOI18N
        themeLbl.setText("THEME");
        themeLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 15, 0));
        appearanceCard.add(themeLbl, java.awt.BorderLayout.NORTH);

        appearanceContent.setLayout(new javax.swing.BoxLayout(appearanceContent, javax.swing.BoxLayout.Y_AXIS));

        themePnl.setAlignmentX(0.0F);
        themePnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 0));

        themeGroup.add(lightThemeBtn);
        lightThemeBtn.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        lightThemeBtn.setText("Light");
        lightThemeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lightThemeBtnActionPerformed(evt);
            }
        });
        themePnl.add(lightThemeBtn);

        themeGroup.add(darkThemeBtn);
        darkThemeBtn.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        darkThemeBtn.setText("Dark");
        darkThemeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                darkThemeBtnActionPerformed(evt);
            }
        });
        themePnl.add(darkThemeBtn);

        appearanceContent.add(themePnl);

        appearanceCard.add(appearanceContent, java.awt.BorderLayout.CENTER);

        SettingsMainPnl.add(appearanceCard);
        SettingsMainPnl.add(filler18);

        statisticsCard.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), javax.swing.BorderFactory.createEmptyBorder(20, 25, 20, 25)));
        statisticsCard.setLayout(new java.awt.BorderLayout());

        statisticsLbl.setFont(new java.awt.Font("JetBrains Mono", 1, 16)); // NOI18N
        statisticsLbl.setText("STATISTICS");
        statisticsLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 15, 0));
        statisticsCard.add(statisticsLbl, java.awt.BorderLayout.NORTH);

        statsContent.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        statsContent.setLayout(new javax.swing.BoxLayout(statsContent, javax.swing.BoxLayout.Y_AXIS));

        totalLbl.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        totalLbl.setText("Total Downloads:   ");
        statsContent.add(totalLbl);
        statsContent.add(filler19);

        youtube_TotalLbl.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        youtube_TotalLbl.setText("Youtube Downloads: ");
        statsContent.add(youtube_TotalLbl);
        statsContent.add(filler20);

        spotify_TotalLbl.setFont(new java.awt.Font("JetBrains Mono", 0, 14)); // NOI18N
        spotify_TotalLbl.setText("Spotify Downloads: ");
        statsContent.add(spotify_TotalLbl);

        statisticsCard.add(statsContent, java.awt.BorderLayout.CENTER);

        SettingsMainPnl.add(statisticsCard);
        SettingsMainPnl.add(filler22);

        actionsCard.setBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), javax.swing.BorderFactory.createEmptyBorder(20, 25, 20, 25)));
        actionsCard.setLayout(new java.awt.BorderLayout());

        actionsLbl.setFont(new java.awt.Font("JetBrains Mono", 1, 16)); // NOI18N
        actionsLbl.setText("SETTINGS");
        actionsLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 15, 0));
        actionsCard.add(actionsLbl, java.awt.BorderLayout.NORTH);

        actionsContent.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        actionsContent.setLayout(new javax.swing.BoxLayout(actionsContent, javax.swing.BoxLayout.Y_AXIS));

        actionButtonsPnl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));

        saveSettingsBtn.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        saveSettingsBtn.setText("SAVE");
        saveSettingsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSettingsBtnActionPerformed(evt);
            }
        });
        actionButtonsPnl.add(saveSettingsBtn);

        resetSettingsBtn.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        resetSettingsBtn.setText("RESTORE TO DEFAULT");
        resetSettingsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetSettingsBtnActionPerformed(evt);
            }
        });
        actionButtonsPnl.add(resetSettingsBtn);

        openSettingsFolderBtn.setFont(new java.awt.Font("JetBrains Mono", 1, 14)); // NOI18N
        openSettingsFolderBtn.setText("OPEN FOLDER");
        openSettingsFolderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openSettingsFolderBtnActionPerformed(evt);
            }
        });
        actionButtonsPnl.add(openSettingsFolderBtn);

        actionsContent.add(actionButtonsPnl);

        actionsCard.add(actionsContent, java.awt.BorderLayout.CENTER);

        SettingsMainPnl.add(actionsCard);

        SettingsScrollPane.setViewportView(SettingsMainPnl);

        SettingsPnl.add(SettingsScrollPane, java.awt.BorderLayout.CENTER);

        TabbedPane.addTab("Settings", new javax.swing.ImageIcon(getClass().getResource("/settings.png")), SettingsPnl, "Settings"); // NOI18N

        MainPanel.add(TabbedPane, java.awt.BorderLayout.CENTER);

        LogPnl.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 30, 30, 30));
        LogPnl.setPreferredSize(new java.awt.Dimension(807, 250));
        LogPnl.setLayout(new java.awt.BorderLayout());

        logLbl.setFont(new java.awt.Font("JetBrains Mono", 1, 15)); // NOI18N
        logLbl.setText("Download Log");
        logLbl.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        LogPnl.add(logLbl, java.awt.BorderLayout.NORTH);

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        logArea.setEditable(false);
        logArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 15, 12, 15));
        logArea.setContentType("text/html"); // NOI18N
        logArea.setFont(new java.awt.Font("JetBrains Mono", 0, 12)); // NOI18N
        logArea.setText("<html><body style='font-family: JetBrains Mono; font-size: 12px;'></body></html>");
        logArea.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
            public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {
                logAreaHyperlinkUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(logArea);

        LogPnl.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        MainPanel.add(LogPnl, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(MainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void yt_BrowseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yt_BrowseBtnActionPerformed
        // TODO add your handling code here:
        browseOutputDirectory(yt_OutputDirFld);
    }//GEN-LAST:event_yt_BrowseBtnActionPerformed

    private void spotify_BrowseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spotify_BrowseBtnActionPerformed
        // TODO add your handling code here:
        browseOutputDirectory(spotify_OutputDirFld);
    }//GEN-LAST:event_spotify_BrowseBtnActionPerformed

    private void lightThemeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lightThemeBtnActionPerformed
        // TODO add your handling code here:
        applyLightTheme();
    }//GEN-LAST:event_lightThemeBtnActionPerformed

    private void darkThemeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_darkThemeBtnActionPerformed
        // TODO add your handling code here:
        applyDarkTheme();
    }//GEN-LAST:event_darkThemeBtnActionPerformed

    private void openSettingsFolderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openSettingsFolderBtnActionPerformed
        // TODO add your handling code here:
        try {
            File settingsFile = new File(settings.getUserSettingsFile());
            Desktop.getDesktop().open(settingsFile.getParentFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_openSettingsFolderBtnActionPerformed

    private void yt_urlFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yt_urlFldActionPerformed
        // TODO add your handling code here:
        detectPlaylistUrl(yt_urlFld, yt_PlaylistCheckBox);
    }//GEN-LAST:event_yt_urlFldActionPerformed

    private void spotify_urlFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spotify_urlFldActionPerformed
        // TODO add your handling code here:
        detectPlaylistUrl(spotify_urlFld, spotify_PlaylistCheckBox);
    }//GEN-LAST:event_spotify_urlFldActionPerformed

    private void yt_DownloadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yt_DownloadBtnActionPerformed
        // TODO add your handling code here:
        startYoutubeDownload(yt_PlaylistCheckBox.isSelected());
    }//GEN-LAST:event_yt_DownloadBtnActionPerformed

    private void yt_CancelDownloadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yt_CancelDownloadBtnActionPerformed
        // TODO add your handling code here:
        cancelDownload();
    }//GEN-LAST:event_yt_CancelDownloadBtnActionPerformed

    private void spotify_DownloadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spotify_DownloadBtnActionPerformed
        // TODO add your handling code here:
        startSpotifyDownload(spotify_PlaylistCheckBox.isSelected());
    }//GEN-LAST:event_spotify_DownloadBtnActionPerformed

    private void spotify_CancelDownloadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spotify_CancelDownloadBtnActionPerformed
        // TODO add your handling code here:
        cancelDownload();
    }//GEN-LAST:event_spotify_CancelDownloadBtnActionPerformed

    private void resetSettingsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetSettingsBtnActionPerformed
        // TODO add your handling code here:
        int result = JOptionPane.showConfirmDialog(this,"Are you sure you want to reset all settings to default?\n",
                "Confirm Reset Settings", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            try {
                settings.resetToDefaults();
                loadSettings();
                applyDefaultTheme();
                updateStatistics();
                log("✅ Settings have been reset to defaults.");
                JOptionPane.showMessageDialog(this, "Settings have been successfully reset to defaults!\n" +
                                "All changes have been applied.", "Reset Complete", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                log("Error resetting settings: " + e.getMessage());
                e.printStackTrace();

                JOptionPane.showMessageDialog(this, "An error occurred while resetting settings:\n"
                                + e.getMessage(), "Reset Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            log("ℹ️ Settings reset cancelled by user.");
        }
    }//GEN-LAST:event_resetSettingsBtnActionPerformed

    private void saveSettingsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveSettingsBtnActionPerformed
        // TODO add your handling code here:
        saveSettings();
    }//GEN-LAST:event_saveSettingsBtnActionPerformed

    private void logAreaHyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {//GEN-FIRST:event_logAreaHyperlinkUpdate
        // TODO add your handling code here:
        if (evt.getEventType() == javax.swing.event.HyperlinkEvent.EventType.ACTIVATED) {
            try {
                Desktop.getDesktop().browse(evt.getURL().toURI());
            } catch (Exception e) {
                log("Error opening link: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_logAreaHyperlinkUpdate

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {   
        
        FlatMacDarkLaf.setup();
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
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler14;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler16;
    private javax.swing.Box.Filler filler17;
    private javax.swing.Box.Filler filler18;
    private javax.swing.Box.Filler filler19;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler20;
    private javax.swing.Box.Filler filler22;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton lightThemeBtn;
    private javax.swing.JTextPane logArea;
    private javax.swing.JLabel logLbl;
    private javax.swing.JButton openSettingsFolderBtn;
    private javax.swing.JButton resetSettingsBtn;
    private javax.swing.JButton saveSettingsBtn;
    private javax.swing.JComboBox<String> spotify_AudioFormatComBox;
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
