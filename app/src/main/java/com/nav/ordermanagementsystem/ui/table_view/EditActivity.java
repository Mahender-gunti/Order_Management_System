package com.nav.ordermanagementsystem.ui.table_view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.nav.ordermanagementsystem.R;
import com.nav.ordermanagementsystem.util.AppConstants;

public class EditActivity extends AppCompatActivity {

    private Button buttonEdit;
    private Button buttonCancel;

    private ListModel listModel;
    private int index = -1;
    private boolean isEdit = false;
    private EditText editTextOrderNumber;
    private EditText editTextOrderDate;
    private EditText editTexName;
    private EditText editTextAddress;
    private EditText editTextPhone;
    private EditText editTextTotal;
    private EditText editTextGPS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setupViews();
        initModel();
        setButtonCancelListener();
        setButtonEditListener();
    }

    private void setupViews(){
        editTextOrderNumber = findViewById(R.id.et_order_no);
        editTextOrderDate = findViewById(R.id.et_order_date);
        editTexName = findViewById(R.id.et_name);
        editTextAddress = findViewById(R.id.et_address);
        editTextPhone = findViewById(R.id.et_phone);
        editTextTotal = findViewById(R.id.et_total);
        editTextGPS = findViewById(R.id.et_gps);
        buttonEdit = findViewById(R.id.btn_edit);
        buttonCancel = findViewById(R.id.btn_cancel);
    }

    private void initModel() {
        Intent intent = getIntent();
        if(intent != null){
            isEdit = intent.getBooleanExtra(AppConstants.PERSON_INTENT_EDIT, false);
            if(isEdit){
                listModel = intent.getParcelableExtra(AppConstants.PERSON_INTENT_OBJECT);
                index = intent.getIntExtra(AppConstants.PERSON_INTENT_INDEX, -1);
                if(index == -1){
                    setResult(RESULT_CANCELED);
                    finish();
                }
                editTextOrderNumber.setText(listModel.getOrderNumber());
                editTextOrderDate.setText(listModel.getOrderDueDate());
                editTexName.setText(listModel.getCustomerBuyerName());
                editTextAddress.setText(listModel.getCustomerAddress());
                editTextPhone.setText(listModel.getCustomerPhone());
                editTextTotal.setText(listModel.getCustomerOrderTotal());
                editTextGPS.setText(listModel.getCustomerGPSAddress());

                buttonEdit.setText(getString(R.string.button_edit));
            }else{
                listModel = new ListModel();
                buttonEdit.setText(getString(R.string.button_add));
            }
        }
    }

    private void setButtonCancelListener(){
        buttonCancel.setOnClickListener(e -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }

    private void setButtonEditListener(){
        buttonEdit.setOnClickListener(e -> {

            String orderNumber = editTextOrderNumber.getText().toString().trim();
            String orderDueDate = editTextOrderDate.getText().toString().trim();
            String customerBuyerName = editTexName.getText().toString().trim();
            String customerAddress = editTextAddress.getText().toString().trim();
            String customerPhone = editTextPhone.getText().toString().trim();
            String customerOrderTotal = editTextTotal.getText().toString().trim();
            String customerGPSAddress = editTextGPS.getText().toString().trim();

            listModel.setOrderNumber(orderNumber);
            listModel.setOrderDueDate(orderDueDate);
            listModel.setCustomerBuyerName(customerBuyerName);
            listModel.setCustomerAddress(customerAddress);
            listModel.setCustomerPhone(customerPhone);
            listModel.setCustomerOrderTotal(customerOrderTotal);
            listModel.setCustomerGPSAddress(customerGPSAddress);

            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putParcelable(AppConstants.PERSON_INTENT_OBJECT, listModel);
            bundle.putBoolean(AppConstants.PERSON_INTENT_EDIT, isEdit);
            bundle.putInt(AppConstants.PERSON_INTENT_INDEX, index);
            intent.putExtras(bundle);

            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
