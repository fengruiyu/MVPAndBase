package com.example.rikao6.mvp.home.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rikao6.R;
import com.example.rikao6.mvp.home.presenter.LoginPersenter;
import com.example.rikao6.mvp.home.view.iview.IView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, IView {

    private EditText editText;
    private LoginPersenter mloginPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.login_et);
        findViewById(R.id.login_btn).setOnClickListener(this);
        mloginPersenter = new LoginPersenter(this);
    }

    @Override
    public void onClick(View v) {
        mloginPersenter.login(editText.getText().toString());
    }

    @Override
    public void onSucceed(final String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,data+"==",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onError(final String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,data+"==",Toast.LENGTH_LONG).show();
            }
        });
    }
}
