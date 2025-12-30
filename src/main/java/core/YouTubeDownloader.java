package core;

import config.Settings;

import java.io.*;
import java.util.function.*;
import java.util.regex.*;

public class YouTubeDownloader {

    private static final Pattern PROGRESS_PATTERN = Pattern.compile("\\[download\\]\\s+([0-9.]+)%\\s+of\\s+~?([0-9.]+[KMG]i?B).*");
    private static Settings settings = new Settings();
    /**
     * Downloads YouTube video(s) or playlist using yt-dlp.
     * Priorities:
     * 1. Direct M4A download (no conversion needed)
     * 2. Best audio converted to M4A if direct not available
     */
    public static void downloadPlaylist(String url, String outputDir, Consumer<String> logger,
                                        Consumer<Integer> progressUpdater, boolean forcePlaylist) throws Exception {

        ProcessBuilder pb = new ProcessBuilder();
        pb.command(
                "yt-dlp",
                "-f", "bestaudio[ext=" + settings.getYouTubeAudioFormat() + "]/bestaudio/best",
                "-x",
                "--audio-format", settings.getYouTubeAudioFormat(),
                "--audio-quality", String.valueOf(settings.getYouTubeQuality()),
                "--embed-thumbnail",
                "--write-thumbnail",
                "--convert-thumbnails", "jpg",
                "--add-metadata",
                "--parse-metadata", "artist:%(uploader)s",
                "--parse-metadata", "album:%(playlist_title)s",
                "--parse-metadata", "track:%(playlist_index)s",
                forcePlaylist ? "--yes-playlist" : "--no-playlist",
                "-o", outputDir + File.separator + "%(title)s.%(ext)s",
                "--no-overwrites",
                "--continue",
                "--ignore-errors",
                url
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
            throw new IOException("yt-dlp process failed with exit code: " + exitCode);
        }
    }

    /**
     * Extracts the playlist title from a URL using yt-dlp.
     * Returns a sanitized string suitable for a folder name.
     */
    public static String getPlaylistTitle(String url) {
        try {
            // Use --flat-playlist to avoid processing all video IDs, and --print to get the title
            ProcessBuilder pb = new ProcessBuilder("yt-dlp", "--flat-playlist", "--skip-download",
                    "--print", "%(playlist_title)s", url);

            pb.redirectErrorStream(true);
            Process process = pb.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String title = reader.readLine();
                process.waitFor();

                // Sanitize the title for use as a folder name
                if (title != null) {
                    // Replace illegal characters for filenames/paths with underscore
                    title = title.trim().replaceAll("[\\\\/:*?\"<>|.]", "_");
                    return title.isEmpty() ? "New Download Session" : title;
                }
                return "New Download Session";
            }
        } catch (Exception e) {
            // Fallback in case yt-dlp fails or URL is not a playlist
            return "New Download Session";
        }
    }

    public static boolean isYtDlpAvailable() {
        try {
            ProcessBuilder pb = new ProcessBuilder("yt-dlp", "--version");
            Process process = pb.start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getYtDlpVersion() {
        try {
            ProcessBuilder pb = new ProcessBuilder("yt-dlp", "--version");
            Process process = pb.start();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                return reader.readLine();
            }
        } catch (Exception e) {
            return "Unknown";
        }
    }

    public static boolean isPlaylistUrl(String url) {
        return url != null && (
                url.contains("playlist?list=") ||
                        url.contains("&list=") ||
                        url.contains("/playlists/")
        );
    }
}