package com.example.mymvptext1.mvp.home.presenter;

import com.example.mymvptext1.mvp.home.model.LoginModel;
import com.example.mymvptext1.mvp.home.view.iview.ILoginView;

public class LoginPresenter {

    private final LoginModel loginModel;
    ILoginView mloginView;

    public LoginPresenter(ILoginView loginView) {
        mloginView = loginView;
        loginModel = new LoginModel();
    }
    public void login(String account, String password) {
        loginModel.login(account, password, new LoginModel.ILoginModelCallback() {
            @Override
            public void onLoginSucceed(String data) {
                mloginView.onLoginSucceed(data);
            }
            @Override
            public void onLoginError(String data) {
                mloginView.onLoginError(data);
            }
        });
    }
}
