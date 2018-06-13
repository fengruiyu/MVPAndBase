package com.example.dell.mvp.mvc.home.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.dell.mvp.mvc.home.model.bean.LoginBean;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginModel {
    private final static int A=0;
    private final  static int E=1;
    private static final String TAG = "LoginModel";
    private String url="https://www.apiopen.top/login?key=00d91e8e0cca2b76f515926a36db68f5&phone=13594347817&passwd=123456";
    public void login(final String account, final String password, final ILoginModelConnector iLoginModelConnector) {
        Log.d(TAG,"用户名“1111111111111111111111111111"+account+password);
        new Thread(){
            @Override
            public void run() {
                try {
                    URL u = new URL(url);//+"?mobile="+account+"&password="+password)
                    HttpURLConnection urlConnection = (HttpURLConnection) u.openConnection();
                    urlConnection.setConnectTimeout(5000);
                    if (urlConnection.getResponseCode()==200){
                        InputStream inputStream = urlConnection.getInputStream();
                        String s = streamTostring(inputStream);
                        Gson gson =  new Gson();
                        LoginBean loginBean = gson.fromJson(s,LoginBean.class);
                        int code = loginBean.getCode();
                        String msg= loginBean.getMsg();
                        if (code==200){
                            Log.d(TAG,"状态：成功");
                            if (iLoginModelConnector!=null){
                                Log.d(TAG,"状态：成功");
                                iLoginModelConnector.onLoginSucceed(msg);
                            }
                        }else {
                            if (iLoginModelConnector!=null){
                                Log.d(TAG,"状态：失败");
                                iLoginModelConnector.onLoginError(msg);
                            }
                        }
                    }
                } catch (Exception e) {
                  //  Log.d(TAG,"报错");
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
    public interface ILoginModelConnector{
        void onLoginSucceed(String data);
        void onLoginError(String data);
    }
}
