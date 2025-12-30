package config;

import org.json.*;
import java.io.*;
import java.nio.file.*;

/**
 *
 * @author SigmaWashe
 */
public class Settings {

    private static final String SETTINGS_RESOURCE = "/settings.json";
    private static final String USER_SETTINGS_FILE = System.getProperty("user.home")
            + File.separator + "soundbox-settings.json";

    private JSONObject settings;

    public Settings() {
        loadSettings();
    }

    private void loadSettings() {
        File userSettingsFile = new File(USER_SETTINGS_FILE);

        if (userSettingsFile.exists()) {
            try {
                String content = new String(Files.readAllBytes(userSettingsFile.toPath()));
                settings = new JSONObject(content);
                ensureDefaults();
                return;
            } catch (Exception e) {
                System.err.println("Failed to load user settings: " + e.getMessage());
            }
        }

        try (InputStream is = getClass().getResourceAsStream(SETTINGS_RESOURCE)) {
            if (is != null) {
                String content = new String(is.readAllBytes());
                settings = new JSONObject(content);
                ensureDefaults();
                saveSettings();
                return;
            }
        } catch (Exception e) {
            System.err.println("Failed to load resource settings: " + e.getMessage());
        }

        // If all else fails, create default settings
        createDefaultSettings();
    }

    private void createDefaultSettings() {
        settings = new JSONObject();

        // YouTube settings
        JSONObject youtube = new JSONObject();
        youtube.put("outputDir", System.getProperty("user.home") + File.separator + "Music" + File.separator + "YouTube");
        youtube.put("lastUrl", "");
        youtube.put("playlistMode", false);
        youtube.put("audioFormat", "m4a");
        youtube.put("quality", 0);
        settings.put("youtube", youtube);

        // Spotify settings
        JSONObject spotify = new JSONObject();
        spotify.put("outputDir", System.getProperty("user.home") + File.separator + "Music" + File.separator + "Spotify");
        spotify.put("lastUrl", "");
        spotify.put("playlistMode", false);
        spotify.put("audioFormat", "m4a");
        spotify.put("quality", 0);
        settings.put("spotify", spotify);

        // General settings
        JSONObject general = new JSONObject();
        general.put("embedThumbnails", true);
        general.put("embedMetadata", true);
        general.put("deleteSourceFiles", true);
        general.put("theme", "light");
        settings.put("general", general);

        // Window settings
        JSONObject window = new JSONObject();
        window.put("width", 700);
        window.put("height", 900);
        window.put("lastTab", 0);
        settings.put("window", window);

        // Statistics
        JSONObject stats = new JSONObject();
        stats.put("totalDownloads", 0);
        stats.put("youtubeDownloads", 0);
        stats.put("spotifyDownloads", 0);
        settings.put("statistics", stats);

        saveSettings();
    }

