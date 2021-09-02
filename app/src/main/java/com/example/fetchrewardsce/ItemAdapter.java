package com.example.fetchrewardsce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<Item> itemList;
    private MainActivity mainActivity;

    public ItemAdapter (List <Item> itemList, MainActivity ma){
        this.itemList = itemList;
        mainActivity = ma;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item i = itemList.get(position);
        holder.itemName.setText(i.getName());
        holder.listId.setText(Integer.toString(i.getListId()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}