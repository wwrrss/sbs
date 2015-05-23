package io.sirio.sbs.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import io.sirio.sbs.R;
import io.sirio.sbs.models.Cursos;

/**
 * Created by Diego on 23/05/2015.
 */
public class CursosAdapter extends RecyclerView.Adapter<CursosAdapter.ViewHolder>{

    ArrayList<Cursos> cursos;
    int itemLayout;

    public CursosAdapter(ArrayList<Cursos> cursos, int itemLayout){
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
        Cursos curso = cursos.get(position);

        holder.nombreCurso.setText(curso.getNombreCurso());
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


        }
    }
}
