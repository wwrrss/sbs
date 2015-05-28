package io.sirio.sbs.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.sirio.sbs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CursosViewFragment extends Fragment {

    TextView tTituloCurso;

    public CursosViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_cursos_view, container, false);
        String nombre_curso = getArguments().getString("nombre_curso_view");

        tTituloCurso = (TextView) view.findViewById(R.id.titulo_curso_view);
        tTituloCurso.setText(nombre_curso);
        return view;



    }


}
