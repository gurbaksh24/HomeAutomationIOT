/*
    Developed by Gurbaksh Singh Gabbi
 */


package com.example.cyberboy.homeautomation;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;
//import java.util.jar.Manifest;

public class Welcome extends AppCompatActivity {
    ImageButton b1,b2,b3,b4,b5;
    TextView tv,t1,t2;
    String address = "00:21:13:04:B6:37";
    //String address="00:14:01:21:29:05";
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    int c=0,d=2,e=4,f=6,g=8;
    int y=0,z=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/raleway.heavy.ttf");
        SharedPreferences sharedPreferences=getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String ss=sharedPreferences.getString("uid","default");
        t1=(TextView)findViewById(R.id.textView3);
        t2=(TextView)findViewById(R.id.textView7);
        t1.setTypeface(typeface);
        t2.setTypeface(typeface);
        tv=(TextView)findViewById(R.id.textView7);
        tv.setTypeface(typeface);
        tv.setText("Welcome "+ss.toString());
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.BLUETOOTH,Manifest.permission.BLUETOOTH_ADMIN,Manifest.permission.INTERNET},1);
        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        if(myBluetooth == null)
        {
            //Show a mensag. that the device has no bluetooth adapter
            Toast.makeText(getApplicationContext(), "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();
            //finish apk
            finish();
        }
        else if(!myBluetooth.isEnabled())
        {
            //Ask to the user turn the bluetooth on
            Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnBTon,1);
        }
        new ConnectBT().execute();

        b1=(ImageButton)findViewById(R.id.imageButton);
        b2=(ImageButton)findViewById(R.id.imageButton1);
        b3=(ImageButton)findViewById(R.id.imageButton2);
        b4=(ImageButton)findViewById(R.id.imageButton3);
        b5=(ImageButton)findViewById(R.id.imageButton4);


        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {final Dialog d=new Dialog(Welcome.this,R.style.Theme_AppCompat_Light_Dialog_MinWidth);
                        d.setContentView(R.layout.dialog_intensity_fan);
                        d.setTitle("Control Intensity");
                        SeekBar s=(SeekBar)d.findViewById(R.id.seekBar);
                        Button button=(Button)d.findViewById(R.id.button5);
                        final ToggleButton tb=(ToggleButton)d.findViewById(R.id.toggleButton);
                        d.show();
                        if(z>0)
                            tb.setText("OFF");
                        else
                            tb.setText("ON");

                        tb.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(z>0)
                                            tb.setText("OFF");
                                        else
                                            tb.setText("ON");
                                        toggleBtn1(String.valueOf(c));
                                        toggleBtn1(String.valueOf(z));
                                        toggleBtn1("c");
                                        z^=255;
                                    }
                                }
                        );
                        s.setOnSeekBarChangeListener(
                                new SeekBar.OnSeekBarChangeListener() {
                                    @Override
                                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                        toggleBtn1(String.valueOf(c));
                                        toggleBtn1(String.valueOf(progress));
                                        toggleBtn1("c");
                                    }

                                    @Override
                                    public void onStartTrackingTouch(SeekBar seekBar) {

                                    }

                                    @Override
                                    public void onStopTrackingTouch(SeekBar seekBar) {

                                    }
                                }
                        );

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                d.dismiss();
                            }
                        });
                    }
                });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog di=new Dialog(Welcome.this,R.style.Theme_AppCompat_Light_Dialog_MinWidth);
                di.setContentView(R.layout.dialog_intensity_fan);
                di.setTitle("Control Intensity");
                TextView t=(TextView)di.findViewById(R.id.textView4);
                t.setVisibility(View.INVISIBLE);
                SeekBar s=(SeekBar)di.findViewById(R.id.seekBar);
                s.setVisibility(View.INVISIBLE);
                Button button=(Button)di.findViewById(R.id.button5);
                final ToggleButton tb=(ToggleButton)di.findViewById(R.id.toggleButton);
                di.show();
                if(d==3)
                    tb.setText("OFF");
                else
                    tb.setText("ON");

                tb.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(d==3)
                                    tb.setText("OFF");
                                else
                                    tb.setText("ON");
                                toggleBtn1(String.valueOf(d));
                                d^=1;
                            }
                        }
                );

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        di.dismiss();
                    }
                });
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog d=new Dialog(Welcome.this,R.style.Theme_AppCompat_Light_Dialog_MinWidth);
                d.setContentView(R.layout.dialog_intensity_fan);
                d.setTitle("Control Intensity");
                SeekBar s=(SeekBar)d.findViewById(R.id.seekBar);
                Button button=(Button)d.findViewById(R.id.button5);
                final ToggleButton tb=(ToggleButton)d.findViewById(R.id.toggleButton);
                d.show();
                if(z>0)
                    tb.setText("OFF");
                else
                    tb.setText("ON");
                tb.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(z>0)
                                    tb.setText("OFF");
                                else
                                    tb.setText("ON");
                                toggleBtn1(String.valueOf(e));
                                toggleBtn1(String.valueOf(y));
                                toggleBtn1("c");
                                y^=255;
                            }
                        }
                );
                s.setOnSeekBarChangeListener(
                        new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                toggleBtn1(String.valueOf(e));
                                toggleBtn1(String.valueOf(progress));
                                toggleBtn1("c");
                                Log.i("Seek:", String.valueOf(progress));
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }
                        }
                );

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        d.dismiss();
                    }
                });
                //e^=1;
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog di=new Dialog(Welcome.this,R.style.Theme_AppCompat_Light_Dialog_MinWidth);
                di.setContentView(R.layout.dialog_intensity_fan);
                di.setTitle("Control Intensity");
                TextView t=(TextView)di.findViewById(R.id.textView4);
                t.setVisibility(View.INVISIBLE);
                SeekBar s=(SeekBar)di.findViewById(R.id.seekBar);
                s.setVisibility(View.INVISIBLE);
                Button button=(Button)di.findViewById(R.id.button5);
                final ToggleButton tb=(ToggleButton)di.findViewById(R.id.toggleButton);
                di.show();
                if(f==7)
                    tb.setText("OFF");
                else
                    tb.setText("ON");

                tb.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(f==7)
                                    tb.setText("OFF");
                                else
                                    tb.setText("ON");
                                toggleBtn1(String.valueOf(f));
                                f^=1;
                            }
                        }
                );

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        di.dismiss();
                    }
                });

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog di=new Dialog(Welcome.this,R.style.Theme_AppCompat_Light_Dialog_MinWidth);
                di.setContentView(R.layout.dialog_intensity_fan);
                di.setTitle("Control Intensity");
                TextView t=(TextView)di.findViewById(R.id.textView4);
                SeekBar s=(SeekBar)di.findViewById(R.id.seekBar);
                s.setVisibility(View.INVISIBLE);
                Button button=(Button)di.findViewById(R.id.button5);
                final ToggleButton tb=(ToggleButton)di.findViewById(R.id.toggleButton);
                di.show();
                if(g==9)
                    tb.setText("OFF");
                else
                    tb.setText("ON");

                tb.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(g==9)
                                    tb.setText("OFF");
                                else
                                    tb.setText("ON");
                                toggleBtn1(String.valueOf(g));
                                g^=1;
                            }
                        }
                );

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        di.dismiss();
                    }
                });

            }
        });
    }

    public void toggleBtn1(String x)
    {
        if (btSocket!=null)
        {
            //fieldvalue=String.valueOf(x);
            //Log.i("fieldvalue",fieldvalue);
            try
            {
              //  MyData d=new MyData();
                //d.updateThingSpeak(1,fieldvalue);
                btSocket.getOutputStream().write((""+x).toString().getBytes());
            }
            catch (IOException e)
            {
                Toast.makeText(this,""+e, Toast.LENGTH_SHORT).show();
            }
        }
    }
