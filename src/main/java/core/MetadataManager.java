package core;

import org.jaudiotagger.audio.*;
import org.jaudiotagger.tag.*;
import org.jaudiotagger.tag.images.*;

import java.io.*;
import java.util.*;

public class MetadataManager {

    private static final String[] IMAGE_EXTENSIONS = {".jpg", ".jpeg", ".png", ".webp"};

    public static String embedAlbumArt(File audioFile, File imageFile) throws Exception {
        AudioFile file = AudioFileIO.read(audioFile);
        Tag tag = file.getTagOrCreateAndSetDefault();

        String title = Optional.ofNullable(tag.getFirst(FieldKey.TITLE)).orElse("N/A");
        String artist = Optional.ofNullable(tag.getFirst(FieldKey.ARTIST)).orElse("N/A");
        String album = Optional.ofNullable(tag.getFirst(FieldKey.ALBUM)).orElse("N/A");
        String track = Optional.ofNullable(tag.getFirst(FieldKey.TRACK)).orElse("N/A");

        String metadataSummary = String.format("Title: \"%s\", Artist: \"%s\", Album: \"%s\", Track: %s", title, artist, album, track);

        tag.deleteArtworkField();

        Artwork artwork = ArtworkFactory.createArtworkFromFile(imageFile);
        tag.setField(artwork);

        AudioFileIO.write(file);

        // Try to delete the image file, but don't fail if we can't
        if (!imageFile.delete()) {
            System.err.println("Warning: Could not delete image file: " + imageFile.getName());
        }

        return metadataSummary;
    }

    public static File findAlbumArt(File audioFile) {
        String baseName = audioFile.getName().replaceFirst("[.][^.]+$", "");
        File parentDir = audioFile.getParentFile();

        for (String ext : IMAGE_EXTENSIONS) {
            File imageFile = new File(parentDir, baseName + ext);
            if (imageFile.exists()) {
                return imageFile;
            }
        }
        return null;
    }
}