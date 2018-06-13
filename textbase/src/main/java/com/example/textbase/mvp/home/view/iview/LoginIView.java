package com.example.textbase.mvp.home.view.iview;

import com.example.textbase.base.IView;

public interface LoginIView extends IView {
    void onSucceed(String data);
    void onError(String data);
}
