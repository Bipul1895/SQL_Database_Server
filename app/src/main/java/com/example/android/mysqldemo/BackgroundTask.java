package com.example.android.mysqldemo;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,Void,String> {

    AlertDialog alertDialog;
    Context ctx;

    BackgroundTask(Context context){
        ctx=context;
    }

    @Override
    protected String doInBackground(String... strings) {

        String reg_url="http://172.20.4.156/webapp/register.php";
        String login_url="http://172.20.4.156/webapp/login.php";

        String method=strings[0];
        Log.d("BackgroundTask : ", "doInbackgorund Invoked !");
        if(method.equals("register")){
            Log.d("BackgroundTask : ", "Register block Invoked !");
            String name=strings[1];
            String user_name=strings[2];
            String user_pass=strings[3];

            try {
                URL url = new URL(reg_url);

                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream=httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data= URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") +"&"+
                            URLEncoder.encode("user_name", "UTF-8") + "=" +URLEncoder.encode(user_name, "UTF-8") +"&"+
                            URLEncoder.encode("user_pass", "UTF-8") + "=" +URLEncoder.encode(user_pass, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                inputStream.close();

                return "registration successful";


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(method.equals("login")){
            Log.d("BackgroundTask : ", "Inside login block!");
            String login_name,login_pass;
            login_name=strings[1];
            login_pass=strings[2];

            try {
                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoInput(true);

                OutputStream os=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                String data=URLEncoder.encode("login_name", "UTF-8") +"="+URLEncoder.encode(login_name, "UTF-8")+"&"+
                            URLEncoder.encode("login_pass", "UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.close();
                os.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response="";
                String line="";
                line=bufferedReader.readLine();
                while (line!=null){
                    response+=line;
                    line=bufferedReader.readLine();
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        return "Registration successful";
        //return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog=new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information .... ");
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("registration successful")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else{
            //Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
