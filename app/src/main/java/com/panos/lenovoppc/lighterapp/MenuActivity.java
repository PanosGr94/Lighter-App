package com.panos.lenovoppc.lighterapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Lenovo pPc on 23-Jan-18.
 */

public class MenuActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void startSoloGame(View view) {
        Intent startNewGame = new Intent(this, MainActivity.class);
        startActivity(startNewGame);
    }
}
