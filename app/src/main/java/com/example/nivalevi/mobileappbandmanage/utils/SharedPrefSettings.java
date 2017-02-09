package com.example.nivalevi.mobileappbandmanage.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.nivalevi.mobileappbandmanage.R;
import com.example.nivalevi.mobileappbandmanage.variants.GenericConsts;

import org.apache.commons.lang3.StringUtils;

import java.util.TimeZone;

/**
 * Created by nivalevi on 09/02/2017.
 */

public class SharedPrefSettings {
    private static SharedPreferences storage;
    private static Context context;

    abstract static class Preference<T> {
        protected final String  key;
        protected final T       defaultValue;

        Preference(final String key, final T defaultValue) {
            this.key = key;
            this.defaultValue = defaultValue;
        }

        public abstract T get();
        public abstract boolean set(T newValue);

        public T getAndSet(T newValue) {
            final T oldValue = get();

            set(newValue);
            return oldValue;
        }

        public boolean  clear() {
            return set(defaultValue);
        }

        public boolean isDefined() {
            return storage.contains(key);
        }

        public boolean isDefault() {
            final T value = get();

            return (
                    (value != null && value.equals(defaultValue))
                            || defaultValue == null
            );
        }

        public String   listenKey(android.preference.Preference.OnPreferenceChangeListener listener) {
            return key;
        }
    }

    public static class BooleanPreference extends Preference<Boolean> {
        public BooleanPreference(final String key) {
            super(key, false);
        }

        public BooleanPreference(final String key, final Boolean defaultValue) {
            super(key, defaultValue);
        }

        @Override
        public Boolean get() {
            return storage.getBoolean(key, defaultValue);
        }

        @Override
        public boolean set(Boolean newValue) {
            return storage
                    .edit()
                    .putBoolean(key, newValue)
                    .commit();
        }
    }

    public static class StringPreference extends Preference<String> {
        public StringPreference(final String key) {
            super(key, null);
        }

        public StringPreference(final String key, final String defaultValue) {
            super(key, defaultValue);
        }

        @Override
        public String get() {
           // return storage.getString(key, defaultValue);
            return "";
        }

        @Override
        public boolean set(String newValue) {
            return storage
                    .edit()
                    .putString(key, newValue)
                    .commit();
        }
    }

    public static class LongPreference extends Preference<Long> {
        public LongPreference(final String key) {
            super(key, 0l);
        }

        public LongPreference(final String key, final Long defaultValue) {
            super(key, defaultValue);
        }

        @Override
        public Long get() {
            return storage.getLong(key, defaultValue);
        }

        @Override
        public boolean set(Long newValue) {
            return storage
                    .edit()
                    .putLong(key, newValue)
                    .commit();
        }
    }

    public static class IntegerPreference extends Preference<Integer> {
        public IntegerPreference(final String key) {
            super(key, 0);
        }

        public IntegerPreference(final String key, final Integer defaultValue) {
            super(key, defaultValue);
        }

        @Override
        public Integer get() {
            return storage.getInt(key, defaultValue);
        }

        @Override
        public boolean set(Integer newValue) {
            return storage
                    .edit()
                    .putInt(key, newValue)
                    .commit();
        }
    }

    public static class FloatPreference extends Preference<Float> {
        public FloatPreference(final String key) {
            super(key, 0f);
        }

        public FloatPreference(final String key, final Float defaultValue) {
            super(key, defaultValue);
        }

        @Override
        public Float get() {
            return storage.getFloat(key, defaultValue);
        }

        @Override
        public boolean set(Float newValue) {
            return storage
                    .edit()
                    .putFloat(key, newValue)
                    .commit();
        }
    }

    public static final IntegerPreference ACTIVITY_RECOGNITION_CURRENT = new IntegerPreference("activityRecognitionCurrentInteger");