    private void ensureDefaults() {
        boolean modified = false;

        // Check YouTube settings
        if (!settings.has("youtube")) {
            settings.put("youtube", new JSONObject());
            modified = true;
        }
        JSONObject youtube = settings.getJSONObject("youtube");
        if (!youtube.has("outputDir")) {
            youtube.put("outputDir", System.getProperty("user.home") + File.separator + "Music" + File.separator + "YouTube");
            modified = true;
        }
        if (!youtube.has("lastUrl")) {
            youtube.put("lastUrl", "");
            modified = true;
        }
        if (!youtube.has("audioFormat")) {
            youtube.put("audioFormat", "m4a");
            modified = true;
        }
        if (!youtube.has("quality")) {
            youtube.put("quality", 0);
            modified = true;
        }
        if (!youtube.has("playlistMode")) {
            youtube.put("playlistMode", false);
            modified = true;
        }

        // Check Spotify settings
        if (!settings.has("spotify")) {
            settings.put("spotify", new JSONObject());
            modified = true;
        }
        JSONObject spotify = settings.getJSONObject("spotify");
        if (!spotify.has("outputDir")) {
            spotify.put("outputDir", System.getProperty("user.home") + File.separator + "Music" + File.separator + "Spotify");
            modified = true;
        }
        if (!spotify.has("lastUrl")) {
            spotify.put("lastUrl", "");
            modified = true;
        }
        if (!spotify.has("audioFormat")) {
            spotify.put("audioFormat", "m4a");
            modified = true;
        }
        if (!spotify.has("quality")) {
            spotify.put("quality", 0);
            modified = true;
        }
        if (!spotify.has("playlistMode")) {
            spotify.put("playlistMode", false);
            modified = true;
        }

        // Check general settings
        if (!settings.has("general")) {
            JSONObject general = new JSONObject();
            general.put("embedThumbnails", true);
            general.put("embedMetadata", true);
            general.put("deleteSourceFiles", true);
            general.put("theme", "light");
            settings.put("general", general);
            modified = true;
        }
        JSONObject general = settings.getJSONObject("general");
        if (!general.has("embedThumbnails")) {
            general.put("embedThumbnails", true);
            modified = true;
        }
        if (!general.has("embedMetadata")) {
            general.put("embedMetadata", true);
            modified = true;
        }
        if (!general.has("deleteSourceFiles")) {
            general.put("deleteSourceFiles", true);
            modified = true;
        }
        if (!general.has("theme")) {
            general.put("theme", "light");
            modified = true;
        }

        // Check window settings
        if (!settings.has("window")) {
            JSONObject window = new JSONObject();
            window.put("width", 700);
            window.put("height", 900);
            window.put("lastTab", 0);
            settings.put("window", window);
            modified = true;
        }

        // Check statistics
        if (!settings.has("statistics")) {
            JSONObject stats = new JSONObject();
            stats.put("totalDownloads", 0);
            stats.put("youtubeDownloads", 0);
            stats.put("spotifyDownloads", 0);
            settings.put("statistics", stats);
            modified = true;
        }

        if (modified) {
            saveSettings();
        }
    }

    public void saveSettings() {
        try {
            String jsonString = settings.toString(4); // Pretty print with 4-space indent
            Files.write(Paths.get(USER_SETTINGS_FILE), jsonString.getBytes());
        } catch (IOException e) {
            System.err.println("Failed to save settings: " + e.getMessage());
        }
    }

    // ==================== Getters ====================

    // YouTube getters
    public String getYouTubeOutputDir() {
        return settings.getJSONObject("youtube").optString("outputDir",
                System.getProperty("user.home") + File.separator + "Music" + File.separator + "YouTube");
    }

    public String getYouTubeLastUrl() {
        return settings.getJSONObject("youtube").optString("lastUrl", "");
    }

    public boolean getYouTubePlaylistMode() {
        return settings.getJSONObject("youtube").optBoolean("playlistMode", false);
    }

    public String getYouTubeAudioFormat() {
        return settings.getJSONObject("youtube").optString("audioFormat", "m4a");
    }

    public int getYouTubeQuality() {
        return settings.getJSONObject("youtube").optInt("quality", 0);
    }

    // Spotify getters
    public String getSpotifyOutputDir() {
        return settings.getJSONObject("spotify").optString("outputDir",
                System.getProperty("user.home") + File.separator + "Music" + File.separator + "Spotify");
    }

    public String getSpotifyLastUrl() {
        return settings.getJSONObject("spotify").optString("lastUrl", "");
    }

    public boolean getSpotifyPlaylistMode() {
        return settings.getJSONObject("spotify").optBoolean("playlistMode", false);
    }

    public String getSpotifyAudioFormat() {
        return settings.getJSONObject("spotify").optString("audioFormat", "m4a");
    }

    public int getSpotifyQuality() {
        return settings.getJSONObject("spotify").optInt("quality", 0);
    }

    // General getters
    public boolean getEmbedThumbnails() {
        return settings.getJSONObject("general").optBoolean("embedThumbnails", true);
    }

    public boolean getEmbedMetadata() {
        return settings.getJSONObject("general").optBoolean("embedMetadata", true);
    }

    public boolean getDeleteSourceFiles() {
        return settings.getJSONObject("general").optBoolean("deleteSourceFiles", true);
    }

    public String getTheme() {
        return settings.getJSONObject("general").optString("theme", "light");
    }

    // Window getters
    public int getWindowWidth() {
        return settings.getJSONObject("window").optInt("width", 700);
    }

    public int getWindowHeight() {
        return settings.getJSONObject("window").optInt("height", 900);
    }

