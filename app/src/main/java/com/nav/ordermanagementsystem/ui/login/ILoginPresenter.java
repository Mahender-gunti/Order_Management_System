package com.nav.ordermanagementsystem.ui.login;

public interface ILoginPresenter {
    void clear();
    void doLogin(String name, String passwd);
    void setProgressBarVisibility();
    void fieldValidation(String s, String s1);
}