    public static final BooleanPreference IS_APP_SHORTCUT_CREATED = new BooleanPreference("isAppShortcutCreated");
    public static final BooleanPreference IS_FIRST_TIME_LAUNCH_OCCURRED = new BooleanPreference("isFirstTimeLaunchOccurred");
    public static final BooleanPreference IS_FIRST_TIME_LOGIN_FINISHED = new BooleanPreference("isFirstTimeLoginFinished");
    public static final BooleanPreference IS_LOGGED_IN = new BooleanPreference("isLoggedIn");

    public static final StringPreference UPDATE_VERSION = new StringPreference("update_version","0.0.0");

    public static final BooleanPreference IS_FIRST_TIME_USER_DETAILS_SENT = new BooleanPreference("isUserDetailsSent");

    public static final IntegerPreference UNREAD_MESSAGES = new IntegerPreference("unreadMessages");

    public static final IntegerPreference SENT_MESSAGES_COUNT = new IntegerPreference("sentMessagesCount");
    public static final LongPreference SENT_MESSAGES_COUNT_START_TIME = new LongPreference("sentMessagesCountTime");
    public static final LongPreference MESSAGES_SENDER_COUNTER = new LongPreference("messagesSenderCounter");

    public static final StringPreference GCM_REGISTRATION_ID = new StringPreference("registrationId"); // gcm token
    public static final StringPreference BACKEND_TOKEN_ID = new StringPreference("backend_token"); // caregiver token
    public static final StringPreference BACKEND_PASSWORD = new StringPreference("backend_password"); // caregiver token
    public static final StringPreference BACKEND_EMAIL = new StringPreference("backend_email"); // caregiver token

    //time zones that will update when changed by TimeZoneReceiver
    public static final IntegerPreference TIME_ZONE_RAW_OFFSET = new IntegerPreference("timeZone");
    public static final StringPreference TIME_ZONE = new StringPreference("DeviceTimeZone");
    public static final StringPreference TIME_ZONE_ID = new StringPreference("DeviceTimeZoneId");
    public static final StringPreference CURRENT_DATE = new StringPreference("currentDate");

    public static final StringPreference LINK_TO_INSTITUTION_LOGO = new StringPreference("logoUrl");

    public static StringPreference getLinkToInstitutionLogo() {
        return LINK_TO_INSTITUTION_LOGO;
    }

    //settings
    public static final BooleanPreference IS_DEVICE_SENSOR_ACTIVE = new BooleanPreference("isDeviceSensorActive");

    public static final BooleanPreference IS_ANGEL_SENSOR_ACTIVE = new BooleanPreference("isAngelSensorActive");
    public static final BooleanPreference IS_MICROSOFT_SENSOR_ACTIVE = new BooleanPreference("isMicrosoftSensorActive");

    public static final StringPreference LAST_ANGEL_DEVICE_FOUND_ADDRESS = new StringPreference("lastAngelDeviceFoundAddress");

    public static final BooleanPreference IS_PERMIT_LOCATION = new BooleanPreference("isPermitLocation");
    public static final BooleanPreference IS_DEVICE_GOT_FEATURE_BLUETOOTH_LE = new BooleanPreference("isDeviceGotBluetoothLE");


    // SamiSession details
    public static final StringPreference SAMI_USER_ID = new StringPreference("userId");
    public static final StringPreference SAMI_BANDMANAGE_APP_DEVICE_ID = new StringPreference("deviceId");
    public static final StringPreference SAMI_ACCESS_TOKEN = new StringPreference("accessToken");
    public static final StringPreference SAMI_REFRESH_TOKEN = new StringPreference("refreshToken");
    public static final IntegerPreference SAMI_ACCESS_TOKEN_EXPIRES_AT = new IntegerPreference("accessTokenExpiresAt");
    public static final StringPreference SAMI_SESSION_TOKEN = new StringPreference("samiSessionToken");
    public static final BooleanPreference SAMI_SESSION_IS_EMPTY = new BooleanPreference("samiSessionIsEmpty");

    public static final LongPreference MILLISECONDS_TIME_OF_LAST_URL_LOADING = new LongPreference("millisecondsTimeofLastUrlLoading");



