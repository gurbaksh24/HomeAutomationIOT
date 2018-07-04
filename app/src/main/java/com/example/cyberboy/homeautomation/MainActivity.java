/*
    Developed by Gurbaksh Singh Gabbi
 */




package com.example.cyberboy.homeautomation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2;
    Button b1,b2;
    TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/raleway.heavy.ttf");

        final SharedPreferences sharedPreferences=getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String ss=sharedPreferences.getString("uid","default");
        if(!ss.equals("default"))
        {
            Intent in=new Intent(MainActivity.this,Welcome.class);
            startActivity(in);
        }
        t1=(TextView)findViewById(R.id.tv1);
        t2=(TextView)findViewById(R.id.tv2);
        t1.setTypeface(typeface);
        t2.setTypeface(typeface);
        e1=(EditText)findViewById(R.id.editText);
        e1.setTypeface(typeface);
        e2=(EditText)findViewById(R.id.editText2);
        e2.setTypeface(typeface);
        b1=(Button)findViewById(R.id.button);
        b1.setTypeface(typeface);
        b2=(Button)findViewById(R.id.button2);
        b2.setTypeface(typeface);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyData d=new MyData();
                        String s=d.loginUser(e1.getText().toString(),e2.getText().toString());
                        if(s.equals("valid")) {
                            SharedPreferences.Editor sp = sharedPreferences.edit();
                            sp.putString("uid", e1.getText().toString());
                            sp.commit();
                            Intent in=new Intent(MainActivity.this,Welcome.class);
                            startActivity(in);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        b2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in=new Intent(MainActivity.this,Signup.class);
                        startActivity(in);
                    }
                }
        );
    }
}
