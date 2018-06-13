package com.example.rikao6.mvp.home.presenter;

import android.util.Log;

import com.example.rikao6.mvp.home.model.LoginModel;
import com.example.rikao6.mvp.home.view.iview.IView;

public class LoginPersenter {
    private static final String TAG = "LoginPersenter";
    private final LoginModel mloginModel;
    IView iView;
    public LoginPersenter(IView miView) {
        iView = miView;
        mloginModel = new LoginModel();
    }

    public void login(String number) {
        if (number =="" || number.equals("")){
            Log.d(TAG,"手机不能为空");
            iView.onError("手机号码不能为空");
            return;
        }else {
            mloginModel.login(number, new LoginModel.ILoginModelJoin() {
                @Override
                public void onSucceed(String data) {
                    iView.onSucceed(data);
                }

                @Override
                public void onError(String data) {
                    iView.onError(data);
                }
            });
        }
    }
}