    // continuous mode action reminder
    public static final BooleanPreference IS_CONTINUOUS_MODE = new BooleanPreference("isContinuousMode");
    public static final StringPreference CONTINUOUS_MODE_ACTION_TAG = new StringPreference("continuousModeActionTag");
    public static final StringPreference CONTINUOUS_MODE_ACTION_TEXT = new StringPreference("continuousModeActionText");
    public static final IntegerPreference REMINDER_INTERVAL_INDEX = new IntegerPreference("reminderIntervalIndex");
    public static final LongPreference MILLISECONDS_TIME_OF_REMINDER = new LongPreference("millisecondsTimeofRemider");

    // dashboard
    public static final StringPreference WEARABLE_LAST_STEPS_MESSAGE = new StringPreference("wearableLastStepMessage");
    public static final StringPreference WEARABLE_LAST_BATTERY_MESSAGE = new StringPreference("wearableLastBatteryMessage");
    public static final StringPreference WEARABLE_LAST_HR_MESSAGE = new StringPreference("wearableLastHrMessage");
    public static final StringPreference WEARABLE_LAST_SKINTEMP_MESSAGE = new StringPreference("wearableLastTempMessage");

    public static final LongPreference WEARABLE_LAST_STEPS_MESSAGE_TIMESTAMP = new LongPreference("wearableLastStepMessageTimestamp");
    public static final LongPreference WEARABLE_LAST_BATTERY_MESSAGE_TIMESTAMP = new LongPreference("wearableLastBatteryMessageTimestamp");
    public static final LongPreference WEARABLE_LAST_HR_MESSAGE_TIMESTAMP = new LongPreference("wearableLastHrMessageTimestamp");
    public static final LongPreference WEARABLE_LAST_SKINTEMP_MESSAGE_TIMESTAMP = new LongPreference("wearableLastTempMessageTimestamp");

    public static final StringPreference WEARABLE_MAC_ADDRESS = new StringPreference("wearableMacAddress");
    public static final StringPreference GATEWAY_MAC_ADDRESS = new StringPreference("gatewayMacAddress");


    // ProfileUpdateActivity preferences
    public static final StringPreference PROFILE_NAME = new StringPreference("profileName");
    public static final IntegerPreference PROFILE_HEIGHT = new IntegerPreference("profileHeight");
    public static final IntegerPreference PROFILE_WEIGHT = new IntegerPreference("profileWeight");
    public static final StringPreference PROFILE_CARE_GIVER_MOBILE = new StringPreference("profileCareGiverMobile");
    public static final StringPreference PROFILE_MOBILE = new StringPreference("profileMobile");
    public static final StringPreference SIMBAND_ID = new StringPreference("simbandId");
    public static final StringPreference SPORTRACKER_ID = new StringPreference("sporTrackerId");
    public static final IntegerPreference PROFILE_DATE_MONTH = new IntegerPreference("profileBirthdayMonth");
    public static final IntegerPreference PROFILE_DATE_DAY = new IntegerPreference("profileBirthdayDay");
    public static final IntegerPreference PROFILE_DATE_YEAR = new IntegerPreference("profileBirthdayYear");
    public static final BooleanPreference PROFILE_IS_MALE= new BooleanPreference("profileGenderIsMale");
    public static final BooleanPreference PROFILE_IS_FEMALE= new BooleanPreference("profileGenderIsFemale");
    public static final BooleanPreference PROFILE_IS_SMOKING = new BooleanPreference("profileIsSmoking");
    public static final StringPreference PROFILE_WEIGHT_UNIT= new StringPreference("profileWeightUnit");
    public static final StringPreference PROFILE_HEIGHT_UNIT = new StringPreference("profileHeightUnit");

    public static final IntegerPreference PROFILE_FILL_COMPLETE_PERCENT = new IntegerPreference("profileFillCompletePercent");




