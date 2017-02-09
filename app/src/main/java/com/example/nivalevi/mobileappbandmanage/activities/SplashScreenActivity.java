package com.example.nivalevi.mobileappbandmanage.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.widget.ImageView;

import com.example.nivalevi.mobileappbandmanage.utils.AudioUtils;
import com.example.nivalevi.mobileappbandmanage.variants.GenericConsts;
import com.example.nivalevi.mobileappbandmanage.MyApp;
import com.example.nivalevi.mobileappbandmanage.R;
import com.example.nivalevi.mobileappbandmanage.utils.SharedPrefSettings;
import com.example.nivalevi.mobileappbandmanage.utils.SystemUiHider;


public class SplashScreenActivity extends AppCompatActivity {

        private final static String TAG = "SplashScreenActivity";

        private static Context context;
        private Animation mZoomInAnimation;

        private boolean isTimeToContinue = false;

        private ImageView contentView;



        private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splashscreen);
            context = MyApp.getApplication();
           /* if (!SharedPrefSettings.UPDATE_VERSION.get().equals(GenericConsts.getAppVersion())){
                MyApp.getApplication().sendPushyTokenToBackendNew();
                SharedPrefSettings.UPDATE_VERSION.set(GenericConsts.getAppVersion());
            }*/

            Intent intent = getIntent();
            if (intent.getAction().contains("VIEW")) {
                finishAndContinue();
                return;
            }

            mZoomInAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_in_splash_screen);
            AnimationUtils.loadAnimation(this, R.anim.zoom_out_splash_screen);

            //final View controlsView = findViewById(R.id.fullscreen_content_controls);
            contentView = (ImageView)findViewById(R.id.fullscreen_content);

            // Set up an instance of SystemUiHider to control the system UI for
            // this activity.
        /*
      The instance of the {@link SystemUiHider} for this activity.
     */
            SystemUiHider mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
            mSystemUiHider.setup();

            mSystemUiHider.hide();


            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) actionBar.hide();

            // set IS_PERMIT_LOCATION
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                        checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                {
                    SharedPrefSettings.IS_PERMIT_LOCATION.set(true);
                }
                else
                {
                    SharedPrefSettings.IS_PERMIT_LOCATION.set(false);
                }
            }

            initStartValues();
        }

        private void initStartValues()
        {
            SharedPrefSettings.ACTIVITY_RECOGNITION_CURRENT.set(-1);

            // set if the device got ble
            if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
                SharedPrefSettings.IS_DEVICE_GOT_FEATURE_BLUETOOTH_LE.set(true);
            }
            else {SharedPrefSettings.IS_DEVICE_GOT_FEATURE_BLUETOOTH_LE.set(false);}
        }

        private void countDownHeartAnimation() {
            //Duration that we want to show the splash activity
            int time = R.integer.splash_display_duration_milliseconds_first_time;//!SharedPrefSettings.IS_FIRST_TIME_LAUNCH_OCCURRED.getPair()? R.integer.splash_display_duration_milliseconds_first_time : R.integer.splash_display_duration_milliseconds_normal;
            if (SharedPrefSettings.IS_FIRST_TIME_LAUNCH_OCCURRED.get()) {
                isTimeToContinue = true;
            }
            else {
                new Handler().postDelayed(() -> isTimeToContinue = true, getResources().getInteger(time));
            }

        }

        private void startAnimating() {
            mZoomInAnimation.setDuration(getResources().getInteger(R.integer.splash_heart_animation));
            mZoomInAnimation.setInterpolator(new CycleInterpolator(2));
            delayedAnimation(100);
        }

        Handler mAnimationHandler = new Handler();
        Runnable mAnimationRunnable = new Runnable() {
            @Override
            public void run() {
                AudioUtils.getInstance().playAudioFromAsset(GenericConsts.SOUND.HEARTBEAT_SOUND);
                contentView.startAnimation(mZoomInAnimation);
                long[] pattern = {192, 10, 208,10};//165, 90, 140,90 //200, 20, 210,20 //165, 10, 220,10
                MyApp.getApplication().vibrate(pattern,-1);

                if (!isTimeToContinue) delayedAnimation(getResources().getInteger(R.integer.animation_cycle_millis));
                else {
                    delayedCloseAnimation(getResources().getInteger(R.integer.animation_cycle_millis));
                }

            }
        };

        /**
         * Schedules a call to startAnimation() in [delay] milliseconds, canceling any
         * previously scheduled calls.
         */
        private void delayedAnimation(int delayMillis) {
            mAnimationHandler.removeCallbacks(mAnimationRunnable);
            mAnimationHandler.postDelayed(mAnimationRunnable, delayMillis);
        }
        private void delayedCloseAnimation(int delayMillis) {
            mAnimationHandler.removeCallbacks(mAnimationRunnable);
            mAnimationHandler.postDelayed(mCloseRunnable, delayMillis);
        }


        Runnable mCloseRunnable;

        {
            mCloseRunnable = this::finishAnimationAndContinueToAnotherActivity;
        }



        @Override
        protected void onPause() {
            super.onPause();
            MyApp.activityPaused();
            mAnimationHandler.removeCallbacks(mAnimationRunnable);
        }

        @Override
        protected void onResume() {
            super.onResume();
            MyApp.activityResumed();
            //in the future this is the time to do some background calculations for the startup
            countDownHeartAnimation();
            startAnimating();
        }

        @Override
        protected void onStop() {
            super.onStop();
            mAnimationHandler.removeCallbacks(mAnimationRunnable);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            finishAnimationAndContinueToAnotherActivity();
        }

        private void finishAnimationAndContinueToAnotherActivity() {
            mAnimationHandler.removeCallbacks(mAnimationRunnable);

            //Mark in the settings if this the first launch of the app
            SharedPrefSettings.IS_FIRST_TIME_LAUNCH_OCCURRED.set(true);
            if (!SharedPrefSettings.IS_FIRST_TIME_LOGIN_FINISHED.get()) {
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            }
            else {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            }
            overridePendingTransition(R.anim.fade_in_slow, R.anim.fade_out_fast);
            finish();
        }

        private void finishAndContinue() {
            if (!SharedPrefSettings.IS_FIRST_TIME_LOGIN_FINISHED.get()) {
                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            }
            else {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            }
            finish();
        }
    }
