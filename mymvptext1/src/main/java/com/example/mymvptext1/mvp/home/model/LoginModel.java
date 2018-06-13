package com.example.mymvptext1.mvp.home.model;

import com.example.mymvptext1.mvp.home.model.bean.LoginBean;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class LoginModel {
    private  String urls="https://www.apiopen.top/login?key=00d91e8e0cca2b76f515926a36db68f5&phone=13594347817&passwd=123456";

    public void login(String account, String password, final ILoginModelCallback iLoginModelCallback) {
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL(urls);
                     HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                     urlConnection.setReadTimeout(5000);
                     if (urlConnection.getResponseCode()==200){
                         InputStream inputStream = urlConnection.getInputStream();
                         String s = streamTostring(inputStream);
                         Gson gson = new Gson();
                         LoginBean loginBean = gson.fromJson(s, LoginBean.class);
                         int code = loginBean.getCode();
                         String msg = loginBean.getMsg();
                         if (code==200){
                             iLoginModelCallback.onLoginSucceed(msg);
                         }else {
                             iLoginModelCallback.onLoginError(msg);
                         }
                     }
                } catch (Exception e) {
                    e.printStackTrace();
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
    public interface ILoginModelCallback{
        void onLoginSucceed(String data);
        void onLoginError(String data);
    }

}
