package core;

import config.Settings;

import java.io.*;
import java.util.function.*;
import java.util.regex.*;

public class SpotifyDownloader {

    private static final Pattern PROGRESS_PATTERN = Pattern.compile("\\[download\\]\\s+([0-9.]+)%.*");
    private static Settings settings = new Settings();

    /**
     * Downloads Spotify track(s) or playlist using spotdl.
     * spotdl automatically handles M4A format and metadata embedding.
     */
    public static void downloadSpotify(String url, String outputDir, Consumer<String> logger,
                                       Consumer<Integer> progressUpdater, boolean isPlaylist) throws Exception {

        ProcessBuilder pb = new ProcessBuilder();
        pb.command(
                "spotdl",
                url,
                "--output", outputDir,
                "--format", settings.getSpotifyAudioFormat(),
                "--bitrate", String.valueOf(settings.getSpotifyQuality()),
                "--lyrics",
                "--threads", String.valueOf(Math.max(1, Runtime.getRuntime().availableProcessors() - 1))
        );

        pb.redirectErrorStream(true);
        Process process = pb.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            int lastProgress = -1;

            while ((line = reader.readLine()) != null) {
                if (logger != null) {
                    logger.accept(line);
                }

                Matcher matcher = PROGRESS_PATTERN.matcher(line);
                if (matcher.find()) {
                    try {
                        int progress = (int) Double.parseDouble(matcher.group(1));
                        if (progress != lastProgress && progressUpdater != null) {
                            progressUpdater.accept(progress);
                            lastProgress = progress;
                        }
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IOException("spotdl process failed with exit code: " + exitCode);
        }
    }

    /**
     * Extracts the playlist/album name from a Spotify URL using spotdl.
     * Returns a sanitized string suitable for a folder name.
     */
    public static String getSpotifyPlaylistTitle(String url) {
        try {
            ProcessBuilder pb = new ProcessBuilder("spotdl", url, "--print-errors", "--list");
            pb.redirectErrorStream(true);
            Process process = pb.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Look for playlist or album name in output
                    if (line.contains("Playlist:") || line.contains("Album:")) {
                        String[] parts = line.split(":", 2);
                        if (parts.length > 1) {
                            String title = parts[1].trim();
                            title = title.replaceAll("[\\\\/:*?\"<>|.]", "_");
                            return title.isEmpty() ? "Spotify Download" : title;
                        }
                    }
                }
                process.waitFor();
            }
            return "Spotify Download";
        } catch (Exception e) {
            return "Spotify Download";
        }
    }

    public static boolean isSpotdlAvailable() {
        try {
            ProcessBuilder pb = new ProcessBuilder("spotdl", "--version");
            Process process = pb.start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getSpotdlVersion() {
        try {
            ProcessBuilder pb = new ProcessBuilder("spotdl", "--version");
            Process process = pb.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String version = reader.readLine();
                return version != null ? version : "Unknown";
            }
        } catch (Exception e) {
            return "Unknown";
        }
    }

    public static boolean isSpotifyUrl(String url) {
        return url != null && (
                url.contains("open.spotify.com/track/") ||
                        url.contains("open.spotify.com/playlist/") ||
                        url.contains("open.spotify.com/album/") ||
                        url.contains("spotify:track:") ||
                        url.contains("spotify:playlist:") ||
                        url.contains("spotify:album:")
        );
    }

    public static boolean isSpotifyPlaylistUrl(String url) {
        return url != null && (
                url.contains("open.spotify.com/playlist/") ||
                        url.contains("open.spotify.com/album/") ||
                        url.contains("spotify:playlist:") ||
                        url.contains("spotify:album:")
        );
    }
}