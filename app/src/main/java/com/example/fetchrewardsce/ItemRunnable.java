package com.example.fetchrewardsce;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ItemRunnable implements Runnable {

    private MainActivity mainActivity;
    private final String download = "https://fetch-hiring.s3.amazonaws.com/hiring.json";
    private static final String TAG = "ItemRunnable";

    public ItemRunnable(MainActivity ma){
        mainActivity = ma;
    }

    @Override
    public void run() {
        try {
            Uri dataUri = Uri.parse(download);
            String urlToUse = dataUri.toString();

            StringBuilder sb = new StringBuilder();

            java.net.URL url = new URL(urlToUse);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK){
                results(null);
            }

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader((new InputStreamReader(is)));

            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }

            results(sb.toString());

        }catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    private void results(String s){
        final List <Item> itemList = parseJSON(s);

        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivity.updateItemlist(itemList);
            }
        });

    }

    private List<Item> parseJSON (String s){
        //creates list to return
        List <Item> itemList = new ArrayList<>();
        try{
            //converts JSON data to array
            JSONArray items = new JSONArray(s);
            //iterate through each JSONObject in the JSONArray
            for (int i = 0; i < items.length(); i++){
                JSONObject item = (JSONObject) items.get(i);
                String itemName = "";
                int itemId;
                int listId;
                try{
                    itemName = item.getString("name");

                    //Filters out items with blank or null item names
                    if (itemName.equalsIgnoreCase("null") || itemName.isEmpty()){
                        continue;
                    }

                    itemId = Integer.parseInt(item.getString("id"));
                    listId = Integer.parseInt(item.getString("listId"));

                    Item finalItem = new Item(itemId, listId, itemName);
                    itemList.add(finalItem);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        /*
        I had to make a decision here whether to sort by the field "name" or by the id of the Item
        If I sorted by "name", I would have received an output that would have not have been numerically accurate
        Sorting each item by its id would produce numerically accurate results, which makes more sense to me because
        each Item name is only distinct by the numbers.
        */
        Comparator <Item> sortItems = Comparator.comparing(Item::getListId).thenComparing(Item::getId);
        /*
        If sorting by the field "name" was the correct interpretation, then the line above would be
        Comparator <Item> sortItems = Comparator.comparing(Item::getListId).thenComparing(Item::getName);
         */
        Collections.sort(itemList, sortItems);
        return itemList;
    }
}