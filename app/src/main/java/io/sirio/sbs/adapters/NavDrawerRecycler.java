package io.sirio.sbs.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import io.sirio.sbs.R;
import io.sirio.sbs.models.Information;


public class NavDrawerRecycler extends RecyclerView.Adapter<NavDrawerRecycler.MyViewHolder> {

    List<Information> data = Collections.emptyList();
    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        public void onClick(View view, int position);
    }

    public NavDrawerRecycler( List<Information> data, OnItemClickListener listener ){
        this.data = data;
        this.mListener = listener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater vi = LayoutInflater.from(parent.getContext());
        View view =vi.inflate(R.layout.custom_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Information currentData = data.get(position);
        holder.title.setText(currentData.title);
        holder.icon.setImageResource(currentData.iconId);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(v, position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        final TextView title;
        final ImageView icon;

        public MyViewHolder(final View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.text_view);
            icon = (ImageView) itemView.findViewById(R.id.image_view);

        }

    }


}
