package com.example.dell.mvp.mvc.home.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.dell.mvp.R;
import com.example.dell.mvp.mvc.home.Presenter.LoginPresenter;
import com.example.dell.mvp.mvc.home.view.iview.LoginIview;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoginIview {
    private static final String TAG = "MainActivity";
    private LoginPresenter mloginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.dell.mvp.R.layout.activity_main);
        findViewById(R.id.login).setOnClickListener(this);
        mloginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View v) {
      mloginPresenter.login("15933101810","1234567");
    }

    @Override
    public void onLoginSucceed(final String data) {
        Log.d(TAG,"1232133213123");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,data+"------------------------",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onLoginError(final String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,data+"------------------------",Toast.LENGTH_LONG).show();
            }
        });
    }
}
