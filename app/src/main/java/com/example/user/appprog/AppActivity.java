package com.example.user.appprog;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;

import java.util.List;

public class AppActivity extends Activity {

    Button btnGrid;
    Button btnList;
    EditText editFindName;
    private RecyclerView appRecyclerView;
    RecViewAdapter appAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Filter filterApp;
    List<ApplicationInfo> appList;
    PackageManager pm;
    public static int layo;

    public void setLayo(int layo) {
        this.layo = layo;
    }

    public static int getLayo() {
        return layo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        btnGrid = (Button) findViewById(R.id.grid_btn);
        btnList = (Button) findViewById(R.id.list_btn);

        appRecyclerView = (RecyclerView) findViewById(R.id.my_rec_view);
        appRecyclerView.setHasFixedSize(true);

        onClickList(null);

        pm = this.getPackageManager();
        appList = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        appAdapter = new RecViewAdapter(this, pm, appList);
        filterApp = appAdapter.getFilter();
        appRecyclerView.setAdapter(appAdapter);

        editFindName = (EditText) findViewById(R.id.edit_find_text);
        editFindName.addTextChangedListener(textWatcher);
    }


    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            filterApp.filter(s);
        }
    };


    public void onClickGrid(View view) {
        setLayo(R.layout.item_grid);
        layoutManager = new GridLayoutManager(this, 3);
        appRecyclerView.setLayoutManager(layoutManager);
        appRecyclerView.getRecycledViewPool().clear();
    }


    public void onClickList(View view) {
        setLayo(R.layout.item_list);
        layoutManager = new LinearLayoutManager(this);
        appRecyclerView.setLayoutManager(layoutManager);
        appRecyclerView.getRecycledViewPool().clear();
    }
}
