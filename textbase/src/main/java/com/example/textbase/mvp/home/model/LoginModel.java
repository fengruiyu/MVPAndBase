package com.example.textbase.mvp.home.model;

import android.util.Log;

import com.example.textbase.mvp.home.model.Bean.LoginBean;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginModel {
    private String url="https://www.zhaoapi.cn/user/login";
    private static final String TAG = "LoginModel";
    public void login(final String user, final String password, final ILoginModelPort iLoginModelPort) {
        new Thread(){
            @Override
            public void run() {
                try {
                    URL u = new URL(url+"?mobile="+user+"&password="+password);
                    HttpURLConnection urlConnection = (HttpURLConnection) u.openConnection();
                    urlConnection.setConnectTimeout(5000);
                    if (urlConnection.getResponseCode()==200){
                        InputStream inputStream = urlConnection.getInputStream();
                        String s = streamTostring(inputStream);
                        Log.d(TAG,s+"");
                        Gson gson = new Gson();
                        LoginBean loginBean = gson.fromJson(s, LoginBean.class);
                        String code = loginBean.getCode();
                        String msg = loginBean.getMsg();
                        if (code.equals("0")){
                            iLoginModelPort.onSucceed(msg);
                            Log.d(TAG,"成功");
                        }else {
                            iLoginModelPort.onError(msg);
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
    public interface ILoginModelPort{
        void onSucceed(String data);
        void onError(String data);
    }

}
