package com.example.fetchrewardsce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ItemViewHolder extends RecyclerView.ViewHolder{

    TextView itemName;
    TextView listId;

    public ItemViewHolder(View v){
        super(v);
        itemName = v.findViewById(R.id.itemName);
        listId = v.findViewById(R.id.listId);
    }
}