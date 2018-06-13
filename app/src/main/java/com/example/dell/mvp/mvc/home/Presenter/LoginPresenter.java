package com.example.dell.mvp.mvc.home.Presenter;

import android.util.Log;

import com.example.dell.mvp.mvc.home.model.LoginModel;
import com.example.dell.mvp.mvc.home.view.iview.LoginIview;

public class LoginPresenter {
    private static final String TAG = "LoginPresenter";
    private final LoginModel mloginModel;
    LoginIview mloginIview;
    public LoginPresenter(LoginIview loginIview) {
        mloginIview =loginIview;
        mloginModel = new LoginModel();
    }

    public void login(String account, String password) {
        Log.d(TAG,"用户名：“"+account+"==========================");
        mloginModel.login(account, password, new LoginModel.ILoginModelConnector() {
            @Override
            public void onLoginSucceed(String data) {

                if (mloginIview!=null){
                    Log.d(TAG,"用户名：“=222222222222222222222222222成功");
                    mloginIview.onLoginSucceed(data);
                }
            }

            @Override
            public void onLoginError(String data) {
                Log.d(TAG,"用户名：“=222222222222222222222222222失败");
                if (mloginIview!=null){
                    mloginIview.onLoginError(data);
                }
            }
        });
    }
}
