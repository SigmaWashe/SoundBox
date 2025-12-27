package core;

/**
 *
 * @author SigmaWashe
 */
import ws.schild.jave.*;
import ws.schild.jave.encode.*;

import java.io.*;

public class AudioConverter {

    public static final String MP3_FORMAT = ".mp3";
    public static final String MP3_CODEC = "libmp3lame";
    
    public static final String WAV_FORMAT = ".wav";
    public static final String WAV_CODEC = "pcm_s16le";
    
    private static final String M4A_FORMAT = ".m4a";
    private static final String M4A_CODEC = "aac";

    /**
     * Converts an audio file to M4A format
     * Preserves metadata from the source file
     */
    public static void convertToM4A(File source, File target, int bitRate) throws Exception {
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec(M4A_CODEC);
        audio.setBitRate(bitRate);
        audio.setChannels(6);
        audio.setSamplingRate(48000);

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOutputFormat(M4A_FORMAT);
        attrs.setAudioAttributes(audio);

        // Preserve metadata from source file
        attrs.setMapMetaData(true);

        Encoder encoder = new Encoder();
        MultimediaObject sourceObject = new MultimediaObject(source);
        encoder.encode(sourceObject, target, attrs);
    }

    public static void convertToMP3(File source, File target, int bitRate) throws Exception {
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec(MP3_CODEC); // MP3 codec
        audio.setBitRate(bitRate);
        audio.setChannels(2);
        audio.setSamplingRate(48000);

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOutputFormat(MP3_FORMAT);
        attrs.setAudioAttributes(audio);
        attrs.setMapMetaData(true);

        Encoder encoder = new Encoder();
        MultimediaObject sourceObject = new MultimediaObject(source);
        encoder.encode(sourceObject, target, attrs);
    }
    
    public static void convertToWAV(File source, File target, int bitRate) throws Exception {
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec(WAV_CODEC);
        audio.setBitRate(bitRate);
        audio.setChannels(2);
        audio.setSamplingRate(48000);

        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOutputFormat(MP3_FORMAT);
        attrs.setAudioAttributes(audio);
        attrs.setMapMetaData(true);

        Encoder encoder = new Encoder();
        MultimediaObject sourceObject = new MultimediaObject(source);
        encoder.encode(sourceObject, target, attrs);
    }
    
}