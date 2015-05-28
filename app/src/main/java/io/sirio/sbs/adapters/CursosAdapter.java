package io.sirio.sbs.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import io.sirio.sbs.R;
import io.sirio.sbs.models.Curso;

public class CursosAdapter extends RecyclerView.Adapter<CursosAdapter.ViewHolder>{

    ArrayList<Curso> cursos;
    int itemLayout;
    OnItemClickListener mItemClickListener;

    public CursosAdapter(ArrayList<Curso> cursos, int itemLayout){
        this.cursos = cursos;
        this.itemLayout = itemLayout;

    }
    @Override
    public CursosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CursosAdapter.ViewHolder holder, int position) {
        Curso curso = cursos.get(position);

        holder.nombreCurso.setText(curso.getNombre());
        holder.fechaInicio.setText(curso.getFechaInicio());
        holder.ciudad.setText(curso.getCiudad());
        holder.valor.setText(curso.getValor());



    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView nombreCurso;
        TextView fechaInicio;
        TextView ciudad;
        TextView valor;

        public ViewHolder(View itemView) {
            super(itemView);

            nombreCurso = (TextView) itemView.findViewById(R.id.nombre_curso);
            fechaInicio = (TextView) itemView.findViewById(R.id.fecha_inicio);
            ciudad = (TextView) itemView.findViewById(R.id.ciudad);
            valor = (TextView) itemView.findViewById(R.id.valor);

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
         void onItemClick(View view , int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

}
