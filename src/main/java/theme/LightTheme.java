package theme;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 
 * @author SigmaWashe
 */

public class LightTheme {

    public LightTheme() {

        UIManager.put("ToolTip.background", new Color(250, 240, 220));
        UIManager.put("ToolTip.foreground", new Color(75, 50, 30));
        UIManager.put("ToolTip.border", BorderFactory.createLineBorder(new Color(220, 195, 160)));
        UIManager.put("ToolTip.font", new Font("JetBrains Mono", Font.PLAIN, 14));

        UIManager.put("ComboBox.buttonBackground", new Color(250, 240, 220));
        UIManager.put("ComboBox.selectionBackground", new Color(220, 195, 160));
        UIManager.put("ComboBox.popupBackground", new Color(250, 240, 220));
        UIManager.put("ComboBox.popupForground", new Color(75, 50, 30));

    }
    
    public static final Color LIGHT_BG = new Color(238, 218, 185);
    public static final Color CARD_BG = new Color(250, 240, 220);

    public static final Color TEXT_PRIMARY = new Color(75, 50, 30);
    public static final Color TEXT_SECONDARY = new Color(140, 110, 85);

    public static final Color YouTube = new Color(215, 45, 45);
    public static final Color SPOTIFY = new Color(45, 160, 85);

    public static final Color HOVER = new Color(220, 195, 160);

}

