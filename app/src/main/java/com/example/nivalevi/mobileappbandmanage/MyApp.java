package com.example.nivalevi.mobileappbandmanage;

import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;

import com.example.nivalevi.mobileappbandmanage.utils.SharedPrefSettings;

/**
 * Created by nivalevi on 08/02/2017.
 */

public class MyApp extends Application {

    private static MyApp s_instance;
    private Vibrator mVibrator;



    public static MyApp getApplication() {
        if (s_instance == null)
            s_instance = new MyApp();
        return s_instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        s_instance = this;
        SharedPrefSettings.init(this);
        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

    }

    // vibrator functions
    public void vibrate(long millis) {
        long[] pattern = {0, millis};
        vibrate(pattern, -1);
    }

    public void vibrate(long[] pattern, int repeatTimes) {

        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        switch (am.getRingerMode()) {
            case AudioManager.RINGER_MODE_SILENT:
                break;
           default: mVibrator.vibrate(pattern, repeatTimes);
        }
    }

    public static void activityResumed() {
    }

    public static void activityPaused() {
    }

    public void sendPushyTokenToBackendNew() {
    }
}
