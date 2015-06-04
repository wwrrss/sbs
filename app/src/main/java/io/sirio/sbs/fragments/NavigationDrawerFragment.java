package io.sirio.sbs.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.sirio.sbs.R;
import io.sirio.sbs.adapters.NavDrawerRecycler;
import io.sirio.sbs.models.Information;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends ActionBarActivity implements NavDrawerRecycler.OnItemClickListener{

    public  static  final String PREF_FILE_NAME = "testpref";

    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private Toolbar toolbar;

    private ActionBarDrawerToggle mDrawerToogle;

    private DrawerLayout mDrawerLayout;
    private View containerView;
    private RecyclerView mDrawerList;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mOptionsTitles;


    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_navigation_drawer);

        mTitle = mDrawerTitle = getTitle();
        mOptionsTitles = getResources().getStringArray(R.array.titles_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (RecyclerView) findViewById(R.id.drawerList);

       // mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        mDrawerList.setHasFixedSize(true);
        mDrawerList.setLayoutManager(new LinearLayoutManager(this));
        mDrawerList.setAdapter(new NavDrawerRecycler(getData(), this));

        toolbar = (Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToogle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar,  R.string.open_drawer, R.string.close_drawer
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                if(!mFromSavedInstanceState){
                    mFromSavedInstanceState = true;
                    saveToPreference(getApplicationContext(), KEY_USER_LEARNED_DRAWER, mFromSavedInstanceState+"");
                }
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if(slideOffset < 0.6){
                    toolbar.setAlpha(1-slideOffset);
                }
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToogle);


        if (savedInstanceState == null) {
            selectItem(0);
        }


     /*   mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToogle.syncState();
            }
        });*/

    }


    public static List<Information> getData(){
        List<Information> data = new ArrayList<>();

        int[] icons = {R.mipmap.ic_account_balance_grey, R.mipmap.ic_explore_grey,  R.mipmap.ic_redeem_grey, R.mipmap.ic_account_circle_grey,};
        String[] titles = {,};
        for (int i = 0; i < titles.length; i++){
            Information currentaData = new Information();

            currentaData.iconId = icons[i];
            currentaData.title = titles[i];

            data.add(currentaData);
        }
        return data;
    }


    public static void saveToPreference(Context context, String preferenceName, String preferenceValue){

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(preferenceName, preferenceValue);
        editor.apply();

    }

    public static String readToPreference(Context context, String preferenceName, String preferenceValue){

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, preferenceValue);
    }


    @Override
    public void onClick(View view, int position) {


        switch (position){
            case 0 :   selectItem(position);
                break;
            case 1 :  selectItem(position);
                break;
            case 2 :   selectItem(position);
                break;
            case 3 :   selectItem(position);
                break;
        }
    }


    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new CursosFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();

        // update selected item title, then close the drawer
        setTitle(mOptionsTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToogle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToogle.onConfigurationChanged(newConfig);
    }
}
