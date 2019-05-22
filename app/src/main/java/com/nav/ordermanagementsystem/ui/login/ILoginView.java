package com.nav.ordermanagementsystem.ui.login;

public interface ILoginView {
    void onClearText();
    void onLoginResult(Boolean result, int code);
    void onSetProgressBarVisibility(int visibility);
    void invalidFields();
    void validationSuccess();
}