package io.sirio.sbs.adapters;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.sirio.sbs.R;
import io.sirio.sbs.fragments.CursosViewFragment;
import io.sirio.sbs.models.Curso;
import io.sirio.sbs.models.CursoPost;

public class CursosAdapter extends RecyclerView.Adapter<CursosAdapter.ViewHolderCursos>{

    ArrayList<CursoPost> cursoPosts;
    private LayoutInflater layoutInflater;

    OnItemClickListener mItemClickListener;


    public CursosAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
    }

    public void setCursoList(ArrayList<CursoPost> cursoList){
        this.cursoPosts = cursoList;
        notifyDataSetChanged();
    }
    @Override
    public ViewHolderCursos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_cursos, parent, false);
        ViewHolderCursos v = new ViewHolderCursos(view);
        return v;
    }

    @Override
    public void onBindViewHolder(ViewHolderCursos holder, int position) {
        CursoPost curso = cursoPosts.get(position);
        holder.tituloCurso.setText(curso.getTitle());

    }

    @Override
    public int getItemCount() {
        return (null != cursoPosts ? cursoPosts.size() : 0);
    }

    public class ViewHolderCursos extends RecyclerView.ViewHolder{
        TextView tituloCurso;

        public ViewHolderCursos(View itemView) {
            super(itemView);

            tituloCurso = (TextView) itemView.findViewById(R.id.nombre_curso);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
            if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition());
                    }

                }
            });
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view , int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }



}
