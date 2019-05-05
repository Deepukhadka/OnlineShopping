package com.example.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.onlineshopping.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import adapter.ItemsAdapter;
import model.Item;

public class DashboardActivity extends AppCompatActivity {

    private Button btnAddItem;
    private RecyclerView recyclerView;
    private FloatingActionButton fabAddItem;
    List<Item> itemList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recyclerViewDashboard);
        fabAddItem = findViewById(R.id.fabAddItem);

//        read all data from file
        readFromFile();

//        passing list to adapter class

        ItemsAdapter itemsAdapter = new ItemsAdapter(this,itemList);
        recyclerView.setAdapter(itemsAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,AddItemActivity.class);
                startActivity(intent);
            }
        });
    }

    private void readFromFile(){
        try{
            FileInputStream fileInputStream = openFileInput("items.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) !=null ){
                String[] parts  = line.split("->");
                itemList.add(new Item(parts[0],Integer.parseInt(parts[1]),parts[2],parts[3]));
            }

        }catch (Exception e)
        {
            Log.d("error", "readFromFile error: "  +e);
            e.printStackTrace();
        }
    }
}
