package io.sirio.sbs.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import io.sirio.sbs.NavigationDrawerCallbacks;
import io.sirio.sbs.R;
import io.sirio.sbs.models.Information;
import io.sirio.sbs.models.NavigationItem;


public class NavDrawerRecycler extends RecyclerView.Adapter<NavDrawerRecycler.MyViewHolder> {

    List<NavigationItem> mData;
    private NavigationDrawerCallbacks mNavigationDrawerCallbacks;
    private View mSelectedView;
    private int mSelectedPosition;


    public NavDrawerRecycler( List<NavigationItem> data ){
        this.mData = data;
    }

    public NavigationDrawerCallbacks getmNavigationDrawerCallbacks(){
        return mNavigationDrawerCallbacks;
    }

    public void setNavigationDrawerCallbacks(NavigationDrawerCallbacks navigationDrawerCallbacks) {
        mNavigationDrawerCallbacks = navigationDrawerCallbacks;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater vi = LayoutInflater.from(parent.getContext());
        View view =vi.inflate(R.layout.drawer_row, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.itemView.setClickable(true);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectedView != null) {
                    mSelectedView.setSelected(false);
                }
                mSelectedPosition = myViewHolder.getPosition();
                v.setSelected(true);
                mSelectedView = v;
                if (mNavigationDrawerCallbacks != null) {
                    mNavigationDrawerCallbacks.onNavigationDrawerItemSelected(myViewHolder.getPosition());
                }
            }
        });

        myViewHolder.itemView.setBackgroundResource(R.drawable.row_selector);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.textView.setText(mData.get(position).getText());
        holder.textView.setCompoundDrawablesWithIntrinsicBounds(mData.get(position).getDrawable(), null, null, null);
        if(mSelectedPosition == position){
            if(mSelectedView != null){
                mSelectedView.setSelected(false);
            }
            mSelectedPosition = position;
            mSelectedView = holder.itemView;
            mSelectedView.setSelected(true);
        }


    }

    public void selectPosition(int position){
        mSelectedPosition = position;
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        final TextView textView;

        public MyViewHolder(final View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.item_name);

        }

    }


}
