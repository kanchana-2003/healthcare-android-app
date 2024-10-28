package com.example.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

private String[][] doctor_details1 = {
        {"Doctor Name : Ajith", "Hospital Address : Bangalore", "Exp : 5yrs", "Mobile No : 9878865645", "Fees : 600"},
        {"Doctor Name : Sneha", "Hospital Address : Chennai", "Exp : 8yrs", "Mobile No : 9123456789", "Fees : 700"},
        {"Doctor Name : Ravi", "Hospital Address : Mumbai", "Exp : 10yrs", "Mobile No : 9876543210", "Fees : 800"},
        {"Doctor Name : Priya", "Hospital Address : Delhi", "Exp : 6yrs", "Mobile No : 9234567890", "Fees : 650"},
        {"Doctor Name : Arjun", "Hospital Address : Hyderabad", "Exp : 12yrs", "Mobile No : 9988776655", "Fees : 900"}
};

    // General Physicians
    private String[][] doctor_details2 = {
            {"Doctor Name : Neha", "Hospital Address : Kochi", "Exp : 3yrs", "Mobile No : 9012345678", "Fees : 500"},
            {"Doctor Name : Rajesh", "Hospital Address : Trivandrum", "Exp : 7yrs", "Mobile No : 9823456789", "Fees : 600"},
            {"Doctor Name : Simran", "Hospital Address : Madurai", "Exp : 6yrs", "Mobile No : 9123654789", "Fees : 550"},
            {"Doctor Name : Anil", "Hospital Address : Mangalore", "Exp : 8yrs", "Mobile No : 9345678901", "Fees : 620"},
            {"Doctor Name : Pallavi", "Hospital Address : Lucknow", "Exp : 5yrs", "Mobile No : 9234567892", "Fees : 530"}
    };

    // Pediatricians
    private String[][] doctor_details3 = {
            {"Doctor Name : Sneha", "Hospital Address : Patna", "Exp : 6yrs", "Mobile No : 9765432109", "Fees : 600"},
            {"Doctor Name : Gaurav", "Hospital Address : Bhubaneswar", "Exp : 4yrs", "Mobile No : 9076543211", "Fees : 550"},
            {"Doctor Name : Aditi", "Hospital Address : Indore", "Exp : 9yrs", "Mobile No : 9564738291", "Fees : 700"},
            {"Doctor Name : Rohit", "Hospital Address : Bhopal", "Exp : 8yrs", "Mobile No : 9087546732", "Fees : 680"},
            {"Doctor Name : Kavita", "Hospital Address : Nagpur", "Exp : 5yrs", "Mobile No : 9890234561", "Fees : 600"}
    };

    // Orthopedic Surgeons
    private String[][] doctor_details4 = {
            {"Doctor Name : Manish", "Hospital Address : Surat", "Exp : 11yrs", "Mobile No : 9123458765", "Fees : 900"},
            {"Doctor Name : Shalini", "Hospital Address : Agra", "Exp : 13yrs", "Mobile No : 9321874560", "Fees : 850"},
            {"Doctor Name : Vinod", "Hospital Address : Kanpur", "Exp : 10yrs", "Mobile No : 9876543212", "Fees : 750"},
            {"Doctor Name : Ramesh", "Hospital Address : Mysore", "Exp : 12yrs", "Mobile No : 9456123498", "Fees : 880"},
            {"Doctor Name : Vandana", "Hospital Address : Rajkot", "Exp : 8yrs", "Mobile No : 9234767890", "Fees : 820"}
    };

    // Dermatologists
    private String[][] doctor_details5 = {
            {"Doctor Name : Priyanka", "Hospital Address : Amritsar", "Exp : 6yrs", "Mobile No : 9782341290", "Fees : 700"},
            {"Doctor Name : Naveen", "Hospital Address : Udaipur", "Exp : 5yrs", "Mobile No : 9023145678", "Fees : 650"},
            {"Doctor Name : Radha", "Hospital Address : Ajmer", "Exp : 7yrs", "Mobile No : 9112345765", "Fees : 720"},
            {"Doctor Name : Pooja", "Hospital Address : Nashik", "Exp : 8yrs", "Mobile No : 9345123076", "Fees : 750"},
            {"Doctor Name : Ashok", "Hospital Address : Gwalior", "Exp : 9yrs", "Mobile No : 9234567898", "Fees : 770"}
    };


    TextView tv;
    Button btn;
    String[][] doctor_details={};
    ArrayList List;
    SimpleAdapter sa;
    HashMap<String,String> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv=findViewById(R.id.textviewLDTitle);
        btn=findViewById(R.id.buttonLDBack);

        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);
        if(title.compareTo("Family physicians")==0)
            doctor_details=doctor_details1;
        else if(title.compareTo("Dietician")==0)
            doctor_details=doctor_details2;
        else if(title.compareTo("Dentist")==0)
            doctor_details=doctor_details3;
       else if(title.compareTo("Surgeon")==0)
            doctor_details=doctor_details4;
        else
            doctor_details=doctor_details5;


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));

            }
        });
        List=new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
            item=new HashMap<String, String>();
            item.put("Line1",doctor_details[i][0]);
            item.put("Line2",doctor_details[i][1]);
            item.put("Line3",doctor_details[i][2]);
            item.put("Line4",doctor_details[i][3]);
            item.put("Line5","Cons Fees"+doctor_details[i][4]+"/-");
            List.add(item);
        }
        sa=new SimpleAdapter(this,List,
                R.layout.multi_lines,
                new  String[]{"Line1","line2","Line3","Line4","Line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );
        ListView lst=findViewById(R.id.listViewCart);
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
            startActivity(it);
            }
        });
    }
}