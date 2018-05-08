package com.example.elcot.armitcha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class products extends AppCompatActivity {

    List<Item> itemList;
    StorageReference refStorage;
    FirebaseStorage storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);


        itemList=new ArrayList<>();



        itemList.add(new Item("Saree 1","Categorie Item","Description item",R.drawable.saree1));
        itemList.add(new Item("Saree 1","Categorie Item","Description item",R.drawable.saree2));
        itemList.add(new Item("Saree 1","Categorie Item","Description item",R.drawable.saree3));
        itemList.add(new Item("Saree 1","Categorie Item","Description item",R.drawable.saree4));
        itemList.add(new Item("Saree 1","Categorie Item","Description item",R.drawable.saree1));
        itemList.add(new Item("Saree 1","Categorie Item","Description item",R.drawable.saree1));
        itemList.add(new Item("Saree 1","Categorie Item","Description item",R.drawable.saree1));
        itemList.add(new Item("Saree 1","Categorie Item","Description item",R.drawable.saree1));
        itemList.add(new Item("Saree 1","Categorie Item","Description item",R.drawable.saree1));
        itemList.add(new Item("Saree 1","Categorie Item","Description item",R.drawable.saree1));
        itemList.add(new Item("Saree 1","Categorie Item","Description item",R.drawable.saree1));
        itemList.add(new Item("Saree 1","Categorie Item","Description item",R.drawable.saree1));
        itemList.add(new Item("Saree 1","Categorie Item","Description item",R.drawable.saree1));
        itemList.add(new Item("Saree 1","Categorie Item","Description item",R.drawable.saree1));
        itemList.add(new Item("Saree 1","Categorie Item","Description item",R.drawable.saree1));
        itemList.add(new Item("Saree 1","Categorie Item","Description item",R.drawable.saree1));
        itemList.add(new Item("Saree 1","Categorie Item","Description item",R.drawable.saree1));



        RecyclerView myrv=(RecyclerView)findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myadapt=new RecyclerViewAdapter(this,itemList);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myadapt);
    }
}
