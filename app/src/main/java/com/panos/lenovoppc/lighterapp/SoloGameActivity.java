package com.panos.lenovoppc.lighterapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This class is used before entering the solo match.
 * Used to add player names.
 */
public class SoloGameActivity extends AppCompatActivity implements PlayerAdapter.onClickListenerInterface{

    private RecyclerView mPlayersRecyclerView;
    private RecyclerView.Adapter mPlayerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<PlayerModel> arrayList = new ArrayList<PlayerModel>();
    private EditText mPlayerName;
    private FloatingActionButton mStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sologame);

        /* Code to make sure user adds at least 2 players and continue if he does*/

        arrayList.add(new PlayerModel("Add at least",R.drawable.info));
        arrayList.add(new PlayerModel("two players",R.drawable.info));

        mPlayerName = (EditText) findViewById(R.id.et_newplayer);
//        mPlayerName.requestFocus(); not sure if this is needed for focus on editText

        mStartGame = (FloatingActionButton) findViewById(R.id.fab_startsologame);
        mStartGame.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                //if the image in the array is the one we provide as info then the user has not
                //entered at least 2 users. same if he just erases and doesn't provide 2 users.
                if(arrayList.get(0).getImage()==R.drawable.info || arrayList.size() < 2){
                    Toast.makeText(SoloGameActivity.this, "Please add min. 2 players before continuing", Toast.LENGTH_SHORT).show();
                    return;
                }
                //puts the list into a parcelable bundle to pass to main solo game
                Intent intent = new Intent(SoloGameActivity.this, MainActivity.class);
                intent.putParcelableArrayListExtra("players", arrayList);
                startActivity(intent);
            }
        });


        /*Code Needed for RecyclerView */

        mPlayersRecyclerView = findViewById(R.id.rv_prepareplayers);
        //this helps performance by saying that the rv's size doesn't change
        mPlayersRecyclerView.setHasFixedSize(true);
        //layout for the RV
        mLayoutManager = new LinearLayoutManager(this);
        mPlayersRecyclerView.setLayoutManager(mLayoutManager);
        //this is where you send data to the rv and let it know it should keep MainActivity's clickListener
        mPlayerAdapter = new PlayerAdapter(arrayList, this);
        mPlayersRecyclerView.setAdapter(mPlayerAdapter);



    }

    /**
     * Method deletes user after tap
     * @param viewHolder the vH being clicked on
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(PlayerAdapter.PlayerViewHolder viewHolder) {

            int toBeRemoved = viewHolder.getAdapterPosition();
            arrayList.remove(toBeRemoved);
            mPlayerAdapter.notifyDataSetChanged();

    }

    /**
     * Method for adding name to arraylist, adding to and refreshing the adapter's dataset
     * Updates the UI and makes sure to replace provided info with players
     * @param view the button being clicked on in activity_sologame.xml
     */
    public void addNewPlayer(View view) {


        String name = mPlayerName.getText().toString();
        int pico = R.mipmap.ic_launcher;
        PlayerModel mNewPlayer = new PlayerModel(name, pico);

        //if the image in the array is the one we provide as info then the user has not
        //entered at least 2 users. So remove the first entry in the list.
        if(arrayList.get(0).getImage()==R.drawable.info){
            arrayList.remove(0);
        }


        arrayList.add(mNewPlayer);

        mPlayerAdapter.notifyDataSetChanged();
        mPlayerName.setText("");

    }
}
