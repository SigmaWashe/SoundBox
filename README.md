# SOUND BOX #

Below Includes A Full Guide For Sound Box

## Table Of Contents ##
1. [About](#about)
1. [Features](#features)
1. [Screenshots](#screenshots)
1. [Installation](#installation)
1. [How To Use](#how-to-use)
1. [License](#license)
1. [Contact And Feedback](#content-and-feedback)

## About ##

Sound Box is a JAVA based application that allows users to download audio and music from YouTube and Spotify. It provides a way for users to manage downloads as playlists or individual tracks.


## Features ##

### Core Functionality
- **YouTube Downloads**: Download individual videos or entire playlists as audio files
- **Spotify Downloads**: Download tracks, playlists, and albums 
- **Multiple Audio Formats**: Support for MP3, M4A, and WAV
- **Audio Quality**: Downloads at 320kbps bitrate
- **Metadata Embedding**: Automatically embeds artist, album, and track information
- **Thumbnail Embedding**: Includes album artwork in downloaded files
- **Lyrics Integration**: Downloads lyrics when available (Spotify)

### User Interface
- **Theme Support**: Light and Dark themes
- **Real-time Progress**: Visual progress bars and detailed logging
- **Persistent Settings**: Remembers your preferences and window layout
- **Download Statistics**: Track total downloads per platform

### Additional Features
- **Custom Output Directories**: Choose where to save your downloads
- **Automatic Playlist Organization**: Creates folders for playlists automatically
- **Cancellable Downloads**: Stop downloads in progress
- **URL Detection**: Automatically detects playlist URLs
- **Settings Management**: Save, reset, or export your settings

## Screenshots ##

### Dark Theme ###

![Youtube Tab](RESOURCES\DarkTheme\youtubeTab.png)

![Spotify Tab](RESOURCES\DarkTheme\spotify.png)

![Settings Tab](RESOURCES\DarkTheme\settings.png)

### Light Theme ###

![Youtube Tab](RESOURCES\LightTheme\youtubeTab.png)
![Spotify Tab](RESOURCES\LightTheme\spotify.png)
![Settings Tab](RESOURCES\LightTheme\settings.png)

### Installation ###
Before installing Sound Box, you need to have the following installed on your system:

1. **JAVA 17 or higher**
   - Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)

2. **Python 3.8 or higher**
   - Download from [Python](https://www.python.org/downloads/)
   - ⚠️ **IMPORTANT**: Check "Add Python to PATH" during installation!
   - **windows**: `winget search python` and download the latest version using `winget install Python.Python[version]`
   - **macOS**: `brew install python3` or download from python.org
   - **Linux**: `sudo apt install python3 python3-pip`

3.  **yt-dlp** (Required for both YouTube and Spotify downloads)
```bash
   # Windows (using winget)
   winget install yt-dlp
   
   # macOS (using Homebrew)
   brew install yt-dlp
   
   # Linux (Ubuntu/Debian)
   sudo apt install yt-dlp
   
   # Alternative: pip install
   pip install yt-dlp
```
4.  **spotdl** (Required for Spotify downloads)
```bash
   pip install spotdl
```
5. **FFmpeg** (Required for audio conversion)
   - **Windows**: `winget install ffmpeg`
   - **macOS**: `brew install ffmpeg`
   - **Linux**: `sudo apt install ffmpeg`

6. Open 'SoundBox.jar' to start the app.
  --If you encounter prompts for which application to use, ensure that **JAVA 17 or higher** is installed.  

### Youtube Downloads ###
1. **Navigate to the YouTube tab**
2. **Enter a YouTube URL**:
   - Single video: `https://www.youtube.com/watch?v=VIDEO_ID`
   - Playlist: `https://www.youtube.com/playlist?list=PLAYLIST_ID`
3. **Configure options**:
   - Select output directory
   - Check "Download entire playlist" for playlist URLs
   - Choose audio format (MP3, M4A, or WAV)
4. **Click DOWNLOAD** and monitor progress in the log panel
![Youtube Downloads](RESOURCES/youtubeDownload.mp4)

### Spotify Downloads ###

1. **Navigate to the Spotify tab**
2. **Enter a Spotify URL**:
   - Track: `https://open.spotify.com/track/TRACK_ID`
   - Playlist: `https://open.spotify.com/playlist/PLAYLIST_ID`
   - Album: `https://open.spotify.com/album/ALBUM_ID`
3. **Configure options**:
   - Select output directory (or leave blank for default: `~/Music/Spotify`)
   - Check "Download entire playlist" for playlists/albums
   - Choose audio format (MP3, M4A, or WAV)
4. **Click DOWNLOAD** and monitor progress in the log panel

### Settings ###

Access the Settings tab to:
- **Switch themes**: Choose between Light and Dark modes
- **View statistics**: See your total downloads by platform
- **Manage settings**:
  - Save current configuration
  - Restore default settings
  - Open settings folder

- **Playlist Auto-detection**: The app automatically detects playlist URLs and organizes downloads into named folders
- **Cancel Anytime**: Click the CANCEL button to stop a download in progress
- **Custom Directories**: Set different output directories for YouTube and Spotify
- **Format Selection**: M4A generally provides the best quality-to-size ratio
- **Settings Persistence**: Your preferences are automatically saved to `~/soundbox-settings.json`

## License ##

This project is licensed under the [MIT License](LICENSE).
You are free to use, modify, and distribute this software with proper credit.
**Note**: This application is for personal use only. Please respect copyright laws and the terms of service of YouTube and Spotify. Only download content you have the right to download.

## Contact And Feedback ##

- **Author**: Chinasa Nwosu
- **GitHub**: [Your GitHub Profile](https://github.com/SigmaWashe)
- **Issues**: [Report bugs or request features](https://github.com/SigmaWashe/SoundBox/issues)
- **Email**: chinasanwosu0@gmail.com

### Contributing ###

Contributions are welcome! Please feel free to submit a Pull Request.

### Acknowledgments ###

- [FlatLaf](https://www.formdev.com/flatlaf/) - Modern look and feel for Swing
- [yt-dlp](https://github.com/yt-dlp/yt-dlp) - YouTube download engine
- [spotdl](https://github.com/spotDL/spotify-downloader) - Spotify download engine
- [FFmpeg](https://ffmpeg.org/) - Audio conversion