/*
    public void toggleBtn2(int x)
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write((""+x).toString().getBytes());
            }
            catch (IOException e)
            {
                Toast.makeText(this,""+e, Toast.LENGTH_SHORT).show();
            }
        }
    }
*/
    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(Welcome.this, "Connecting...", "Please wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                Toast.makeText(Welcome.this,"Connection Failed. Check the device address or add a new device",Toast.LENGTH_LONG).show();
                //finish();
            }
            else
            {
                Toast.makeText(Welcome.this,"Connected.",Toast.LENGTH_LONG).show();
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=this.getMenuInflater();
        menuInflater.inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.m1)
        {
            SharedPreferences sharedPreferences=getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor sp=sharedPreferences.edit();
            sp.putString("uid","default");
            sp.commit();
            Intent intent=new Intent(Welcome.this,MainActivity.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.m2)
        {
            final Dialog d=new Dialog(Welcome.this,R.style.Theme_AppCompat_Light_Dialog_MinWidth);
            d.setContentView(R.layout.dialog_add_device);
            d.setTitle("Add Device");
            final EditText e=(EditText)d.findViewById(R.id.editText6);
            Button b1=(Button)d.findViewById(R.id.button6);
            Button b2=(Button)d.findViewById(R.id.button7);
            d.show();
            b1.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            address=e.getText().toString();
                            new ConnectBT().execute();
                        }
                    }
            );
            b2.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            d.dismiss();
                        }
                    }
            );
        }

        return super.onOptionsItemSelected(item);
    }
}
