package com.example.user.appprog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton imgBtnToCall;
    ImageButton imgBtnToSend;
    Button btnGoToApp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBtnToCall = (ImageButton) findViewById(R.id.img_btn_call);
        imgBtnToSend = (ImageButton) findViewById(R.id.img_btn_send);
        btnGoToApp = (Button) findViewById(R.id.btn_app);

        imgBtnToCall.setOnClickListener(this);
        imgBtnToSend.setOnClickListener(this);
        btnGoToApp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {

            case R.id.img_btn_call:
                intent = new Intent(Intent.ACTION_DIAL);
                break;

            case R.id.img_btn_send:
                intent = new Intent(Intent.ACTION_VIEW).setType("vnd.android-dir/mms-sms");
                break;

            case R.id.btn_app:
                intent = new Intent(this, AppActivity.class);
                break;

        }
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
