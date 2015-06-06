package io.sirio.sbs;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.sirio.sbs.adapters.NavDrawerRecycler;
import io.sirio.sbs.fragments.BeneficiosFragment;
import io.sirio.sbs.fragments.CursosFragment;
import io.sirio.sbs.fragments.HomeFragment;
import io.sirio.sbs.fragments.NavigationDrawerFragment;
import io.sirio.sbs.fragments.PerfilFragment;


public class MainActivity extends ActionBarActivity implements NavigationDrawerCallbacks{


    private Toolbar mToolbar;
    private NavigationDrawerFragment mNavigationDrawerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_drawer);

        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);

        mNavigationDrawerFragment.setUserData("Sirio", "hello@sirio.io", BitmapFactory.decodeResource(getResources(), R.drawable.avatar));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {

        Fragment fragment;
        switch (position) {
            case 0:
                fragment = getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG);
                if (fragment == null) {
                    fragment = new HomeFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, HomeFragment.TAG).commit();
                break;
            case 1:
                fragment = getSupportFragmentManager().findFragmentByTag(CursosFragment.TAG);
                if (fragment == null) {
                    fragment = new CursosFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, CursosFragment.TAG).commit();
                break;
            case 2:
                fragment = getSupportFragmentManager().findFragmentByTag(BeneficiosFragment.TAG);
                if (fragment == null) {
                    fragment = new BeneficiosFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, BeneficiosFragment.TAG).commit();
                break;
            case 3:
                fragment = getSupportFragmentManager().findFragmentByTag(PerfilFragment.TAG);
                if (fragment == null) {
                    fragment = new PerfilFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment, PerfilFragment.TAG).commit();
                break;
        }


        // update selected item title, then close the drawer

//        setTitle(mListas[position]);
//        mDrawerLayout.closeDrawer(mDrawerList);

    }
}
