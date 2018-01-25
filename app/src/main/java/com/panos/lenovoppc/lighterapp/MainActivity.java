package com.panos.lenovoppc.lighterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GRAY;
import static android.graphics.Color.GREEN;

//TODO 1: Setup Question Model and bring random to question
//TODO 2: Use CardViews for players - https://developer.android.com/reference/android/support/v7/widget/CardView.html


public class MainActivity extends AppCompatActivity implements PlayerAdapter.onClickListenerInterface {

    private RecyclerView mPlayersRecyclerView;
    private RecyclerView.Adapter mPlayerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* DataSet for RecyclerView */

        ArrayList<PlayerModel> arrayList = new ArrayList<PlayerModel>();
        arrayList.add(new PlayerModel(getResources().getString(R.string.player1), R.mipmap.ic_launcher));
        arrayList.add(new PlayerModel(getResources().getString(R.string.player2), R.mipmap.ic_launcher));
        arrayList.add(new PlayerModel(getResources().getString(R.string.player3), R.mipmap.ic_launcher));
        arrayList.add(new PlayerModel(getResources().getString(R.string.player4), R.mipmap.ic_launcher));
        arrayList.add(new PlayerModel(getResources().getString(R.string.player1), R.mipmap.ic_launcher));
        arrayList.add(new PlayerModel(getResources().getString(R.string.player2), R.mipmap.ic_launcher));
        arrayList.add(new PlayerModel(getResources().getString(R.string.player3), R.mipmap.ic_launcher));
        arrayList.add(new PlayerModel(getResources().getString(R.string.player4), R.mipmap.ic_launcher));

        /*Code Needed for RecyclerView */

        mPlayersRecyclerView = findViewById(R.id.rv_playerrecyclerview);
        //this helps performance by saying that the rv's size doesn't change
        mPlayersRecyclerView.setHasFixedSize(true);
        //layout for the RV
        mLayoutManager = new LinearLayoutManager(this);
        mPlayersRecyclerView.setLayoutManager(mLayoutManager);
        //this is where you send data to the rv and let it know it should keep MainActivity's clickListener
        mPlayerAdapter = new PlayerAdapter(arrayList, this);
        mPlayersRecyclerView.setAdapter(mPlayerAdapter);


    }

    //when MY INTERFACE's onClick is called through the click of a ViewHolder
    @Override
    public void onClick(PlayerAdapter.PlayerViewHolder player) {
        int current = player.mPlayerNameTextView.getCurrentTextColor();
        if(current==GREEN){
            player.mPlayerNameTextView.setTextColor(GRAY);
        }else{
            player.mPlayerNameTextView.setTextColor(GREEN);
        }
    }
}
