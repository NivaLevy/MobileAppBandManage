package com.example.nivalevi.mobileappbandmanage.activities;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nivalevi.mobileappbandmanage.MyApp;
import com.example.nivalevi.mobileappbandmanage.R;
import com.example.nivalevi.mobileappbandmanage.database.DatabaseHelper;
import com.example.nivalevi.mobileappbandmanage.models.drawer.care_receiver.CareReceiverSystemData;
import com.example.nivalevi.mobileappbandmanage.models.drawer.drawer.DrawerAdapter;
import com.example.nivalevi.mobileappbandmanage.models.drawer.drawer.DrawerItemModel;
import com.example.nivalevi.mobileappbandmanage.models.drawer.drawer.DrawerItemModel.DrawerItemType;
import com.example.nivalevi.mobileappbandmanage.models.drawer.drawer.DrawerItemView;

import java.util.List;
import java.util.Vector;

import static com.example.nivalevi.mobileappbandmanage.models.drawer.drawer.DrawerItemModel.DrawerItemType.*;

public class MainActivity extends AppCompatActivity {

    private String[] mPlanetTitles;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private static DrawerAdapter drawerAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private static DrawerItemClickListener drawerItemClickListener;
    private static Context context;
    private boolean listIsOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MyApp.getApplication();

        listIsOpen = false;
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);


        // Set the list's click listener
        drawerItemClickListener = new DrawerItemClickListener();
        mDrawerList.setOnItemClickListener(drawerItemClickListener);
        createAndSetDrawer();

        mTitle = mDrawerTitle = getTitle();
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                if (listIsOpen)
                    closeResidentsList();
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    public int getItemPositionByName() {
        return 0;
    }

///test
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            DrawerItemType drawerItemType = drawerAdapter.getItem(position).getType();
            if(drawerItemType.equals(DrawerItemType.ADD_CARERECEIVER))
            {
                selectItem(ADD_CARERECEIVER);
                closeDrawer(null);
                return;
            }

            else if (drawerItemType.equals(DrawerItemType.TITLE)){
                if (!listIsOpen) {
                    openResidentsList();
                }
                else {
                    closeResidentsList();
                }
                drawerAdapter.notifyDataSetChanged();
            }

            if (drawerItemType == DrawerItemType.CARE_PANEL_PHONE){

                mDrawerList.setItemChecked(position, true);

               // nameOfSelection = drawerAdapter.getItem(position - 1).getText();
                closeResidentsList();
                closeDrawer(null);
                drawerAdapter.notifyDataSetChanged();
                //drawerList.invalidate();
            }




            DrawerItemView drawerItemView = ((DrawerItemView) view);
            if(drawerItemView.getType() != DrawerItemType.CARE_PANEL_APP_HEADER) {
                selectItem(drawerItemView.getType());
            }
        }
    }

    private void closeResidentsList() {
        DrawerItemModel mod = null;
        for (int i = 0; i < drawerAdapter.getCount();) {
            mod = (DrawerItemModel)drawerAdapter.getItem(i);
            if (mod != null && mod.getType().equals(DrawerItemType.CARE_PANEL_PHONE)){
                drawerAdapter.remove(mod);
                drawerAdapter.notifyDataSetChanged();
            }
            else
                i++;
        }
        listIsOpen = false;
    }

    private void openResidentsList() {
        for (CareReceiverSystemData c : loadCR()) {
            DrawerItemModel model = new DrawerItemModel(c.getName(), R.drawable.ic_profile, DrawerItemType.CARE_PANEL_PHONE);
            drawerAdapter.add(model);
        }
        listIsOpen = true;
    }

    static List<CareReceiverSystemData> loadCR(){
        List<CareReceiverSystemData> careReceivers = new Vector<>();
        //TEST
        for (int i = 0; i < 3 ; i++) {
            careReceivers.add(new CareReceiverSystemData(0, (i + 1) + "", null, false, 0, 0, null));
        }
        //DatabaseHelper.getInstance().loadAllCareReceiversDataItems(careReceivers, CarePanelPatientsListFragment.OrderToRefreshBy.NAME);
        return careReceivers;
    }

    public synchronized void closeDrawer(android.support.v4.app.Fragment fragment) {
       // if (fragment != null) mainOpenedFragmentsList.add(fragment);
        if (mDrawerLayout != null){// && drawerState == 0) {
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }

    private void selectItem(DrawerItemType type) {
    }


    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
       /* Fragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);*/
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }



    public void createAndSetDrawer() {
        drawerAdapter = new DrawerAdapter(context, -1);


        drawerAdapter.add(new DrawerItemModel(context.getString(R.string.drawer_helpdesk_button), R.drawable.selector_icon_helpdesk, CARE_PANEL_HELP_DESK));
        drawerAdapter.add(new DrawerItemModel(context.getString(R.string.drawer_add_carereceiver_button), R.drawable.ic_on, ADD_CARERECEIVER));
        drawerAdapter.add(new DrawerItemModel(context.getString(R.string.drawer_residents), R.drawable.selector_icon_stethoscope, TITLE));


        mDrawerList.setAdapter(drawerAdapter);
        mDrawerList.setSelector(R.drawable.selector_menu_main);
        mDrawerList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mDrawerList.setOnItemClickListener(drawerItemClickListener);
        //drawerList.setItemChecked(getItemPositionBySelectedItemType(), BandManageApplication.isWearableSensorOn());
        mDrawerList.setItemChecked(getItemPositionByName(), true);
        mDrawerList.invalidate();
    }
    
    
    


}
