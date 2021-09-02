package com.example.fetchrewardsce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List <Item> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        itemAdapter = new ItemAdapter(itemList, this);

        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemRunnable itemRunnable = new ItemRunnable(this);
        new Thread(itemRunnable).start();

    }

    public void updateItemlist(List <Item> iList){
        itemList.clear();
        itemList.addAll(iList);
        itemAdapter.notifyDataSetChanged();
    }


}