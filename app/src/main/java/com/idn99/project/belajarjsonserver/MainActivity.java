package com.idn99.project.belajarjsonserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterRv adapterRv = new AdapterRv(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialKomponen();
        generateAdapter();
        getData();
    }

    public void inisialKomponen(){
        recyclerView = findViewById(R.id.home_rv);
    }

    public void generateAdapter(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterRv);
    }

    public void getData(){
        InputStream jsonFile = getResources().openRawResource(R.raw.data);
        AsyncTaskData asyncTaskData = new AsyncTaskData(getApplicationContext(),adapterRv);
        asyncTaskData.execute(jsonFile);
    }
}