    // Diagnoser
    //todo: when add variable, defualt value should be inputed in initDiagnoserDefaultValues()
    public static final IntegerPreference RSSI_LOWER_THRESHOLD = new IntegerPreference("RSSI_LOWER_THRESHOLD");
    public static final IntegerPreference RSSI_UPPER_THRESHOLD = new IntegerPreference("RSSI_UPPER_THRESHOLD");
    public static final BooleanPreference RSSI_LOW_SIGNAL = new BooleanPreference("RSSI_LOW_SIGNAL",false);

    public static final IntegerPreference BATTERY_LOWER_THRESHOLD = new IntegerPreference("BATTERY_LOWER_THRESHOLD");
    public static final IntegerPreference BATTERY_UPPER_THRESHOLD = new IntegerPreference("BATTERY_UPPER_THRESHOLD");

    public static final IntegerPreference OFF_REGIME_UPPER_THRESHOLD = new IntegerPreference("OFF_REGIME_UPPER_THRESHOLD");

    public static final FloatPreference FALL_MAGNITUDE_UPPER_THRESHOLD = new FloatPreference("FALL_MAGNITUDE_UPPER_THRESHOLD");
    public static final FloatPreference MAGNITUDE_VARIANCE_RESTING_THRESHOLD = new FloatPreference("MAGNITUDE_VARIANCE_RESTING_THRESHOLD");
    public static final FloatPreference MAGNITUDE_VARIANCE_AWAKE_THRESHOLD = new FloatPreference("MAGNITUDE_VARIANCE_AWAKE_THRESHOLD");

    public static final FloatPreference MAGNITUDE_MEAN_AWAKE_THRESHOLD = new FloatPreference("MAGNITUDE_MEAN_AWAKE_THRESHOLD");
    public static final FloatPreference MAGNITUDE_MAX_AWAKE_THRESHOLD = new FloatPreference("MAGNITUDE_MAX_AWAKE_THRESHOLD");

    public static final BooleanPreference USE_VARIANCE = new BooleanPreference("USE_VARIANCE");
    public static final BooleanPreference USE_MEAN = new BooleanPreference("USE_MEAN");
    public static final BooleanPreference USE_MAX = new BooleanPreference("USE_MAX");



    public static final IntegerPreference FALL_MAGNITUDE_DELTA_UPPER_THRESHOLD = new IntegerPreference("FALL_MAGNITUDE_DELTA_UPPER_THRESHOLD");
    public static final IntegerPreference FALL_HR_DELTA_UPPER_THRESHOLD = new IntegerPreference("FALL_HR_DELTA_UPPER_THRESHOLD");
    public static final FloatPreference FALL_TEMPERATURE_DELTA_UPPER_THRESHOLD = new FloatPreference("FALL_TEMPERATURE_DELTA_UPPER_THRESHOLD");
    public static final IntegerPreference FALL_STEPS_UPPER_THRESHOLD = new IntegerPreference("FALL_STEPS_UPPER_THRESHOLD");
    public static final IntegerPreference FALL_IRREGULARITIES_UPPER_THRESHOLD = new IntegerPreference("FALL_IRREGULARITIES_UPPER_THRESHOLD");

    public static final IntegerPreference NORMAL_HR = new IntegerPreference("hrNormal");
    public static final IntegerPreference NORMAL_STEPS = new IntegerPreference("stepsNormal");
    public static final IntegerPreference NORMAL_MAGNITUDE = new IntegerPreference("NORMAL_MAGNITUDE");
    public static final FloatPreference NORMAL_TEMP = new FloatPreference("NORMAL_TEMP");

    public static final IntegerPreference CRITICAL_DELTA_HR = new IntegerPreference("hrCriticalDelta");
    public static final IntegerPreference CRITICAL_DELTA_MAGNITUDE = new IntegerPreference("CRITICAL_DELTA_MAGNITUDE");
    public static final FloatPreference CRITICAL_DELTA_TEMP = new FloatPreference("CRITICAL_DELTA_TEMP");

