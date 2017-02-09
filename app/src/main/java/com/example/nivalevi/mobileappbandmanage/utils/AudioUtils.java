package com.example.nivalevi.mobileappbandmanage.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;

import com.example.nivalevi.mobileappbandmanage.variants.GenericConsts;
import com.example.nivalevi.mobileappbandmanage.MyApp;

import java.io.File;
import java.io.IOException;

/**
 * Created by nivalevi on 09/02/2017.
 */
public class AudioUtils implements MediaPlayer.OnCompletionListener {

    private static final String TAG = AudioUtils.class.getName();

    public static final int AUDIO_OUTPUT_FORMAT = MediaRecorder.OutputFormat.MPEG_4;
    public static final int AUDIO_SOURCE = MediaRecorder.AudioSource.MIC;
    public static final int AUDIO_ENCODER = MediaRecorder.AudioEncoder.AAC;
    public static final int AUDIO_SAMPLING_RATE = 16 * 1000;
    public static final int AUDIO_CHANNELS = 1;
    public static final int AUDIO_ENCODING_BIT_RATE = 24000;

    // singleton instance
    private static AudioUtils instance;

    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;

    private String mPlayBackFile;

    public static AudioUtils getInstance() {
        if (instance == null) {
            instance = new AudioUtils();
        }
        return instance;
    }

    // -------- MediaPlayer methods --------

    public synchronized void prepareAndPlay(String audioFile) {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnCompletionListener(this);
        }

        try {
            // reset if the mediaPlayer is already playing
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }

            mediaPlayer.reset();
            mPlayBackFile = audioFile;
            mediaPlayer.setDataSource(mPlayBackFile);
            mediaPlayer.prepare();

            mediaPlayer.start();
        } catch (IOException e) {
          //  BandManageApplication.getInstance().sendEStackTraceToCrashlytics(e);
        }
    }

    public void seekTo(int position) {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(position);
        }
    }

    public boolean isCurrentFilePlaying(String filePath) {
        return filePath.equalsIgnoreCase(mPlayBackFile);
    }

    public int getCurrentPosition() {
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void pausePlayback() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void resumePlayback(int pausedPosition) {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(pausedPosition);
            mediaPlayer.start();
        }
    }

    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // -------- MediaRecorder methods --------

    public void startRecording(File outputFile) {
        if (mediaRecorder == null) {
            mediaRecorder = new MediaRecorder();
        }
        mediaRecorder.setAudioSource(AUDIO_SOURCE);
        mediaRecorder.setOutputFormat(AUDIO_OUTPUT_FORMAT);
        mediaRecorder.setAudioEncoder(AUDIO_ENCODER);
        mediaRecorder.setAudioChannels(AUDIO_CHANNELS);
        mediaRecorder.setAudioSamplingRate(AUDIO_SAMPLING_RATE);
        mediaRecorder.setAudioEncodingBitRate(AUDIO_ENCODING_BIT_RATE);
        mediaRecorder.setOutputFile(outputFile.getAbsolutePath());
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            //Log.d(TAG, "MediaRecorder - Start");
        } catch (Exception e) {
           // BandManageApplication.getInstance().sendEStackTraceToCrashlytics(e);
            releaseMediaRecorder();
        }
    }

    public void stopRecording() {
        if (mediaRecorder != null) {
            try {
                mediaRecorder.stop();
            } catch (Exception e) {
                //BandManageApplication.getInstance().sendEStackTraceToCrashlytics(e);
            }
            mediaRecorder.reset();   // You can reuse the object by going back to setAudioSource() step
            //Log.d(TAG, "MediaRecorder - Reset");
        }
    }

    public void releaseMediaRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
    }

    public long getDurationFromFilePath(String mFilePath) {
        MediaPlayer mediaPlayer = MediaPlayer.create(MyApp.getApplication(), Uri.parse(mFilePath));
        int duration = 0;
        if (mediaPlayer != null) {
            duration = mediaPlayer.getDuration();
            mediaPlayer.release();
        }
        return duration;
    }

    public static String getAudioUri(String fileName) {
        return GenericConsts.Folders.SOUNDS + "/" + fileName + ".mp3";
    }

    /**
     * creating a new media player every time and releasing it every time
     * @param assetPath - the local asset path
     */
    public void playAudioFromAsset(String assetPath, final MediaPlayer.OnCompletionListener listener) {
        try {

            //prepareAndPlay(assetPath);


            AssetFileDescriptor afd = MyApp.getApplication().getAssets().openFd(AudioUtils.getAudioUri(assetPath));
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            afd.close();

            mediaPlayer.prepare();
            mediaPlayer.setLooping(false);
            mediaPlayer.setVolume(1.0f, 1.0f);

            mediaPlayer.setOnCompletionListener(mediaPlayer1 -> {
                if (listener != null) {
                    listener.onCompletion(mediaPlayer1);
                }
                mediaPlayer1.release();
                mediaPlayer1 = null;
            });

            mediaPlayer.start();


        } catch (Exception e) {
            //BandManageApplication.getInstance().sendEStackTraceToCrashlytics(e);
        }
    }

    public void playAudioFromAssetIfPossible(String assetPath, final MediaPlayer.OnCompletionListener listener) {

        AudioManager am = (AudioManager) MyApp.getApplication().getSystemService(Context.AUDIO_SERVICE);

        switch (am.getRingerMode()) {
            case AudioManager.RINGER_MODE_SILENT:
                //Log.i("MyApp", "Silent mode");
                break;
            case AudioManager.RINGER_MODE_VIBRATE:
                //Log.i("MyApp", "Vibrate mode");
                break;
            case AudioManager.RINGER_MODE_NORMAL:
                //Log.i("MyApp", "Normal mode");
                playAudioFromAsset(assetPath,listener);
                break;
        }

    }

    public void playAudioFromAsset(String assetPath) {
        playAudioFromAssetIfPossible(assetPath, null);
    }

    public boolean isMediaPlayerPlaying() {
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

}
