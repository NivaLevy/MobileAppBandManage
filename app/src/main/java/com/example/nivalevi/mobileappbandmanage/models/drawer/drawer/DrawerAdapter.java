package com.example.nivalevi.mobileappbandmanage.models.drawer.drawer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

/**
 * Created by nivalevi on 09/02/2017.
 */
public class DrawerAdapter extends ArrayAdapter<DrawerItemModel> implements AdapterView.OnItemClickListener {
    private final Context context;

    public DrawerAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    @Override
    public int getCount() {

        return super.getCount();
    }

    @Override
    public boolean isEnabled(int position) {
        for(int i = 0 ; i < getCount() ; i++)
        {
            if(getItem(i).getType() == DrawerItemModel.DrawerItemType.ADD_CARERECEIVER) {
                return position != i || super.isEnabled(position);
            }
        }

        return super.isEnabled(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DrawerItemModel model = getItem(position);
        if (convertView == null) {
            convertView = new DrawerItemView(context);
        }
        ((DrawerItemView)convertView).set(model);

        //if care giver phone -> give residents list an indent to look like sub list
        if (model.getType().equals(DrawerItemModel.DrawerItemType.CARE_PANEL_PHONE)){

            convertView.setPadding(50, 0, 0, 0);
        }else {
            convertView.setPadding(0, 0, 0, 0);
        }
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        // using the listView and not the adapter because we have a header view which the adapter is unaware of. Stupid Android

        //TODO: will not work cuz onItemClick on MainActivity assigned
        switch (((DrawerItemModel)adapterView.getItemAtPosition(i)).getType()) {
           }
    }

}
