package io.sirio.sbs.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import io.sirio.sbs.R;
import io.sirio.sbs.adapters.CursosAdapter;
import io.sirio.sbs.models.Curso;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCursos extends Fragment {


    public FragmentCursos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cursos, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Curso> cursos = new ArrayList<>();

        Curso curso = new Curso();
        curso.setNombreCurso("Android");
        curso.setFechaInicio("25/04/2015");
        curso.setCiudad("Quito");
        curso.setValor("25000");
        cursos.add(curso);

        Curso curso2 = new Curso();
        curso2.setNombreCurso("Android");
        curso2.setFechaInicio("25/04/2015");
        curso2.setCiudad("Quito");
        curso2.setValor("25000");
        cursos.add(curso2);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view_cursos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new CursosAdapter(cursos, R.layout.row_cursos));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}
