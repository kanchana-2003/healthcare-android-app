package com.example.miniproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LabTestDetailsActivity extends AppCompatActivity {

    private EditText editTextDetails;
    private TextView textViewTotalCost, textViewPackageName;
    private Button buttonBack, buttonAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        editTextDetails = findViewById(R.id.listViewCart);
        textViewTotalCost = findViewById(R.id.textviewCartTotalCost);
        textViewPackageName = findViewById(R.id.textviewLDTitle);
        buttonBack = findViewById(R.id.buttonLDBack);
        buttonAddToCart = findViewById(R.id.buttonLDGoToCart);

        // Make the details EditText non-editable
        editTextDetails.setKeyListener(null);

        // Retrieve data from the Intent and set it
        Intent intent = getIntent();
        if (intent != null) {
            String packageName = intent.getStringExtra("text1");
            String packageDetails = intent.getStringExtra("text2");
            String totalCost = intent.getStringExtra("text3");

            if (packageName != null) {
                textViewPackageName.setText(packageName);
            }
            if (packageDetails != null) {
                editTextDetails.setText(packageDetails);
            }
            if (totalCost != null) {
                textViewTotalCost.setText("Total cost: " + totalCost);
            }
        }

        // Back button functionality
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Close current activity and return to previous
            }
        });

        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product=textViewPackageName.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());
            Database db=new Database(getApplicationContext(),"healthcare",null,1);
            if(db.checkCart(username,product)==1){
               Toast.makeText(getApplicationContext(),"product already Added",Toast.LENGTH_SHORT).show();
            }
            else {
                db.addCart(username,product,price,"lab");
                Toast.makeText(getApplicationContext(),"Record inserted to cart",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));

            }
            }
        });
    }
}
