package com.example.mymvptext1.mvp.home.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mymvptext1.R;
import com.example.mymvptext1.mvp.home.presenter.LoginPresenter;
import com.example.mymvptext1.mvp.home.view.iview.ILoginView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {

    private LoginPresenter mloginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.login).setOnClickListener(this);
        mloginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View v) {
        mloginPresenter.login("1","2");
    }

    @Override
    public void onLoginSucceed(final String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,data,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onLoginError(final String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,data,Toast.LENGTH_LONG).show();
            }
        });
    }
}
