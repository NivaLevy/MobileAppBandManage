package com.example.nivalevi.mobileappbandmanage.variants;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.nivalevi.mobileappbandmanage.MyApp;

/**
 * Created by nivalevi on 09/02/2017.
 */

public class GenericConsts {
    public static class SOUND {
        public static final String HEARTBEAT_SOUND = "heartbeat";
        public static final String BEEP_SOUND = "beep-08b";
        public static final String ALERT_SOUND = "notify";
    }

    public static final class Folders {
        public static final String SOUNDS = "sounds"; // Sub folder in the assets folder
    }

    public enum HTTP_MSG_TYPE {
        GATEWAY_DATA, DATA , FALL_TRIGGER , EVENT_REPORT, HEALTH_CHECK, DAILY_MESSAGE_COUNT, ABNORMAL_LOW_HR, ABNORMAL_HR_JUMP, FALL_SUSPICIOUS_EVENT
    }

    public enum FILTER_TYPE {
        STATUS, BY, ACTION;
    }

    public static String getAppVersion() {
        Context context = MyApp.getApplication();
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // should never happen
            //throw new RuntimeException("Could not getPair package name: " + e);
            return null;
        }
    }
    public static final int SECONDS_IN_ONE_MINUTE = 60;
    public static final int DAYS_TO_REFER_WEEKLY_SUMMARY = 7;
    public static final long MILLIS_IN_ONE_SECOND = 1000;
    public static final long HOURS_IN_ONE_DAY = 24;
    public static final long MINUTES_IN_ONE_HOUR = 60;
    public static final long MILLIS_IN_ONE_MINUTE = MINUTES_IN_ONE_HOUR * MILLIS_IN_ONE_SECOND;
    public static final long MILLIS_IN_ONE_HOUR = MINUTES_IN_ONE_HOUR * MILLIS_IN_ONE_MINUTE;
    public static final long MILLIS_IN_ONE_DAY = HOURS_IN_ONE_DAY * MILLIS_IN_ONE_HOUR;
    public static final long MILLIS_IN_ONE_WEEK = 7 * MILLIS_IN_ONE_DAY;
    public static final long MILLIS_IN_ONE_MONTH = 4 * MILLIS_IN_ONE_WEEK;


    public static final long MIN_TIME_INTERVAL_TO_ANNOUNCE_WEARABLE_DISPLAYED_DATA_OUTDATED = MILLIS_IN_ONE_MINUTE;


    public static final class AddEventMenu {
        public static final int NO_CHOICE = 0;
        public static final int FALL = 1;
        public static final int FLU = 2;
        public static final int DEHYDRATION = 3;
        public static final int ABNORMALITY = 4;
    }

    public static final class ActionsNameTagsOfXML {
        public static final String DINING = "Dining";
        public static final String DRIVING = "Driving";
        public static final String DRINKING_COFFEE = "DrinkingCoffee";
        public static final String BOWEL_MOVEMENT = "BowelMovement";
        public static final String SMOKING = "Smoking";
        public static final String FATIGUED = "Fatigued";
        public static final String STRESSED = "Stressed";
        public static final String RUNNING = "Running";
        public static final String WALKING = "Walking";
        public static final String EXERCISING = "Exercising";
        public static final String OFFLINE = "Offline";
        public static final String FLU = "Flu";
        public static final String FALL = "Fall";
        public static final String DEHYDRATION = "Dehydration";
        public static final String OFF_REGIME = "OffRegime";
    }

    public static final class PushNotificationMessageTitles {
        public static final String CARECEIVER_DIAGNOSE = "event_detected";
        public static final String DELETE_CARE_RECEIVER = "delete_care_receiver";
        public static final String NEW_CARE_RECEIVER = "new_care_receiver";
        public static final String DAILY_UPDATE_REPORT = "daily_update_report";
        public static final String SIMPLE_NOTIFICATION = "simpleNotificationMessage";
        public static final String WEEKLY_REPORT = "weekly_report";
    }

    public static final class SAMIDeviceEvent {
        public static final String SAMI_FIELDS_GROUP_NAME = "deviceEvent";
        public static final String DEVICE_EVENT_NAME = "deviceEventName";
    }
    public static final class SAMIEventActions {
        public static final String EVENT_NAME = "eventName";
        public static final String INPUT_TEXT_FIELD = "inputTextFieldEventReportingScreen";
        public static final String SAMI_FIELDS_GROUP_NAME = "userEventReport";
        public static final String EVENT_OCCURRENCE_TIME = "eventOccurrenceTime"; // long -- time in millis since epoch
        public static final String EVENT_REPORTING_METHOD = "eventReportingMethod"; // string --  automatic/manual
        public static final String EVENT_HANDLE_STATE = "eventStatus"; // string -- accepted/declined/unsure
    }
    public static final class SAMIMobileSensorsData {
        public static final String SAMI_MOBILE_SENSORS_FIELDS_GROUP_NAME = "androidMobileSensorsData";

        public static final String GRAVITY_X_FIELD_NAME = "gravityXs";
        public static final String GRAVITY_Y_FIELD_NAME = "gravityYs";
        public static final String GRAVITY_Z_FIELD_NAME = "gravityZs";
        public static final String GRAVITY_TIME_VALUES_FIELD_NAME = "gravityTimes";
        public static final String GRAVITY_ACCURACY_VALUES_FIELD_NAME = "gravityAccuracy";

        public static final String ACCELEROMETER_X_VALUES_FIELD_NAME = "accelerometerXs";
        public static final String ACCELEROMETER_Y_VALUES_FIELD_NAME = "accelerometerYs";
        public static final String ACCELEROMETER_Z_VALUES_FIELD_NAME = "accelerometerZs";
        public static final String ACCELEROMETER_TIME_VALUES_FIELD_NAME = "accelerometerTimes";
        public static final String ACCELEROMETER_ACCURACY_VALUES_FIELD_NAME = "accelerometerAccuracy";


        public static final String ACC_MAGNITUDE_FIELD_NAME = "accMagnitude";
        public static final String ACC_MAGNITUDE_TIME_VALUES_FIELD_NAME = "accMagnitudeTimes";
        public static final String ACC_MAGNITUDE_ACCURACY_VALUES_FIELD_NAME = "accMagnitudeAccuracy";

        public static final String TEMPERATURE_FIELD_NAME = "temperature";
        public static final String TEMPERATURE_TIME_VALUES_FIELD_NAME = "temperatureTimes";
        public static final String TEMPERATURE_ACCURACY_VALUES_FIELD_NAME = "temperatureAccuracy";

        public static final String AMBIENT_TEMPERATURE_FIELD_NAME = "ambientTemperature";
        public static final String AMBIENT_TEMPERATURE_TIME_VALUES_FIELD_NAME = "ambientTemperatureTimes";
        public static final String AMBIENT_TEMPERATURE_ACCURACY_VALUES_FIELD_NAME = "ambientTemperatureAccuracy";

        public static final String AMBIENT_LIGHT_FIELD_NAME = "ambientLight";
        public static final String AMBIENT_LIGHT_TIME_VALUES_FIELD_NAME = "ambientLightTimes";
        public static final String AMBIENT_LIGHT_ACCURACY_VALUES_FIELD_NAME = "ambientLightAccuracy";

        public static final String AMBIENT_AIR_HUMIDITY_FIELD_NAME = "ambientAirHumidity";
        public static final String AMBIENT_AIR_HUMIDITY_TIME_VALUES_FIELD_NAME = "ambientAirHumidityTimes";
        public static final String AMBIENT_AIR_HUMIDITY_ACCURACY_VALUES_FIELD_NAME = "ambientAirHumidityAccuracy";

        public static final String CENTIMETERS_PROXIMITY_FIELD_NAME = "centimetersProximity";
        public static final String CENTIMETERS_PROXIMITY_TIME_VALUES_FIELD_NAME = "centimetersProximityTimes";
        public static final String CENTIMETERS_PROXIMITY_ACCURACY_VALUES_FIELD_NAME = "centimetersProximityAccuracy";

        public static final String ATMOSPHERIC_PRESSURE_FIELD_NAME = "atmosphericPressure";
        public static final String ATMOSPHERIC_PRESSURE_TIME_VALUES_FIELD_NAME = "atmosphericPressureTimes";
        public static final String ATMOSPHERIC_PRESSURE_ACCURACY_VALUES_FIELD_NAME = "atmosphericPressureAccuracy";

        public static final String ROTATION_VECTOR_X_FIELD_NAME = "rotationX";
        public static final String ROTATION_VECTOR_Y_FIELD_NAME = "rotationY";
        public static final String ROTATION_VECTOR_Z_FIELD_NAME = "rotationZ";
        public static final String ROTATION_COS_FIELD_NAME = "rotationCos";
        public static final String ROTATION_HEADING_ACCURACY_FIELD_NAME = "rotationHeadingAccuracy";
        public static final String ROTATION_TIME_VALUES_FIELD_NAME = "rotationTimes";
        public static final String ROTATION_ACCURACY_VALUES_FIELD_NAME = "rotationAccuracy";

        public static final String ORIENTATION_AZIMUTH_FIELD_NAME = "orientationAzimuth";
        public static final String ORIENTATION_PITCH_FIELD_NAME = "orientationPitch";
        public static final String ORIENTATION_ROLL_FIELD_NAME = "orientationRoll";
        public static final String ORIENTATION_TIME_VALUES_FIELD_NAME = "orientationTimes";
        public static final String ORIENTATION_ACCURACY_VALUES_FIELD_NAME = "orientationAccuracy";

        public static final String MAGNETIC_FIELD_VECTOR_X_FIELD_NAME = "magneticFieldX";
        public static final String MAGNETIC_FIELD_VECTOR_Y_FIELD_NAME = "magneticFieldY";
        public static final String MAGNETIC_FIELD_VECTOR_Z_FIELD_NAME = "magneticFieldZ";
        public static final String MAGNETIC_FIELD_TIME_VALUES_FIELD_NAME = "magneticFieldTimes";
        public static final String MAGNETIC_FIELD_ACCURACY_VALUES_FIELD_NAME = "magneticFieldAccuracy";

        public static final String GYRO_X_FIELD_NAME = "gyroscopeX";
        public static final String GYRO_Y_FIELD_NAME = "gyroscopeY";
        public static final String GYRO_Z_FIELD_NAME = "gyroscopeZ";
        public static final String GYRO_TIME_VALUES_FIELD_NAME = "gyroscopeTimes";
        public static final String GYRO_ACCURACY_VALUES_FIELD_NAME = "gyroscopeAccuracy";

        public static final String LOCATION_ALTITUDE_FIELD_NAME = "locationAltitude";
        public static final String LOCATION_LATITUDE = "locationLatitude";
        public static final String LOCATION_LONGITUDE = "locationLongitude";
        public static final String LOCATION_TIME_VALUES_FIELD_NAME = "locationTimes";
        public static final String LOCATION_ACCURACY_VALUES_FIELD_NAME = "locationAccuracy";

    }

    public static final class SAMIMicrosoftBandDataTags {
        public static final String SAMI_MICROSOFT_BAND_FIELDS_GROUP_NAME = "microsoftBandData";
        public static final String BOX_FIELDS_GROUP_NAME = "boxData";


        public static final String UV = "uv";
        public static final String SKIN_TEMP = "skinTemp";
        public static final String RR_INTERVAL = "rr";
        public static final String PEDOMETER = "steps";
        public static final String HEART_RATE = "hr";

        public static final String RESTING = "rest";
        public static final String FALL_TRIGGER = "fall";
        public static final String ABNORMAL_HR_TRIGGER = "abnormalHr";
        public static final String GW_VERSION = "ver";
        public static final String REBOOT = "rbt";

        public static final String HEART_RATE_QUALITY = "hrQ";
        public static final String GYRO_VELOCITY_Z = "gyroZ";
        public static final String GYRO_VELOCITY_Y = "gyroY";
        public static final String GYRO_VELOCITY_X = "gyroX";
        public static final String GSR = "gsr";
        public static final String DISTANCE_CURR_MOTION = "motion";
        public static final String DISTANCE_TOTAL_DISTANCE = "distance";
        public static final String DISTANCE_CURR_SPEED = "speed";
        public static final String BARO_TEMP = "barometerTemp";
        public static final String BARO_AIR_PRESSURE = "barometerAP";
        public static final String CONTACT_STATE = "contact";
        public static final String CALORIES_STATE = "cal";
        public static final String AMBIENT_LIGHT_BRIGHTNESS = "brightness";
        public static final String ACCELE_X = "accX";
        public static final String ACCELE_Y = "accY";
        public static final String ACCELE_Z = "accZ";
        public static final String ACC_MAGNITUDE = "accMag";
        public static final String ALTIMETER_RATE = "altimeter";

        public static final String UV_TIME_STAMP = "uvTimes";
        public static final String SKIN_TEMP_TIME_STAMP = "skinTempTimes";
        public static final String RR_INTERVAL_TIME_STAMP = "rrTimes";
        public static final String PEDOMETER_TIME_STAMP = "stepsTimes";
        public static final String HEART_RATE_TIME_STAMP = "hrTimes";

        public static final String RESTING_TIME_STAMP = "restTimes";
        public static final String FALL_TRIGGER_TIME_STAMP = "fallTimes";

        public static final String ABNORMAL_HR_TRIGGER_TIME_STAMP = "abnormalHrTimes";
        public static final String GW_VERSION_TIME_STAMP = "verTimes";
        public static final String REBOOT_TIME_STAMP = "rbtTimes";

        public static final String GYRO_VELOCITY_TIME_STAMP = "gyroTimes";
        public static final String GSR_TIME_STAMP = "gsrTimes";
        public static final String DISTANCE_CURR_MOTION_TIME_STAMP = "motionTimes";
        public static final String BARO_TIME_STAMP = "barometerTimes";
        public static final String AMBIENT_LIGHT_BRIGHTNESS_TIME_STAMP = "brightnessTimes";
        public static final String ACC_TIME_STAMP = "accTimes";
        public static final String ALTIMETER_RATE_TIME_STEMP = "altimeterTimes";
        public static final String CONTACT_STATE_TIME_STAMP = "contactTimes";
        public static final String CALORIES_STATE_TIME_STAMP = "calTimes";
    }
    public static final class SAMIProfileUpdate {
        public static final String SAMI_FIELDS_GROUP_NAME = "userProfileData";

        public static final String PROFILE_FIELD_TITLE_HEIGHT = "profileHeight";
        public static final String PROFILE_FIELD_TITLE_WEIGHT = "profileWeight";
        public static final String PROFILE_FIELD_TITLE_EMERGENCY_MOBILE= "profileCareGiverMobile";
        public static final String PROFILE_FIELD_TITLE_BIRTHDAY_YEAR= "profileBirthdayYear";
        public static final String PROFILE_FIELD_TITLE_BIRTHDAY_MONTH = "profileBirthdayMonth";
        public static final String PROFILE_FIELD_TITLE_BIRTHDAY_DAY = "profileBirthdayDay";
        public static final String PROFILE_FIELD_TITLE_IS_MALE = "profileGenderIsMale";
        public static final String PROFILE_FIELD_TITLE_MOBILE = "profileMobile";
        public static final String PROFILE_FIELD_TITLE_SPORTRACKER_ID = "sporTrackerDeviceID";
        public static final String PROFILE_FIELD_TITLE_SIMBAND_ID = "simbandDeviceID";
        public static final String PROFILE_FIELD_TITLE_NAME = "profileName";
        public static final String PROFILE_FIELD_TITLE_IS_SMOKING= "profileIsSmoking";
    }

    public static final class ProfileUpdateHeightUnits {
        public static final double INCH_TO_CM_RATIO = 2.54;
        public static final double FEET_TO_CM_RATIO = 30.48;
        public static final double CM_TO_INCH_RATIO = 0.393700787;

        public static final double CM_TO_FEET_RATIO = 0.0328;
        public static final double FEET_TO_INCHES_RATIO = 12;
    }

    public static final class ProfileUpdateWeightUnits {
        public static final double KG_TO_LBS_RATIO = 2.20462262;
        public static final double LBS_TO_KG_RATIO = 0.453592;
    }

    public static final class BackEndpoints {
        public static final String CAREGIVER_UPDATE = "mobile_validations/caregiver_updated";
        public static final String CAREGIVER_DIAGNOSE = "mobile_validations";
    }
    public static final class BackendHeaders {
        public static final String EMAIL_HEADER = "X-API-EMAIL";
        public static final String TOKEN_HEADER = "X-API-TOKEN";
    }

    public static final class NotificationsTypesIDs {
        public static final int SUMMARY_REPORT = 100000;
        public static final int CONTINUOUS_ALARM = 200000;
        public static final int GENERIC = 400000;
        public static final int WEARABLE_LOW_BATTERY = 300000;
        public static final int WEARABLE_LOW_SIGNAL = 500000;
        public static final int OFF_REGIME = 600000;
        public static final int FALL = 700000;
    }
    public static final class PermissionCallbackIDs {
        public static final int REQUEST_CODE_LOCATION_PERMISSIONS_DEVICE_SENSORS = 1;
        public static final int REQUEST_CODE_LOCATION_PERMISSIONS_ANGEL_SENSORS = 2;
    }
    public static final class PendingIntentsIDs {
        public static final int startSummaryDashBoardPendingIntentId = 7000123;
        public static final int activityPendingIntentCode = 576219076;
        public static final int wakefulPendingIntentCode = 576233336;
        public static final int dailyPendingIntentCode = 547807035;
    }

    public static class JobManagerGroupsType {
        public static final String bandmanageBackendServerNetworkCallGroupType = "BandmanageBackendServerNetworkCall";
        public static final String samiServerNetworkCallGroupType = "SamiServerNetworkCall";
    }

    public static class INTENT {

        public static final int CONTINUOUS_ACTION_REMINDER_ID = 13991004;

        public static final int SENSORS_RESULTS_CLEANER_ID = 13991005;

        public static final String WARNING_POP_UP_TYPE = "warning_pop_up_type";
        public enum WarningPopUpType {
            FLU_POP_UP,
            ENERGY_POP_UP,
            FALL_ALERT_POP_UP
        }
    }


    public static class EXTRAS {
        public static final String EXTRA_FROM_LOGIN = "ExtraFromLogin";

        public final static int INVALID = 0;
        public final static int DEFAULT_BIRTH_YEAR = 0;

    }

    public static final class MinMaxValues {
        public static final int MIN_HEIGHT = 0;
        public static final int MAX_FEET = 8;
        public static final int MAX_INCHES = 11;
        public static final int MAX_METERS = 2;
        public static final int MAX_CENTIMETERS = 99;

        public static final int MIN_AGE = 0;
        public static final int MAX_AGE = 120;

        public static final int MIN_BODY_TEMP_C = 34;
        public static final int MIN_BODY_TEMP_F = 93;

        public static final int MAX_BODY_TEMP_C = 42;
        public static final int MAX_BODY_TEMP_F = 108;


        public static final int MIN_BLOOD_PRESSURE_SYSTOLIC = 70;
        public static final int MAX_BLOOD_PRESSURE_SYSTOLIC = 220;

        public static final int MIN_BLOOD_PRESSURE_DIASTOLIC = 30;
        public static final int MAX_BLOOD_PRESSURE_DIASTOLIC = 120;

        public static final int MAX_ROW_TITLE_LENGTH = 8;



    }
}
