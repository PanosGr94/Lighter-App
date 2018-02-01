package com.panos.lenovoppc.lighterapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Lenovo pPc on 25-Jan-18.
 */

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    /*implementing rv item clickability*/
    //asking for the player in order to be able to send it when method onClick is called
    public interface onClickListenerInterface{
        public void onClick(PlayerViewHolder viewHolder);
    }
    //this var is used to call onClick inside the ViewHolder with the selected player
    private final onClickListenerInterface mclickListener;


    /*getting the DataSet from MainActivity */

    private ArrayList<PlayerModel> mPlayerModelArrayList;
    //basically passing the DS through the constructor
    public PlayerAdapter(ArrayList<PlayerModel> mainActivityArrayList, onClickListenerInterface mainClickListener) {
            mPlayerModelArrayList = mainActivityArrayList;
            mclickListener = mainClickListener;
    }

    /*implementing Adapter's necessary methods */

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //this inflates the view from the item representation (aka what a single view in the RV looks like)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item_rv, parent, false);
        //this creates the viewHolder with the view we just inflated.
        //by passing this view to PlayerViewHolder the class can connect the views to their id's
        PlayerViewHolder pVH = new PlayerViewHolder(view);
        //this sends the view to onBindViewHolder
        return pVH;
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        String mViewHolderName = mPlayerModelArrayList.get(position).getName();
        int mViewHolderImage = mPlayerModelArrayList.get(position).getImage();
        holder.mPlayerImageView.setImageResource(mViewHolderImage);
        holder.mPlayerNameTextView.setText(mViewHolderName);
    }

    @Override
    public int getItemCount() {
        return mPlayerModelArrayList.size();
    }



    /* This could be a separate class. It implements Android.onclicklistener in order for it to be clickable */
    public class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CircleImageView mPlayerImageView ;
        TextView mPlayerNameTextView;

        public PlayerViewHolder(View itemView) {
            super(itemView);
            mPlayerImageView = itemView.findViewById(R.id.rvitem_image);
            mPlayerNameTextView = itemView.findViewById(R.id.rvitem_playername);
            //this line sets the VH as clickable
            itemView.setOnClickListener(this);
        }

        //when a VH is clicked call MY INTERFACE's onClick.Pass the clicked player
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mclickListener.onClick(this);
        }
    }


}
