package com.nav.ordermanagementsystem.ui.table_view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;

import com.nav.ordermanagementsystem.R;


public class ModifyDialogue {
    public static ModifyDialogue mDialog;
    public DialogueInterface mDialogClickInterface;
    private Context mContext;
    private MainActivityView view;
    private ListModel listModel;
    private int index;


    public static ModifyDialogue getInstance() {

        if (mDialog == null)
            mDialog = new ModifyDialogue();
        return mDialog;

    }
    public void init(Context context, MainActivityView view, ListModel listModel, int index) {

        mContext = context;
        this.view = view;
        this.listModel = listModel;
        this.index = index;
    }
    /**
     * Show confirmation dialog with two buttons
     *
     */
    public void showConfirmDialog() {
        mDialogClickInterface = (DialogueInterface) mContext;
       final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogue_modify);
        Button buttonCancel =  dialog.findViewById(R.id.btn_cancel);
        Button buttonOK =  dialog.findViewById(R.id.btn_ok);
        final RadioGroup radioGroupLanguage =  dialog.findViewById(R.id.radio_group);




        dialog.setCancelable(true);
        dialog.show();      // if decline button is clicked, close the custom dialog
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                mDialogClickInterface.onLanguageSelected(dialog);
            }
        });
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                int selectedId = radioGroupLanguage.getCheckedRadioButtonId();

                switch (selectedId) {

                    case R.id.rb_arabic:
                       view.editItem(listModel,index);
                        break;
                    case R.id.rb_english:
                      view.deleteItem(listModel);
                        break;
                }
                mDialogClickInterface.onLanguageSelected(dialog);
            }
        });

    }



}