    public static final LongPreference SIGNAL_LIST_EXPIRATION_TIME_LIMIT = new LongPreference("SIGNAL_LIST_EXPIRATION_TIME_LIMIT");
    public static final LongPreference BATTERY_LIST_EXPIRATION_TIME_LIMIT = new LongPreference("BATTERY_LIST_EXPIRATION_TIME_LIMIT");
    public static final LongPreference MAGNITUDE_LIST_EXPIRATION_TIME_LIMIT = new LongPreference("MAGNITUDE_LIST_EXPIRATION_TIME_LIMIT");
    public static final LongPreference FALL_TRIGGERS_LIST_EXPIRATION_TIME_LIMIT = new LongPreference("FALL_TRIGGERS_LIST_EXPIRATION_TIME_LIMIT");
    public static final LongPreference FALL_TRIGGERS_WINDOW_TIME_LIMIT = new LongPreference("FALL_TRIGGERS_WINDOW_TIME_LIMIT");

    public static final LongPreference MAGNITUDE_VARIANCE_LIST_EXPIRATION_TIME_LIMIT = new LongPreference("MAGNITUDE_VARIANCE_LIST_EXPIRATION_TIME_LIMIT");
    public static final LongPreference MAGNITUDE_STATS_RESTING_TIME_LIMIT = new LongPreference("MAGNITUDE_STATS_RESTING_TIME_LIMIT");
    public static final LongPreference MAGNITUDE_MEAN_RESTING_TIME_LIMIT = new LongPreference("MAGNITUDE_MEAN_RESTING_TIME_LIMIT");

    public static final LongPreference TEMPERATURE_LIST_EXPIRATION_TIME_LIMIT = new LongPreference("TEMPERATURE_LIST_EXPIRATION_TIME_LIMIT");
    public static final LongPreference HR_LIST_EXPIRATION_TIME_LIMIT = new LongPreference("HR_LIST_EXPIRATION_TIME_LIMIT");
    public static final IntegerPreference HR_LOWER_THRESHOLD = new IntegerPreference("HR_LOWER_THRESHOLD");
    public static final IntegerPreference HR_UPPER_THRESHOLD = new IntegerPreference("HR_UPPER_THRESHOLD");


