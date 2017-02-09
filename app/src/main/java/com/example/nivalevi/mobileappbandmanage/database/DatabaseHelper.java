package com.example.nivalevi.mobileappbandmanage.database;

import android.content.Context;
import android.database.Cursor;

import com.example.nivalevi.mobileappbandmanage.models.drawer.care_receiver.CareReceiverSystemData;

import java.util.List;

/**
 * Created by nivalevi on 09/02/2017.
 */
public class DatabaseHelper {

    private static DatabaseHelper sInstance = null;

    public DatabaseHelper(Context context) {

    }

    public static void init(Context context) {

        sInstance = new DatabaseHelper(context);
    }

    public static DatabaseHelper getInstance() {
        return sInstance;
    }

   /* public int loadAllCareReceiversDataItems(List<CareReceiverSystemData> itemArrayList, CarePanelPatientsListFragment.OrderToRefreshBy orderToRefreshBy) {
        preventMainThread();

        int itemsNumber = 0;

        Cursor cursor = null;
        try {
            String orderBy = "";
            if (orderToRefreshBy != null) {
                switch (orderToRefreshBy) {
                    case ROOM_NUM:
                        orderBy += CareReceiversSystemDataTable.Columns.PATIENT_ROOM_NUMBER + " ASC";
                        break;
                    case NAME:
                    default:
                        orderBy += CareReceiversSystemDataTable.Columns.PATIENT_NAME + " ASC";
                        break;
                }
            }
            long timeBefore = System.currentTimeMillis();
            cursor = getReadableDatabase().query(CareReceiversSystemDataTable.TABLE_NAME, CareReceiversSystemDataTable.PROJECTION_ALL, null, null, null, null, orderBy);

            boolean canMoveToFirst = cursor.moveToFirst();
            //Log.i(TAG, "query to load all items took " + (System.currentTimeMillis() - timeBefore) + " millis. cursor has: " + cursor.getCount() + " rows.");
            if (canMoveToFirst) {

                do {
                    try {
                        long itemId = cursor.getLong(0);
                        String patientName = cursor.getString(1);
                        String patientSystemID = cursor.getString(2);
                        Boolean isNeedCare = cursor.getInt(3) > 0;
                        int patientRoomNum = cursor.getInt(4);
                        int patientTimeZone = cursor.getInt(6);
                        String patientImageUrl = cursor.getString(7);
                        String patientKitID = cursor.getString(8);

                        itemArrayList.add(new CareReceiverSystemData(itemId,patientName,patientSystemID,isNeedCare,patientRoomNum,patientTimeZone,patientImageUrl, patientKitID));
                        itemsNumber++;
                    } catch (Exception e) {
                       // BandManageApplication.getInstance().sendEStackTraceToCrashlytics(e);
                    }
                }
                while (cursor.moveToNext());
            }

        } catch (Exception e) {
          //  BandManageApplication.getInstance().sendEStackTraceToCrashlytics(e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        //Log.i(TAG, "Total time to load " + itemsNumber + " items took " + (System.currentTimeMillis() - startTime) + "ms");
        return itemsNumber;
    }*/

}
