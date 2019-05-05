package com.example.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshopping.R;

import java.io.PrintStream;

public class AddItemActivity extends AppCompatActivity {

    private EditText etName, etPrice,etDescription,etImage;
    private ImageView imgItem;
    private Button btnAddItem;
    private TextView tvDisplayMessage;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        etName = findViewById(R.id.etName);
        etPrice= findViewById(R.id.etPrice);
        etDescription = findViewById(R.id.etDescription);
        etImage= findViewById(R.id.etImage);
        tvDisplayMessage = findViewById(R.id.tvDisplayMessage);

        btnBack = findViewById(R.id.btnBack);

        btnAddItem = findViewById(R.id.btnAddItem);
        tvDisplayMessage.setText("");

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    save();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddItemActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    private void save(){
        try{
            PrintStream printStream = new PrintStream(openFileOutput("items.txt",MODE_PRIVATE|MODE_APPEND));
            printStream.println(etName.getText().toString() + "->" + etPrice.getText().toString() + "->" + etImage.getText().toString() + "->" + etDescription.getText().toString());
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            tvDisplayMessage.setText("Added Item");
            etImage.setText("");
            etName.setText("");
            etDescription.setText("");
            etPrice.setText("");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Boolean validate() {
        boolean validate = true;
        if (TextUtils.isEmpty(etName.getText().toString())) {
            etName.setError("Enter Name");
            etName.requestFocus();
            validate = false;
        }
        if (TextUtils.isEmpty(etPrice.getText().toString())) {
            etPrice.requestFocus();
            etName.setError("Enter Price");
            validate = false;
        }
        if (TextUtils.isEmpty(etImage.getText().toString())) {
            etImage.requestFocus();
            etImage.setError("Enter Image");
            validate = false;
        }
        if (TextUtils.isEmpty(etDescription.getText().toString())) {
            etDescription.requestFocus();
            etDescription.setError("Enter Price");
            validate = false;
        }

        return validate;
    }
}
