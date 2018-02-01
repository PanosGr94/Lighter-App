package com.panos.lenovoppc.lighterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static android.graphics.Color.GRAY;

//TODO:
//TODO: Fix crash when input number of players == 0
//TODO: Get Questions from a DB into the app and showing...
//TODO: Make create an insert question menu & make it float on top of the menu activity
//TODO: Connect the inserted question into a DB
//TODO: Make sure only one player can be selected and when Update UI deselect player
//TODO: Fix the inconsistency in state when the user switches to landscape. Maybe make landscape inaccessible - temporarily?
//TODO: Use CardViews for players - https://developer.android.com/reference/android/support/v7/widget/CardView.html


public class MainActivity extends AppCompatActivity implements PlayerAdapter.onClickListenerInterface {

    private RecyclerView mPlayersRecyclerView;
    private RecyclerView.Adapter mPlayerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private QuestionModel currentQuestion;
    private TextView mQuestionTextView;
    private TextView mCategoryTextView;
    private Button mSkipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<PlayerModel> arrayList = new ArrayList<PlayerModel>();


        /*DataSet for Questions*/

        final ArrayList<QuestionModel> questions = new ArrayList<>();
        questions.add(new QuestionModel("Who is the strongest?", "categoryA", false));
        questions.add(new QuestionModel("Who is the fastest?", "categoryB", false));
        questions.add(new QuestionModel("Who is the tallest?", "categoryC", true));
        questions.add(new QuestionModel("Who is the wierdest?", "categoryD", false));
        questions.add(new QuestionModel("Who is the smartest?", "categoryA", true));
        //sets up the current question in the UI
        mQuestionTextView = (TextView) findViewById(R.id.tv_question);
        mCategoryTextView = (TextView) findViewById(R.id.tv_category);
        updateCurrentUI(questions);
        //sets a listener to the skip button to change the question when necessary
        mSkipButton = (Button) findViewById(R.id.btn_skip);
        mSkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCurrentUI(questions);
            }
        });


        /* DataSet for RecyclerView */

        Intent intent = getIntent();
        arrayList = intent.getParcelableArrayListExtra("players");

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
        if(current==getResources().getColor(R.color.colorPrimary)){
            player.mPlayerNameTextView.setTextColor(GRAY);
        }else{
            player.mPlayerNameTextView.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }


    /**
     * Method for selecting a single question out of the datasource and returning it.
     * @param questionList is the datasource from which it will retrieve a new question
     * @return a {@link QuestionModel} type question
     */
    public QuestionModel getQuestion(ArrayList<QuestionModel> questionList){
        QuestionModel newQuestion = questionList.get(new Random().nextInt(questionList.size()));
        return newQuestion;
    }

    /**
     * Method for updating the UI when a new question must come up
     * @param questionList the datasource from which a question will be picked
     */
    public void updateCurrentUI(ArrayList<QuestionModel> questionList){

        //gets a random question from the above list
        currentQuestion = getQuestion(questionList);
        mQuestionTextView.setText(currentQuestion.getQuestion());
        mCategoryTextView.setText(currentQuestion.getCategory());


    }


}
