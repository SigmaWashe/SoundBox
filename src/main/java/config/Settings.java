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
        createDefaultSettings();
    }

    private void createDefaultSettings() {
        settings = new JSONObject();

        // YouTube settings
        JSONObject youtube = new JSONObject();
        youtube.put("outputDir", "");
        youtube.put("playlistMode", false);
        youtube.put("audioFormat", "mp3");
        settings.put("youtube", youtube);

        // Spotify settings
        JSONObject spotify = new JSONObject();
        spotify.put("outputDir", "");
        spotify.put("playlistMode", false);
        spotify.put("audioFormat", "mp3");
        settings.put("spotify", spotify);

        // Theme settings
        JSONObject theme = new JSONObject();
        theme.put("theme", "dark");
        settings.put("theme", theme);

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
            youtube.put("outputDir","");
            modified = true;
        }
        if (!youtube.has("audioFormat")) {
            youtube.put("audioFormat", "mp3");
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
            spotify.put("outputDir", "");
            modified = true;
        }
        if (!spotify.has("audioFormat")) {
            spotify.put("audioFormat", "mp3");
            modified = true;
        }
        if (!spotify.has("playlistMode")) {
            spotify.put("playlistMode", false);
            modified = true;
        }

        // Check Theme settings
        JSONObject theme = settings.getJSONObject("theme");
        if (!theme.has("theme")) {
            theme.put("theme", "dark");
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
        return settings.getJSONObject("youtube").optString("outputDir", "");
    }

    public boolean getYouTubePlaylistMode() {
        return settings.getJSONObject("youtube").optBoolean("playlistMode", false);
    }

    public String getYouTubeAudioFormat() {
        return settings.getJSONObject("youtube").optString("audioFormat", "mp3");
    }

    // Spotify getters
    public String getSpotifyOutputDir() {
        return settings.getJSONObject("spotify").optString("outputDir", "");
    }

    public boolean getSpotifyPlaylistMode() {
        return settings.getJSONObject("spotify").optBoolean("playlistMode", false);
    }

    public String getSpotifyAudioFormat() {
        return settings.getJSONObject("spotify").optString("audioFormat", "mp3");
    }

    // General getters

    public String getTheme() {
        return settings.getJSONObject("theme").optString("theme", "dark");
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

    // Settings File Directory Getter
    public String getUserSettingsFile(){ return USER_SETTINGS_FILE; }

    // ==================== Setters ====================

    // YouTube setters
    public void setYouTubeOutputDir(String dir) {
        settings.getJSONObject("youtube").put("outputDir", dir);
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

    // Spotify setters
    public void setSpotifyOutputDir(String dir) {
        settings.getJSONObject("spotify").put("outputDir", dir);
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

    // Theme Setter
    public void setTheme(String theme) {
        settings.getJSONObject("theme").put("theme", theme);
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
     * Reset all settings to defaults
     */
    public void resetToDefaults() {
        try {
            // Create fresh default settings in memory
            settings = new JSONObject();

            // YouTube settings
            JSONObject youtube = new JSONObject();
            youtube.put("outputDir", "");
            youtube.put("playlistMode", false);
            youtube.put("audioFormat", "mp3");
            settings.put("youtube", youtube);

            // Spotify settings
            JSONObject spotify = new JSONObject();
            spotify.put("outputDir", "");
            spotify.put("playlistMode", false);
            spotify.put("audioFormat", "mp3");
            settings.put("spotify", spotify);

            // Theme settings
            JSONObject theme = new JSONObject();
            theme.put("theme", "dark");
            settings.put("theme", theme);

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

            // Override the existing file with default settings
            saveSettings();
            System.out.println("Settings reset to defaults and file overwritten: " + USER_SETTINGS_FILE);

        } catch (Exception e) {
            System.err.println("Error during settings reset: " + e.getMessage());
            e.printStackTrace();
        }
    }
}