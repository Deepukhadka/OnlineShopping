package com.example.onlineshopping;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.onlineshopping.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class DescriptionActivity extends AppCompatActivity {

    private CircleImageView cirImg;
    private TextView tvName, tvDescription, tvPrice;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        cirImg = findViewById(R.id.imgDescription);
        tvName = findViewById(R.id.tvNameDis);
        tvPrice = findViewById(R.id.tvPriceDis);
        tvDescription = findViewById(R.id.tvDescriptionDis);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){

            cirImg.setImageResource(bundle.getInt("image"));
            tvName.setText("Name : " + bundle.getString("name"));
            tvPrice.setText("Price : " + bundle.getInt("price"));
            tvDescription.setText("Description : " +bundle.getString("description"));

        }
    }
}
