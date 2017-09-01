package com.jiangxin.packing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePage extends AppCompatActivity {

    Button start;
    Button extension;
    Button my_account;
    String start_time;
    String end_time;
    String my_balance;
    String my_code;


    TextView textView_start;
    TextView textView_end;
    TextView textView_balance;
    TextView textView_code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        start = (Button) findViewById(R.id.start);
        extension = (Button) findViewById(R.id.addtime);
        my_account= (Button) findViewById(R.id.my_account);
        textView_start = (TextView) findViewById(R.id.start_time);
        textView_end = (TextView) findViewById(R.id.end_time);
        textView_balance= (TextView) findViewById(R.id.current_balance_home);
        textView_code = (TextView) findViewById(R.id.place);

        SharedPreferences sharedata = getSharedPreferences("data", 0);
        start_time = sharedata.getString("start",null);
        end_time=sharedata.getString("end",null);
        my_balance=sharedata.getString("balance",null);
        my_code=sharedata.getString("code",null);
        textView_start.setText(start_time);
        textView_end.setText(end_time);
        textView_balance.setText(my_balance);
        textView_code.setText(my_code);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,Packing.class));
            }
        });


        extension.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
                try {
                    Date start = df1.parse(start_time);
                    Date end = df1.parse(end_time);
                    long start_time_long = start.getTime();
                    long end_time_long = end.getTime();

                    if((end_time_long-start_time_long)<70 * 60 * 1000){
                        end_time_long = end_time_long + 60 * 60 * 1000;
                        end = new Date(end_time_long);
                        end_time = df1.format(end);
                        textView_end.setText(end_time);

                        Toast.makeText(getApplicationContext(),"extension accepted",Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(getApplicationContext(),"The maximum packing hour is 2",Toast.LENGTH_SHORT).show();
                    }


                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        my_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this,Account.class));
            }
        });


    }
}
