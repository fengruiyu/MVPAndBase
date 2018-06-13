package com.example.rikao6.mvp.home.model;

import android.util.Log;

import com.example.rikao6.mvp.home.model.bean.LoginBean;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginModel {
    private static final String TAG = "LoginModel";
    private String url="https://www.apiopen.top/login?key=00d91e8e0cca2b76f515926a36db68f5&phone=13594347817&passwd=123456";
    public void login(String number, final ILoginModelJoin iLoginModelJoin) {
        new Thread(){
            @Override
            public void run() {
                try {
                    URL u = new URL(url);
                    HttpURLConnection urlConnection = (HttpURLConnection) u.openConnection();
                    urlConnection.setConnectTimeout(5000);
                    if (urlConnection.getResponseCode()==200){
                        InputStream inputStream = urlConnection.getInputStream();
                        String s = streamTostring(inputStream);
                        Log.d(TAG,s+"");
                        Gson gson = new Gson();
                        LoginBean loginBean = gson.fromJson(s, LoginBean.class);
                        int code = loginBean.getCode();
                        String msg = loginBean.getMsg();
                        if (code==200){
                            iLoginModelJoin.onSucceed(msg);
                            Log.d(TAG,"成功");
                        }else {
                            iLoginModelJoin.onError(msg);
                            Log.d(TAG,"失败");
                        }


                    }
                } catch (Exception e) {
                    Log.d(TAG,"报错");
                }
            }
        }.start();

    }
    private String streamTostring(InputStream inputStream){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((str=bufferedReader.readLine())!=null){
                stringBuilder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  stringBuilder.toString();
    }
    public interface ILoginModelJoin{
        void onSucceed(String data);
        void onError (String data);
    }

}
