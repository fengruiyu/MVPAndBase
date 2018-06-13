package com.example.textbase.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.textbase.R;
import com.example.textbase.mvp.home.presenter.LoginPersenter;

public abstract class BaseLoginActivty<p extends BaseLoginPersenter> extends AppCompatActivity{
    protected p persenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutId());
        initview();
         persenter = providePresenter();
        initsupervise();
        initData();
    }

    protected abstract p providePresenter();

    protected abstract int provideLayoutId();

    protected  void initData(){};

    protected  void initsupervise(){};

    protected  void initview(){};



    @Override
    protected void onDestroy() {
        persenter.onDestroy();
        super.onDestroy();
    }
}
