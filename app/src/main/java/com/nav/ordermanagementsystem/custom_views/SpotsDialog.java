package com.nav.ordermanagementsystem.custom_views;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import com.nav.ordermanagementsystem.R;


public class SpotsDialog extends AlertDialog {

    public static class Builder {

        private Context context;
        private String message;
        private int messageId;
        private int themeId;
        private boolean cancelable = true;
        private OnCancelListener cancelListener;

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(@StringRes int messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder setTheme(@StyleRes int themeId) {
            this.themeId = themeId;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setCancelListener(OnCancelListener cancelListener) {
            this.cancelListener = cancelListener;
            return this;
        }

        public AlertDialog build() {
            return new SpotsDialog(
                    context,
                    messageId != 0 ? context.getString(messageId) : message,
                    themeId != 0 ? themeId : android.R.style.Theme_Black_NoTitleBar_Fullscreen,
                    cancelable,
                    cancelListener
            );
        }
    }

    private static final int DELAY = 150;
    private static final int DURATION = 1500;

    private int size;
    private CharSequence message;

    private SpotsDialog(Context context, String message, int theme, boolean cancelable, OnCancelListener cancelListener) {
        super(context, theme);
        this.message = message;

        setCancelable(cancelable);
        if (cancelListener != null) setOnCancelListener(cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.custom_dialogue);
        setCanceledOnTouchOutside(false);

        initMessage();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void setMessage(CharSequence message) {
        this.message = message;
        if (isShowing()) initMessage();
    }


    private void initMessage() {
        if (message != null && message.length() > 0) {
            ((TextView) findViewById(R.id.spots_title)).setText(message);
        }
    }


}
