package com.example.android.mysqldemo;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,Void,String> {

    Context ctx;

    BackgroundTask(Context context){
        ctx=context;
    }

    @Override
    protected String doInBackground(String... strings) {

        String reg_url="http://172.20.4.139/webapp/register.php";
        String login_url="http://172.20.4.139/webapp/login.php";

        String method=strings[0];
        if(method.equals("register")){
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

                return "registraion successful";


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
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