    public int getLastTab() {
        return settings.getJSONObject("window").optInt("lastTab", 0);
    }

    // Statistics getters
    public int getTotalDownloads() {
        return settings.getJSONObject("statistics").optInt("totalDownloads", 0);
    }

    public int getYouTubeDownloads() {
        return settings.getJSONObject("statistics").optInt("youtubeDownloads", 0);
    }

    public int getSpotifyDownloads() {
        return settings.getJSONObject("statistics").optInt("spotifyDownloads", 0);
    }

    // ==================== Setters ====================

    // YouTube setters
    public void setYouTubeOutputDir(String dir) {
        settings.getJSONObject("youtube").put("outputDir", dir);
        saveSettings();
    }

    public void setYouTubeLastUrl(String url) {
        settings.getJSONObject("youtube").put("lastUrl", url);
        saveSettings();
    }

    public void setYouTubePlaylistMode(boolean mode) {
        settings.getJSONObject("youtube").put("playlistMode", mode);
        saveSettings();
    }

    public void setYouTubeAudioFormat(String format) {
        settings.getJSONObject("youtube").put("audioFormat", format.toLowerCase());
        saveSettings();
    }

    public void setYouTubeQuality(int quality) {
        settings.getJSONObject("youtube").put("quality", quality);
        saveSettings();
    }

    // Spotify setters
    public void setSpotifyOutputDir(String dir) {
        settings.getJSONObject("spotify").put("outputDir", dir);
        saveSettings();
    }

    public void setSpotifyLastUrl(String url) {
        settings.getJSONObject("spotify").put("lastUrl", url);
        saveSettings();
    }

    public void setSpotifyPlaylistMode(boolean mode) {
        settings.getJSONObject("spotify").put("playlistMode", mode);
        saveSettings();
    }

    public void setSpotifyAudioFormat(String format) {
        settings.getJSONObject("spotify").put("audioFormat", format.toLowerCase());
        saveSettings();
    }

    public void setSpotifyQuality(int quality) {
        settings.getJSONObject("spotify").put("quality", quality);
        saveSettings();
    }

    // General setters
    public void setEmbedThumbnails(boolean embed) {
        settings.getJSONObject("general").put("embedThumbnails", embed);
        saveSettings();
    }

    public void setEmbedMetadata(boolean embed) {
        settings.getJSONObject("general").put("embedMetadata", embed);
        saveSettings();
    }

    public void setDeleteSourceFiles(boolean delete) {
        settings.getJSONObject("general").put("deleteSourceFiles", delete);
        saveSettings();
    }

    public void setTheme(String theme) {
        settings.getJSONObject("general").put("theme", theme);
        saveSettings();
    }

    // Window setters
    public void setWindowSize(int width, int height) {
        JSONObject window = settings.getJSONObject("window");
        window.put("width", width);
        window.put("height", height);
        saveSettings();
    }

    public void setLastTab(int tabIndex) {
        settings.getJSONObject("window").put("lastTab", tabIndex);
        saveSettings();
    }

    // Statistics setters
    public void incrementDownloadStats(boolean isYouTube) {
        JSONObject stats = settings.getJSONObject("statistics");
        stats.put("totalDownloads", stats.getInt("totalDownloads") + 1);

        if (isYouTube) {
            stats.put("youtubeDownloads", stats.getInt("youtubeDownloads") + 1);
        } else {
            stats.put("spotifyDownloads", stats.getInt("spotifyDownloads") + 1);
        }

        saveSettings();
    }

    // ==================== Utility Methods ====================

    /**
     * Get the entire settings JSON object (for advanced usage)
     */
    public JSONObject getSettings() {
        return settings;
    }

    /**
     * Export settings to a file
     */
    public void exportSettings(String filePath) throws IOException {
        String jsonString = settings.toString(4);
        Files.write(Paths.get(filePath), jsonString.getBytes());
    }

    /**
     * Import settings from a file
     */
    public void importSettings(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        settings = new JSONObject(content);
        ensureDefaults();
        saveSettings();
    }

    /**
     * Reset all settings to defaults
     */
    public void resetToDefaults() {
        createDefaultSettings();
    }

    /**
     * Get the path to the user settings file
     */
    public String getSettingsFilePath() {
        return USER_SETTINGS_FILE;
    }
}