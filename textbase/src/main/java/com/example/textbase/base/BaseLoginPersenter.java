package com.example.textbase.base;

public abstract class BaseLoginPersenter<v extends IView> {
    protected v view;
    public BaseLoginPersenter(v view) {
        this.view = view;
        initModel();
    }

    protected abstract void initModel();
     void onDestroy() {
        view = null;
    }
}
