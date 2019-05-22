package com.nav.ordermanagementsystem.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.nav.ordermanagementsystem.R;
import com.nav.ordermanagementsystem.custom_views.SpotsDialog;
import com.nav.ordermanagementsystem.ui.table_view.MainActivity;
import com.nav.ordermanagementsystem.util.Preferences;


public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {
    private EditText editUser;
    private EditText editPass;
    private Button btnLogin;
    private Button btnCancel;
    private ILoginPresenter loginPresenter;
    private android.app.AlertDialog mSpotsDialog;
    private TextView textError;
    private SharedPreferences preferences;
    private CheckBox chk_remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //find view
        editUser = findViewById(R.id.et_username);
        editPass = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnCancel = findViewById(R.id.btn_cancel);
        textError = findViewById(R.id.text_error);
        chk_remember = findViewById(R.id.chkbox_remember);
        preferences = Preferences.getSharedPreferences(getApplicationContext());

        String userName = preferences.getString(Preferences.USER_NAME, null);
        String password = preferences.getString(Preferences.PASSWORD, null);
        if (userName != null && password != null) {
            editUser.setText(userName);
            editPass.setText(password);
        }
        //set listener
        btnLogin.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        //init
        loginPresenter = new LoginPresenterCompl(this);
        mSpotsDialog = new SpotsDialog.Builder().setContext(this).build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                finish();
                break;
            case R.id.btn_login:
                textError.setVisibility(View.GONE);
                loginPresenter.fieldValidation(editUser.getText().toString(), editPass.getText().toString());
                break;
        }
    }

    @Override
    public void onClearText() {
        editUser.setText("");
        editPass.setText("");
        textError.setVisibility(View.GONE);
    }

    @Override
    public void onLoginResult(Boolean result, int code) {
        btnLogin.setEnabled(true);
        mSpotsDialog.dismiss();
        if (chk_remember.isChecked()) {
            SharedPreferences.Editor editor = Preferences.getShardPreferenceEditor(getApplicationContext());
            editor.putString(Preferences.USER_NAME, editUser.getText().toString());
            editor.putString(Preferences.PASSWORD, editUser.getText().toString());
            editor.apply();
        }
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        if (visibility == View.VISIBLE) {
            mSpotsDialog.setMessage("Please Wait");
            mSpotsDialog.show();
        } else {

            mSpotsDialog.dismiss();
        }
    }

    @Override
    public void invalidFields() {

        textError.setVisibility(View.VISIBLE);
    }

    @Override
    public void validationSuccess() {
        loginPresenter.setProgressBarVisibility();
        loginPresenter.doLogin(editUser.getText().toString(), editPass.getText().toString());
    }
}