package com.example.nivalevi.mobileappbandmanage.models.drawer.drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.ctrlplusz.anytextview.AnyTextView;
import com.example.nivalevi.mobileappbandmanage.MyApp;
import com.example.nivalevi.mobileappbandmanage.R;
import com.example.nivalevi.mobileappbandmanage.utils.ScreenViewsUtils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by nivalevi on 09/02/2017.
 */
public class DrawerItemView extends LinearLayout {
    private final AnyTextView headerTextView, commentTextView;
    private DrawerItemModel.DrawerItemType type;

    boolean isThereCareReceivers;


    public DrawerItemView(Context context) {
        super(context);

        View v = LayoutInflater.from(context).inflate(R.layout.drawer_list_item, this);
        headerTextView = (AnyTextView) v.findViewById(R.id.drawer_item_header);
        commentTextView = (AnyTextView) v.findViewById(R.id.drawer_item_comment);
    }

    public void set(DrawerItemModel model) {
        type = model.getType();
        headerTextView.setText(model.getText());

        if (model.getPosition() == DrawerItemModel.IconPosition.LEFT) {
            headerTextView.setCompoundDrawablesWithIntrinsicBounds(model.getIcon(), 0, 0, 0);
        }
        else if (model.getPosition() == DrawerItemModel.IconPosition.RIGHT) {
            headerTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, model.getIcon(), 0);
            headerTextView.setPadding(ScreenViewsUtils.pxFromDp(MyApp.getApplication(), 40), 0, 0, 0);
        }

        if(StringUtils.isNoneBlank(model.getComment()))
        {
            commentTextView.setText(model.getComment());
            commentTextView.setVisibility(VISIBLE);
        }
        else {commentTextView.setVisibility(GONE);}

    }

    public DrawerItemModel.DrawerItemType getType() {
        return type;
    }


    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }
}
