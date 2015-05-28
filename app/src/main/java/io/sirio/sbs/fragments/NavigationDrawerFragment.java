package io.sirio.sbs.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.FeatureGroupInfo;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.BasicNetwork;

import java.util.ArrayList;
import java.util.List;

import io.sirio.sbs.BeneficiosActivity;
import io.sirio.sbs.CursosActivity;
import io.sirio.sbs.MiPerfilActivity;
import io.sirio.sbs.MisCursosActivity;
import io.sirio.sbs.R;
import io.sirio.sbs.adapters.NavDrawerRecycler;
import io.sirio.sbs.models.Information;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements NavDrawerRecycler.ClickListener{

    public  static  final String PREF_FILE_NAME = "testpref";

    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;

    private ActionBarDrawerToggle mDrawerToogle;

    private DrawerLayout mDrawerLayout;
    private View containerView;
    private RecyclerView recyclerView;
    private NavDrawerRecycler adapter;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.parseBoolean(readToPreference(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));

        if(savedInstanceState!= null){
            mFromSavedInstanceState = true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        adapter = new NavDrawerRecycler(getActivity(), getData());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public static List<Information> getData(){
        List<Information> data = new ArrayList<>();

        int[] icons = {R.mipmap.ic_account_balance_grey, R.mipmap.ic_explore_grey,  R.mipmap.ic_redeem_grey, R.mipmap.ic_account_circle_grey,};
        String[] titles = {"Mis Cursos", "Cursos",  "Beneficios", "Perfil",};
        for (int i = 0; i < titles.length; i++){
            Information currentaData = new Information();

            currentaData.iconId = icons[i];
            currentaData.title = titles[i];

            data.add(currentaData);
        }
        return data;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar){
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToogle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mFromSavedInstanceState){
                    mFromSavedInstanceState = true;
                    saveToPreference(getActivity(), KEY_USER_LEARNED_DRAWER, mFromSavedInstanceState+"");
                }

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if(slideOffset < 0.6){
                    toolbar.setAlpha(1-slideOffset);
                }
            }
        };


        if (!mUserLearnedDrawer && !mFromSavedInstanceState){
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToogle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToogle.syncState();
            }
        });
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
    public void itemClicked(View view, int position) {

        switch (position){
            case 0 :  startActivity(new Intent(getActivity(),MisCursosActivity.class));
                break;
            case 1 :  startActivity(new Intent(getActivity(),CursosActivity.class));
                break;
            case 2 :  startActivity(new Intent(getActivity(),BeneficiosActivity.class));
                break;
            case 3 :  startActivity(new Intent(getActivity(),MiPerfilActivity.class));
                break;
        }



    }
}
