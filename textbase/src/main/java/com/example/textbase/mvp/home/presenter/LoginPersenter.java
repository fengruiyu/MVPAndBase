package com.example.textbase.mvp.home.presenter;

import android.util.Log;

import com.example.textbase.base.BaseLoginPersenter;
import com.example.textbase.base.IView;
import com.example.textbase.mvp.home.model.LoginModel;
import com.example.textbase.mvp.home.view.iview.LoginIView;

public class LoginPersenter extends BaseLoginPersenter<LoginIView>{
    private static final String TAG = "LoginPersenter";
    private LoginModel mloginModel;

    public LoginPersenter(LoginIView loginIView) {
        super(loginIView);
    }
    @Override
    protected void initModel() {
        mloginModel = new LoginModel();
    }
    public void login(String user, String password) {
        mloginModel.login(user, password, new LoginModel.ILoginModelPort() {
            @Override
            public void onSucceed(String data) {
                Log.d(TAG,"p 成功");
                view.onSucceed(data);
            }
            @Override
            public void onError(String data) {
                view.onError(data);
                Log.d(TAG,"p 失败");
            }
        });
    }
}
