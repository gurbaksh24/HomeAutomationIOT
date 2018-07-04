/*
    Developed by Gurbaksh Singh Gabbi
 */


package com.example.cyberboy.homeautomation;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.UnsupportedEncodingException;
        import java.net.URL;
        import java.net.URLConnection;
        import java.net.URLEncoder;

        import android.os.StrictMode;
        import android.util.Log;

public class MyData {

    public String saveUser(String name, String uname, String pass) {
        final String SERVER_URL = "http://10.10.10.10:8080/IOT/signup.jsp"; // The server(Servlet) which will process the request. Note 10.0.2.2 is the localhost IP for emulator
        final String CHAR_SET = "UTF-8"; // Encoding used for the parameters
        OutputStream output = null;
        String response = "";

        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Log.i("HTTP :", "Preparing data");
            // ------------------------------------------------------START: PREPARE CONNETION AND REQUEST ------------------------------- //

            // Prepare the data string  [ firstName=JOHN&lastName=SMITH ]
            String data = URLEncoder.encode("name", CHAR_SET) + "=" + URLEncoder.encode(name, CHAR_SET);
            data += "&" + URLEncoder.encode("uname", CHAR_SET) + "=" + URLEncoder.encode(uname, CHAR_SET);
            data += "&" + URLEncoder.encode("pass", CHAR_SET) + "=" + URLEncoder.encode(pass, CHAR_SET);


            URLConnection connection = new URL(SERVER_URL).openConnection(); // Create a connection to server using URL

            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);

            output = null;
            output = connection.getOutputStream(); //open a Output stream from the connection for posting data
            output.write(data.getBytes(CHAR_SET)); //Post data
            output.flush();
            output.close();
            //------------------------------------------------------ END: PREPARE CONNETION AND REQUEST --------------------------------- //

            Log.i("HTTP :", "sending data");
            InputStream responseStream = connection.getInputStream(); //This is when the request is actually fired


            //------------------------------------------------------ START: READ RESPONSE ------------------------------------------------ //

            Log.i("HTTP :", "Reading response");
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream, CHAR_SET)); // Connect a BufferReader to the inputStream
            String line = null;

            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }

            //------------------------------------------------------ END: READ RESPONSE ------------------------------------------------- //
            Log.i("response :", response);


        } catch (UnsupportedEncodingException e) {
            response = response + e.getMessage();
            Log.i("Error1 :", e.getMessage());
            e.printStackTrace();

        } catch (IOException io) {
            response = response + io.getMessage();
            //Log and check exp
            Log.i("Error2 :", io.getMessage());
        } finally {
            if (output != null) try {
                output.close();
            } catch (IOException ignoreIO) {
                Log.i("Error3 :", ignoreIO.getMessage());
            }
        }


        return response;

    }


    public String loginUser(String uid, String pass) {
        final String SERVER_URL = "http://10.10.10.10:8080/IOT/login.jsp"; // The server(Servlet) which will process the request. Note 10.0.2.2 is the localhost IP for emulator
        final String CHAR_SET = "UTF-8"; // Encoding used for the parameters
        OutputStream output = null;
        String response = "";

        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Log.i("HTTP :", "Preparing data");
            // ------------------------------------------------------START: PREPARE CONNETION AND REQUEST ------------------------------- //

            // Prepare the data string  [ firstName=JOHN&lastName=SMITH ]
            String data = URLEncoder.encode("uid", CHAR_SET) + "=" + URLEncoder.encode(uid, CHAR_SET);
            data += "&" + URLEncoder.encode("pass", CHAR_SET) + "=" + URLEncoder.encode(pass, CHAR_SET);


            URLConnection connection = new URL(SERVER_URL).openConnection(); // Create a connection to server using URL

            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);

            output = null;
            output = connection.getOutputStream(); //open a Output stream from the connection for posting data
            output.write(data.getBytes(CHAR_SET)); //Post data
            output.flush();
            output.close();
            //------------------------------------------------------ END: PREPARE CONNETION AND REQUEST --------------------------------- //

            Log.i("HTTP :", "sending data");
            InputStream responseStream = connection.getInputStream(); //This is when the request is actually fired


            //------------------------------------------------------ START: READ RESPONSE ------------------------------------------------ //

            Log.i("HTTP :", "Reading response");
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream, CHAR_SET)); // Connect a BufferReader to the inputStream
            String line = null;

            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }

            //------------------------------------------------------ END: READ RESPONSE ------------------------------------------------- //
            Log.i("response :", response);


        } catch (UnsupportedEncodingException e) {
            response = response + e.getMessage();
            Log.i("Error1 :", e.getMessage());
            e.printStackTrace();

        } catch (IOException io) {
            response = response + io.getMessage();
            //Log and check exp
            Log.i("Error2 :", io.getMessage());
        } finally {
            if (output != null) try {
                output.close();
            } catch (IOException ignoreIO) {
                Log.i("Error3 :", ignoreIO.getMessage());
            }
        }


        return response;

    }
    public String updateThingSpeak(int x,String y) {
        final String SERVER_URL = "https://api.thingspeak.com/update"; // The server(Servlet) which will process the request. Note 10.0.2.2 is the localhost IP for emulator
        final String CHAR_SET = "UTF-8"; // Encoding used for the parameters
        String api_key="AMQG6KBHYCI6EEQH";
        String fieldname="field"+x;
        OutputStream output = null;
        String response = "";

        try {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Log.i("HTTP :", "Preparing data");
            // ------------------------------------------------------START: PREPARE CONNETION AND REQUEST ------------------------------- //

            // Prepare the data string  [ firstName=JOHN&lastName=SMITH ]
            String data = URLEncoder.encode("api_key", CHAR_SET) + "=" + URLEncoder.encode(api_key, CHAR_SET);
            data += "&" + URLEncoder.encode(fieldname, CHAR_SET) + "=" + URLEncoder.encode(y, CHAR_SET);

            URLConnection connection = new URL(SERVER_URL).openConnection(); // Create a connection to server using URL

            connection.setDoOutput(true); // This means POST method to be used
            connection.setRequestProperty("Accept-Charset", CHAR_SET); //For servers to know what encoding is used for the parameters
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + CHAR_SET);

            output = null;
            output = connection.getOutputStream(); //open a Output stream from the connection for posting data
            output.write(data.getBytes(CHAR_SET)); //Post data
            output.flush();
            output.close();
            //------------------------------------------------------ END: PREPARE CONNETION AND REQUEST --------------------------------- //

            Log.i("HTTP :", "sending data");
            InputStream responseStream = connection.getInputStream(); //This is when the request is actually fired


            //------------------------------------------------------ START: READ RESPONSE ------------------------------------------------ //

            Log.i("HTTP :", "Reading response");
            BufferedReader rd = new BufferedReader(new InputStreamReader(responseStream, CHAR_SET)); // Connect a BufferReader to the inputStream
            String line = null;

            while ((line = rd.readLine()) != null) // Read the response line-by-line from the bufferedReader
            {
                response += line;
            }

            //------------------------------------------------------ END: READ RESPONSE ------------------------------------------------- //
            Log.i("response :", response);


        } catch (UnsupportedEncodingException e) {
            response = response + e.getMessage();
            Log.i("Error1 :", e.getMessage());
            e.printStackTrace();

        } catch (IOException io) {
            response = response + io.getMessage();
            //Log and check exp
            Log.i("Error2 :", io.getMessage());
        } finally {
            if (output != null) try {
                output.close();
            } catch (IOException ignoreIO) {
                Log.i("Error3 :", ignoreIO.getMessage());
            }
        }


        return response;

    }


}
