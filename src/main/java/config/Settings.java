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
            + File.separator + ".music-downloader-settings.json";

    private JSONObject settings;

    public Settings() {
        loadSettings();
    }

    /**
     * Load settings from user's home directory, or from resources if not found
     */
    private void loadSettings() {
        File userSettingsFile = new File(USER_SETTINGS_FILE);

        // Try to load from user's home directory first
        if (userSettingsFile.exists()) {
            try {
                String content = new String(Files.readAllBytes(userSettingsFile.toPath()));
                settings = new JSONObject(content);
                defaultSettings();
                return;
            } catch (Exception e) {
                System.err.println("Failed to load user settings: " + e.getMessage());
            }
        }

        // Try to load from resources folder
        try (InputStream is = getClass().getResourceAsStream(SETTINGS_RESOURCE)) {
            if (is != null) {
                String content = new String(is.readAllBytes());
                settings = new JSONObject(content);
                defaultSettings();

                // Save to user's home directory for future modifications
                saveSettings();
                return;
            }
        } catch (Exception e) {
            System.err.println("Failed to load resource settings: " + e.getMessage());
        }

        // If all else fails, create default settings
        createDefaultSettings();
    }

    /**
     * Create default settings structure
     */
    private void createDefaultSettings() {
        settings = new JSONObject();

        // YouTube settings
        JSONObject youtube = new JSONObject();
        youtube.put("outputDir", System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "SoundBox Downloads");
        youtube.put("playlistMode", false);
        youtube.put("audioFormat", "M4A");
        youtube.put("quality", "0");
        settings.put("youtube", youtube);

        // Spotify settings
        JSONObject spotify = new JSONObject();
        spotify.put("outputDir", System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "SoundBox Downloads");
        spotify.put("playlistMode", false);
        spotify.put("audioFormat", "M4A");
        spotify.put("quailty", 0);
        settings.put("spotify", spotify);

        // General settings
        JSONObject general = new JSONObject();
        general.put("embedThumbnails", true);
        general.put("embedMetadata", true);
        general.put("deleteSourceFiles", true);
        general.put("theme", "dark");
        settings.put("general", general);

        // Window settings
        JSONObject window = new JSONObject();
        window.put("width", 850);
        window.put("height", 800);
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

    /**
     * Ensure all default keys exist (for backwards compatibility)
     */
    private void defaultSettings() {
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
        if (!youtube.has("playlistMode")) {
            youtube.put("playlistMode", false);
            modified = true;
        }
        if (!youtube.has("audioFormat")) {
            youtube.put("audioFormat", "MP3");
        }
        if (!youtube.has("quality")) {
            youtube.put("quality", "0");
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
        if (!spotify.has("playlistMode")) {
            spotify.put("playlistMode", false);
            modified = true;
        }
        if (!spotify.has("audioFormat")) {

        }
        if (!spotify.has("quality")) {
            spotify.put("quality", "0");
            modified = true;
        }

        // Check general settings
        if (!settings.has("general")) {
            JSONObject general = new JSONObject();
            general.put("embedThumbnails", true);
            general.put("embedMetadata", true);
            general.put("convertToM4A", true);
            general.put("parallelDownloads", Math.max(1, Runtime.getRuntime().availableProcessors() - 1));
            general.put("deleteSourceFiles", true);
            settings.put("general", general);
            modified = true;
        }

        // Check window settings
        if (!settings.has("window")) {
            JSONObject window = new JSONObject();
            window.put("width", 850);
            window.put("height", 800);
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
            stats.put("lastDownloadDate", "");
            settings.put("statistics", stats);
            modified = true;
        }

        if (modified) {
            saveSettings();
        }
    }

    /**
     * Save settings to user's home directory (not resources folder)
     */
    public void saveSettings() {
        try {
            String jsonString = settings.toString(4); // Pretty print with 4-space indent
            Files.write(Paths.get(USER_SETTINGS_FILE), jsonString.getBytes());
        } catch (IOException e) {
            System.err.println("Failed to save settings: " + e.getMessage());
        }
    }
}