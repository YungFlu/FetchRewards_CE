package com.example.fetchrewardsce;

public class Item {

    int id;
    int listId;
    String name;

    public Item(int id, int listId, String name){
        this.id = id;
        this.listId = listId;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public int getListId(){
        return listId;
    }

    public String getName(){
        return name;
    }
}