    public static void initDiagnoserDefaultValues() {
        // set default values
        //if (RSSI_LOWER_THRESHOLD.getPair() == 0)
        RSSI_LOWER_THRESHOLD.set(-90);
        //if (RSSI_UPPER_THRESHOLD.getPair() == 0)
        RSSI_UPPER_THRESHOLD.set(-75);
        //if (BATTERY_LOWER_THRESHOLD.getPair() == 0)
        BATTERY_LOWER_THRESHOLD.set(5);
        //if (BATTERY_UPPER_THRESHOLD.getPair() == 0)
        BATTERY_UPPER_THRESHOLD.set(20);
        //if (OFF_REGIME_UPPER_THRESHOLD.getPair() == 0)
        OFF_REGIME_UPPER_THRESHOLD.set(3);
        //if (FALL_IRREGULARITIES_UPPER_THRESHOLD.getPair() == 0)
        FALL_IRREGULARITIES_UPPER_THRESHOLD.set(2);
        //if (FALL_MAGNITUDE_DELTA_UPPER_THRESHOLD.getPair() == 0)
        FALL_MAGNITUDE_DELTA_UPPER_THRESHOLD.set(1);

        //if (FALL_HR_DELTA_UPPER_THRESHOLD.getPair() == 0)
        FALL_HR_DELTA_UPPER_THRESHOLD.set(6);
        //if (FALL_TEMPERATURE_DELTA_UPPER_THRESHOLD.getPair() == 0)
        FALL_TEMPERATURE_DELTA_UPPER_THRESHOLD.set(0.15f);


        // if (FALL_MAGNITUDE_UPPER_THRESHOLD.getPair() == 0)
        FALL_MAGNITUDE_UPPER_THRESHOLD.set(4f);

        HR_LOWER_THRESHOLD.set(40);
        HR_UPPER_THRESHOLD.set(100);
        MAGNITUDE_VARIANCE_RESTING_THRESHOLD.set(0.0008f);//0.0005f
        MAGNITUDE_VARIANCE_AWAKE_THRESHOLD.set(0.005f);//0.001f
        //if (FALL_STEPS_UPPER_THRESHOLD.getPair() == 0)
        FALL_STEPS_UPPER_THRESHOLD.set(2);

        NORMAL_HR.set(65);

        CRITICAL_DELTA_HR.set(20);

        NORMAL_MAGNITUDE.set(80);

        NORMAL_TEMP.set(36.2f);

        CRITICAL_DELTA_MAGNITUDE.set(10);

        CRITICAL_DELTA_TEMP.set(1.5f);

        NORMAL_STEPS.set(1000);

        SIGNAL_LIST_EXPIRATION_TIME_LIMIT.set(GenericConsts.MILLIS_IN_ONE_MINUTE * 2);

        BATTERY_LIST_EXPIRATION_TIME_LIMIT.set(GenericConsts.MILLIS_IN_ONE_MINUTE);

        MAGNITUDE_LIST_EXPIRATION_TIME_LIMIT.set(GenericConsts.MILLIS_IN_ONE_SECOND * 2);
        MAGNITUDE_VARIANCE_LIST_EXPIRATION_TIME_LIMIT.set(GenericConsts.MILLIS_IN_ONE_MINUTE + GenericConsts.MILLIS_IN_ONE_SECOND * 5);
        MAGNITUDE_STATS_RESTING_TIME_LIMIT.set(GenericConsts.MILLIS_IN_ONE_SECOND * 10);
        MAGNITUDE_MEAN_RESTING_TIME_LIMIT.set(GenericConsts.MILLIS_IN_ONE_SECOND * 10);

        FALL_TRIGGERS_LIST_EXPIRATION_TIME_LIMIT.set(GenericConsts.MILLIS_IN_ONE_MINUTE * 15);

        FALL_TRIGGERS_WINDOW_TIME_LIMIT.set(GenericConsts.MILLIS_IN_ONE_MINUTE);

        TEMPERATURE_LIST_EXPIRATION_TIME_LIMIT.set(GenericConsts.MILLIS_IN_ONE_MINUTE * 2);

        HR_LIST_EXPIRATION_TIME_LIMIT.set(GenericConsts.MILLIS_IN_ONE_MINUTE);
    }


    // sunmmary
    public static final LongPreference LAST_SUMMARY_MESSAGE_DATE_MILLIS = new LongPreference("lastSummaryMessageDateMillis");


    public static void init(Context context) {
        SharedPrefSettings.context = context;
        storage = PreferenceManager.getDefaultSharedPreferences(SharedPrefSettings.context);

        // set default values
        if (StringUtils.isBlank(PROFILE_HEIGHT_UNIT.get()))
            PROFILE_HEIGHT_UNIT.set(context.getResources().getString(R.string.centimeter));
        if (StringUtils.isBlank(PROFILE_WEIGHT_UNIT.get()))
            PROFILE_WEIGHT_UNIT.set(context.getResources().getString(R.string.kilograms));

        if (StringUtils.isBlank(WEARABLE_LAST_STEPS_MESSAGE.get()))
            WEARABLE_LAST_STEPS_MESSAGE.set(context.getResources().getString(R.string.default_empty_gauge_value));
        if (StringUtils.isBlank(WEARABLE_LAST_BATTERY_MESSAGE.get()))
            WEARABLE_LAST_BATTERY_MESSAGE.set(context.getResources().getString(R.string.default_empty_gauge_value));
        if (StringUtils.isBlank(WEARABLE_LAST_HR_MESSAGE.get()))
            WEARABLE_LAST_HR_MESSAGE.set(context.getResources().getString(R.string.default_empty_gauge_value));
        if (StringUtils.isBlank(WEARABLE_LAST_SKINTEMP_MESSAGE.get()))
            WEARABLE_LAST_SKINTEMP_MESSAGE.set(context.getResources().getString(R.string.default_empty_gauge_value));


        TimeZone timezone = TimeZone.getDefault();
        SharedPrefSettings.TIME_ZONE_RAW_OFFSET.set(timezone.getRawOffset());
    }
}
