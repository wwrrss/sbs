package io.sirio.sbs.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import io.sirio.sbs.R;
import io.sirio.sbs.models.Information;

/**
 * Created by Diego on 20/05/2015.
 */
public class NavDrawerRecycler extends RecyclerView.Adapter<NavDrawerRecycler.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    List<Information> data = Collections.emptyList();

    public NavDrawerRecycler(Context context, List<Information> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information currentData = data.get(position);
        holder.title.setText(currentData.title);
        holder.icon.setImageResource(currentData.iconId);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        ImageView icon;

        public MyViewHolder(final View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.text_view);
            icon = (ImageView) itemView.findViewById(R.id.image_view);
            title.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Tiene que abrir proximamente! : " + getPosition(), Toast.LENGTH_SHORT).show();
        }
    }
}
