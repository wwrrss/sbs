package io.sirio.sbs.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.sirio.sbs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeneficiosFragment extends Fragment {

    public static final String TAG = "Beneficios";
    public BeneficiosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_beneficios, container, false);
    }


}
