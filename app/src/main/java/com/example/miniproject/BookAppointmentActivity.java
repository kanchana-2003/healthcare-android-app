package com.example.miniproject;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
    private TextView edittextTitle;
    private EditText editTextFullName, editTextAddress, editTextContactNumber, editTextConsultantFees;
    private Button buttonAppDate, buttonAppTime, buttonBookAppointment, buttonAppBack;
    private TextView textViewSelectDate, textViewSelectTime;
private DatePickerDialog datePickerDialog;
private TimePickerDialog timePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        edittextTitle=findViewById(R.id.textviewAppTitle);
        editTextFullName = findViewById(R.id.editTextAppFullName);
        editTextAddress = findViewById(R.id.editTextAppAddress);
        editTextContactNumber = findViewById(R.id.editTextAppContactNumber);
        editTextConsultantFees = findViewById(R.id.editTextAppConsultantFees);
        buttonAppDate = findViewById(R.id.ButtoncartDate);
        buttonAppTime = findViewById(R.id.buttonCartTime);
        buttonBookAppointment = findViewById(R.id.buttonBookAppointment);
        buttonAppBack = findViewById(R.id.buttonAppBack);


        editTextFullName.setKeyListener(null);
        editTextAddress.setKeyListener(null);
        editTextContactNumber.setKeyListener(null);
        editTextConsultantFees.setKeyListener(null);

        Intent it=getIntent();
        String title=it.getStringExtra("text1");
        String fullname=it.getStringExtra("text2");
        String address=it.getStringExtra("text3");
        String contact=it.getStringExtra("text4");
        String fees=it.getStringExtra("text5");

        edittextTitle.setText(title);
        editTextFullName.setText(fullname);
        editTextAddress.setText(address);
        editTextContactNumber.setText(contact);
        editTextConsultantFees.setText(fees);

        initDatepicker();
        buttonAppDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        initTimePicker();
        buttonAppTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });


        buttonAppBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class));
            }
        });

        buttonBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private  void initDatepicker(){
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                buttonAppDate.setText(i2+"/"+i1+"/"+i);

            }
        };
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);

    }


    private  void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                buttonAppTime.setText(i+":"+i1);


            }
        };
        Calendar cal=Calendar.getInstance();
        int hrs=cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);

        int style=AlertDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,timeSetListener,hrs,mins,true);

    }
}