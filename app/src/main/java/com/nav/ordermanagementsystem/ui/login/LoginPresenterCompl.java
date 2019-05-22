package com.nav.ordermanagementsystem.ui.login;

import android.text.TextUtils;
import android.view.View;

public class LoginPresenterCompl implements ILoginPresenter {

    private ILoginView iLoginView;

    public LoginPresenterCompl(ILoginView iLoginView) {
            this.iLoginView = iLoginView;
        }

        @Override
        public void clear() {
            iLoginView.onClearText();
        }

        @Override
        public void doLogin(String name, String passwd) {
        iLoginView.onLoginResult(true,1);
        }

        @Override
        public void setProgressBarVisibility(){
            iLoginView.onSetProgressBarVisibility(View.VISIBLE);
        }

    @Override
    public void fieldValidation(String name, String pwd) {
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)){
            iLoginView.invalidFields();
        }else{
            iLoginView.validationSuccess();
        }

    }




}