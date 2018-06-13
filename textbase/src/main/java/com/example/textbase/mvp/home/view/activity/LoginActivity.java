package com.example.textbase.mvp.home.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.textbase.R;
import com.example.textbase.base.BaseLoginActivty;
import com.example.textbase.base.BaseLoginPersenter;
import com.example.textbase.mvp.home.presenter.LoginPersenter;
import com.example.textbase.mvp.home.view.iview.LoginIView;

public class LoginActivity extends BaseLoginActivty<LoginPersenter> implements  LoginIView, View.OnClickListener{
    private static final String TAG = "LoginActivity";
    private EditText user;
    private EditText password;
    private Button button;

    @Override
    protected LoginPersenter providePresenter() {
        return new LoginPersenter(this);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initview() {
        super.initview();
        user = findViewById(R.id.login_user);
        password = findViewById(R.id.login_password);
        button =  findViewById(R.id.login_btn);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Log.d(TAG,"dfdsfsdfsdfsdfsdfsdf"+user.getText().toString()+"-----------------"+password.getText().toString());
        persenter.login(user.getText().toString(),password.getText().toString());
    }
    @Override
    public void onSucceed(final String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,data,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onError(final String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,data,Toast.LENGTH_LONG).show();
            }
        });
    }

}