/*

    private void exportSettings() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Export Settings");
        chooser.setSelectedFile(new File("music-downloader-settings.json"));

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                String path = chooser.getSelectedFile().getAbsolutePath();
                if (!path.endsWith(".json")) {
                    path += ".json";
                }
                settingsManager.exportSettings(path);
                JOptionPane.showMessageDialog(this,
                        "Settings exported successfully!",
                        "Export Complete",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Failed to export settings: " + ex.getMessage(),
                        "Export Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void importSettings() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Import Settings");
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".json");
            }
            public String getDescription() {
                return "JSON Files (*.json)";
            }
        });

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                settingsManager.importSettings(chooser.getSelectedFile().getAbsolutePath());
                JOptionPane.showMessageDialog(this,
                        "Settings imported successfully!\nPlease restart the application.",
                        "Import Complete",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Failed to import settings: " + ex.getMessage(),
                        "Import Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void resetSettings() {
        int result = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to reset all settings to default?\nThis cannot be undone.",
                "Confirm Reset",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            settingsManager.resetToDefaults();
            JOptionPane.showMessageDialog(this,
                    "Settings have been reset to default.\nPlease restart the application.",
                    "Reset Complete",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void openSettingsFolder() {
        try {
            File settingsFile = new File(settingsManager.getSettingsFilePath());
            File settingsFolder = settingsFile.getParentFile();

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(settingsFolder);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Settings file location:\n" + settingsFile.getAbsolutePath(),
                        "Settings Location",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Failed to open settings folder: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(DARK_BG);
        field.setForeground(TEXT);
        field.setCaretColor(TEXT);
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(60, 60, 60), 1, true),
                BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void styleCheckBox(JCheckBox checkBox) {
        checkBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        checkBox.setForeground(TEXT_SECONDARY);
        checkBox.setBackground(CARD_BG);
        checkBox.setFocusPainted(false);
        checkBox.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void saveSettings() {
        settingsManager.setYouTubeOutputDir(ytOutputDirField.getText());
        settingsManager.setSpotifyOutputDir(spotifyOutputDirField.getText());
        settingsManager.setYouTubeLastUrl(ytUrlField.getText());
        settingsManager.setSpotifyLastUrl(spotifyUrlField.getText());
        settingsManager.setYouTubePlaylistMode(ytPlaylistCheckBox.isSelected());
        settingsManager.setSpotifyPlaylistMode(spotifyPlaylistCheckBox.isSelected());
    }

    private void detectPlaylistUrl(JTextField urlField, JCheckBox playlistCheckBox) {
        String url = urlField.getText().trim();
        if (YouTubeDownloader.isPlaylistUrl(url)) {
            playlistCheckBox.setSelected(true);
            log("ℹ️ Playlist URL detected - playlist mode enabled automatically");
        }
    }

    private void detectSpotifyPlaylistUrl() {
        String url = spotifyUrlField.getText().trim();
        if (SpotifyDownloader.isSpotifyPlaylistUrl(url)) {
            spotifyPlaylistCheckBox.setSelected(true);
            log("ℹ️ Spotify playlist/album URL detected - playlist mode enabled automatically");
        }
    }

    private void checkDownloadTools() {
        if (!YouTubeDownloader.isYtDlpAvailable()) {
            JOptionPane.showMessageDialog(this,
                    "⚠️ yt-dlp is not installed or not in PATH.\n" +
                            "Install from: https://github.com/yt-dlp/yt-dlp\n\n" +
                            "Installation:\n" +
                            "- Windows: winget install yt-dlp\n" +
                            "- macOS: brew install yt-dlp\n" +
                            "- Linux: sudo apt install yt-dlp\n\n" +
                            "Note: yt-dlp is required for both YouTube and Spotify downloads.",
                    "yt-dlp Not Found",
                    JOptionPane.ERROR_MESSAGE);
            ytDownloadButton.setEnabled(false);
            spotifyDownloadButton.setEnabled(false);
        } else {
            String version = YouTubeDownloader.getYtDlpVersion();
            log("✓ yt-dlp found (version: " + version + ")");
            log("✓ Spotify downloads enabled via Spotify API + YouTube Music");
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

    private void startYouTubeDownload() {
        String url = ytUrlField.getText().trim();
        String outputDir = ytOutputDirField.getText().trim();

        if (!validateAndPrepareDownload(url, outputDir)) {
            return;
        }

        saveSettings();
        prepareDownloadUI(ytDownloadButton, ytCancelButton, ytProgressBar, ytStatusLabel);

        boolean isPlaylistMode = ytPlaylistCheckBox.isSelected();
        log(isPlaylistMode ? "📋 Playlist mode enabled" : "🎵 Single video mode");

        new Thread(() -> runYouTubeDownloadProcess(url, outputDir, isPlaylistMode)).start();
    }

    private void startSpotifyDownload() {
        String url = spotifyUrlField.getText().trim();
        String outputDir = spotifyOutputDirField.getText().trim();

        if (!validateAndPrepareDownload(url, outputDir)) {
            return;
        }

        saveSettings();
        prepareDownloadUI(spotifyDownloadButton, spotifyCancelButton, spotifyProgressBar, spotifyStatusLabel);

        boolean isPlaylistMode = spotifyPlaylistCheckBox.isSelected();
        log(isPlaylistMode ? "📋 Spotify playlist/album mode" : "🎵 Single track mode");

        new Thread(() -> runSpotifyDownloadProcess(url, outputDir, isPlaylistMode)).start();
    }

    private boolean validateAndPrepareDownload(String url, String outputDir) {
        if (url.isEmpty() || !url.startsWith("http")) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid URL starting with http:// or https://",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        File outputFolder = new File(outputDir);
        if (!outputFolder.exists() && !outputFolder.mkdirs()) {
            JOptionPane.showMessageDialog(this,
                    "Cannot create output directory: " + outputDir,
                    "Directory Error",
                    JOptionPane.ERROR_MESSAGE);
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

    private void prepareDownloadUI(JButton downloadBtn, JButton cancelBtn, JProgressBar progressBar, JLabel statusLabel) {
        logArea.setText("");
        downloadBtn.setEnabled(false);
        cancelBtn.setEnabled(true);
        isCancelled.set(false);
        progressBar.setValue(0);
        progressBar.setString("Starting...");
        statusLabel.setText("Initializing...");
    }

    private void cancelDownload() {
        isCancelled.set(true);
        log("⚠️ Cancellation requested...");

        if (currentDownloadType.equals("youtube")) {
            ytCancelButton.setEnabled(false);
        } else {
            spotifyCancelButton.setEnabled(false);
        }

        if (executorService != null) {
            executorService.shutdownNow();
        }
    }

    private void runYouTubeDownloadProcess(String url, String outputDir, boolean isPlaylistMode) {
        try {
            final int totalPhases = isPlaylistMode ? 3 : 2;
            JProgressBar progressBar = ytProgressBar;
            JLabel statusLabel = ytStatusLabel;

            updateStatus("Downloading audio (Phase 1/" + totalPhases + ")...", statusLabel);
            log("📥 Starting YouTube download from: " + url);
            log("📂 Output directory: " + outputDir);

            progressBar.setIndeterminate(false);
            progressBar.setValue(0);
            progressBar.setString("0% (Download) | 0% (Overall)");

            Consumer<Integer> downloadProgressUpdater = progress -> {
                int overallProgress = (int) (progress * 0.5);
                SwingUtilities.invokeLater(() -> {
                    progressBar.setValue(overallProgress);
                    progressBar.setString(progress + "% (Download) | " + overallProgress + "% (Overall)");
                });
            };

            YouTubeDownloader.downloadPlaylist(url, outputDir, this::log,
                    downloadProgressUpdater, isPlaylistMode);

            if (isCancelled.get()) {
                updateStatus("Cancelled", statusLabel);
                finishProcess(false, ytDownloadButton, ytCancelButton, progressBar);
                return;
            }

            processDownloadedFiles(outputDir, isPlaylistMode, url, totalPhases,
                    progressBar, statusLabel, ytDownloadButton, ytCancelButton);

            // Update statistics
            settingsManager.incrementDownloadStats(true);

        } catch (Exception e) {
            log("❌ Error: " + e.getMessage());
            e.printStackTrace();
            updateStatus("Error occurred", ytStatusLabel);
            finishProcess(false, ytDownloadButton, ytCancelButton, ytProgressBar);
        }
    }

    private void runSpotifyDownloadProcess(String url, String outputDir, boolean isPlaylistMode) {
        try {
            JProgressBar progressBar = spotifyProgressBar;
            JLabel statusLabel = spotifyStatusLabel;

            updateStatus("Downloading from Spotify...", statusLabel);
            log("📥 Starting Spotify download from: " + url);
            log("📂 Output directory: " + outputDir);

            progressBar.setIndeterminate(true);
            progressBar.setString("Downloading...");

            Consumer<Integer> progressUpdater = progress -> {
                SwingUtilities.invokeLater(() -> {
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(progress);
                    progressBar.setString(progress + "% Complete");
                });
            };

            SpotifyDownloader.downloadSpotify(url, outputDir, this::log,
                    progressUpdater, isPlaylistMode);

            if (isCancelled.get()) {
                updateStatus("Cancelled", statusLabel);
                finishProcess(false, spotifyDownloadButton, spotifyCancelButton, progressBar);
                return;
            }

            // Organize files if playlist mode
            if (isPlaylistMode) {
                updateStatus("Organizing files...", statusLabel);
                File folder = new File(outputDir);
                FolderOrganizer.organizeLoosenImageFiles(folder, this::log);
            }

            progressBar.setValue(100);
            progressBar.setString("100% Complete!");
            log("✅ Spotify download completed successfully!");
            updateStatus("Complete!", statusLabel);
            finishProcess(true, spotifyDownloadButton, spotifyCancelButton, progressBar);

            // Update statistics
            settingsManager.incrementDownloadStats(false);

        } catch (Exception e) {
            log("❌ Error: " + e.getMessage());
            e.printStackTrace();
            updateStatus("Error occurred", spotifyStatusLabel);
            finishProcess(false, spotifyDownloadButton, spotifyCancelButton, spotifyProgressBar);
        }
    }

    private void processDownloadedFiles(String outputDir, boolean isPlaylistMode, String url, int totalPhases, JProgressBar progressBar, JLabel statusLabel, JButton downloadButton, JButton cancelButton) throws Exception {
        updateStatus("Processing downloaded files (Phase 2/" + totalPhases + ")...", statusLabel);

        File folder = new File(outputDir);
        File[] audioFiles = folder.listFiles((dir, name) ->
                name.endsWith(WEBM_EXT) || name.endsWith(OPUS_EXT) || name.endsWith(M4A_EXT));

        if (audioFiles == null || audioFiles.length == 0) {
            log("ℹ️ No audio files found to process.");
            finishProcess(true, downloadButton, cancelButton, progressBar);
            return;
        }

        log("🔄 Processing " + audioFiles.length + " file(s)...");

        int threadCount = settingsManager.getParallelDownloads();
        executorService = Executors.newFixedThreadPool(threadCount);

        final int totalFiles = audioFiles.length;
        final List<File> processedM4aFiles = Collections.synchronizedList(new ArrayList<>());
        final int[] processedFiles = {0};

        for (File audioFile : audioFiles) {
            if (isCancelled.get()) {
                executorService.shutdownNow();
                updateStatus("Cancelled", statusLabel);
                finishProcess(false, downloadButton, cancelButton, progressBar);
                return;
            }

            executorService.submit(() -> {
                try {
                    File finalFile = processAudioFile(audioFile, outputDir);
                    processedM4aFiles.add(finalFile);

                    synchronized (processedFiles) {
                        processedFiles[0]++;
                        int conversionProgress = (processedFiles[0] * 100) / totalFiles;
                        int overallProgress = 50 + (int) (conversionProgress * 0.4);

                        SwingUtilities.invokeLater(() -> {
                            progressBar.setValue(overallProgress);
                            progressBar.setString(conversionProgress + "% (Processing) | " +
                                    overallProgress + "% (Overall) - " +
                                    processedFiles[0] + "/" + totalFiles);
                        });
                    }
                } catch (Exception e) {
                    log("❌ Error processing " + audioFile.getName() + ": " + e.getMessage());
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);

        if (isCancelled.get()) {
            finishProcess(false, downloadButton, cancelButton, progressBar);
            return;
        }

        // Phase 3: Organize files
        if (isPlaylistMode) {
            updateStatus("Organizing into playlist folder (Phase 3/3)...", statusLabel);
            progressBar.setValue(90);
            progressBar.setString("Moving files...");

            String playlistTitle = YouTubeDownloader.getPlaylistTitle(url);
            File newFolder = new File(outputDir, playlistTitle);

            if (!newFolder.exists() && newFolder.mkdirs()) {
                log("📁 Created playlist folder: " + newFolder.getName());
            }

            if (newFolder.exists()) {
                int movedCount = 0;
                for (File audioFile : processedM4aFiles) {
                    try {
                        if (audioFile.exists()) {
                            Path source = audioFile.toPath();
                            Path destination = newFolder.toPath().resolve(audioFile.getName());
                            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
                            movedCount++;
                        }
                    } catch (Exception e) {
                        log("⚠️ Error moving file " + audioFile.getName() + ": " + e.getMessage());
                    }
                }
                if (movedCount > 0) {
                    log("✅ Moved " + movedCount + " file(s) into folder: " + newFolder.getName());
                }
            }

            FolderOrganizer.organizeLoosenImageFiles(folder, this::log);
        } else {
            FolderOrganizer.organizeLoosenImageFiles(folder, this::log);
        }

        progressBar.setValue(100);
        progressBar.setString("100% (Complete!)");
        log("✅ All tasks completed successfully!");
        updateStatus("Complete!", statusLabel);
        finishProcess(true, downloadButton, cancelButton, progressBar);
    }

    private File processAudioFile(File audioFile, String outputDir) throws Exception {
        File finalM4aFile;

        if (audioFile.getName().toLowerCase().endsWith(TARGET_EXT)) {
            finalM4aFile = audioFile;
            log("✓ Format Verified (M4A): " + audioFile.getName());
        } else {
            String baseName = audioFile.getName().replaceFirst("[.][^.]+$", "");
            finalM4aFile = new File(outputDir, baseName + TARGET_EXT);

            log("🔄 Converting to M4A: " + audioFile.getName());
            AudioConverter.convertToM4A(audioFile, finalM4aFile);

            if (finalM4aFile.exists() && audioFile.delete()) {
                log("🗑️ Cleaned up source: " + audioFile.getName());
            }
        }

        File coverArt = MetadataManager.findAlbumArt(finalM4aFile);

        if (coverArt != null && coverArt.exists()) {
            log("🖼️ Embedding album art: " + coverArt.getName());
            String metadataSummary = MetadataManager.embedAlbumArt(finalM4aFile, coverArt);
            log("✓ Metadata Confirmed. " + metadataSummary);
        } else {
            log("✓ Completed (no album art): " + finalM4aFile.getName());
        }

        return finalM4aFile;
    }

    private void updateStatus(String status, JLabel statusLabel) {
        SwingUtilities.invokeLater(() -> statusLabel.setText(status));
    }

    private void finishProcess(boolean success, JButton downloadButton, JButton cancelButton, JProgressBar progressBar) {
        SwingUtilities.invokeLater(() -> {
            downloadButton.setEnabled(true);
            cancelButton.setEnabled(false);
            progressBar.setValue(success ? 100 : 0);

            if (success) {
                progressBar.setString("Complete!");
                JOptionPane.showMessageDialog(this,
                        "Download and processing finished!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                progressBar.setString("Failed or Cancelled");
            }
        });
    }

    private void log(String message) {
        SwingUtilities.invokeLater(() -> {
            logArea.append(message + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }
*/