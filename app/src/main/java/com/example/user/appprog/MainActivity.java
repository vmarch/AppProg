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

        imgBtnToCall = (ImageButton) findViewById(R.id.imgBtnCall);
        imgBtnToSend = (ImageButton) findViewById(R.id.imgBtnSend);
        btnGoToApp = (Button) findViewById(R.id.btnApp);


        imgBtnToSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW).setType("vnd.android-dir/mms-sms"));
            }
        });

        imgBtnToCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_DIAL));
            }
        });

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnApp:
                Intent intent = new Intent(this, ListOfApp.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
