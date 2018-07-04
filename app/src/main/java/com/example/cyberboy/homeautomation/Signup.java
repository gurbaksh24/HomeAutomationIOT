/*
    Developed by Gurbaksh Singh Gabbi
 */


package com.example.cyberboy.homeautomation;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b,b1;
    TextView t1,t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/raleway.heavy.ttf");
        t1=(TextView)findViewById(R.id.textView2);
        t2=(TextView)findViewById(R.id.textView5);
        t3=(TextView)findViewById(R.id.textView6);
        e1=(EditText)findViewById(R.id.editText3);
        e2=(EditText)findViewById(R.id.editText4);
        e3=(EditText)findViewById(R.id.editText5);
        b=(Button)findViewById(R.id.button3);
        b1=(Button)findViewById(R.id.button4);
        t1.setTypeface(typeface);
        t2.setTypeface(typeface);
        t3.setTypeface(typeface);
        e1.setTypeface(typeface);
        e2.setTypeface(typeface);
        e3.setTypeface(typeface);
        b.setTypeface(typeface);
        b1.setTypeface(typeface);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyData d=new MyData();
                String s=d.saveUser(e1.getText().toString(),e2.getText().toString(),e3.getText().toString());
                Toast.makeText(Signup.this,s,Toast.LENGTH_LONG).show();
                Intent in;
                if(s.equals("Data Saved")) {
                    in = new Intent(Signup.this, MainActivity.class);
                    startActivity(in);
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(Signup.this,MainActivity.class);
                startActivity(in);
            }
        });
    }
}
